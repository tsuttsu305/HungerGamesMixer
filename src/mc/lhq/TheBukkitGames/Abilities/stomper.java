package mc.lhq.TheBukkitGames.Abilities;

import java.util.ArrayList;
import java.util.List;

import mc.lhq.TheBukkitGames.Kit;
import mc.lhq.TheBukkitGames.Listeners.GamePlayerListener;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class stomper implements Listener {

	@EventHandler
	public void onDamage(EntityDamageEvent event){
		if(event.getCause()==DamageCause.FALL){
			if(event.getEntity() instanceof Player){
				Player p = (Player) event.getEntity();
				if(Kit.haveAbility(Ability.getAbility("stomper"), p)){
					if(event.getDamage()>2){
						List<Player> pl = new ArrayList<Player>();
						int damage = event.getDamage()-2;
						if(damage==0){event.setDamage(2);return;}
						int u = 0;
						int distance =Ability.getAbility("stomper").getdistance();
						while(100>=u){
							List<Entity> ens = p.getNearbyEntities(u,64.0D, u);
							int a = 0;
							while(a!=ens.size()){
								if(ens.get(a) instanceof Player){
									Player c = (Player) ens.get(a);
									if(distance>=p.getLocation().distance(c.getLocation())){
										if(!pl.contains(c)){
											pl.add(c);
										}
									}
								}
								a++;
							}
							u++;
						}
						int size = pl.size();
						if(size==0){
							event.setDamage(2);
							return;
						}
						int damage1 = damage/size;
						while(pl.size()!=0){
							GamePlayerListener.lastDamagePlayer.put(pl.get(0).getName(), p.getName());
							pl.get(0).damage(damage1);
							pl.remove(0);
						}
						event.setDamage(2);
					}
				}
			}
		}
	}

}
