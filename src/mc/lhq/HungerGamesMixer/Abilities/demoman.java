package mc.lhq.HungerGamesMixer.Abilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mc.lhq.HungerGamesMixer.Kit;
import mc.lhq.HungerGamesMixer.HungerGamesMixer;
import mc.lhq.HungerGamesMixer.Listeners.GamePlayerListener;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class demoman implements Listener{
	public static List<Location> mine = new ArrayList<Location>();
	public static HashMap<Location,Player> minehm = new HashMap<Location,Player>();

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event){
		if(event.getBlock().getType()==Material.STONE_PLATE){
			Location loc = event.getBlock().getLocation();
			if(mine.contains(loc)){
				mine.remove(loc);
				minehm.put(loc, null);
			}
		}
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event){
		if(event.getAction()==Action.PHYSICAL){
			if(event.getClickedBlock().getType()==Material.STONE_PLATE){
				List<Location> locs = demoman.mine;
				Location loc = event.getClickedBlock().getLocation();
				if(locs.contains(loc)){
					if(HungerGamesMixer.GamingWorld.getBlockAt(loc.getBlockX(),loc.getBlockY()-1,loc.getBlockZ()).getType()==Material.GRAVEL){
						if(GamePlayerListener.livingplist.contains(minehm.get(loc).getName())){
							if(!minehm.get(loc).equals(event.getPlayer())){
								GamePlayerListener.lastDamagePlayer.put(event.getPlayer().getName(), minehm.get(loc).getName());
							}else{
							}
							HungerGamesMixer.GamingWorld.createExplosion(loc, 3);
							demoman.mine.remove(loc);
							minehm.put(loc, null);
						}
					}
				}
			}
		}
	}
	@EventHandler
	public void onEntityInteract(EntityInteractEvent event){
		if(event.getBlock().getType()==Material.STONE_PLATE){
			List<Location> locs = demoman.mine;
			Location loc = event.getBlock().getLocation();
			if(locs.contains(loc)){
				if(HungerGamesMixer.GamingWorld.getBlockAt(loc.getBlockX(),loc.getBlockY()-1,loc.getBlockZ()).getType()==Material.GRAVEL){
					HungerGamesMixer.GamingWorld.createExplosion(loc, 5);
					demoman.mine.remove(loc);
					minehm.put(loc, null);
				}
			}
		}
	}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event){
		if(event.getBlock().getType()==Material.STONE_PLATE){
			if(Kit.haveAbility(Ability.getAbility("demoman"), event.getPlayer())){
				Location loc = event.getBlock().getLocation();
				mine.add(loc);
				minehm.put(loc, event.getPlayer());
			}
		}
	}
}
