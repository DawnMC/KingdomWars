package me.dawn.kingdomwars.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class NexusBreakEvent implements Listener {
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		e.getPlayer().sendMessage(e.getBlock().getLocation().toString());
	}
}
