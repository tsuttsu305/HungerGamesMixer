package mc.lhq.HungerGamesMixer.Abilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mc.lhq.HungerGamesMixer.Kit;
import mc.lhq.HungerGamesMixer.HungerGamesMixer;
import mc.lhq.HungerGamesMixer.LoadUtil.LoadFiles;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ghost implements Listener {

	static List<Player> plist = new ArrayList<Player>();
	static Plugin plg;
	HashMap<Player,Boolean> ghosting = new HashMap<Player,Boolean>();

	public ghost(Plugin p){
		plg=p;
	}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event){
		if(plist.contains(event.getPlayer())){
			event.setCancelled(true);
		}
	}
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event){
		if(plist.contains(event.getPlayer())){
			event.setCancelled(true);
		}
	}
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event){
		if(event.getDamager() instanceof Player){
			if(plist.contains((Player)event.getDamager())){
				event.setCancelled(true);
			}
		}else if(event.getDamager() instanceof Arrow){
			Arrow a = (Arrow) event.getDamager();
			if(a.getShooter() instanceof Player){
				if(plist.contains((Player)a.getShooter())){
					event.setCancelled(true);
				}
			}
		}
	}
	@EventHandler
	public void onInteract(PlayerInteractEvent event){
		if(event.getAction()==Action.RIGHT_CLICK_AIR){
			if(event.getItem().getType()==Material.GHAST_TEAR){
				Player p = event.getPlayer();
				if(Kit.haveAbility(Ability.getAbility("ghost"), p)){
					if(!plist.contains(p)){
						if(ghosting.get(p)!=null){
						}else{
							ghosting.put(p, Boolean.valueOf("false"));
						}
						if(ghosting.get(p).equals(Boolean.valueOf("false"))){
							plist.add(p);
							sethide();
							p.sendMessage(ChatColor.DARK_AQUA+"You are ghosting...");
							p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, LoadFiles.EndGamingTime*20, 1));
							p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, LoadFiles.EndGamingTime*20, 1));
							setScheduler(p);
						}
					}
				}
			}
		}else if(event.getAction()==Action.RIGHT_CLICK_BLOCK){
			if(plist.contains(event.getPlayer())){
				event.setCancelled(true);
			}
		}
	}

	public void setScheduler(final Player p){
		HungerGamesMixer.server.getScheduler().scheduleAsyncDelayedTask(plg, new Runnable(){
			public void run() {
				plist.remove(p);
				setshow(p);
				p.removePotionEffect(PotionEffectType.SPEED);
				p.removePotionEffect(PotionEffectType.JUMP);
				ghosting.put(p, true);
				setScheduler2(p);
				p.sendMessage(ChatColor.DARK_AQUA+"Your ghost power is empty...");
				p.sendMessage(ChatColor.DARK_AQUA+"You are humaning...");
			}}, Ability.getAbility("ghost").getduration()*20L);
	}

	public void setScheduler2(final Player p){
		HungerGamesMixer.server.getScheduler().scheduleAsyncDelayedTask(plg, new Runnable(){
			public void run() {
				ghosting.put(p, false);
				p.sendMessage(ChatColor.DARK_AQUA+"Your ghost power is charged...");
			}
		}, Ability.getAbility("ghost").getcooldown()*20L);

	}

	private void setshow(Player p ) {
		Player[] pl = HungerGamesMixer.server.getOnlinePlayers();
		int u = 0;
		while(u!=pl.length){
			pl[u].showPlayer(p);
			u++;
		}
		sethide();
	}

	public static void sethide() {
		Player[] pl = HungerGamesMixer.server.getOnlinePlayers();
		int u = 0;
		while(u!=pl.length){
			int a = 0;
			while(a!=plist.size()){
				if(!Kit.haveAbility(Ability.getAbility("finder"), pl[u])){
					pl[u].hidePlayer(plist.get(a));
				}
				a++;
			}
			u++;
		}
	}

}
