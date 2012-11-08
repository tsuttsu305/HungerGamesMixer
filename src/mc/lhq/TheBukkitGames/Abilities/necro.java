package mc.lhq.TheBukkitGames.Abilities;

import mc.lhq.TheBukkitGames.Kit;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class necro implements Listener {

	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event){
		if(event.getEntity() instanceof Skeleton){
			Player attack = null;
			if(event.getDamager() instanceof Player){
				attack = (Player) event.getDamager();
			}else if(event.getDamager() instanceof Arrow){
				Arrow a = (Arrow) event.getDamager();
				if(a.getShooter() instanceof Player){
					attack = (Player) a.getShooter();
				}else{
					return;
				}
			}else{
				return;
			}
			if(Kit.haveAbility(Ability.getAbility("necro"), attack)){
				Skeleton s = (Skeleton) event.getEntity();
				s.damage(20);
			}
		}
	}

}
