package mc.lhq.HungerGamesMixer.Abilities;

import java.util.List;

import mc.lhq.HungerGamesMixer.Kit;
import mc.lhq.HungerGamesMixer.HungerGamesMixer;
import mc.lhq.HungerGamesMixer.Listeners.GamePlayerListener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class werewolf implements Listener {

	public werewolf(Plugin plg){
		HungerGamesMixer.server.getScheduler().scheduleAsyncRepeatingTask(plg, new Runnable(){
			public void run() {
				if(HungerGamesMixer.GamingWorld.getTime()>12000){
					List<String> plist = GamePlayerListener.livingplist;
					int u = 0;
					while(u!=plist.size()){
						Player p =  Bukkit.getPlayer(plist.get(u));
						if(Kit.haveAbility(Ability.getAbility("werewolf"), p)){
							p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,200,0));
							p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,200,0));
							p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION,200,0));
							p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP,200,0));
						}
						u++;
					}
				}else{
					List<String> plist = GamePlayerListener.livingplist;
					int u = 0;
					while(u!=plist.size()){
						Player p =  Bukkit.getPlayer(plist.get(u));
						if(Kit.haveAbility(Ability.getAbility("werewolf"), p)){
							p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS,200,0));
						}
						u++;
					}
				}
			}
		}, 200L, 200L);
	}

}
