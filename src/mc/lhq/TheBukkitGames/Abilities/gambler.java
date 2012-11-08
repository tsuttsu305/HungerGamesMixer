package mc.lhq.TheBukkitGames.Abilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import mc.lhq.TheBukkitGames.Kit;
import mc.lhq.TheBukkitGames.TheBukkitGames;
import mc.lhq.TheBukkitGames.LoadUtil.LoadFiles;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class gambler implements Listener {

	List<Player> endp = new ArrayList<Player>();
	Plugin plg;

	public gambler(Plugin plugin){
		plg = plugin;
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent event){
		if(event.getAction()==Action.RIGHT_CLICK_BLOCK){
			if(event.getClickedBlock().getType()==Material.STONE_BUTTON){
				Player p = event.getPlayer();
				if(Kit.haveAbility(Ability.getAbility("gambler"), p)){
					if(!endp.contains(p)){
						Random r = new Random();
						int i = r.nextInt(999);
						PotionEffectType pet = null;
						endp.add(p);
						if(i==0){
							pet = PotionEffectType.REGENERATION;
						}else if(i==1){
							TheBukkitGames.GamingWorld.createExplosion(p.getLocation(), 10);
						}else if(11>=i&i>1){
							p.sendMessage(ChatColor.AQUA+"You get full diamond-armor!");
							PlayerInventory in = p.getInventory();
							in.setHelmet(new ItemStack(Material.DIAMOND_HELMET,1));
							in.setHelmet(new ItemStack(Material.DIAMOND_BOOTS,1));
							in.setHelmet(new ItemStack(Material.DIAMOND_CHESTPLATE,1));
							in.setHelmet(new ItemStack(Material.DIAMOND_LEGGINGS,1));
							setScheduler(p);
							return;
						}else if(111>=i&i>11){
							pet = PotionEffectType.BLINDNESS;
						}else if(211>=i&i>111){
							pet = PotionEffectType.CONFUSION;
						}else if(311>=i&i>211){
							pet = PotionEffectType.DAMAGE_RESISTANCE;
						}else if(411>=i&i>311){
							pet = PotionEffectType.FAST_DIGGING;
						}else if(511>=i&i>411){
							pet = PotionEffectType.FIRE_RESISTANCE;
						}else if(611>=i&i>511){
							pet = PotionEffectType.HUNGER;
						}else if(711>=i&i>611){
							pet = PotionEffectType.INVISIBILITY;
						}else if(811>=i&i>711){
							pet = PotionEffectType.JUMP;
						}else if(911>=i&i>811){
							pet = PotionEffectType.NIGHT_VISION;
						}else{
							pet = PotionEffectType.FAST_DIGGING;
						}
						p.addPotionEffect(new PotionEffect(pet, LoadFiles.EndGamingTime*20, r.nextInt(2)));
						setScheduler(p);
						return;
					}else{
						p.sendMessage(ChatColor.RED+"There isn't your chance!");
					}
				}
			}
		}
	}

	private void setScheduler(final Player p) {
		TheBukkitGames.server.getScheduler().scheduleAsyncDelayedTask(plg, new Runnable(){
			public void run() {
				endp.remove(p);
			}
		}, Ability.getAbility("gambler").getduration()*20L);
	}



}
