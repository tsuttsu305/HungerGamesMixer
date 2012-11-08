package mc.lhq.HungerGamesMixer.Abilities;

import mc.lhq.HungerGamesMixer.Kit;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class cannibal implements Listener {

	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event){
		if(event.getDamager() instanceof Player){
			Player p = (Player) event.getDamager();
			if(Kit.haveAbility(Ability.getAbility("cannibal"), p)){
				int damage = event.getDamage();
				int food = p.getFoodLevel()+damage;
				p.setFoodLevel(food);
			}
		}else if(event.getDamager() instanceof Arrow){
			Arrow a = (Arrow) event.getDamager();
			if(a.getShooter() instanceof Player){
				Player p = (Player) a.getShooter();
				if(Kit.haveAbility(Ability.getAbility("cannibal"), p)){
					int damage = event.getDamage();
					int food = p.getFoodLevel()+damage;
					p.setFoodLevel(food);
				}
			}
		}
	}
}
