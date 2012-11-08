package mc.lhq.TheBukkitGames.Abilities;

import mc.lhq.TheBukkitGames.Kit;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class fireman implements Listener {

	@EventHandler
	public void onEntityDamage(EntityDamageEvent event){
		if(event.getCause()==DamageCause.LIGHTNING){
			if(event.getEntity() instanceof Player){
				Player p = (Player) event.getEntity();
				if(Kit.haveAbility(Ability.getAbility("fireman"), p)){
					event.setCancelled(true);
				}
			}
		}
	}

}
