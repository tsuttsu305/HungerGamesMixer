package mc.lhq.TheBukkitGames.LoadUtil;

import java.util.HashMap;
import java.util.List;

import mc.lhq.TheBukkitGames.Kit;
import mc.lhq.TheBukkitGames.Abilities.ghost;
import mc.lhq.TheBukkitGames.Listeners.GamePlayerListener;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

public class KitUtil {

	public static void setKit(Player p){
		HashMap<String,List<Kit>> hm = GamePlayerListener.plkit;
		ghost.sethide();
		if(hm.get(p.getName())!=null){

		List<Kit> kl = hm.get(p.getName());
		int u = 0;
		while(u!=kl.size()){
			Kit k = kl.get(u);
			List<ItemStack> isl = k.getitems();
			int e = 0;
			while(e!=isl.size()){
				p.getInventory().addItem(isl.get(e));
				e++;
			}
			List<PotionEffect> pel = k.getpotioneffects();
			int a = 0;
			while(a!=pel.size()){
				p.addPotionEffect(pel.get(a));
				a++;
			}
			u++;
		}
		}
	}
	public static void setKit(Player p,Kit k){
		ghost.sethide();
		List<ItemStack> isl = k.getitems();
		int e = 0;
		while(e!=isl.size()){
			p.getInventory().addItem(isl.get(e));
			e++;
		}
		List<PotionEffect> pel = k.getpotioneffects();
		int a = 0;
		while(a!=pel.size()){
			p.addPotionEffect(pel.get(a));
			a++;
		}
	}
}
