package me.dawn.kingdomwars.inv;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.dawn.kingdomwars.CustomInventory;
import net.md_5.bungee.api.ChatColor;

public class ClassSelection extends CustomInventory {
	
	public ClassSelection() {
		super("ClassSelection" , 3, new onClick() {
		    @Override
		    public boolean click(Player p, CustomInventory menu, Row row, int slot, ItemStack item) {
		        if(row.getRow() == 0 && slot == 0){
		        	//
		        }
		        return true;
		    }
		});
		
		for(int i = 0; i < 9; i++) {
			this.addButton(this.getRow(0), i, new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15), " ");
		}
		
		for(int i = 0; i < 9; i++) {
			this.addButton(this.getRow(2), i, new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15), " ");
		}
		this.addButton(this.getRow(1), 0, new ItemStack(Material.DIAMOND_CHESTPLATE), ChatColor.RED + "Warrior");
		this.addButton(this.getRow(1), 1, new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15), " ");
		this.addButton(this.getRow(1), 2, new ItemStack(Material.BOW), ChatColor.GREEN + "Archer");
		this.addButton(this.getRow(1), 3, new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15), " ");
		this.addButton(this.getRow(1), 4, new ItemStack(Material.STONE_PICKAXE), ChatColor.BLUE + "Miner");
		this.addButton(this.getRow(1), 5, new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15), " ");
		this.addButton(this.getRow(1), 6, new ItemStack(Material.GOLD_HELMET), ChatColor.YELLOW + "Support");
		this.addButton(this.getRow(1), 7, new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15), " ");
		this.addButton(this.getRow(1), 8, new ItemStack(Material.IRON_SWORD, 1), ChatColor.DARK_RED + "Assassin");
	}
}

