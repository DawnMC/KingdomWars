package me.dawn.kingdomwars.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.dawn.kingdomwars.kwMessages; 

public class CommandMain implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		//if(!(cmd.getName().equals("gm"))) return false;
		if(!(sender instanceof Player)) return false;
		
		Player p = (Player) sender;
		
		if(args.length == 0) { 
			p.sendMessage(kwMessages.msg(kwMessages.HELP));
			return true;
		}
		
		else if(args[0].equals("join")) {
			join();
			return true;
		}
		return true;
	}
	
	public void join() {
		
	}
}
