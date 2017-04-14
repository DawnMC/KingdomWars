package me.dawn.kingdomwars.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import me.dawn.kingdomwars.managers.DeathManager;

public class DeadPlayerInteract implements Listener	{
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if(DeathManager.getInstance().contains(e.getPlayer().getUniqueId().toString())) {
			e.setCancelled(true);
		}
	}
}
