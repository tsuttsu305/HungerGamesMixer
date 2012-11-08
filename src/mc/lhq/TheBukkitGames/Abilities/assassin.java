package mc.lhq.TheBukkitGames.Abilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import mc.lhq.TheBukkitGames.Kit;
import mc.lhq.TheBukkitGames.TheBukkitGames;
import mc.lhq.TheBukkitGames.Listeners.GamePlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;

public class assassin implements Listener {

	HashMap<Player,List<Player>> locatp = new HashMap<Player,List<Player>>();
	HashMap<Player, Player> locat = new HashMap<Player,Player>();

	public assassin(Plugin plg){
		TheBukkitGames.server.getScheduler().scheduleAsyncRepeatingTask(plg, new Runnable(){
			public void run() {
				int u = 0;
				while(u!=GamePlayerListener.livingplist.size()){
					if(Bukkit.getPlayer(GamePlayerListener.livingplist.get(u))!=null){
						Player p = Bukkit.getPlayer(GamePlayerListener.livingplist.get(u));
						if(locat.get(p)!=null){
							p.setCompassTarget(locat.get(p).getLocation());
						}
					}
					u++;
				}
			}
		}, 60L, 60L);
	}
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event){
		if(event.getAction()==Action.LEFT_CLICK_AIR|event.getAction()==Action.LEFT_CLICK_BLOCK|event.getAction()==Action.RIGHT_CLICK_AIR|event.getAction()==Action.RIGHT_CLICK_BLOCK){
			Player p = event.getPlayer();
			if(p.getItemInHand().getType()==Material.COMPASS){
					if(Kit.haveAbility(Ability.getAbility("assassin"), event.getPlayer())){
						if(locatp.get(p)!=null){
						}else{
							locatp.put(p, getList(GamePlayerListener.livingplist));
						}
							List<Player> list = locatp.get(p);
							int u = 0;
								while(u!=list.size()){
									if(list.get(u)!=null){
										Player np = list.get(u);
										if(GamePlayerListener.livingplist.contains(list.get(u).getName())){
											if(!list.get(u).equals(p)){
											p.setCompassTarget(np.getLocation());
											p.sendMessage(ChatColor.BLUE+"Tracking player "+np.getName());
											locat.put(p, np);
											list.remove(np);
											if(list.size()==0){
												locatp.put(p, null);
											}else{
												locatp.put(p, list);
											}
											return;
											}
										}
									}
									list.remove(list.get(u));
									u++;
								}
								locatp.put(p, null);
								return;
					}
			}
		}
	}
	public static List<Player> getList(List<String> livingplist) {
		List<Player> lp = new ArrayList<Player>();
		int u = 0;
		while(u!=livingplist.size()){
			lp.add(Bukkit.getPlayer(livingplist.get(u)));
			u++;
		}
		return lp;
	}


}
