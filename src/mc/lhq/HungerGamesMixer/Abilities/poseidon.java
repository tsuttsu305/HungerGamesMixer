package mc.lhq.HungerGamesMixer.Abilities;

import mc.lhq.HungerGamesMixer.Kit;
import mc.lhq.HungerGamesMixer.PlayerType;
import mc.lhq.HungerGamesMixer.HungerGamesMixer;
import mc.lhq.HungerGamesMixer.Listeners.GamePlayerListener;
import mc.lhq.HungerGamesMixer.LoadUtil.LoadFiles;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class poseidon implements Listener {

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event){
		if(GamePlayerListener.pltype.get(event.getPlayer().getName())!=PlayerType.WAITING){
				Location loc = event.getTo();
				int x = loc.getBlockX();
				int y = loc.getBlockY();
				int z = loc.getBlockZ();
				if(HungerGamesMixer.GamingWorld.getBlockAt(x,y,z).getType()==Material.WATER|HungerGamesMixer.GamingWorld.getBlockAt(x,y+1,z).getType()==Material.WATER|HungerGamesMixer.GamingWorld.getBlockAt(x,y,z).getType()==Material.STATIONARY_WATER|HungerGamesMixer.GamingWorld.getBlockAt(x,y+1,z).getType()==Material.STATIONARY_WATER){
					if(!Kit.haveAbility(Ability.getAbility("poseidon"), event.getPlayer())){
						return;
					}
					Player p = event.getPlayer();
					p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, LoadFiles.EndGamingTime*20, 1));
					p.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, LoadFiles.EndGamingTime*20, 1));
					p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, LoadFiles.EndGamingTime*20, 2));
				}else{
					if(!Kit.haveAbility(Ability.getAbility("poseidon"), event.getPlayer())){
						return;
					}
					Player p = event.getPlayer();
					p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
					p.removePotionEffect(PotionEffectType.WATER_BREATHING);
					p.removePotionEffect(PotionEffectType.SPEED);
				}
			}
	}

}
