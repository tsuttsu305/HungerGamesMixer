package mc.lhq.TheBukkitGames.LoadUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Server;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import mc.lhq.TheBukkitGames.PlayerType;
import mc.lhq.TheBukkitGames.TheBukkitGames;
import mc.lhq.TheBukkitGames.Listeners.GamePlayerListener;

public class LoadMessages {
	static Server server = TheBukkitGames.server;
	public static String prefix;
	public static String WatchingPrefix;
	public static String WatchingPlayer;
	public static String togglechatPrefix;
	public static String frontkitname;
	public static String rearkitname;
	public static String kitnamesplit;
	public static String splistChatMsg;
	public static String PlayerDeathEvent;
	public static String PlayerKillEvent;
	public static String GOLDEN_SWORD;
	public static String WOOD_SWORD;
	public static String STONE_SWORD;
	public static String IRON_SWORD;
	public static String DIAMOND_SWORD;
	public static String EMPTYHAND;
	public static String PointGetting;
	public static String KickMessage;
	public static String GameBeforeWaitingInfo;
	public static String GameBeforeInfo;
	public static String SecondWaitBeforeInfo;
	public static String LastBattleBeforeInfo;
	public static String RespawnTimeInfo;
	public static String EndGamingInfo;
	public static String GameBegin;
	public static String BeforeGamingEnd;
	public static String StartSecondWait;
	public static String StartSecondWaitSP;
	public static String StartLastBattle;
	public static String GamingWinnerInfo;
	public static String PlayerDeathInfo;
	public static String commandpointname;

	public static void loadMessages(){
		File f = new File("plugins/TheBukkitGames/messages.txt");
		LoadFiles.checkExists(f);
		try {
			FileReader in = new FileReader(f);
			BufferedReader br = new BufferedReader(in);
			String line;
			while ((line = br.readLine()) != null) {
				if(line.contains("=")){
				String[] strs = line.split("=");
				if(strs[0].equalsIgnoreCase("prefix")){
					prefix = strs[1];
				}else if(strs[0].equalsIgnoreCase("WatchingPrefix")){
					WatchingPrefix = strs[1];
				}else if(strs[0].equalsIgnoreCase("WatchingPlayer")){
					WatchingPlayer = strs[1];
				}else if(strs[0].equalsIgnoreCase("togglechatPrefix")){
					togglechatPrefix = strs[1];
				}else if(strs[0].equalsIgnoreCase("frontkitname")){
					frontkitname = strs[1];
				}else if(strs[0].equalsIgnoreCase("rearkitname")){
					rearkitname = strs[1];
				}else if(strs[0].equalsIgnoreCase("kitnamesplit")){
					kitnamesplit = strs[1];
				}else if(strs[0].equalsIgnoreCase("splistChatMsg")){
					splistChatMsg = strs[1];
				}else if(strs[0].equalsIgnoreCase("PlayerDeathEvent")){
					PlayerDeathEvent = strs[1];
				}else if(strs[0].equalsIgnoreCase("PlayerKillEvent")){
					PlayerKillEvent = strs[1];
				}else if(strs[0].equalsIgnoreCase("GOLDEN_SWORD")){
					GOLDEN_SWORD = strs[1];
				}else if(strs[0].equalsIgnoreCase("WOOD_SWORD")){
					WOOD_SWORD = strs[1];
				}else if(strs[0].equalsIgnoreCase("STONE_SWORD")){
					STONE_SWORD = strs[1];
				}else if(strs[0].equalsIgnoreCase("IRON_SWORD")){
					IRON_SWORD = strs[1];
				}else if(strs[0].equalsIgnoreCase("DIAMOND_SWORD")){
					DIAMOND_SWORD = strs[1];
				}else if(strs[0].equalsIgnoreCase("EMPTYHAND")){
					EMPTYHAND = strs[1];
				}else if(strs[0].equalsIgnoreCase("PointGetting")){
					PointGetting = strs[1];
				}else if(strs[0].equalsIgnoreCase("KickMessage")){
					KickMessage = strs[1];
				}else if(strs[0].equalsIgnoreCase("GameBeforeWaitingInfo")){
					GameBeforeWaitingInfo = strs[1];
				}else if(strs[0].equalsIgnoreCase("GameBeforeInfo")){
					GameBeforeInfo = strs[1];
				}else if(strs[0].equalsIgnoreCase("SecondWaitBeforeInfo")){
					SecondWaitBeforeInfo = strs[1];
				}else if(strs[0].equalsIgnoreCase("LastBattleBeforeInfo")){
					LastBattleBeforeInfo = strs[1];
				}else if(strs[0].equalsIgnoreCase("RespawnTimeInfo")){
					RespawnTimeInfo = strs[1];
				}else if(strs[0].equalsIgnoreCase("EndGamingInfo")){
					EndGamingInfo = strs[1];
				}else if(strs[0].equalsIgnoreCase("GameBegin")){
					GameBegin = strs[1];
				}else if(strs[0].equalsIgnoreCase("BeforeGamingEnd")){
					BeforeGamingEnd = strs[1];
				}else if(strs[0].equalsIgnoreCase("StartSecondWait")){
					StartSecondWait = strs[1];
				}else if(strs[0].equalsIgnoreCase("StartSecondWaitSP")){
					StartSecondWaitSP = strs[1];
				}else if(strs[0].equalsIgnoreCase("StartLastBattle")){
					StartLastBattle = strs[1];
				}else if(strs[0].equalsIgnoreCase("GamingWinnerInfo")){
					GamingWinnerInfo = strs[1];
				}else if(strs[0].equalsIgnoreCase("PlayerDeathInfo")){
					PlayerDeathInfo = strs[1];
				}else if(strs[0].equalsIgnoreCase("commandpointname")){
					commandpointname = strs[1];
				}

				}
			}
			LoadMessages.broadcast("loading TimeSettings completed.");
		}catch(IOException e){
			TheBukkitGames.log.warning("times error");
		}
	}

