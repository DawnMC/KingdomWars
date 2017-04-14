package me.dawn.kingdomwars.managers;

import java.util.ArrayList;

import org.bukkit.Material;

import me.dawn.kingdomwars.CustomItem;
import net.md_5.bungee.api.ChatColor;

public class CustomItemManager {
	/*******************************************************************************************
	 *                                    Singleton setup
	 *******************************************************************************************/
	private CustomItemManager() { }

	private static CustomItemManager instance = new CustomItemManager();
	
	public static CustomItemManager getInstance() {
		return instance;
	}
	
	ArrayList<CustomItem> items = new ArrayList<CustomItem>();
	
	public void setup() {
		items.add(new CustomItem("Play", Material.EYE_OF_ENDER, 1, ChatColor.GREEN + "Play " + ChatColor.GRAY + "(Right Click)", ChatColor.GRAY + "Click to join a game"));
		
		//Kit items
		items.add(new CustomItem("WoodenSword", Material.WOOD_SWORD, 1, ChatColor.GOLD + "Wooden Sword", ChatColor.RED + "Soulbound"));
		items.add(new CustomItem("WoodenPickaxe", Material.WOOD_PICKAXE, 1, ChatColor.GOLD + "Wooden Pickaxe", ChatColor.RED + "Soulbound"));
		items.add(new CustomItem("WoodenAxe", Material.WOOD_AXE, 1, ChatColor.GOLD + "Wooden Axe", ChatColor.RED + "Soulbound"));
	}
	
	public CustomItem getItem(String id) {
		for(CustomItem item : items) {
			if(item.getId().equals(id))
				return item;
		}
		return null;	
	}
}
