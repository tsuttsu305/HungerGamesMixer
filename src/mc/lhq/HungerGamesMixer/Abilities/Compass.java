package mc.lhq.HungerGamesMixer.Abilities;

import java.util.List;

import mc.lhq.HungerGamesMixer.Kit;
import mc.lhq.HungerGamesMixer.PlayerType;
import mc.lhq.HungerGamesMixer.Listeners.GamePlayerListener;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class Compass implements Listener{



	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event){
		if(event.getAction()==Action.LEFT_CLICK_AIR|event.getAction()==Action.LEFT_CLICK_BLOCK|event.getAction()==Action.RIGHT_CLICK_AIR|event.getAction()==Action.RIGHT_CLICK_BLOCK){
			Player p = event.getPlayer();
			if(p.getItemInHand().getType()==Material.COMPASS){
				if(Kit.haveAbility(Ability.getAbility("assassin"), event.getPlayer())){
					return;
				}
				if(Kit.haveAbility(Ability.getAbility("spy"), event.getPlayer())){
					return;
				}
				int i = 0;
				while(300>=i){
					List<Entity> entities = p.getNearbyEntities(i, 64.0D, i);
					int a = 0;
					while(a!=entities.size()){
						if (entities.get(a) instanceof Player){
							if(GamePlayerListener.pltype.get(((Player) entities.get(a)).getName())!=PlayerType.WATCHING){
								Player np = (Player) entities.get(a);
								p.setCompassTarget(entities.get(a).getLocation());
								p.sendMessage(ChatColor.BLUE+"Tracking player "+np.getName());
								return;
							}
						}
						a++;
					}
					i++;
				}
			}
		}
	}

	public static String getdistance(Location loc,Location loc2){
		return String.valueOf(loc.distance(loc2));
	}

}
