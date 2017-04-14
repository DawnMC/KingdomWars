package me.dawn.kingdomwars.cmds;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.dawn.kingdomwars.managers.SettingsManager;


public class CommandReload implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		//if(!(cmd.getName().equals("gm"))) return false;
		//if(!(sender instanceof Player)) return false;
		if(!(sender.hasPermission("tPlayer.op"))) return false;
		SettingsManager.getInstance().reloadConfig();
		sender.sendMessage(ChatColor.GOLD + "config.yml has been reloaded");
		return true;
	}
}
