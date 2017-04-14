package me.dawn.kingdomwars.managers;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.dawn.kingdomwars.KingdomWars;
import me.dawn.kingdomwars.Team;
import me.dawn.kingdomwars.kwPlayer;
import net.md_5.bungee.api.ChatColor;

public class DeathManager {
	/*******************************************************************************************
	 *                                    Singleton setup
	 *******************************************************************************************/
	private DeathManager() { }

	private static DeathManager instance = new DeathManager();
	
	public static DeathManager getInstance() {
		return instance;
	}
	
	ArrayList<kwPlayer> deadPlayers = new ArrayList<kwPlayer>();
	
	public boolean contains(String uuid) {
		for(kwPlayer p : deadPlayers) {
			if(p.getUuid().equals(uuid))
				return true;
		}
		
		return false;
	}
	public boolean addPlayer(kwPlayer p) {
		if(!(contains(p.getUuid()))) {
			deadPlayers.add(p);
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean removePlayer(kwPlayer p) {
		if(contains(p.getUuid())) {
			deadPlayers.remove(p);
			return true;
		}
		else {
			return false;
		}
	}
	
	public void gameDeath(Player p) {
		kwPlayer p2 = kwPlayerManager.getInstance().getPlayer(p.getUniqueId().toString());
		Team t = p2.getTeam();
		
		if(t == null) return;
		
		if(t.getThroneHealth() > 0) {
			
			for(ItemStack item : p.getInventory()) {
				if(item != null)
					p.getLocation().getWorld().dropItemNaturally(p.getLocation(), item.clone());
			}
			playerDeath(p);
			deadPlayers.add(p2);
			
			p.teleport(new Location(p.getWorld(), 0, 85, 0));
			
			p.setInvulnerable(true);
			p.setGameMode(GameMode.ADVENTURE);
			
			p.setCanPickupItems(false);
			p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 100, 1));
			p.setAllowFlight(true);
			p.setFlying(true);
			
			p.sendTitle(ChatColor.RED + "You have died", ChatColor.GOLD + "Respawning in 5 seconds...", 20, 60, 20);
			
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(KingdomWars.getPlugin(), new Runnable() {
				public void run() {
					deadPlayers.remove(p2);
					
					p.teleport(t.getSpawn());
					
					p.setInvulnerable(false);
					p.setGameMode(GameMode.SURVIVAL);

					p.setCanPickupItems(true);
					p.setAllowFlight(false);
					p.setFlying(false);
					
					p.getInventory().setArmorContents(p2.getArmorContents());
					p.getInventory().setContents(p2.getInventoryContents());
				}
			}, 100L);
		}
		else {
			playerDeath(p);
			LocationManager.getInstance().toLobby(p);
			t.removePlayer(p2);
		}
	}
	
	public void playerDeath(Player p) {
		p.getInventory().clear();
		p.setHealth(20);
		p.setFoodLevel(20);
		p.setSaturation(20);
		p.setFireTicks(0);
		p.setExp(0);
		p.setLevel(0);
		p.getActivePotionEffects().clear();
	}
}
