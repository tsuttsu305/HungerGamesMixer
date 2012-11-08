package mc.lhq.HungerGamesMixer.Abilities;

import mc.lhq.HungerGamesMixer.Kit;

import org.bukkit.ChatColor;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class sniper implements Listener {

	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event){
		if(event.getDamager() instanceof Arrow){
			Arrow a = (Arrow) event.getDamager();
			if(a.getShooter() instanceof Player){
				Player p = (Player) a.getShooter();
				if(Kit.haveAbility(Ability.getAbility("sniper"), p)){
					double distance = p.getLocation().distance(event.getEntity().getLocation());
					if(distance>=Ability.getAbility("sniper").getdistance()){
						event.setDamage(20);
						p.sendMessage(ChatColor.RED+"xxxxxSniping succeed!xxxxx");
					}
				}
			}
		}
	}
}
