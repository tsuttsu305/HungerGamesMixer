package mc.lhq.HungerGamesMixer.Abilities;

import java.util.List;

import mc.lhq.HungerGamesMixer.Kit;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.EntityTargetEvent.TargetReason;

public class monster implements Listener {

	@EventHandler
	public void onTarger(EntityTargetEvent event){
		if(event.getReason()==TargetReason.CLOSEST_PLAYER){
			if(event.getTarget() instanceof Player){
				if(Kit.haveAbility(Ability.getAbility("monster"), (Player) event.getTarget())){
					event.setCancelled(true);
				}
			}
		}
	}

	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event){
		if(event.getEntity() instanceof Player){
			Player deffend = (Player) event.getEntity();
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
			if(Kit.haveAbility(Ability.getAbility("monster"),deffend)){
				if(!Kit.haveAbility(Ability.getAbility("monster"), attack)){
					setTarget(attack);
				}
			}else if(Kit.haveAbility(Ability.getAbility("monster"), attack)){
				if(!Kit.haveAbility(Ability.getAbility("monster"), deffend)){
					setTarget(deffend);
				}
			}
		}
	}

	private void setTarget(Player p) {
		int u = 0;
		int distance =Ability.getAbility("monster").getdistance();
		while(100>=u){
			List<Entity> ens = p.getNearbyEntities(u,64.0D, u);
			int a = 0;
			while(a!=ens.size()){
				if(ens.get(a) instanceof Creature){
					Creature c = (Creature) ens.get(a);
					if(distance>=p.getLocation().distance(c.getLocation())){
						c.setTarget(p);
					}
				}
				a++;
			}
			u++;
		}
	}

}
