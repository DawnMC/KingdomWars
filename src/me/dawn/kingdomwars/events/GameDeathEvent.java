package me.dawn.kingdomwars.events;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import me.dawn.kingdomwars.managers.DeathManager;

public class GameDeathEvent implements Listener {
	@EventHandler
	public void onDeath(EntityDamageEvent e) {
		if(!(e.getEntity().getType().equals(EntityType.PLAYER))) return;
		Player p = (Player) e.getEntity();
		if(!(p.getHealth() < 1)) {
			return;
		}
		else {
			e.setCancelled(true);
			DeathManager.getInstance().gameDeath(p);
		}
	}
}
