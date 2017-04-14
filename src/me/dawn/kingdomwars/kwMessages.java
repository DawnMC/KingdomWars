package me.dawn.kingdomwars;

import me.dawn.kingdomwars.managers.QueueManager;
import net.md_5.bungee.api.ChatColor;

public enum kwMessages {
	HELP, QUEUE, QUEUE_CANCEL, QUEUE_LEADER_ONLY, NO_AVAILABLE_GAMES, ALREADY_IN_GAME;
	
	public static String msg(kwMessages type) {
		String msg = "";
		if(type.equals(HELP))
			msg = ChatColor.GOLD + "Kingdom Wars \n" + ChatColor.AQUA + "/kw join \n /kw leave \n /kw kits \n /kw profile";
		else if(type.equals(QUEUE))
			msg = ChatColor.GREEN + "You are now queued with " + ChatColor.YELLOW + (QueueManager.getInstance().getSize() -1) + ChatColor.GREEN + " other players";
		else if(type.equals(QUEUE_CANCEL))
			msg = ChatColor.RED + "You are no longer queued";
		else if(type.equals(QUEUE_LEADER_ONLY))
			msg = ChatColor.RED + "Only your party leader can enter the queue";
		else if(type.equals(NO_AVAILABLE_GAMES))
			msg = ChatColor.RED + "There are currently no available games (try again later)";
		else if(type.equals(ALREADY_IN_GAME))
			msg = ChatColor.RED + "You are already in a game. " + ChatColor.YELLOW + "/rejoin " + ChatColor.RED + "to join the fight!";
		return msg;
	}
}
