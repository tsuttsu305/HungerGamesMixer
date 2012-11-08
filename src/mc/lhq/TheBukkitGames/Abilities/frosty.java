package mc.lhq.TheBukkitGames.Abilities;

import mc.lhq.TheBukkitGames.Kit;
import mc.lhq.TheBukkitGames.PlayerType;
import mc.lhq.TheBukkitGames.TheBukkitGames;
import mc.lhq.TheBukkitGames.Listeners.GamePlayerListener;
import mc.lhq.TheBukkitGames.LoadUtil.LoadFiles;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class frosty implements Listener {

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event){
		if(GamePlayerListener.pltype.get(event.getPlayer().getName())!=PlayerType.WAITING){
			if(Kit.haveAbility(Ability.getAbility("frosty"), event.getPlayer())){
				Location loc = event.getTo();
				int x = loc.getBlockX();
				int y = loc.getBlockY();
				int z = loc.getBlockZ();
				if(TheBukkitGames.GamingWorld.getBlockAt(x,y-1,z).getType()==Material.ICE|TheBukkitGames.GamingWorld.getBlockAt(x,y-1,z).getType()==Material.SNOW_BLOCK|TheBukkitGames.GamingWorld.getBlockAt(x,y,z).getType()==Material.SNOW){
					Player p = event.getPlayer();
					p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, LoadFiles.EndGamingTime*20, 2));
				}else{
					Player p = event.getPlayer();
					p.removePotionEffect(PotionEffectType.SPEED);
				}
			}
		}
	}

	@EventHandler
	public void onPlojecTileEvent(ProjectileHitEvent event){
		if(event.getEntity() instanceof Snowball){
			Snowball sb = (Snowball) event.getEntity();
			if(Kit.haveAbility(Ability.getAbility("frosty"), (Player)sb.getShooter())){
				if(TheBukkitGames.GamingWorld.getBlockAt(sb.getLocation()).getType()==Material.AIR){
					if(TheBukkitGames.GamingWorld.getBlockAt(sb.getLocation().getBlockX(),sb.getLocation().getBlockY()-1,sb.getLocation().getBlockZ()).getType()!=Material.AIR){
						TheBukkitGames.GamingWorld.getBlockAt(sb.getLocation()).setType(Material.SNOW);
					}
				}
			}
		}

	}

}
