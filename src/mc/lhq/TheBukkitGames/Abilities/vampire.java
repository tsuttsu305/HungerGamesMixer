package mc.lhq.TheBukkitGames.Abilities;

import mc.lhq.TheBukkitGames.Kit;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class vampire implements Listener {

	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event){
		if(event.getDamager() instanceof Player){
			Player p = (Player) event.getDamager();
			if(Kit.haveAbility(Ability.getAbility("vampire"), p)){
				int damage = event.getDamage();
				int health = p.getHealth()+damage;
				if(20>=health){
					p.setHealth(health);
				}else{
					p.setHealth(20);
				}
			}
		}else if(event.getDamager() instanceof Arrow){
			Arrow a = (Arrow) event.getDamager();
			if(a.getShooter() instanceof Player){
				Player p = (Player) a.getShooter();
				if(Kit.haveAbility(Ability.getAbility("vampire"), p)){
					int damage = event.getDamage();
					int health = p.getHealth()+damage;
					if(20>=health){
						p.setHealth(health);
					}else{
						p.setHealth(20);
					}
				}
			}
		}
	}

}
