package mc.lhq.TheBukkitGames.Abilities;

import mc.lhq.TheBukkitGames.Kit;
import mc.lhq.TheBukkitGames.TheBukkitGames;
import org.bukkit.Material;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class fletcher implements Listener {

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event){
		if(event.getBlock().getType()==Material.GRAVEL){
			Player p = event.getPlayer();
			if(Kit.haveAbility(Ability.getAbility("fletcher"), p)){
				TheBukkitGames.GamingWorld.dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.FLINT,1));
			}
		}
	}

	@EventHandler
	public void onEntityDeath(EntityDeathEvent event){
		if(event.getEntity() instanceof Chicken){
			if(event.getEntity().getKiller()!=null&event.getEntity().getKiller() instanceof Player){
				Player p = event.getEntity().getKiller();
				if(Kit.haveAbility(Ability.getAbility("fletcher"), p)){
					event.getDrops().add(new ItemStack(Material.FEATHER,Ability.getAbility("fletcher").amount()));
				}
			}
		}
	}

}
