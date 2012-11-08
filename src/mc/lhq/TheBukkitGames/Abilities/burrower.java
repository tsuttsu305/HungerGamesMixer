package mc.lhq.TheBukkitGames.Abilities;

import java.util.Random;

import mc.lhq.TheBukkitGames.Kit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class burrower implements Listener {

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event){
		if(event.getAction()==Action.RIGHT_CLICK_BLOCK){
			Player p = event.getPlayer();
			if(p.getItemInHand().getType()==Material.SLIME_BALL){
				if(Kit.haveAbility(Ability.getAbility("burrower"), p)){
					Random r = new Random();
					Location loc = p.getLocation();
					World w = loc.getWorld();
					int y = w.getHighestBlockYAt(loc)-r.nextInt(Ability.getAbility("burrower").getdistance());
					int x = loc.getBlockX();
					int z = loc.getBlockZ();
					w.getBlockAt(x, y, z).setType(Material.AIR);
					w.getBlockAt(x, y-1, z).setType(Material.AIR);
					w.getBlockAt(x+1, y, z).setType(Material.AIR);
					w.getBlockAt(x+1, y-1, z).setType(Material.AIR);
					w.getBlockAt(x, y, z+1).setType(Material.AIR);
					w.getBlockAt(x, y-1, z+1).setType(Material.AIR);
					w.getBlockAt(x+1, y, z+1).setType(Material.TORCH);
					w.getBlockAt(x+1, y-1, z+1).setType(Material.AIR);
					w.getBlockAt(x-1, y+1, z+2).setType(Material.BRICK);
					w.getBlockAt(x, y+1, z+2).setType(Material.BRICK);
					w.getBlockAt(x+1, y+1, z+2).setType(Material.BRICK);
					w.getBlockAt(x+2, y+1, z+2).setType(Material.BRICK);
					w.getBlockAt(x-1, y, z+2).setType(Material.BRICK);
					w.getBlockAt(x, y, z+2).setType(Material.BRICK);
					w.getBlockAt(x+1, y, z+2).setType(Material.BRICK);
					w.getBlockAt(x+2, y, z+2).setType(Material.BRICK);
					w.getBlockAt(x-1, y-1, z+2).setType(Material.BRICK);
					w.getBlockAt(x, y-1, z+2).setType(Material.BRICK);
					w.getBlockAt(x+1, y-1, z+2).setType(Material.BRICK);
					w.getBlockAt(x+2, y-1, z+2).setType(Material.BRICK);
					w.getBlockAt(x-1, y-2, z+2).setType(Material.BRICK);
					w.getBlockAt(x, y-2, z+2).setType(Material.BRICK);
					w.getBlockAt(x+1, y-2, z+2).setType(Material.BRICK);
					w.getBlockAt(x+2, y-2, z+2).setType(Material.BRICK);
					w.getBlockAt(x-1, y+1, z+1).setType(Material.BRICK);
					w.getBlockAt(x, y+1, z+1).setType(Material.BRICK);
					w.getBlockAt(x+1, y+1, z+1).setType(Material.BRICK);
					w.getBlockAt(x+2, y+1, z+1).setType(Material.BRICK);
					w.getBlockAt(x-1, y, z+1).setType(Material.BRICK);
					w.getBlockAt(x+2, y, z+1).setType(Material.BRICK);
					w.getBlockAt(x-1, y-1, z+1).setType(Material.BRICK);
					w.getBlockAt(x+2, y-1, z+1).setType(Material.BRICK);
					w.getBlockAt(x-1, y-2, z+1).setType(Material.BRICK);
					w.getBlockAt(x, y-2, z+1).setType(Material.BRICK);
					w.getBlockAt(x+1, y-2, z+1).setType(Material.BRICK);
					w.getBlockAt(x+2, y-2, z+1).setType(Material.BRICK);
					w.getBlockAt(x-1, y+1, z).setType(Material.BRICK);
					w.getBlockAt(x, y+1, z).setType(Material.BRICK);
					w.getBlockAt(x+1, y+1, z).setType(Material.BRICK);
					w.getBlockAt(x+2, y+1, z).setType(Material.BRICK);
					w.getBlockAt(x-1, y, z).setType(Material.BRICK);
					w.getBlockAt(x+2, y, z).setType(Material.BRICK);
					w.getBlockAt(x-1, y-1, z).setType(Material.BRICK);
					w.getBlockAt(x+2, y-1, z).setType(Material.BRICK);
					w.getBlockAt(x-1, y-2, z).setType(Material.BRICK);
					w.getBlockAt(x, y-2, z).setType(Material.BRICK);
					w.getBlockAt(x+1, y-2, z).setType(Material.BRICK);
					w.getBlockAt(x+2, y-2, z).setType(Material.BRICK);
					w.getBlockAt(x-1, y+1, z-1).setType(Material.BRICK);
					w.getBlockAt(x, y+1, z-1).setType(Material.BRICK);
					w.getBlockAt(x+1, y+1, z-1).setType(Material.BRICK);
					w.getBlockAt(x+2, y+1, z-1).setType(Material.BRICK);
					w.getBlockAt(x-1, y, z-1).setType(Material.BRICK);
					w.getBlockAt(x, y, z-1).setType(Material.BRICK);
					w.getBlockAt(x+1, y, z-1).setType(Material.BRICK);
					w.getBlockAt(x+2, y, z-1).setType(Material.BRICK);
					w.getBlockAt(x-1, y-1, z-1).setType(Material.BRICK);
					w.getBlockAt(x, y-1, z-1).setType(Material.BRICK);
					w.getBlockAt(x+1, y-1, z-1).setType(Material.BRICK);
					w.getBlockAt(x+2, y-1, z-1).setType(Material.BRICK);
					w.getBlockAt(x-1, y-2, z-1).setType(Material.BRICK);
					w.getBlockAt(x, y-2, z-1).setType(Material.BRICK);
					w.getBlockAt(x+1, y-2, z-1).setType(Material.BRICK);
					w.getBlockAt(x+2, y-2, z-1).setType(Material.BRICK);
					p.teleport(new Location(w, x+1, y-1, z+1));
					p.getInventory().removeItem(new ItemStack(Material.SLIME_BALL,1));
				}
			}
		}
	}

}
