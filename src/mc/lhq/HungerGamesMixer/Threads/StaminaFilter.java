package mc.lhq.HungerGamesMixer.Threads;

import java.util.HashMap;
import java.util.List;

import mc.lhq.HungerGamesMixer.Listeners.GamePlayerListener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class StaminaFilter implements Runnable{

	public static HashMap<Player,Boolean> filter = new HashMap<Player,Boolean>();

	public void run() {
		List<String> pl = GamePlayerListener.livingplist;
		int u = 0;
		while(u!=pl.size()){
			if(Bukkit.getPlayer(pl.get(u))!=null){
				Player p = Bukkit.getPlayer(pl.get(u));
				if(filter.get(p)!=null){
					if(filter.get(p)==false){
						int i = p.getFoodLevel();
						p.setFoodLevel(i-1);
					}
				}
				filter.put(p, Boolean.getBoolean("false"));
			}
			u++;
		}
	}

}
