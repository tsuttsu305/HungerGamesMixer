package mc.lhq.TheBukkitGames.Abilities;

import java.util.Collection;

import mc.lhq.TheBukkitGames.Kit;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class miner implements Listener {

	@EventHandler
	public void onInteract(PlayerInteractEvent event){
		if(event.getAction()==Action.RIGHT_CLICK_AIR|event.getAction()==Action.RIGHT_CLICK_BLOCK){
			if(event.getItem()!=null){
				if(event.getItem().getType()==Material.APPLE){
					Player p = event.getPlayer();
					if(Kit.haveAbility(Ability.getAbility("miner"), p)){
						if(p.getHealth()==20&p.getFoodLevel()==20){
							Collection<PotionEffect> list = p.getActivePotionEffects();
							Object[] ob = list.toArray();
							int u = 0;
							while(u!=ob.length){
								if(((PotionEffect)ob[u]).getType()==PotionEffectType.FAST_DIGGING){
									return;
								}
								u++;
							}
							p.removePotionEffect(PotionEffectType.FAST_DIGGING);
							p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 1200, 2));
							p.getInventory().removeItem(new ItemStack(Material.APPLE,1));
						}
					}
				}
			}
		}
	}

}
