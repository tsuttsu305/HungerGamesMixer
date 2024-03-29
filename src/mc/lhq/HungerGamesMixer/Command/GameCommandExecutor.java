package mc.lhq.HungerGamesMixer.Command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mc.lhq.HungerGamesMixer.Kit;
import mc.lhq.HungerGamesMixer.PlayerType;
import mc.lhq.HungerGamesMixer.HungerGamesMixer;
import mc.lhq.HungerGamesMixer.Listeners.GamePlayerListener;
import mc.lhq.HungerGamesMixer.LoadUtil.KitUtil;
import mc.lhq.HungerGamesMixer.LoadUtil.LoadFiles;
import mc.lhq.HungerGamesMixer.LoadUtil.LoadMessages;
import mc.lhq.HungerGamesMixer.Threads.GameTimer;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GameCommandExecutor implements CommandExecutor {

	public static HashMap<Player,Boolean> ptoggle = new HashMap<Player,Boolean>();

	public boolean onCommand(CommandSender sender, Command cmd, String arg2,String[] arg3) {
		if(sender instanceof Player){
			Player p = (Player) sender;
			if(cmd.getName().equalsIgnoreCase("kit")){
				if(GamePlayerListener.pltype.get(p.getName())==PlayerType.WATCHING){
					p.sendMessage(ChatColor.RED+"あなたは現在ゲームを観戦しています");
					return true;
				}
				if(arg3.length==0){
						int points = GamePlayerListener.points.get(p.getName());
						List<String> list = LoadFiles.abilitiesnames;
						StringBuffer sb = new StringBuffer();
						int u = 0;
						while(u!=list.size()){
							Kit k = Kit.getKit(list.get(u));
							if(k!=null){
								if(!Kit.haveKit(k, p)){
								if(u!=0){
									sb.append(ChatColor.WHITE+",");
								}
								int needpoint = k.getpoint();
									if(points>=needpoint){
										sb.append(ChatColor.AQUA+k.getname()+"("+String.valueOf(needpoint)+")");
									}else{
										sb.append(ChatColor.RED+k.getname()+"("+String.valueOf(needpoint)+")");
									}
								}
							}
							u++;
						}
						p.sendMessage(sb.toString());
						p.sendMessage(ChatColor.AQUA+"あなたは現在"+String.valueOf(points)+LoadMessages.commandpointname+"所持しています");
						return true;
				}else if(arg3.length==1){
					String kitname = arg3[0];
					Kit k = Kit.getKit(kitname);
					if(k!=null){
						List<Kit> klist;
						if(GamePlayerListener.plkit.get(p.getName())!=null){
							klist = GamePlayerListener.plkit.get(p.getName());
							if(klist.contains(k)){
								p.sendMessage(ChatColor.RED+"あなたは既にそのkitを所持しています!");
								return true;
							}
						}else{
							klist = new ArrayList<Kit>();
						}
						int points = GamePlayerListener.points.get(p.getName());
						int needpoint = k.getpoint();
						int setpoint = points-needpoint;
						if(setpoint>=0){
							ItemStack[] con = p.getInventory().getContents();
							int u = 0;
							int emptyslot = 0;
							while(u!=con.length){
								if(con[u]==null){
									emptyslot++;
								}
								u++;
							}
							if(emptyslot>=k.getitems().size()){
							GamePlayerListener.points.put(p.getName(), setpoint);
							klist.add(k);
							GamePlayerListener.plkit.put(p.getName(), klist);
							p.sendMessage(ChatColor.AQUA+k.getname()+"を購入しました!");
							if(GamePlayerListener.pltype.get(p.getName())!=PlayerType.WAITING){
								KitUtil.setKit(p, k);
							}
							return true;
						}else{
							p.sendMessage(ChatColor.RED+"そのkitを購入するにはインベントリーに空きを作る必要があります!");
							return true;
						}
						}else{
							p.sendMessage(ChatColor.RED+"そのkitを購入するには"+LoadMessages.commandpointname+"が足りません!");
							return true;
						}
					}else{
						p.sendMessage(ChatColor.RED+"そのkitは存在しません!");
						return true;
					}
				}else{
					return false;
				}
			}else if(cmd.getName().equalsIgnoreCase("stats")){
				if(arg3.length==0){
					if(GamePlayerListener.plkit.get(p.getName())!=null){
						List<Kit> kits = GamePlayerListener.plkit.get(p.getName());
						StringBuffer sb = new StringBuffer();
						int u = 0;
						while(u!=kits.size()){
							if(u!=0){
								sb.append(",");
							}
							sb.append(kits.get(u).getname().replaceAll("\\$", "\247"));
							u++;
						}
						p.sendMessage(ChatColor.AQUA+"あなたが所持しているkit<"+sb.toString()+">");
					}
					if(GamePlayerListener.pltype.get(p.getName())!=PlayerType.BEFORE){
						p.sendMessage(ChatColor.AQUA+"(GameState)"+getStats(p));
						p.sendMessage(ChatColor.AQUA+"残りゲーム時間"+String.valueOf(LoadFiles.EndGamingTime-GameTimer.time)+"秒");
					}else{
						p.sendMessage(ChatColor.AQUA+"(GameState)NotStarting");
					}
					return true;
				}else{
					return false;
				}
			}else if(cmd.getName().equalsIgnoreCase("p")){
				if(arg3.length==0){
					if(GamePlayerListener.pltype.get(p.getName())==PlayerType.WATCHING){
						p.sendMessage(ChatColor.RED+"あなたは現在ゲームを観戦しています");
						return true;
					}
					if(ptoggle.get(p)!=null){
					}else{
						ptoggle.put(p, false);
					}
					if(ptoggle.get(p)==true){
						p.sendMessage(ChatColor.AQUA+"Chat toggle off");
						ptoggle.put(p, false);
						return true;
					}else{
						p.sendMessage(ChatColor.AQUA+"Chat toggle on");
						ptoggle.put(p, true);
						return true;
					}
				}else{
					return false;
				}
			}else{
				return false;
			}
		}else{
			return true;
		}
	}

	private String getStats(Player p) {
		PlayerType pt = HungerGamesMixer.serverstate;
		switch(pt){
		case WAITING:
			return "ゲーム開始前";
		case BEFOREGAMING:
			return "ゲーム中：無敵時間";
		case GAMING:
			return "ゲーム中";
		case SECONDWAIT:
			return "ラストバトル前：待機時間";
		case LASTBATTLERESP:
			return "ラストバトル：無敵時間";
		case LASTBATTLE:
			return "ラストバトル";
		default:
			return "ゲーム終了";
		}
	}
}
