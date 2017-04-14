package me.dawn.kingdomwars;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CustomItem extends ItemStack {
	private String id;
	
	public CustomItem(String id, Material m, int amount, String name, String ...lore) {
		super(m, amount);
		this.setId(id);
		ItemMeta im = this.getItemMeta();
		im.setDisplayName(name);
		im.setLore(Arrays.asList(lore));
		this.setItemMeta(im);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public ItemStack toItemStack() {
		ItemStack item = new ItemStack(this.getType(), this.getAmount());
		ItemMeta im = this.getItemMeta();
		item.setItemMeta(im);
		
		return item;
	}
}
