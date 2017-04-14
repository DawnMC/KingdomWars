package me.dawn.kingdomwars.cmds;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHeal implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		//if(!(cmd.getName().equals("gm"))) return false;
		if(!(sender instanceof Player)) return false;
		if(!(sender.hasPermission("tPlayer.op"))) return false;
		
		Player p = (Player) sender;
		p.setHealth(20.0);
		p.setFoodLevel(20);
		p.setSaturation(20);
		p.sendMessage(ChatColor.GOLD + "You have been healed");
		return true;
	}
}
