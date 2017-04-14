package me.dawn.kingdomwars;

import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import me.dawn.kingdomwars.cmds.CommandClearinventory;
import me.dawn.kingdomwars.cmds.CommandGamemode;
import me.dawn.kingdomwars.cmds.CommandHeal;
import me.dawn.kingdomwars.cmds.CommandLeave;
import me.dawn.kingdomwars.cmds.CommandLocation;
import me.dawn.kingdomwars.cmds.CommandMain;
import me.dawn.kingdomwars.cmds.CommandReload;
import me.dawn.kingdomwars.cmds.CommandUp;
import me.dawn.kingdomwars.events.DeadPlayerInteract;
import me.dawn.kingdomwars.events.DropSoulboundEvent;
import me.dawn.kingdomwars.events.GameDeathEvent;
import me.dawn.kingdomwars.events.JoinEvent;
import me.dawn.kingdomwars.events.LobbyWorldEvents;
import me.dawn.kingdomwars.events.MotdEvent;
import me.dawn.kingdomwars.events.NexusBreakEvent;
import me.dawn.kingdomwars.events.OreMineEvent;
import me.dawn.kingdomwars.events.QueueEvent;
import me.dawn.kingdomwars.events.ThroneMineEvent;
import me.dawn.kingdomwars.events.VoidCancelEvent;
import me.dawn.kingdomwars.managers.CustomInventoryManager;
import me.dawn.kingdomwars.managers.CustomItemManager;
import me.dawn.kingdomwars.managers.GameManager;
import me.dawn.kingdomwars.managers.LocationManager;
import me.dawn.kingdomwars.managers.SettingsManager;
import me.dawn.kingdomwars.managers.kwPlayerManager;

public class KingdomWars extends JavaPlugin {
	
	public static Plugin plugin;
	
	public static Plugin getPlugin() {
		return plugin;
	}
	
	public void onEnable() {
		plugin = this;
		registerEvents(this, 
				new JoinEvent(), 
				new MotdEvent(), 
				new QueueEvent(), 
				new NexusBreakEvent(), 
				new LobbyWorldEvents(), 
				new OreMineEvent(),
				new DropSoulboundEvent(),
				new GameDeathEvent(),
				new VoidCancelEvent(),
				new DeadPlayerInteract(),
				new ThroneMineEvent());
		getCommand("kingdomwars").setExecutor(new CommandMain());
		getCommand("ci").setExecutor(new CommandClearinventory());
		getCommand("gm").setExecutor(new CommandGamemode());
		getCommand("treload").setExecutor(new CommandReload());
		getCommand("up").setExecutor(new CommandUp());
		getCommand("heal").setExecutor(new CommandHeal());
		getCommand("loc").setExecutor(new CommandLocation());
		getCommand("leave").setExecutor(new CommandLeave());
		SettingsManager.getInstance().openConnection();
		SettingsManager.getInstance().setupConfig(this);
		CustomItemManager.getInstance().setup();
		CustomInventoryManager.getInstance().setup();
		LocationManager.getInstance().setup();
		GameManager.getInstance().setup();
		MotdEvent.setup();
		for(World w : Bukkit.getServer().getWorlds()) {
			if(!(w.getName().equals("world") || w.getName().equals("mobarena"))) {
				w.setAutoSave(false);
			}
			w.setGameRuleValue("doMobSpawning", "false");
			w.setGameRuleValue("doLeafDecay", "false");
			w.setGameRuleValue("reducedDebugInfo", "true");
			w.setGameRuleValue("doWeatherCycle", "false");
		}
	}
	
	public void onDisable() { 
		plugin = null;
		for(kwPlayer p : kwPlayerManager.getInstance().getPlayers()) {
			try {
				p.save();
				kwPlayerManager.getInstance().removePlayer(p);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		try {
			SettingsManager.getInstance().getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void registerEvents(Plugin plugin, Listener... listeners) {
		for (Listener listener : listeners) {
			Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
		}
	}
}
