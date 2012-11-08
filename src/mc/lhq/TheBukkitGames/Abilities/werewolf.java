package mc.lhq.TheBukkitGames.Abilities;

import java.util.List;

import mc.lhq.TheBukkitGames.Kit;
import mc.lhq.TheBukkitGames.TheBukkitGames;
import mc.lhq.TheBukkitGames.Listeners.GamePlayerListener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class werewolf implements Listener {

	public werewolf(Plugin plg){
		TheBukkitGames.server.getScheduler().scheduleAsyncRepeatingTask(plg, new Runnable(){
			public void run() {
				if(TheBukkitGames.GamingWorld.getTime()>12000){
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
