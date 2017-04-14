package me.dawn.kingdomwars.events;

import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import me.dawn.kingdomwars.GameState;
import me.dawn.kingdomwars.Team;
import me.dawn.kingdomwars.kwPlayer;
import me.dawn.kingdomwars.managers.GameManager;
import me.dawn.kingdomwars.managers.kwPlayerManager;
import net.md_5.bungee.api.ChatColor;

public class ThroneMineEvent implements Listener {
	@EventHandler
	public void onMine(BlockBreakEvent e) {
		kwPlayer p2 = kwPlayerManager.getInstance().getPlayer(e.getPlayer().getUniqueId().toString());
		for(Team t : GameManager.getInstance().getGame(e.getBlock().getWorld().getName()).getTeams()) {
			if(e.getBlock().getLocation().equals(t.getNexus())) {
				e.setCancelled(true);
				if(p2.getTeam().equals(t))
					return;
				if(p2.getGame().getPhase().equals(GameState.PHASE1)) {
					e.getPlayer().sendMessage(ChatColor.RED + "You can only attack thrones after Phase 1!");
					return;
				}
				if(p2.getGame().getPhase().equals(GameState.PHASE5)) {
					if(t.getThroneHealth() - 2 <= 0) {
						p2.getGame().destroyThrone(t);
						p2.setData("throneDamange", ((int) p2.getData("throneDamage") + 2));
					}
					else {
						t.setThroneHealth(t.getThroneHealth() - 2);
						p2.setData("throneDamange", ((int) p2.getData("throneDamage") + 2));
						p2.getGame().updateScoreboard();
						t.getNexus().getWorld().playSound(e.getPlayer().getLocation(), Sound.BLOCK_ANVIL_PLACE, 25F, 1F);
					}
				}
				else {
					if(t.getThroneHealth() - 1 <= 0) {
						p2.getGame().destroyThrone(t);
						p2.setData("throneDamange", ((int) p2.getData("throneDamage") + 1));
					}
					else {
						t.setThroneHealth(t.getThroneHealth() - 1);
						p2.setData("throneDamage", ((int) p2.getData("throneDamage") + 1));
						p2.getGame().updateScoreboard();
						t.getNexus().getWorld().playSound(t.getNexus(), Sound.BLOCK_ANVIL_PLACE, 25F, 1F);
					}
				}
			}
		}
	}
}
