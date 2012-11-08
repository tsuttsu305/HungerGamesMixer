package mc.lhq.HungerGamesMixer.LoadUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import mc.lhq.HungerGamesMixer.Kit;
import mc.lhq.HungerGamesMixer.HungerGamesMixer;
import mc.lhq.HungerGamesMixer.Abilities.Ability;

public class LoadFiles {
	public static List<Ability> abilities = new ArrayList<Ability>();
	public static List<String> abilitiesnames = new ArrayList<String>();
	public static List<Kit> kits = new ArrayList<Kit>();
	public static List<String> kitsnames = new ArrayList<String>();
	public static int defPoints;
	public static int StartSecondWaittimeplayers;
	public static int togglechatdistance;
	public static int GamingLimitDistance;
	public static int LastBattleListmitDistance;
	public static int WaitEndTime;
	public static int GameStartTime;
	public static int SecondWaitTime;
	public static int LastBattleStartTime;
	public static int LastBattleAfterTime;
	public static int EndGamingTime;
	static Logger log = HungerGamesMixer.log;

	public static void loadTimes(){
		File f = new File("plugins/HungerGamesMixer/time.txt");
		checkExists(f);
		try {
			FileReader in = new FileReader(f);
			BufferedReader br = new BufferedReader(in);
			String line;
			while ((line = br.readLine()) != null) {
				if(line.contains("=")){
				String[] strs = line.split("=");
				if(strs[0].equalsIgnoreCase("WaitEndTime")){
					WaitEndTime = getInt(strs[1]);
				}else if(strs[0].equalsIgnoreCase("GameStartTime")){
					GameStartTime = getInt(strs[1]);
				}else if(strs[0].equalsIgnoreCase("SecondWaitTime")){
					SecondWaitTime = getInt(strs[1]);
				}else if(strs[0].equalsIgnoreCase("LastBattleStartTime")){
					LastBattleStartTime = getInt(strs[1]);
				}else if(strs[0].equalsIgnoreCase("LastBattleAfterTime")){
					LastBattleAfterTime = getInt(strs[1]);
				}else if(strs[0].equalsIgnoreCase("EndGamingTime")){
					EndGamingTime = getInt(strs[1]);
				}else{

				}
				}
			}
			LoadMessages.broadcast("loading TimeSettings completed.");
		}catch(IOException e){
			log.warning("times error");
		}
	}
	public static void loadAbilities(){
		abilities.clear();
		abilitiesnames.clear();
		File f = new File("plugins/HungerGamesMixer/Ability.txt");
		checkExists(f);
		try {
			FileReader in = new FileReader(f);
			BufferedReader br = new BufferedReader(in);
			String line;
			while ((line = br.readLine()) != null) {
				int Cooldown = 0;
				int Chance = 0;
				int Duration = 0;
				int Distance = 0;
				int Amount = 0;
				if(line.contains("/")){
					String[] strs = line.split("/");
					String name = strs[0];
					if(!strs[1].equalsIgnoreCase("null")){
						if(strs[1].contains(",")){
							String[] strsss = strs[1].split(",");
							int size = strsss.length;
							int u = 0;
							while(u!=size){
								String[] strs4 = strsss[u].split("=");
								String key = strs4[0];
								if(key.equalsIgnoreCase("cooldown")){
									Cooldown = getInt(strs4[1]);
								}else if(key.equalsIgnoreCase("chance")){
									Chance = getInt(strs4[1]);
								}else if(key.equalsIgnoreCase("duration")){
									Duration = getInt(strs4[1]);
								}else if(key.equalsIgnoreCase("distance")){
									Distance = getInt(strs4[1]);
								}else if(key.equalsIgnoreCase("amount")){
									Amount = getInt(strs4[1]);
								}else{}
								u++;
							}
						}else{
							String[] strs4 = strs[1].split("=");
							String key = strs4[0];
							if(key.equalsIgnoreCase("cooldown")){
								Cooldown = getInt(strs4[1]);
							}else if(key.equalsIgnoreCase("chance")){
								Chance = getInt(strs4[1]);
							}else if(key.equalsIgnoreCase("duration")){
								Duration = getInt(strs4[1]);
							}else if(key.equalsIgnoreCase("distance")){
								Distance = getInt(strs4[1]);
							}else if(key.equalsIgnoreCase("amount")){
								Amount = getInt(strs4[1]);
							}else{}
						}
					}
					Ability a = new Ability(name, Cooldown, Chance, Duration, Distance, Amount);
					abilities.add(a);
					abilitiesnames.add(name);
				}else{
					Ability a = new Ability(line, Cooldown, Chance, Duration, Distance, Amount);
					abilities.add(a);
					abilitiesnames.add(line);
				}
			}
			LoadMessages.broadcast("loading Abilities completed.");
		} catch (IOException e) {
			log.warning("abilities error");
		}
	}

