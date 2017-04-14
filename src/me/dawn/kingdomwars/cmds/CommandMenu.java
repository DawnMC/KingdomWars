package me.dawn.kingdomwars.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.dawn.kingdomwars.managers.CustomInventoryManager;


public class CommandMenu implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		//if(!(cmd.getName().equals("gm"))) return false;
		if(!(sender instanceof Player)) return false;
		
		Player p = (Player) sender;
		CustomInventoryManager.getInstance().getInventory("Menu").open(p);
		return true;
	}
}
