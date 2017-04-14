package me.dawn.kingdomwars.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.dawn.kingdomwars.Game;
import me.dawn.kingdomwars.Team;
import me.dawn.kingdomwars.kwPlayer;
import me.dawn.kingdomwars.managers.GameManager;
import me.dawn.kingdomwars.managers.LocationManager;
import me.dawn.kingdomwars.managers.kwPlayerManager;

public class CommandLeave implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		//if(!(cmd.getName().equals("gm"))) return false;
		if(!(sender instanceof Player)) return false;
//		if(!(sender.hasPermission("tPlayer.op"))) return false;
		
		Player p = (Player) sender;
		if(!(kwPlayerManager.getInstance().getPlayer(p.getUniqueId().toString()).getGame().equals("none"))) {
			for(Game g : GameManager.getInstance().getGames()) {
				for(Team t : g.getTeams()) {
					for(kwPlayer p2 : t.getPlayers()) {
						if(p2.getUuid().equals(p.getUniqueId().toString())) {
							t.removePlayer(p2);
							p2.setGame(null);
							p2.setTeam(null);
							LocationManager.getInstance().toLobby(p);
							return true;
						}
					}
				}
			}
		}
		return true;
	}
}