	public static void chatbroadcast(String str){
		server.broadcastMessage(str.replaceAll("\\$", "\247"));
	}
	public static void togglechatbroadcast(Player p,String str){
		List<Player> plist = new ArrayList<Player>();
		plist.add(p);
		p.sendMessage(str.replaceAll("\\$", "\247"));
		int u = 0;
		int radi = LoadFiles.togglechatdistance;
		while(100>=u){
			List<Entity> ens = p.getNearbyEntities(u,64.0D, u);
			int a = 0;
			while(a!=ens.size()){
				if(ens.get(a) instanceof Player){
					Player pa = (Player) ens.get(a);
					if(!plist.contains(pa)){
						if(GamePlayerListener.pltype.get(pa.getName())!=PlayerType.WATCHING){
							if(radi>=p.getLocation().distance(pa.getLocation())){
								plist.add(pa);
								pa.sendMessage(str.replaceAll("\\$", "\247"));
							}
						}
					}
				}
				a++;
			}
			u++;
		}
	}
	public static void watchingbroadcast(String str){
		Player[] pl = server.getOnlinePlayers();
		int u = 0;
		while(u!=pl.length){
		if(GamePlayerListener.pltype.get(pl[u].getName())==PlayerType.WATCHING){
		pl[u].sendMessage(str.replaceAll("\\$", "\247"));
		}
		u++;
		}
	}
	public static void broadcast(String str){
		server.broadcastMessage((prefix+str).replaceAll("\\$", "\247"));
	}
	public static void send(Player p , String str){
		p.sendMessage((prefix+str).replaceAll("\\$", "\247"));
	}
	public static String addPrefix(String str){
		return prefix+str;
	}

	public static void deathInfo(String last) {
		server.broadcastMessage((last.replaceAll("\\$", "\247")));
		server.broadcastMessage(PlayerDeathInfo.replaceAll("<PLAYER_INT>", String.valueOf(GamePlayerListener.livingplist.size())));
	}

}
