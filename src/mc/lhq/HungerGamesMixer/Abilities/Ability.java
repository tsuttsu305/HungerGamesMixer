package mc.lhq.HungerGamesMixer.Abilities;

import java.util.List;

import mc.lhq.HungerGamesMixer.LoadUtil.LoadFiles;

public class Ability {
	private String abilityname;
	private int Cooldown;
	private int Chance;
	private int Duration;
	private int Distance;
	private int Amount;

	public Ability(String name,int cooldown,int chance,int duration,int distance,int amount){
		this.abilityname = name;
		this.Cooldown = cooldown;
		this.Chance = chance;
		this.Duration = duration;
		this.Distance = distance;
		this.Amount = amount;
	}

	public String getname(){
		return abilityname;
	}

	public int getcooldown(){
		return Cooldown;
	}
	public int getchance(){
		return Chance;
	}
	public int getduration(){
		return Duration;
	}
	public int getdistance(){
		return Distance;
	}
	public int amount(){
		return Amount;
	}
	public static Ability getAbility(String str){
		List<Ability> abl = LoadFiles.abilities;
		int u = 0;
		while(u!=abl.size()){
			if(abl.get(u).getname().equalsIgnoreCase(str)){return abl.get(u);}
			u++;
		}
		return null;
	}
}
