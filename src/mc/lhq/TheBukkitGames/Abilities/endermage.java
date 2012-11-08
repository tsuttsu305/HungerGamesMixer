package mc.lhq.TheBukkitGames.Abilities;

import java.util.ArrayList;
import java.util.List;

import mc.lhq.TheBukkitGames.Kit;
import mc.lhq.TheBukkitGames.TheBukkitGames;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class endermage implements Listener {
	List<Player> defplist = new ArrayList<Player>();
	boolean defon = false;

	Plugin plugin;

	public endermage(Plugin plg){
		this.plugin = plg;
	}

	@EventHandler
	public void onPlayerDamage(EntityDamageEvent event){
		if(event.getEntity() instanceof Player){
			if(defon){
				if(defplist.contains((Player)event.getEntity())){
					event.setCancelled(true);
				}
			}
		}
	}
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		Player p = event.getPlayer();
		Block b = event.getBlockPlaced();
		if(b.getType()==Material.PORTAL){
			if(Kit.haveAbility(Ability.getAbility("endermage"), p)){
				b.setType(Material.AIR);
				Location loc = b.getLocation();
				Block bd = TheBukkitGames.GamingWorld.getBlockAt(loc.getBlockX(), loc.getBlockY()-1, loc.getBlockZ());
				List<Player> plist = new ArrayList<Player>();
				bd.setType(Material.ENDER_PORTAL);
				defon = true;
					List<Entity> ens = p.getNearbyEntities(4,300,4);
					int u = 0;
					while(u!=ens.size()){
						if (ens.get(u) instanceof Player){
							Player pl = (Player) ens.get(u);
							if(!p.equals(pl)){
									plist.add(pl);
									pl.teleport(bd.getLocation());
							}
						}
						u++;
					}
				int e = 0;
				while(e!=plist.size()){
					defplist.add(plist.get(e));
					e++;
				}
				p.getInventory().removeItem(new ItemStack(Material.PORTAL,1));
				onSetScheduler(plist);
			}
		}
	}

	public void onSetScheduler(List<Player> pl){
		final List<Player> playerlist = pl;
		Ability k = Ability.getAbility("endermage");
		TheBukkitGames.server.getScheduler().scheduleAsyncDelayedTask(plugin, new Runnable(){
			public void run(){
				int u = 0;
				while(u!=playerlist.size()){
					defplist.remove(playerlist.get(u));
					u++;
				}
				if(defplist.size()==0){
					defon = false;
				}
			}
		}, k.getduration()*20L);
	}

}
