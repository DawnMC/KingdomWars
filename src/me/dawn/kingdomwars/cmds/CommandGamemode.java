package me.dawn.kingdomwars.cmds;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandGamemode implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		//if(!(cmd.getName().equals("gm"))) return false;
		if(!(sender instanceof Player)) return false;
		if(!(sender.hasPermission("tPlayer.op"))) return false;
		
		Player p = (Player) sender;
		
		if(args.length == 0) {
			p.sendMessage(ChatColor.RED + cmd.getUsage());
			return true;
		}
		if(args[0].equals("1")) {
			p.setGameMode(GameMode.CREATIVE);
			p.sendMessage(ChatColor.GOLD + "Gamemode set to Creative");
			return true;
		}
		else if(args[0].equals("0")) {
			p.setGameMode(GameMode.SURVIVAL);
			p.sendMessage(ChatColor.GOLD + "Gamemode set to Survival");
			return true;
		}
		else if(args[0].equals("spec")) {
			p.setGameMode(GameMode.SPECTATOR);
			p.sendMessage(ChatColor.GOLD + "Gamemode set to Spectator");
			return true;
		}
		else {
			p.sendMessage(ChatColor.RED + cmd.getUsage());
			return false;
		}
	}

}
