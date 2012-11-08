package mc.lhq.HungerGamesMixer.Abilities;

import java.util.List;

import mc.lhq.HungerGamesMixer.Kit;

import org.bukkit.Material;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class hunter implements Listener {

	@EventHandler
	public void onEntityDeath(EntityDeathEvent event){
		if(event.getEntity() instanceof Pig){
			if(event.getEntity().getKiller()!=null){
				if(Kit.haveAbility(Ability.getAbility("hunter"), (Player) event.getEntity().getKiller())){
					List<ItemStack> isl = event.getDrops();
					int u = 0;
					while(u!=isl.size()){
						if(isl.get(u).getType()==Material.PORK){
							isl.get(u).setType(Material.GRILLED_PORK);
						}
						u++;
					}
				}
			}
		}
	}

}
