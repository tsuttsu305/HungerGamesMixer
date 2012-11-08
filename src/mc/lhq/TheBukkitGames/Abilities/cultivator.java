package mc.lhq.TheBukkitGames.Abilities;

import mc.lhq.TheBukkitGames.Kit;

import org.bukkit.CropState;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class cultivator implements Listener {
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		Player p = event.getPlayer();
		Block block = event.getBlockPlaced();
		if(block.getType()==Material.CROPS){
			if(Kit.haveAbility(Ability.getAbility("cultivator"), p)){
				block.setData(CropState.RIPE.getData());
			}
		}else if(block.getType()==Material.SAPLING){
			if(Kit.haveAbility(Ability.getAbility("cultivator"), p)){
				TreeType t = getTree(block.getData());
				p.getWorld().generateTree(block.getLocation(), t);
			}
		}
	}

	public TreeType getTree(int data) {
		TreeType tretyp = TreeType.TREE;
		switch(data) {
		case 0:
			tretyp = TreeType.TREE;
			break;
		case 1:
			tretyp = TreeType.REDWOOD;
			break;
		case 2:
			tretyp = TreeType.BIRCH;
			break;
		case 3:
			tretyp = TreeType.JUNGLE;
			break;
		default:
			tretyp = TreeType.TREE;
		}
		return tretyp;
	}
}
