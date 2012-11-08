package mc.lhq.HungerGamesMixer.Threads;

import java.util.Random;

import mc.lhq.HungerGamesMixer.PlayerType;
import mc.lhq.HungerGamesMixer.HungerGamesMixer;
import mc.lhq.HungerGamesMixer.Listeners.GamePlayerListener;
import mc.lhq.HungerGamesMixer.LoadUtil.KitUtil;
import mc.lhq.HungerGamesMixer.LoadUtil.LoadFiles;
import mc.lhq.HungerGamesMixer.LoadUtil.LoadMessages;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class GameTimer implements Runnable{
	public Plugin plugin;
	public static int time;
	public int WaitEndTime;
	public int GameStartTime;
	public int SecondWaitTime;
	public int LastBattleStartTime;
	public int LastBattleAfterTime;
	public int EndGamingTime;

	public boolean Endwaitendtime;
	public boolean Endgamestarttime;
	public boolean Endsecondwaittime;
	public boolean Endlastbattlestarttime;
	public boolean Endlastbattleaftertime;
	public boolean Endgamingtime;
	public boolean win = false;

	static String prefix;

	public String GameBeforeWaitingInfo;
	public String GameBeforeInfo;
	public String SecondWaitBeforeInfo;
	public String LastBattleBeforeInfo;
	public String RespawnTimeInfo;
	public String EndGamingInfo;

	public GameTimer(Plugin plugin){
		SetPlayersType(PlayerType.WAITING, true, true);
		this.WaitEndTime=LoadFiles.WaitEndTime;
		this.GameStartTime=LoadFiles.GameStartTime;
		this.SecondWaitTime=LoadFiles.SecondWaitTime;
		this.LastBattleStartTime=LoadFiles.LastBattleStartTime;
		this.LastBattleAfterTime=LoadFiles.LastBattleAfterTime;
		this.EndGamingTime=LoadFiles.EndGamingTime;
		this.Endwaitendtime = true;
		this.GameBeforeWaitingInfo = LoadMessages.GameBeforeWaitingInfo;
		this.GameBeforeInfo = LoadMessages.GameBeforeInfo;
		this.SecondWaitBeforeInfo = LoadMessages.SecondWaitBeforeInfo;
		this.LastBattleBeforeInfo = LoadMessages.LastBattleBeforeInfo;
		this.RespawnTimeInfo = LoadMessages.RespawnTimeInfo;
		this.EndGamingInfo = LoadMessages.EndGamingInfo;
		GameTimer.prefix = LoadMessages.prefix;
	}
	public void run() {
		time++;
		if(Endwaitendtime){
		if(time==WaitEndTime-600){
			LoadMessages.broadcast((GameBeforeWaitingInfo).replaceAll("<TIME>", "10分"));
		}
		if(time==WaitEndTime-540){
			LoadMessages.broadcast((GameBeforeWaitingInfo).replaceAll("<TIME>", "9分"));
		}
		if(time==WaitEndTime-480){
			LoadMessages.broadcast((GameBeforeWaitingInfo).replaceAll("<TIME>", "8分"));
		}
		if(time==WaitEndTime-420){
			LoadMessages.broadcast((GameBeforeWaitingInfo).replaceAll("<TIME>", "7分"));
		}
		if(time==WaitEndTime-360){
			LoadMessages.broadcast((GameBeforeWaitingInfo).replaceAll("<TIME>", "6分"));
		}
		if(time==WaitEndTime-300){
			LoadMessages.broadcast((GameBeforeWaitingInfo).replaceAll("<TIME>", "5分"));
		}
		if(time==WaitEndTime-240){
			LoadMessages.broadcast((GameBeforeWaitingInfo).replaceAll("<TIME>", "4分"));
		}
		if(time==WaitEndTime-180){
			LoadMessages.broadcast((GameBeforeWaitingInfo).replaceAll("<TIME>", "3分"));
		}
		if(time==WaitEndTime-120){
			LoadMessages.broadcast((GameBeforeWaitingInfo).replaceAll("<TIME>", "2分"));
		}
		if(time==WaitEndTime-60){
			LoadMessages.broadcast((GameBeforeWaitingInfo).replaceAll("<TIME>", "1分"));
		}
		if(time==WaitEndTime-50){
			LoadMessages.broadcast((GameBeforeWaitingInfo).replaceAll("<TIME>", "50秒"));
		}
		if(time==WaitEndTime-40){
			LoadMessages.broadcast((GameBeforeWaitingInfo).replaceAll("<TIME>", "40秒"));
		}
		if(time==WaitEndTime-30){
			LoadMessages.broadcast((GameBeforeWaitingInfo).replaceAll("<TIME>", "30秒"));
		}
		if(time==WaitEndTime-20){
			LoadMessages.broadcast((GameBeforeWaitingInfo).replaceAll("<TIME>", "20秒"));
		}
		if(time==WaitEndTime-10){
			LoadMessages.broadcast((GameBeforeWaitingInfo).replaceAll("<TIME>", "10秒"));
		}
		if(time==LoadFiles.WaitEndTime){
			SetPlayersType(PlayerType.BEFOREGAMING,true,false);
			LoadMessages.broadcast((LoadMessages.GameBegin));
			LoadMessages.broadcast((LoadMessages.StartSecondWait));
			Player[] pl = HungerGamesMixer.server.getOnlinePlayers();
			int size = pl.length;
			int u = 0;
			ItemStack is = new ItemStack(Material.COMPASS, 1);
			while(u!=size){
				pl[u].getInventory().addItem(is);
				KitUtil.setKit(pl[u]);
				Random r = new Random();
				Location startFrom = getSpawn();
				Location loc = startFrom.clone();
				loc.add((r.nextBoolean() ? 1 : -1) * r.nextInt(6), 60, (r.nextBoolean() ? 1 : -1) * r.nextInt(6));
				loc.setY(HungerGamesMixer.GamingWorld.getHighestBlockYAt(loc) + 1.5);
				HungerGamesMixer.GamingWorld.loadChunk(loc.getChunk());
				pl[u].teleport(loc);
				u++;
			}
			this.Endwaitendtime = false;
			this.Endgamestarttime = true;
		}
		}else if(Endgamestarttime){
			if(time==GameStartTime-600){
				LoadMessages.broadcast((GameBeforeInfo.replaceAll("<TIME>", "10分")));
			}
			if(time==GameStartTime-540){
				LoadMessages.broadcast((GameBeforeInfo.replaceAll("<TIME>", "9分")));
			}
			if(time==GameStartTime-480){
				LoadMessages.broadcast((GameBeforeInfo.replaceAll("<TIME>", "8分")));
			}
			if(time==GameStartTime-420){
				LoadMessages.broadcast((GameBeforeInfo.replaceAll("<TIME>", "7分")));
			}
			if(time==GameStartTime-360){
				LoadMessages.broadcast((GameBeforeInfo.replaceAll("<TIME>", "6分")));
			}
			if(time==GameStartTime-300){
				LoadMessages.broadcast((GameBeforeInfo.replaceAll("<TIME>", "5分")));
			}
			if(time==GameStartTime-240){
				LoadMessages.broadcast((GameBeforeInfo.replaceAll("<TIME>", "4分")));
			}
			if(time==GameStartTime-180){
				LoadMessages.broadcast((GameBeforeInfo.replaceAll("<TIME>", "3分")));
			}
			if(time==GameStartTime-120){
				LoadMessages.broadcast((GameBeforeInfo.replaceAll("<TIME>", "2分")));
			}
			if(time==GameStartTime-60){
				LoadMessages.broadcast((GameBeforeInfo.replaceAll("<TIME>", "1分")));
			}
			if(time==GameStartTime-50){
				LoadMessages.broadcast((GameBeforeInfo.replaceAll("<TIME>", "50秒")));
			}
			if(time==GameStartTime-40){
				LoadMessages.broadcast((GameBeforeInfo.replaceAll("<TIME>", "40秒")));
			}
			if(time==GameStartTime-30){
				LoadMessages.broadcast((GameBeforeInfo.replaceAll("<TIME>", "30秒")));
			}
			if(time==GameStartTime-20){
				LoadMessages.broadcast((GameBeforeInfo.replaceAll("<TIME>", "20秒")));
			}
			if(time==GameStartTime-10){
				LoadMessages.broadcast((GameBeforeInfo.replaceAll("<TIME>", "10秒")));
			}
			if(time==GameStartTime){
				SetPlayersType(PlayerType.GAMING,false,false);
				LoadMessages.broadcast((LoadMessages.BeforeGamingEnd));
				this.Endgamestarttime = false;
				this.Endsecondwaittime = true;
			}
		}else if(Endsecondwaittime){
			if(time==SecondWaitTime-180){
				LoadMessages.broadcast((SecondWaitBeforeInfo.replaceAll("<TIME>", "3分")));
			}
			if(time==SecondWaitTime-120){
				LoadMessages.broadcast((SecondWaitBeforeInfo.replaceAll("<TIME>", "2分")));
			}
			if(time==SecondWaitTime-60){
				LoadMessages.broadcast((SecondWaitBeforeInfo.replaceAll("<TIME>", "1分")));
			}
			if(time==SecondWaitTime-50){
				LoadMessages.broadcast((SecondWaitBeforeInfo.replaceAll("<TIME>", "50秒")));
			}
			if(time==SecondWaitTime-40){
				LoadMessages.broadcast((SecondWaitBeforeInfo.replaceAll("<TIME>", "40秒")));
			}
			if(time==SecondWaitTime-30){
				LoadMessages.broadcast((SecondWaitBeforeInfo.replaceAll("<TIME>", "30秒")));
			}
			if(time==SecondWaitTime-20){
				LoadMessages.broadcast((SecondWaitBeforeInfo.replaceAll("<TIME>", "20秒")));
			}
			if(time==SecondWaitTime-10){
				LoadMessages.broadcast((SecondWaitBeforeInfo.replaceAll("<TIME>", "10秒")));
			}
			if(time==SecondWaitTime){
				SetPlayersType(PlayerType.SECONDWAIT,false,false);
				LoadMessages.broadcast((LoadMessages.StartSecondWait));
				this.Endsecondwaittime = false;
				this.Endlastbattlestarttime = true;
			}
			if(LoadFiles.StartSecondWaittimeplayers>GamePlayerListener.livingplist.size()){
				time=SecondWaitTime;
				SetPlayersType(PlayerType.SECONDWAIT,false,false);
				LoadMessages.broadcast((LoadMessages.StartSecondWaitSP));
				this.Endsecondwaittime = false;
				this.Endlastbattlestarttime = true;
			}
		}else if(Endlastbattlestarttime){
			if(time==LastBattleStartTime-60){
				LoadMessages.broadcast((LastBattleBeforeInfo.replaceAll("<TIME>", "1分")));
			}
			if(time==LastBattleStartTime-50){
				LoadMessages.broadcast((LastBattleBeforeInfo.replaceAll("<TIME>", "50秒")));
			}
			if(time==LastBattleStartTime-40){
				LoadMessages.broadcast((LastBattleBeforeInfo.replaceAll("<TIME>", "40秒")));
			}
			if(time==LastBattleStartTime-30){
				LoadMessages.broadcast((LastBattleBeforeInfo.replaceAll("<TIME>", "30秒")));
			}
			if(time==LastBattleStartTime-20){
				LoadMessages.broadcast((LastBattleBeforeInfo.replaceAll("<TIME>", "20秒")));
			}
			if(time==LastBattleStartTime-10){
				LoadMessages.broadcast((LastBattleBeforeInfo.replaceAll("<TIME>", "10秒")));
			}
			if(time==LastBattleStartTime){
				SetPlayersType(PlayerType.LASTBATTLERESP,false,false);
				LoadMessages.broadcast((LoadMessages.StartLastBattle));
				Player[] pl = HungerGamesMixer.server.getOnlinePlayers();
				int size = pl.length;
				int u = 0;
				while(u!=size){
					Random r = new Random();
					Location startFrom = getSpawn();
					Location loc = startFrom.clone();
					loc.add((r.nextBoolean() ? 1 : -1) * r.nextInt(6), 60, (r.nextBoolean() ? 1 : -1) * r.nextInt(6));
					loc.setY(HungerGamesMixer.GamingWorld.getHighestBlockYAt(loc) + 1.5);
					HungerGamesMixer.GamingWorld.loadChunk(loc.getChunk());
					pl[u].teleport(loc);
					u++;
				}
				HungerGamesMixer.GamingWorld.strikeLightning(HungerGamesMixer.GamingWorld.getSpawnLocation().add(0.0D, 100.0D, 0.0D));
				this.Endlastbattlestarttime = false;
				this.Endlastbattleaftertime = true;
			}
		}else if(Endlastbattleaftertime){
			if(time==LastBattleAfterTime-10){
				LoadMessages.broadcast((RespawnTimeInfo.replaceAll("<TIME>", "10秒")));
			}
			if(time==LastBattleAfterTime-9){
				LoadMessages.broadcast((RespawnTimeInfo.replaceAll("<TIME>", "9秒")));
			}
			if(time==LastBattleAfterTime-8){
				LoadMessages.broadcast((RespawnTimeInfo.replaceAll("<TIME>", "8秒")));
			}
			if(time==LastBattleAfterTime-7){
				LoadMessages.broadcast((RespawnTimeInfo.replaceAll("<TIME>", "7秒")));
			}
			if(time==LastBattleAfterTime-6){
				LoadMessages.broadcast((RespawnTimeInfo.replaceAll("<TIME>", "6秒")));
			}
			if(time==LastBattleAfterTime-5){
				LoadMessages.broadcast((RespawnTimeInfo.replaceAll("<TIME>", "5秒")));
			}
			if(time==LastBattleAfterTime-4){
				LoadMessages.broadcast((RespawnTimeInfo.replaceAll("<TIME>", "4秒")));
			}
			if(time==LastBattleAfterTime-3){
				LoadMessages.broadcast((RespawnTimeInfo.replaceAll("<TIME>", "3秒")));
			}
			if(time==LastBattleAfterTime-2){
				LoadMessages.broadcast((RespawnTimeInfo.replaceAll("<TIME>", "2秒")));
			}
			if(time==LastBattleAfterTime-1){
				LoadMessages.broadcast((RespawnTimeInfo.replaceAll("<TIME>", "1秒")));
			}
			if(time==LastBattleAfterTime){
				SetPlayersType(PlayerType.LASTBATTLE,false,false);
				LoadMessages.broadcast((LoadMessages.BeforeGamingEnd));
				this.Endlastbattleaftertime = false;
				this.Endgamingtime = true;
			}
		}else if(Endgamingtime){
			if(!win&GamePlayerListener.livingplist.size()==1){
				LoadMessages.broadcast(LoadMessages.GamingWinnerInfo.replaceAll("<WINNER>", GamePlayerListener.livingplist.get(0)));
				time = EndGamingTime-21;
				win = true;
			}
			if(time==EndGamingTime-180){
				LoadMessages.broadcast((EndGamingInfo.replaceAll("<TIME>", "3分")));
			}
			if(time==EndGamingTime-120){
				LoadMessages.broadcast((EndGamingInfo.replaceAll("<TIME>", "2分")));
			}
			if(time==EndGamingTime-60){
				LoadMessages.broadcast((EndGamingInfo.replaceAll("<TIME>", "1分")));
			}
			if(time==EndGamingTime-50){
				LoadMessages.broadcast((EndGamingInfo.replaceAll("<TIME>", "50秒")));
			}
			if(time==EndGamingTime-40){
				LoadMessages.broadcast((EndGamingInfo.replaceAll("<TIME>", "40秒")));
			}
			if(time==EndGamingTime-30){
				LoadMessages.broadcast((EndGamingInfo.replaceAll("<TIME>", "30秒")));
			}
			if(time==EndGamingTime-20){
				LoadMessages.broadcast((EndGamingInfo.replaceAll("<TIME>", "20秒")));
			}
			if(time==EndGamingTime-10){
				LoadMessages.broadcast((EndGamingInfo.replaceAll("<TIME>", "10秒")));
			}
			if(time==EndGamingTime){
				HungerGamesMixer.server.shutdown();
			}
		}else{

		}
	}
	public Location getSpawn() {
		Location loc = HungerGamesMixer.GamingWorld.getSpawnLocation();
		loc.setY(HungerGamesMixer.GamingWorld.getHighestBlockYAt(HungerGamesMixer.GamingWorld.getSpawnLocation()) + 1.5);
		return loc;
	}

	public static void SetPlayersType(PlayerType pt,boolean statesreset,boolean pointreset){
		HungerGamesMixer.serverstate = pt;
		Player[] pl = HungerGamesMixer.server.getOnlinePlayers();
		int u = 0;
		while(u!=pl.length){
			Player p = pl[u];
			if(GamePlayerListener.pltype.get(p.getName())!=PlayerType.WATCHING){
			if(statesreset){
			p.setHealth(20);
			p.setFoodLevel(20);
			p.setExp(0);
			}
			if(pointreset){
			GamePlayerListener.setDefPoint(p);
			}
			GamePlayerListener.pltype.put(p.getName(), HungerGamesMixer.serverstate);
			}else{
			}
			u++;
		}
	}
}
