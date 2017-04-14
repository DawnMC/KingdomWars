package me.dawn.kingdomwars.events;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class DropSoulboundEvent implements Listener {
	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		if(e.getItemDrop().getItemStack().getItemMeta().getLore() == null) return;
		for(String s : e.getItemDrop().getItemStack().getItemMeta().getLore()) {
			if(s.contains("Soulbound")) {
				e.getItemDrop().getItemStack().setType(Material.AIR);
				e.getPlayer().getWorld().playSound(e.getPlayer().getLocation(), Sound.ENTITY_ITEM_BREAK, 2F, 1F);
			}
		}
	}
	
	@EventHandler
	public void onItemSpawn(ItemSpawnEvent e) {
		if(e.getEntity().getItemStack().getItemMeta().getLore() == null) return;
		for(String s : e.getEntity().getItemStack().getItemMeta().getLore()) {
			if(s.contains("Soulbound")) {
				e.setCancelled(true);
			}
		}
	}
}
