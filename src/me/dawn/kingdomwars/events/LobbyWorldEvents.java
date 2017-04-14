package me.dawn.kingdomwars.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class LobbyWorldEvents implements Listener {
	@EventHandler
	public void onFall(EntityDamageEvent e) {
		if(!(e.getEntity() instanceof Player)) return;
		if(!(e.getEntity().getWorld().getName().equals("world"))) return;
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onHunger(FoodLevelChangeEvent e) {
		if(!(e.getEntity() instanceof Player)) return;
		if(!(e.getEntity().getWorld().getName().equals("world"))) return;
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onTrample(EntityInteractEvent e) {
		Material mat = e.getBlock().getType();
		
		if(mat == Material.SOIL)
			e.setCancelled(true);
	}
}
