package mc.lhq.TheBukkitGames;

import java.io.File;
import java.util.logging.Logger;

import mc.lhq.TheBukkitGames.Abilities.Compass;
import mc.lhq.TheBukkitGames.Abilities.assassin;
import mc.lhq.TheBukkitGames.Abilities.berserker;
import mc.lhq.TheBukkitGames.Abilities.boxer;
import mc.lhq.TheBukkitGames.Abilities.burrower;
import mc.lhq.TheBukkitGames.Abilities.cannibal;
import mc.lhq.TheBukkitGames.Abilities.cookiemonster;
import mc.lhq.TheBukkitGames.Abilities.cultivator;
import mc.lhq.TheBukkitGames.Abilities.demoman;
import mc.lhq.TheBukkitGames.Abilities.endermage;
import mc.lhq.TheBukkitGames.Abilities.fireman;
import mc.lhq.TheBukkitGames.Abilities.fletcher;
import mc.lhq.TheBukkitGames.Abilities.frosty;
import mc.lhq.TheBukkitGames.Abilities.gambler;
import mc.lhq.TheBukkitGames.Abilities.ghost;
import mc.lhq.TheBukkitGames.Abilities.hunter;
import mc.lhq.TheBukkitGames.Abilities.kaya;
import mc.lhq.TheBukkitGames.Abilities.lumberjack;
import mc.lhq.TheBukkitGames.Abilities.miner;
import mc.lhq.TheBukkitGames.Abilities.monster;
import mc.lhq.TheBukkitGames.Abilities.necro;
import mc.lhq.TheBukkitGames.Abilities.poseidon;
import mc.lhq.TheBukkitGames.Abilities.sniper;
import mc.lhq.TheBukkitGames.Abilities.spy;
import mc.lhq.TheBukkitGames.Abilities.stomper;
import mc.lhq.TheBukkitGames.Abilities.thor;
import mc.lhq.TheBukkitGames.Abilities.turtle;
import mc.lhq.TheBukkitGames.Abilities.vampire;
import mc.lhq.TheBukkitGames.Abilities.viper;
import mc.lhq.TheBukkitGames.Abilities.werewolf;
import mc.lhq.TheBukkitGames.Command.GameCommandExecutor;
import mc.lhq.TheBukkitGames.Listeners.GamePlayerListener;
import mc.lhq.TheBukkitGames.LoadUtil.LoadFiles;
import mc.lhq.TheBukkitGames.LoadUtil.LoadMessages;
import mc.lhq.TheBukkitGames.Threads.GameTimer;
import mc.lhq.TheBukkitGames.Threads.StaminaFilter;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.plugin.java.JavaPlugin;

public class TheBukkitGames extends JavaPlugin{

	public static PlayerType serverstate = PlayerType.BEFORE;
	public final static Logger log =Logger.getLogger("Minecraft");
	public static World GamingWorld;
	public static Server server;
	public static int time;
	public static boolean WorldCreated = false;;

	public void onEnable(){
		server = getServer();
		loadFiles();
		loadCommands();
		worldCreate();
		GamePlayerListener.spawnloc=GamingWorld.getSpawnLocation();
		GameStart();
		getServer().getPluginManager().registerEvents(new GamePlayerListener(), this);
		loadAbilityListeners();
	}
	public void onDisable(){

	}
	private void loadAbilityListeners() {
		getServer().getPluginManager().registerEvents(new Compass(), this);
		getServer().getPluginManager().registerEvents(new assassin(this), this);
		getServer().getPluginManager().registerEvents(new cultivator(), this);
		getServer().getPluginManager().registerEvents(new demoman(), this);
		getServer().getPluginManager().registerEvents(new endermage(this), this);
		getServer().getPluginManager().registerEvents(new berserker(), this);
		getServer().getPluginManager().registerEvents(new boxer(), this);
		getServer().getPluginManager().registerEvents(new burrower(), this);
		getServer().getPluginManager().registerEvents(new cannibal(), this);
		getServer().getPluginManager().registerEvents(new cookiemonster(), this);
		getServer().getPluginManager().registerEvents(new fireman(), this);
		getServer().getPluginManager().registerEvents(new fletcher(), this);
		getServer().getPluginManager().registerEvents(new sniper(), this);
		getServer().getPluginManager().registerEvents(new frosty(), this);
		getServer().getPluginManager().registerEvents(new gambler(this), this);
		getServer().getPluginManager().registerEvents(new ghost(this), this);
		getServer().getPluginManager().registerEvents(new hunter(), this);
		getServer().getPluginManager().registerEvents(new kaya(), this);
		getServer().getPluginManager().registerEvents(new lumberjack(), this);
		getServer().getPluginManager().registerEvents(new miner(), this);
		getServer().getPluginManager().registerEvents(new monster(), this);
		getServer().getPluginManager().registerEvents(new necro(), this);
		getServer().getPluginManager().registerEvents(new poseidon(), this);
		getServer().getPluginManager().registerEvents(new spy(), this);
		getServer().getPluginManager().registerEvents(new stomper(), this);
		getServer().getPluginManager().registerEvents(new thor(this), this);
		getServer().getPluginManager().registerEvents(new turtle(), this);
		getServer().getPluginManager().registerEvents(new vampire(), this);
		getServer().getPluginManager().registerEvents(new viper(), this);
		getServer().getPluginManager().registerEvents(new werewolf(this), this);
	}
	private void loadFiles() {
		LoadMessages.loadMessages();
		LoadFiles.loadTimes();
		LoadFiles.loadAbilities();
		LoadFiles.loadkits();
		LoadFiles.loadSettings();
	}
	private void loadCommands() {
		GameCommandExecutor ce = new GameCommandExecutor();
		getCommand("kit").setExecutor(ce);
		getCommand("stats").setExecutor(ce);
		getCommand("p").setExecutor(ce);
	}
	private void worldCreate() {
		Bukkit.getServer().unloadWorld("GamingWorld", false);
		deleteDir(new File("GamingWorld"));
		WorldCreator wc = new WorldCreator("GamingWorld");
		wc.environment(Environment.NORMAL);
		wc.type(WorldType.NORMAL);
		GamingWorld = wc.createWorld();
		GamingWorld.setDifficulty(Difficulty.HARD);
		GamingWorld.setThundering(false);
		GamingWorld.setStorm(false);
		WorldCreated = true;
		GamingWorld.loadChunk(GamingWorld.getSpawnLocation().getChunk());
	}
	public static void deleteDir(File dir) {
		if(dir.isDirectory()){
			String[] children = dir.list();
			for(int i = 0;i<children.length;i++){
				deleteDir(new File(dir,children[i]));
			}
		}
		dir.delete();
	}
	public void GameStart(){
		getServer().getScheduler().scheduleAsyncRepeatingTask(this, new GameTimer(this), 20L, 20L);
		getServer().getScheduler().scheduleAsyncRepeatingTask(this, new StaminaFilter(), 400L, 600L);
	}
}
