package mc.lhq.TheBukkitGames.Abilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mc.lhq.TheBukkitGames.Kit;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

public class kaya implements Listener {

	List<Block> kayablocks = new ArrayList<Block>();
	HashMap<Location,Player> kayahm = new HashMap<Location,Player>();

	@EventHandler
	public void onMove(PlayerMoveEvent event){
		Location loc = event.getTo();
		World w = loc.getWorld();
		int x = loc.getBlockX();
		int y = loc.getBlockY();
		int z = loc.getBlockZ();
		Block b =w.getBlockAt(x, y-1, z);
		Block b1 =w.getBlockAt(x+1, y-1, z);
		Block b2 =w.getBlockAt(x-1, y-1, z);
		Block b3 =w.getBlockAt(x, y-1, z+1);
		Block b4 =w.getBlockAt(x, y-1, z-1);
		Block b5 =w.getBlockAt(x+1, y-1, z+1);
		Block b6 =w.getBlockAt(x+1, y-1, z-1);
		Block b7 =w.getBlockAt(x-1, y-1, z+1);
		Block b8 =w.getBlockAt(x-1, y-1, z-1);
		checkKaya(b,event.getPlayer());
		checkKaya(b1,event.getPlayer());
		checkKaya(b2,event.getPlayer());
		checkKaya(b3,event.getPlayer());
		checkKaya(b4,event.getPlayer());
		checkKaya(b5,event.getPlayer());
		checkKaya(b6,event.getPlayer());
		checkKaya(b7,event.getPlayer());
		checkKaya(b8,event.getPlayer());

	}

	public void checkKaya(Block b,Player p){
		if(kayablocks.contains(b)){
			if(kayahm.get(b.getLocation())!=null){
				if(!kayahm.get(b.getLocation()).equals(p)){
					kayablocks.remove(b);
					b.setType(Material.AIR);
					kayahm.get(b.getLocation()).getInventory().addItem(new ItemStack(Material.GRASS,1));
					kayahm.put(b.getLocation(), null);
				}
			}
		}
	}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event){
		if(event.getBlockPlaced().getType()==Material.GRASS){
			Player p = event.getPlayer();
			if(Kit.haveAbility(Ability.getAbility("kaya"), p)){
				kayablocks.add(event.getBlockPlaced());
				kayahm.put(event.getBlockPlaced().getLocation(), p);
			}
		}
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event){
		if(event.getBlock().getType()==Material.GRASS){
			kayablocks.remove(event.getBlock().getLocation());
			kayahm.put(event.getBlock().getLocation(), null);
		}
	}
}
