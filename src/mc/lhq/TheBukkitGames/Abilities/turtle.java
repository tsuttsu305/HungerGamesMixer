package mc.lhq.TheBukkitGames.Abilities;

import mc.lhq.TheBukkitGames.Kit;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class turtle implements Listener {

	@EventHandler
	public void onDamage(EntityDamageEvent event){
		if(event.getCause()==DamageCause.ENTITY_ATTACK){
			if(event.getEntity() instanceof Player){
				Player p = (Player) event.getEntity();
				if(p.isSneaking()){
					if(Kit.haveAbility(Ability.getAbility("turtle"), p)){
						Double damage = (double) event.getDamage();
						Double i = 2.0;
						event.setDamage((int) (damage/i));
					}
				}
			}
		}
	}

}
