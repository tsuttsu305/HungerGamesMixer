package mc.lhq.TheBukkitGames.Abilities;

import mc.lhq.TheBukkitGames.Kit;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class berserker implements Listener {

	@EventHandler
	public void onPlayerKill(EntityDeathEvent event){
		if(event.getEntity().getKiller()!=null){
			if(event.getEntity().getKiller() instanceof Player){
				if(event.getEntity() instanceof Player){
					Player p = event.getEntity().getKiller();
					if(Kit.haveAbility(Ability.getAbility("berserker"), p)){
						p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
						p.removePotionEffect(PotionEffectType.CONFUSION);
						p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,200, 1));
						p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION,200, 1));
						p.sendMessage(ChatColor.RED+"xxxxxBLOOD LUST!xxxxx");
					}
				}
			}
		}
	}
}
