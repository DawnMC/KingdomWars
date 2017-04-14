package me.dawn.kingdomwars.managers;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;



public class LocationManager {
	private LocationManager() { }

	private static LocationManager instance = new LocationManager();
	
	public static LocationManager getInstance() {
		return instance;
	}
	
	HashMap<String, Location> locations = new HashMap<String, Location>();
	
	public void setup() {
		locations.put("Spawn", new Location(Bukkit.getWorld("world"), 0.5, 157, 0.5, 0, 0));
	}
	
	public Location getLocation(String key) {
		for(String k : locations.keySet()) {
			if(k.equals(key))
				return locations.get(k);
		}
		
		return null;
	}
	
	public void toLobby(Player p) {
		p.teleport(getLocation("Spawn"));
		p.setGameMode(GameMode.SURVIVAL);
		p.getInventory().clear();
		p.getInventory().setItem(4, CustomItemManager.getInstance().getItem("Play"));
		p.setHealth(20.0);
		p.setFoodLevel(20);
		p.setSaturation(20);
		p.setFireTicks(0);
		p.getActivePotionEffects().clear();
	}
}
