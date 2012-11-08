package mc.lhq.TheBukkitGames.Abilities;

import java.util.ArrayList;
import java.util.List;

import mc.lhq.TheBukkitGames.Kit;
import mc.lhq.TheBukkitGames.TheBukkitGames;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;

public class thor implements Listener {

	List<Player> pl = new ArrayList<Player>();
	Plugin plg;

	public thor(Plugin p){
		plg = p;
	}
	@EventHandler
	public void onEntityDamage(EntityDamageEvent event){
		if(event.getCause()==DamageCause.LIGHTNING){
			if(event.getEntity() instanceof Player){
				Player p = (Player) event.getEntity();
				if(Kit.haveAbility(Ability.getAbility("thor"), p)){
					event.setCancelled(true);
				}
			}
		}
	}
	@EventHandler
	public void onInteract(PlayerInteractEvent event){
		if(event.getAction()==Action.RIGHT_CLICK_BLOCK){
			if(event.getItem()!=null){
				if(event.getItem().getType()==Material.WOOD_AXE){
					Player p = event.getPlayer();
					if(Kit.haveAbility(Ability.getAbility("thor"), p)){
						if(!pl.contains(p)){
							Block b = event.getClickedBlock();
							World w = b.getWorld();
							Block b1 = w.getBlockAt(b.getX(), b.getY()+1, b.getZ());
							b.setType(Material.NETHERRACK);
							b1.setType(Material.FIRE);
							Location loc = new Location(w,b.getX(),w.getHighestBlockYAt(b.getLocation()),b.getZ());
							w.strikeLightning(loc);
							pl.add(p);
							setcooldown(p);
						}
					}
				}
			}
		}
	}

	private void setcooldown(final Player p) {
		TheBukkitGames.server.getScheduler().scheduleAsyncDelayedTask(plg, new Runnable(){
			public void run() {
				pl.remove(p);
				p.sendMessage(ChatColor.DARK_RED+"Thor's power is charged!");
			}
		}, Ability.getAbility("thor").getcooldown()*20L);
	}

}
