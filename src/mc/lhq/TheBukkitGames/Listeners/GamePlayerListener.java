package mc.lhq.TheBukkitGames.Listeners;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import mc.lhq.TheBukkitGames.Kit;
import mc.lhq.TheBukkitGames.PlayerType;
import mc.lhq.TheBukkitGames.TheBukkitGames;
import mc.lhq.TheBukkitGames.Abilities.assassin;
import mc.lhq.TheBukkitGames.Command.GameCommandExecutor;
import mc.lhq.TheBukkitGames.LoadUtil.LoadFiles;
import mc.lhq.TheBukkitGames.LoadUtil.LoadMessages;
import mc.lhq.TheBukkitGames.Threads.StaminaFilter;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;

public class GamePlayerListener implements Listener {
	public static HashMap<String,List<Kit>> plkit = new HashMap<String,List<Kit>>();
	public static HashMap<String,PlayerType> pltype = new HashMap<String,PlayerType>();
	public static HashMap<String,Integer> points = new HashMap<String,Integer>();
	public static HashMap<String,String> lastDamagePlayer = new HashMap<String,String>();
	HashMap<Player,List<Player>> watchp = new HashMap<Player,List<Player>>();
	public static List<String> watchingplist = new ArrayList<String>();
	public static List<String> livingplist = new ArrayList<String>();
	public static List<Player> flyplist = new ArrayList<Player>();
	public static Location spawnloc;

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event){
		if(TheBukkitGames.serverstate==PlayerType.BEFORE){
			return;
		}
		event.setCancelled(true);
		Player p = event.getPlayer();
		if(pltype.get(p.getName())==PlayerType.WATCHING){
			LoadMessages.watchingbroadcast(LoadMessages.WatchingPrefix+p.getName()+LoadMessages.splistChatMsg+event.getMessage());
			return;
		}
			String msg = p.getName()+LoadMessages.splistChatMsg+event.getMessage();
			if(GameCommandExecutor.ptoggle.get(p)!=null){
				if(GameCommandExecutor.ptoggle.get(p)==true){
					LoadMessages.togglechatbroadcast(p,LoadMessages.togglechatPrefix+msg);
					return;
				}
			}else{
				GameCommandExecutor.ptoggle.put(p, Boolean.getBoolean("false"));
			}
			LoadMessages.chatbroadcast(msg);
	}
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		PlayerType ss = TheBukkitGames.serverstate;
		Player joinp = event.getPlayer();
		PlayerInventory i = joinp.getInventory();
		i.clear();
		i.setHelmet(null);
		i.setChestplate(null);
		i.setLeggings(null);
		i.setLeggings(null);
		i.setBoots(null);
		joinp.setGameMode(GameMode.SURVIVAL);
		joinp.setHealth(20);
		joinp.setFoodLevel(20);
		joinp.setExp(0);
		Object[] pe = joinp.getActivePotionEffects().toArray();
		int u = 0;
		while(u!=pe.length){
			joinp.removePotionEffect(((PotionEffect)pe[u]).getType());
			u++;
		}
		Random r = new Random();
		Location startFrom = getSpawn();
		Location loc = startFrom.clone();
		loc.add((r.nextBoolean() ? 1 : -1) * r.nextInt(6), 60, (r.nextBoolean() ? 1 : -1) * r.nextInt(6));
		loc.setY(TheBukkitGames.GamingWorld.getHighestBlockYAt(loc) + 1.5);
		TheBukkitGames.GamingWorld.loadChunk(loc.getChunk());
		joinp.teleport(loc);
		if(ss==PlayerType.BEFORE){
			pltype.put(joinp.getName(), PlayerType.BEFORE);
			points.put(joinp.getName(), 0);
			if(!livingplist.contains(joinp.getName())){livingplist.add(joinp.getName());}
		}else if(ss==PlayerType.WAITING){
			pltype.put(joinp.getName(), PlayerType.WAITING);
			points.put(joinp.getName(), LoadFiles.defPoints);
			setDefPoint(joinp);
			if(!livingplist.contains(joinp.getName())){livingplist.add(joinp.getName());}
		}else{
			event.setJoinMessage(null);
			setWatching(joinp);
		}
	}
	public Location getSpawn() {
		Location loc = TheBukkitGames.GamingWorld.getSpawnLocation();
		loc.setY(TheBukkitGames.GamingWorld.getHighestBlockYAt(TheBukkitGames.GamingWorld.getSpawnLocation()) + 1.5);
		return loc;
	}
	@EventHandler
	public void onPlayerLeft(PlayerQuitEvent event){
		Player p = event.getPlayer();
		if(watchingplist.contains(p.getName())){watchingplist.remove(p.getName());}
		if(livingplist.contains(p.getName())){livingplist.remove(p.getName());}
		plkit.put(p.getName(), null);
		pltype.put(p.getName(), null);
		points.put(p.getName(), null);
		lastDamagePlayer.put(p.getName(), null);
		watchp.put(p, null);
	}
	@EventHandler
	public void onPlayerKick(PlayerKickEvent event){
		Player p = event.getPlayer();
		if(watchingplist.contains(p.getName())){watchingplist.remove(p.getName());}
		if(livingplist.contains(p.getName())){livingplist.remove(p.getName());}
		plkit.put(p.getName(), null);
		pltype.put(p.getName(), null);
		points.put(p.getName(), null);
		lastDamagePlayer.put(p.getName(), null);
		watchp.put(p, null);
	}
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event){
		PlayerType ss = TheBukkitGames.serverstate;
		if(ss==PlayerType.BEFORE|ss==PlayerType.WAITING){
			event.setCancelled(true);
		}
		Player p = event.getPlayer();
		if(pltype.get(p.getName())==PlayerType.WATCHING){
			event.setCancelled(true);
		}
		if(event.getBlock().getType()==Material.DIAMOND_ORE){
			event.setCancelled(true);
		}
	}
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event){
		PlayerType ss = TheBukkitGames.serverstate;
		if(ss==PlayerType.BEFORE|ss==PlayerType.WAITING){
			event.setCancelled(true);
		}
		Player p = event.getPlayer();
		if(pltype.get(p.getName())==PlayerType.WATCHING){
			event.setCancelled(true);
		}
	}
	@EventHandler
	public void onItemPick(PlayerPickupItemEvent event){
		Player p = event.getPlayer();
		if(pltype.get(p.getName())==PlayerType.WATCHING){
			event.setCancelled(true);
		}
	}
	@EventHandler
	public void onEntityDamage(EntityDamageEvent event){
		PlayerType ss = TheBukkitGames.serverstate;
		if(ss==PlayerType.BEFORE|ss==PlayerType.WAITING|ss==PlayerType.SECONDWAIT|ss==PlayerType.LASTBATTLERESP){
			event.setCancelled(true);
		}
		if(event.getEntity() instanceof Player){
			if(pltype.get(((Player)event.getEntity()).getName())==PlayerType.WATCHING|pltype.get(((Player)event.getEntity()).getName())==PlayerType.BEFOREGAMING){
				event.setCancelled(true);
			}
		}
	}
	@EventHandler
	public void onEntityTarget(EntityTargetEvent event){
		PlayerType ss = TheBukkitGames.serverstate;
		if(ss==PlayerType.BEFORE|ss==PlayerType.WAITING|ss==PlayerType.BEFOREGAMING|ss==PlayerType.SECONDWAIT|ss==PlayerType.LASTBATTLERESP){
			event.setCancelled(true);
		}
		if(event.getTarget()!=null){
			if(event.getTarget() instanceof Player){
				Player p = (Player) event.getTarget();
				if(pltype.get(p.getName())==PlayerType.WATCHING|pltype.get(p.getName())==PlayerType.BEFOREGAMING){
					event.setCancelled(true);
				}
			}
		}
	}
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event){
		Player dp = event.getEntity();
		Location loc = dp.getLocation();
		event.setDeathMessage("");
		Player p = null;
		String killmsg = LoadMessages.PlayerKillEvent.replaceAll("<KILLED_PLAYER>", dp.getName());
		if(dp.getKiller() != null) {
			if(dp.getKiller() instanceof Player){
				p = dp.getKiller();
			}
		}else{
			if(lastDamagePlayer.get(dp.getName())!=null){
				if(Bukkit.getPlayer(lastDamagePlayer.get(dp.getName()))!=null){
					if(!livingplist.contains(pltype.get(Bukkit.getPlayer(lastDamagePlayer.get(dp.getName()))))){
						p = Bukkit.getPlayer(lastDamagePlayer.get(dp.getName()));
					}
				}
			}else{
				livingplist.remove(dp.getName());
				TheBukkitGames.GamingWorld.strikeLightningEffect(loc.add(0, 100, 0));
				LoadMessages.deathInfo(LoadMessages.PlayerDeathEvent.replaceAll("<KILLED_PLAYER>", dp.getName()));
				dp.kickPlayer(LoadMessages.KickMessage);
				return;
			}
		}
		int point = 0;
		if(plkit.get(dp.getName())!=null){
			point = plkit.get(dp.getName()).size()+points.get(dp.getName());
		}else{
			point = points.get(dp.getName());
		}
		int fpoint = point+points.get(p.getName());
		points.put(p.getName(), fpoint);
		livingplist.remove(dp.getName());
		TheBukkitGames.GamingWorld.strikeLightningEffect(loc.add(0, 100, 0));
		LoadMessages.send(p,LoadMessages.PointGetting.replaceAll("<POINT_INT>", String.valueOf(point)));
		String last = getAccesory(p)+killmsg.replaceAll("<KILLER>", p.getName());
		LoadMessages.deathInfo(last);
		dp.kickPlayer(LoadMessages.KickMessage);
	}

	private String getAccesory(Player p) {
		if(p.getItemInHand()!=null){
			Material m = p.getItemInHand().getType();
			switch(m){
			case GOLD_SWORD:
				return LoadMessages.GOLDEN_SWORD;
			case WOOD_SWORD:
				return LoadMessages.WOOD_SWORD;
			case STONE_SWORD:
				return LoadMessages.WOOD_SWORD;
			case IRON_SWORD:
				return LoadMessages.WOOD_SWORD;
			case DIAMOND_SWORD:
				return LoadMessages.WOOD_SWORD;
			default:
				return "";
			}
		}else{
			return LoadMessages.EMPTYHAND;
		}
	}
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		Entity damager = event.getDamager();
		Entity defender = event.getEntity();
		if(damager instanceof Player){
			Player p = (Player) damager;
			if(pltype.get(p.getName())==PlayerType.WATCHING){
				event.setCancelled(true);
			}else if(defender instanceof Player){
				lastDamagePlayer.put(((Player) defender).getName(), p.getName());
			}
		}else if(damager instanceof Arrow){
			Arrow a = (Arrow) damager;
			if(a.getShooter() instanceof Player){
				Player p = (Player) a.getShooter();
				if(pltype.get(p.getName())==PlayerType.WATCHING){
					event.setCancelled(true);
				}else if(defender instanceof Player){
					lastDamagePlayer.put(((Player) defender).getName(), p.getName());
				}
			}
		}
	}
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event){
		PlayerType pt = pltype.get(event.getPlayer().getName());
		if(pt==PlayerType.BEFORE){
			event.setCancelled(true);
		}else{
			StaminaFilter.filter.put(event.getPlayer(), true);
		}
		if(event.getTo().distance(spawnloc)>LoadFiles.GamingLimitDistance){
			event.setCancelled(true);
		}
		if(pt==PlayerType.LASTBATTLE|pt==PlayerType.LASTBATTLERESP){
			if(event.getTo().distance(spawnloc)>LoadFiles.LastBattleListmitDistance){
				event.setCancelled(true);
			}
		}
	}
	@EventHandler
	public void onInteractEvent(PlayerInteractEvent event){
		if(event.getAction()==Action.RIGHT_CLICK_AIR|event.getAction()==Action.RIGHT_CLICK_BLOCK){
			Player p = event.getPlayer();
			if(pltype.get(p.getName())==PlayerType.WATCHING){
				event.setCancelled(true);
				if(p.isSneaking()){
						if(watchp.get(p)!=null){
						}else{
							watchp.put(p, assassin.getList(livingplist));
						}
							List<Player> list = watchp.get(p);
							int u = 0;
								while(u!=list.size()){
									if(list.get(u)!=null){
										Player np = list.get(u);
										if(livingplist.contains(list.get(u).getName())){
											if(!list.get(u).equals(p)){
												p.teleport(np.getLocation());
												list.remove(np);
												if(list.size()==0){
													watchp.put(p, null);
												}else{
													watchp.put(p, list);
												}
												return;
											}
										}
									}
									list.remove(list.get(u));
									u++;
								}
								watchp.put(p, null);
								return;
				}
			}
		}else if(event.getAction()==Action.LEFT_CLICK_AIR){
			Player p = event.getPlayer();
			if(pltype.get(p.getName())==PlayerType.WATCHING){
				if(flyplist.contains(p)){
					p.setAllowFlight(false);
					p.sendMessage(ChatColor.DARK_AQUA+"flight off");
					flyplist.remove(p);
				}else{
					p.setAllowFlight(true);
					p.sendMessage(ChatColor.DARK_AQUA+"flight on");
					flyplist.add(p);
				}
			}
		}
	}
	@EventHandler
	public void onFoodLevelChange(FoodLevelChangeEvent event){
		if(pltype.get(event.getEntity().getName())==PlayerType.WATCHING){
			event.setCancelled(true);
		}else{
			if(event.getEntity() instanceof Player){
				Player p = (Player) event.getEntity();
				if(p.getItemInHand()!=null){
					if(p.getItemInHand().getType()==Material.MUSHROOM_SOUP){
						event.setCancelled(true);
						int phealth = p.getHealth()+3;
						if(20>=phealth){
							p.setHealth(phealth);
						}else{
							p.setHealth(20);
						}
					}
				}
			}
		}
	}
	private void setWatching(Player joinp) {
		if(livingplist.contains(joinp.getName())){livingplist.remove(joinp.getName());}
		watchingplist.add(joinp.getName());
		pltype.put(joinp.getName(), PlayerType.WATCHING);
		PlayerInventory i = joinp.getInventory();
		i.clear();
		i.setHelmet(null);
		i.setChestplate(null);
		i.setLeggings(null);
		i.setLeggings(null);
		i.setBoots(null);
		joinp.setGameMode(GameMode.SURVIVAL);
		joinp.setHealth(20);
		joinp.setFoodLevel(20);
		joinp.setExp(0);
		Object[] pe = joinp.getActivePotionEffects().toArray();
		int u = 0;
		while(u!=pe.length){
			joinp.removePotionEffect(((PotionEffect)pe[u]).getType());
			u++;
		}
		playerHides();
		joinp.setPlayerListName(ChatColor.DARK_RED+joinp.getName());
		LoadMessages.send(joinp, LoadMessages.WatchingPlayer);
	}

	private void playerHides() {
		Player[] pl = TheBukkitGames.server.getOnlinePlayers();
		int size = pl.length;
		int u = 0;
		while(u!=size){
			int size2 = watchingplist.size();
			int e = 0;
			while(e!=size2){
			pl[u].hidePlayer(Bukkit.getPlayer(watchingplist.get(e)));
			e++;
			}
			u++;
		}
	}
	public static String getKits(Player p){
		List<Kit> kits = plkit.get(p.getName());
		int u = 0;
		StringBuffer sb = new StringBuffer();
		while(u!=kits.size()){
			if(u==0){
				sb.append(kits.get(u).getname());
			}else{
				sb.append(LoadMessages.kitnamesplit);
				sb.append(kits.get(u).getname());
			}
			u++;
		}
		return sb.toString();
	}

	public static void setDefPoint(Player p){
		points.put(p.getName(), LoadFiles.defPoints);
	}
}
