package mc.lhq.HungerGamesMixer.Abilities;

import java.util.ArrayList;
import java.util.List;

import mc.lhq.HungerGamesMixer.Kit;
import mc.lhq.HungerGamesMixer.PlayerType;
import mc.lhq.HungerGamesMixer.Listeners.GamePlayerListener;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class lumberjack implements Listener {


	@EventHandler
	public void onBreak(BlockBreakEvent event){
		if(event.getBlock().getType()==Material.LOG){
			if(GamePlayerListener.pltype.get(event.getPlayer().getName())!=PlayerType.WAITING){
				if(Kit.haveAbility(Ability.getAbility("lumberjack"), event.getPlayer())){
					List<Block> deletelogs = new ArrayList<Block>();
					List<Block> lastb = new ArrayList<Block>();
					deletelogs.add(event.getBlock());
					lastb.add(event.getBlock());
					while(lastb.size()!=0){
						List<Block> bl = getBlocks(lastb.get(0));
						Block b1 = bl.get(0);
						Block b2 = bl.get(1);
						Block b3 = bl.get(2);
						Block b4 = bl.get(3);
						Block b5 = bl.get(4);
						if(isLog(b1)){
							if(!deletelogs.contains(b1)){
								deletelogs.add(b1);
								lastb.add(b1);
							}
						}
						if(isLog(b2)){
							if(!deletelogs.contains(b2)){
								deletelogs.add(b2);
								lastb.add(b2);
							}
						}
						if(isLog(b3)){
							if(!deletelogs.contains(b3)){
								deletelogs.add(b3);
								lastb.add(b3);
							}
						}
						if(isLog(b4)){
							if(!deletelogs.contains(b4)){
								deletelogs.add(b4);
								lastb.add(b4);
							}
						}
						if(isLog(b5)){
							if(!deletelogs.contains(b5)){
								deletelogs.add(b5);
								lastb.add(b5);
							}
						}
						lastb.remove(0);
					}
					int u = 0;
					int limit = Ability.getAbility("lumberjack").amount();
					while(deletelogs.size()!=0&u!=limit){
						deletelogs.get(0).breakNaturally();
						deletelogs.remove(0);
						u++;
					}
				}
			}
		}
	}

	public List<Block> getBlocks(Block b){
		List<Block> bl = new ArrayList<Block>();
		World w = b.getWorld();
		int x = b.getX();
		int y = b.getY();
		int z = b.getZ();
		bl.add(w.getBlockAt(x, y+1, z));
		bl.add(w.getBlockAt(x+1, y, z));
		bl.add(w.getBlockAt(x-1, y, z));
		bl.add(w.getBlockAt(x, y, z+1));
		bl.add(w.getBlockAt(x, y, z-1));
		return bl;
	}

	public boolean isLog(Block b){
		return b.getType()==Material.LOG;
	}
}
