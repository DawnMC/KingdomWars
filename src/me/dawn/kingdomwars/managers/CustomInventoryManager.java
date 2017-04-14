package me.dawn.kingdomwars.managers;

import java.util.ArrayList;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.dawn.kingdomwars.CustomInventory;
import me.dawn.kingdomwars.inv.ClassSelection;
import me.dawn.kingdomwars.inv.InvMenu;


public class CustomInventoryManager {
	/*******************************************************************************************
	 *                                    Singleton setup
	 *******************************************************************************************/
	private CustomInventoryManager() { }

	private static CustomInventoryManager instance = new CustomInventoryManager();
	
	public static CustomInventoryManager getInstance() {
		return instance;
	}
	
	ArrayList<CustomInventory> menus = new ArrayList<CustomInventory>();
	
	ArrayList<ItemStack[]> inventories = new ArrayList<ItemStack[]>();
	
	public void setup() {
		menus.add(new InvMenu());
		menus.add(new ClassSelection());
	}
	
	public CustomInventory getInventory(String name) {
		for(CustomInventory inv : menus) {
			if(inv.getName().equals(name))
				return inv;
		}
		return null;	
	}
	
	public ItemStack[] copyInventory(Inventory inv)
	{
	    ItemStack[] original = inv.getContents();
	    ItemStack[] copy = new ItemStack[original.length];
	    for(int i = 0; i < original.length; ++i)
	        if(original[i] != null)
	            copy[i] = new ItemStack(original[i]);
	    return copy;
	}
}
