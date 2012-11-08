package mc.lhq.HungerGamesMixer.Abilities;

import java.util.Collection;

import mc.lhq.HungerGamesMixer.Kit;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class cookiemonster implements Listener {

	@EventHandler
	public void onFood(FoodLevelChangeEvent  event){
		if(event.getEntity() instanceof Player){
			Player p = (Player) event.getEntity();
			if(p.getItemInHand()!=null){
				if(p.getItemInHand().getType()==Material.COOKIE){
					if(Kit.haveAbility(Ability.getAbility("cookiemonster"), p)){
						if(p.getHealth()!=20){
							p.setHealth(20);
						}else if(p.getFoodLevel()!=20){
							p.setFoodLevel(20);
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent event){
		if(event.getAction()==Action.RIGHT_CLICK_AIR|event.getAction()==Action.RIGHT_CLICK_BLOCK){
			if(event.getItem()!=null){
				if(event.getItem().getType()==Material.COOKIE){
					Player p = event.getPlayer();
					if(Kit.haveAbility(Ability.getAbility("cookiemonster"), p)){
						if(p.getHealth()==20&p.getFoodLevel()==20){
							Collection<PotionEffect> list = p.getActivePotionEffects();
							Object[] ob = list.toArray();
							int u = 0;
							while(u!=ob.length){
								if(((PotionEffect)ob[u]).getType()==PotionEffectType.SPEED){
									return;
								}
								u++;
							}
							p.removePotionEffect(PotionEffectType.SPEED);
							p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1200, 2));
							p.getInventory().removeItem(new ItemStack(Material.COOKIE,1));
						}
					}
				}
			}
		}
	}

}
