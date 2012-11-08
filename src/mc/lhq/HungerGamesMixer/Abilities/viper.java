package mc.lhq.HungerGamesMixer.Abilities;

import java.util.Random;

import mc.lhq.HungerGamesMixer.Kit;
import mc.lhq.HungerGamesMixer.PlayerType;
import mc.lhq.HungerGamesMixer.Listeners.GamePlayerListener;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class viper implements Listener {

	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event){
		if(event.getEntity() instanceof Player){
			Player defend = (Player) event.getEntity();
			if(GamePlayerListener.pltype.get(defend.getName())==PlayerType.GAMING|GamePlayerListener.pltype.get(defend.getName())==PlayerType.LASTBATTLE){
		if(event.getDamager() instanceof Player){
			Player p = (Player) event.getDamager();
			if(Kit.haveAbility(Ability.getAbility("viper"), p)){
				int chance = Ability.getAbility("viper").getchance();
				Random r = new Random();
				if(chance>r.nextInt(100)){
					defend.removePotionEffect(PotionEffectType.POISON);
					defend.addPotionEffect(new PotionEffect(PotionEffectType.POISON,Ability.getAbility("viper").getduration()*20,0));
				}
			}
		}else if(event.getDamager() instanceof Arrow){
			Arrow a = (Arrow) event.getDamager();
			if(a.getShooter() instanceof Player){
				Player p = (Player) a.getShooter();
				if(Kit.haveAbility(Ability.getAbility("viper"), p)){
					int chance = Ability.getAbility("viper").getchance();
					Random r = new Random();
					if(chance>r.nextInt(100)){
						defend.removePotionEffect(PotionEffectType.POISON);
						defend.addPotionEffect(new PotionEffect(PotionEffectType.POISON,Ability.getAbility("viper").getduration()*20,0));
					}
				}
			}
		}
	}
		}
	}

}