	public static void loadkits(){
		kits.clear();
		kitsnames.clear();
		File f = new File("plugins/HungerGamesMixer/kit.txt");
		checkExists(f);
		try {
			FileReader in = new FileReader(f);
			BufferedReader br = new BufferedReader(in);
			String line;
			while ((line = br.readLine()) != null) {
				if(line.contains("/")){
				String[] strs = line.split("/");
				String name = strs[0];
				ArrayList<ItemStack> items = new ArrayList<ItemStack>();
				ArrayList<Ability> labilities = new ArrayList<Ability>();
				ArrayList<PotionEffect> potioneffects = new ArrayList<PotionEffect>();
				if(!strs[1].equals("null")){
					String[] strs2;
					if(strs[1].contains(",")){
						strs2 = strs[1].split(",");
						int size = strs2.length;
						int u = 0;
						while(u!=size){
							String[] strs3 = strs2[u].split("x");
							if(strs3[0].contains(":")){
								String[] strs4 = strs3[0].split(":");
								Short durability = Short.valueOf(strs4[1]);
								ItemStack is = new ItemStack(getInt(strs4[0]),getInt(strs3[1]),durability);
								items.add(is);
							}else{
								ItemStack is = new ItemStack(getInt(strs3[0]),getInt(strs3[1]));
								items.add(is);
							}
							u++;
						}
					}else{
						strs2 = strs[1].split("x");
						if(strs2[0].contains(":")){
							String[] strs3 = strs2[0].split(":");
							Short durability = Short.valueOf(strs3[1]);
							ItemStack is = new ItemStack(getInt(strs3[0]),getInt(strs2[1]),durability);
							items.add(is);
						}else{
							ItemStack is = new ItemStack(getInt(strs2[0]),getInt(strs2[1]));
							items.add(is);
						}
					}
				}
				if(!strs[2].equals("null")){
					if(strs[2].contains(",")){
						String[] strs2 = strs[2].split(",");
						int size = strs2.length;
						int u = 0;
						while(u!=size){
							if(isAbility(strs2[u])){
								labilities.add(getAbility(strs2[u]));
							}
							u++;
						}
					}else{
						if(isAbility(strs[2])){
							labilities.add(getAbility(strs[2]));
						}
					}
				}
				if(!strs[3].equals("null")){
					if(strs[3].contains(",")){
						String[] strss = strs[3].split(",");
						int size = strss.length;
						int u = 0;
						while(u!=size){
							String[] strs4 = strss[u].split(":");
							PotionEffect pe = new PotionEffect(PotionEffectType.getById(getInt(strs4[0])),getInt(strs4[1]), getInt(strs4[2]));
							potioneffects.add(pe);
							u++;
						}
					}else{
						String[] strs4 = strs[3].split(":");
						PotionEffect pe = new PotionEffect(PotionEffectType.getById(getInt(strs4[0])),getInt(strs4[1]), getInt(strs4[2]));
						potioneffects.add(pe);
					}
				}
				Integer point = getInt(strs[4]);
				Kit k = new Kit(name,labilities,items,potioneffects,point);
				kits.add(k);
				kitsnames.add(name);
				}
			}
			LoadMessages.broadcast("loading Kits completed.");
		} catch (IOException e) {
			log.warning("Kits error");
		}
	}

	public static void loadSettings(){
		File f = new File("plugins/HungerGamesMixer/config.txt");
		checkExists(f);
		try {
			FileReader in = new FileReader(f);
			BufferedReader br = new BufferedReader(in);
			String line;
			while ((line = br.readLine()) != null) {
				if(line.contains("=")){
				String[] strs = line.split("=");
				if(strs[0].equalsIgnoreCase("defPoints")){
					defPoints = getInt(strs[1]);
				}else if(strs[0].equalsIgnoreCase("StartSecondWaittimeplayers")){
					StartSecondWaittimeplayers = getInt(strs[1]);
				}else if(strs[0].equalsIgnoreCase("togglechatdistance")){
					togglechatdistance = getInt(strs[1]);
				}else if(strs[0].equalsIgnoreCase("GamingLimitDistance")){
					GamingLimitDistance = getInt(strs[1]);
				}else if(strs[0].equalsIgnoreCase("LastBattleListmitDistance")){
					LastBattleListmitDistance = getInt(strs[1]);
				}else{
				}
				}
			}
			LoadMessages.broadcast("loading config completed.");
		}catch(IOException e){
			log.warning("config error");
		}
	}

	public static int getInt(String str){
		if(isInt(str)){
			return Integer.parseInt(str);
		}else{
			return 0;
		}
	}
	public static boolean isInt(String str){
		try{
			Integer.parseInt(str);
			return true;
		}catch(NumberFormatException e){
			return false;
		}
	}

	public static Ability getAbility(String name){
		int size = abilities.size();
		int u = 0;
		while(u!=size){
			if(name.equals(abilities.get(u).getname())){
				return abilities.get(u);
			}
			u++;
		}
		return null;
	}
	public static boolean isAbility(String name){
		int size = abilities.size();
		int u = 0;
		while(u!=size){
			if(name.equals(abilities.get(u).getname())){
				return true;
			}
			u++;
		}
		return false;
	}

	public static boolean checkExists(File f){
		if(!f.exists()){
			try {
				f.createNewFile();
			} catch (IOException e) {
				return false;
			}
		}
		return true;
	}
}
