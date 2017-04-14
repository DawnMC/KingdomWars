package me.dawn.kingdomwars.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import me.dawn.kingdomwars.kwMessages;
import me.dawn.kingdomwars.managers.CustomItemManager;
import me.dawn.kingdomwars.managers.QueueManager;
import me.dawn.kingdomwars.managers.kwPlayerManager;

public class QueueEvent implements Listener {
	@EventHandler
	public void onClick(PlayerInteractEvent e) {
		if(!(e.getAction().equals(Action.RIGHT_CLICK_AIR) || 
		   e.getAction().equals(Action.RIGHT_CLICK_BLOCK) ||
		   e.getAction().equals(Action.LEFT_CLICK_AIR) ||
		   e.getAction().equals(Action.LEFT_CLICK_BLOCK))) return;
		if(!(e.getPlayer().getInventory().getItemInMainHand().isSimilar(CustomItemManager.getInstance().getItem("Play").toItemStack()))) return;
		e.setCancelled(true);
		if(QueueManager.getInstance().contains(e.getPlayer().getUniqueId().toString())) {
			QueueManager.getInstance().removePlayer(e.getPlayer().getUniqueId().toString());
		}
		else if(kwPlayerManager.getInstance().getPlayer(e.getPlayer().getUniqueId().toString()).isPartyMember())
			e.getPlayer().sendMessage(kwMessages.msg(kwMessages.QUEUE_LEADER_ONLY));
		else {
			QueueManager.getInstance().addPlayer(e.getPlayer().getUniqueId().toString());
		}
	}
}
