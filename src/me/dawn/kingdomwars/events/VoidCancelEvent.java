package me.dawn.kingdomwars.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import me.dawn.kingdomwars.managers.DeathManager;
import me.dawn.kingdomwars.managers.LocationManager;

public class VoidCancelEvent implements Listener{
	@EventHandler
	public void onFall(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if(p.getLocation().getY() < 0) {
			if(p.getWorld().getName().equals("world")) {
				DeathManager.getInstance().playerDeath(p);
				LocationManager.getInstance().toLobby(p);
			}
			else if(p.getWorld().getName().equals("mobarena")) {
				
			}
			else {
				DeathManager.getInstance().gameDeath(p);
			}
		}
	}
}
