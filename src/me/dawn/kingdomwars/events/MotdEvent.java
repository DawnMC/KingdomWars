package me.dawn.kingdomwars.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import me.dawn.kingdomwars.managers.SettingsManager;


public class MotdEvent implements Listener {
public static void setup() {
	//45 characters per line in motd
	
	//motd:                &c&lNextMC &cNetwork &8[&a1.8-1.11&8]                                       &eKingdom Wars Beta
		if (!SettingsManager.getInstance().configContains("motd")) {
			SettingsManager.getInstance().createConfigurationSection("motd");
			SettingsManager.getInstance().set("motd", "This is the default motd");
		}
	}
	@EventHandler
	public void onServerPing(ServerListPingEvent e) {
		e.setMaxPlayers(e.getNumPlayers() + 1);
		e.setMotd(SettingsManager.getInstance().configGet("motd").toString().replaceAll("&", "\u00A7"));
	}
}
