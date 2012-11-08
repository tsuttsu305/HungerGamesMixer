package mc.lhq.HungerGamesMixer;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import mc.lhq.HungerGamesMixer.Abilities.Ability;
import mc.lhq.HungerGamesMixer.Listeners.GamePlayerListener;
import mc.lhq.HungerGamesMixer.LoadUtil.LoadFiles;

public class Kit {
	private String Kitname;
	private List<Ability> ability;
	private List<ItemStack> items;
	private List<PotionEffect> potioneffects;
	private Integer point;


	public Kit(String name,ArrayList<Ability> ability,ArrayList<ItemStack> items,ArrayList<PotionEffect> potioneffects,Integer point){
		this.Kitname = name;
		this.ability = ability;
		this.items = items;
		this.potioneffects = potioneffects;
		this.point = point;
	}

	public String getname(){
		return Kitname;
	}
	public List<Ability> getability(){
		return ability;
	}
	public List<ItemStack> getitems(){
		return items;
	}
	public List<PotionEffect> getpotioneffects(){
		return potioneffects;
	}
	public Integer getpoint(){
		return point;
	}

	public static Kit getKit(String str){
		List<Kit> list = LoadFiles.kits;
		int u = 0;
		while(u!=list.size()){
			if(list.get(u).getname().equalsIgnoreCase(str)){
				return list.get(u);
			}
			u++;
		}
		return null;
	}

	public static boolean haveKit(Kit kit,Player p){
		if(GamePlayerListener.plkit.get(p.getName())!=null){
			return GamePlayerListener.plkit.get(p.getName()).contains(kit);
		}
		return false;
	}

	public static boolean haveAbility(Ability ability , Player p){
		if(ability!=null){
		if(GamePlayerListener.plkit.get(p.getName())!=null){
			List<Kit> kits = GamePlayerListener.plkit.get(p.getName());
			int u = 0;
			while(u!=kits.size()){
				if(kits.get(u).getability()!=null){
					if(kits.get(u).getability().contains(ability)){
						return true;
					}
				}
				u++;
			}
		return false;
		}else{
			return false;
		}
		}else{
			return false;
		}
	}

}
