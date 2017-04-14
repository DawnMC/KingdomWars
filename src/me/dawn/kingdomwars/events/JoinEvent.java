package me.dawn.kingdomwars.events;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.dawn.kingdomwars.kwPlayer;
import me.dawn.kingdomwars.managers.LocationManager;
import me.dawn.kingdomwars.managers.SettingsManager;
import me.dawn.kingdomwars.managers.kwPlayerManager;

public class JoinEvent implements Listener {
	@EventHandler
	public void onJoin(PlayerJoinEvent e) throws SQLException {
		Player p = (Player) e.getPlayer();
		LocationManager.getInstance().toLobby(p);
		
		Statement statement = SettingsManager.getInstance().getConnection().createStatement();
		ResultSet res = SettingsManager.getInstance().getRow(e.getPlayer().getUniqueId().toString());
		
		if(!(res.next())) {
			statement.executeUpdate("INSERT INTO KingdomWars (uuid, name) VALUES('" + e.getPlayer().getUniqueId().toString() +"','" + e.getPlayer().getName() + "');");
			kwPlayerManager.getInstance().addPlayer(new kwPlayer(e.getPlayer()));
			System.out.println(e.getPlayer().getName() + " successfully saved to SQL database.");
		}
		else {
			//tScoreboard.setScoreboard(e.getPlayer(), res);
			kwPlayerManager.getInstance().addPlayer(new kwPlayer(e.getPlayer(), res));
		}
	}
}
