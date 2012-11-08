package mc.lhq.TheBukkitGames.Abilities;

import java.math.BigDecimal;

import mc.lhq.TheBukkitGames.Kit;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class boxer implements Listener {

	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event){
		if(event.getDamager() instanceof Player){
			Player attack = (Player) event.getDamager();
			if(event.getEntity() instanceof Player){
				if(Kit.haveAbility(Ability.getAbility("boxer"), (Player) event.getEntity())){
					if(attack.getItemInHand().getType()!=null){
						if(attack.getItemInHand().getType()==Material.AIR){
							Double d = event.getDamage()*0.75;
							BigDecimal bi = new BigDecimal(String.valueOf(d));
							double k1 = bi.setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue();
							BigDecimal b2 = new BigDecimal(String.valueOf(k1));
							event.setDamage((int) b2.setScale(0,BigDecimal.ROUND_HALF_UP).doubleValue());
						}
					}
				}
			}
			if(Kit.haveAbility(Ability.getAbility("boxer"), (Player) attack)){
				if(attack.getItemInHand().getType()!=null){
					if(attack.getItemInHand().getType()==Material.AIR){
						event.setDamage(event.getDamage()*5);
					}
				}
			}
		}
	}

}
