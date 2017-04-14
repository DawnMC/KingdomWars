package me.dawn.kingdomwars.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import me.dawn.kingdomwars.KingdomWars;

public class OreMineEvent implements Listener {
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		if(e.getBlock().getWorld().getName().equals("world") || e.getBlock().getWorld().getName().equals("mobarena")) return;
		
		switch(e.getBlock().getType()) {
		case COAL_ORE:
			e.setCancelled(true);
			blockRegen(e.getBlock(), Material.COAL_ORE, 15);
			e.getPlayer().getInventory().addItem(new ItemStack(Material.COAL, 1));
			e.getPlayer().giveExp((int)(Math.random() * 2 + 1));
			break;
		case IRON_ORE:
			e.setCancelled(true);
			blockRegen(e.getBlock(), Material.IRON_ORE, 20);
			e.getPlayer().getInventory().addItem(new ItemStack(Material.IRON_ORE, 1));
			break;
		case GOLD_ORE:
			e.setCancelled(true);
			blockRegen(e.getBlock(), Material.GOLD_ORE, 25);
			e.getPlayer().getInventory().addItem(new ItemStack(Material.GOLD_ORE, 1));
			break;
		case REDSTONE_ORE:
			e.setCancelled(true);
			blockRegen(e.getBlock(), Material.REDSTONE_ORE, 25);
			e.getPlayer().getInventory().addItem(new ItemStack(Material.REDSTONE, 5));
			e.getPlayer().giveExp((int)(Math.random() * 5 + 1));
			break;
		case LAPIS_ORE:
			e.setCancelled(true);
			blockRegen(e.getBlock(), Material.LAPIS_ORE, 25);
			e.getPlayer().getInventory().addItem(new ItemStack(Material.INK_SACK, 1, (byte) 6));
			e.getPlayer().giveExp((int)(Math.random() * 4 + 2));
			break;
		case EMERALD_ORE:
			e.setCancelled(true);
			blockRegen(e.getBlock(), Material.EMERALD_ORE, 30);
			e.getPlayer().getInventory().addItem(new ItemStack(Material.EMERALD, 1));
			e.getPlayer().giveExp((int)(Math.random() * 5 + 3));
			break;
		case DIAMOND_ORE:
			e.setCancelled(true);
			blockRegen(e.getBlock(), Material.DIAMOND_ORE, 45);
			e.getPlayer().getInventory().addItem(new ItemStack(Material.DIAMOND, 1));
			e.getPlayer().giveExp((int)(Math.random() * 5 + 3));
			break;
		case GRAVEL:
			e.setCancelled(true);
			blockRegen(e.getBlock(), Material.GRAVEL, 30);
			break;
		case LOG:
			blockRegen(e.getBlock(), Material.LOG, 15);
			e.getPlayer().getInventory().addItem(new ItemStack(Material.LOG, 1, (byte) e.getBlock().getData()));
			e.setCancelled(true);
			break;
		case MELON_BLOCK:
			e.setCancelled(true);
			blockRegen(e.getBlock(), Material.MELON_BLOCK, 15);
			e.getPlayer().getInventory().addItem(new ItemStack(Material.MELON, 4));
			break;
		case COBBLESTONE:
			e.setCancelled(true);
			break;
		default:
			return;
		}
		
	}
	
	public void blockRegen(Block b, Material m, long time) {
		if(b.getType().equals(Material.LOG) || b.getType().equals(Material.MELON_BLOCK))
			b.getLocation().getBlock().setType(Material.AIR);
		else
			b.getLocation().getBlock().setType(Material.COBBLESTONE);
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(KingdomWars.getPlugin(), new Runnable() {
			public void run() {
				b.getLocation().getBlock().setType(m);
			}
		}, time*20L);
	}
}
