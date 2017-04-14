package me.dawn.kingdomwars.inv;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.dawn.kingdomwars.CustomInventory;
import me.dawn.kingdomwars.managers.CustomInventoryManager;
import net.md_5.bungee.api.ChatColor;

public class InvMenu extends CustomInventory {
	
	public InvMenu() {
		super("Menu" , 1, new onClick() {
		    @Override
		    public boolean click(Player p, CustomInventory menu, Row row, int slot, ItemStack item) {
		        if(row.getRow() == 0 && slot == 0){
		        	CustomInventoryManager.getInstance().getInventory("Quests").open(p);
		        }
		        return true;
		    }
		});
		
		this.addButton(this.getRow(0), 0, new ItemStack(Material.BOOK), ChatColor.GOLD + "Quests");
		this.addButton(this.getRow(0), 1, new ItemStack(Material.RECORD_10), ChatColor.GREEN + "My City");
		this.addButton(this.getRow(0), 2, new ItemStack(Material.SADDLE), ChatColor.RED + "My Mounts");
		this.addButton(this.getRow(0), 3, new ItemStack(Material.CHEST), ChatColor.YELLOW + "Backpack");
		this.addButton(this.getRow(0), 4, new ItemStack(Material.GOLD_INGOT), ChatColor.LIGHT_PURPLE + "Crowns Shop");
		this.addButton(this.getRow(0), 5, new ItemStack(Material.BEACON), ChatColor.DARK_PURPLE + "Faction");
		this.addButton(this.getRow(0), 6, new ItemStack(Material.BOOK_AND_QUILL), ChatColor.DARK_AQUA + "Reports");
		this.addButton(this.getRow(0), 7, new ItemStack(Material.EYE_OF_ENDER), ChatColor.AQUA + "Talents");
		this.addButton(this.getRow(0), 8, new ItemStack(Material.SKULL_ITEM, 1, (byte) 2), ChatColor.RED + "Mob Arena");
	}
}
