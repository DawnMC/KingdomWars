package me.dawn.kingdomwars.managers;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.dawn.kingdomwars.Game;
import me.dawn.kingdomwars.GameState;
import me.dawn.kingdomwars.Team;
import me.dawn.kingdomwars.TeamType;
import me.dawn.kingdomwars.kwMessages;
import me.dawn.kingdomwars.kwPlayer;
import net.md_5.bungee.api.ChatColor;

public class QueueManager {
	private QueueManager() { }

	private static QueueManager instance = new QueueManager();
	
	public static QueueManager getInstance() {
		return instance;
	}
	
	ArrayList<kwPlayer> queue = new ArrayList<kwPlayer>();
	
	public boolean addPlayer(String uuid) {
		kwPlayer kwp = kwPlayerManager.getInstance().getPlayer(uuid);
		Player p = Bukkit.getPlayer(UUID.fromString(uuid));
		
		//Checks if they are already in a game
		if(kwp.getGame() != null) {
			p.sendMessage(kwMessages.msg(kwMessages.ALREADY_IN_GAME));
			return false;
		}
		//Checking if they are party leader
		if(!kwp.isPartyLeader()) {
			p.sendMessage(kwMessages.msg(kwMessages.QUEUE_LEADER_ONLY));
			return false;
		}
		
		//Cycles through all games
		for(Game g : GameManager.getInstance().getGames()) {
			//If the game hasn't started yet
			if(g.getState().equals(GameState.WAITING)) {
				
				Team lowest = null;
				
				//Gets the lowest team size
				for(Team t : g.getTeams()) {
					if(lowest == null)
						lowest = t;
					if(lowest.getPlayers().size() > t.getPlayers().size())
						lowest = t;
				}
				
				//Adds players to the queue/to the games
				//If solo queueing
				if(kwp.getParty().equals(null)) {
					lowest.addPlayer(kwp);
				}
				//If queueing with party
				else {
					
				}
			}
			
			
			//If the game is in phase1 or phase2
			else if(g.getState().equals(GameState.PHASE1) || g.getState().equals(GameState.PHASE2)) {
				for(Team t : g.getTeams()) {
					//If solo queueing
					if(kwp.getParty().equals(null)) {
						
					}
					//If queueing with party
					else {
						
					}
				}
			}
		}
		return false;
	}
	
	public void removePlayer(String uuid) {
		if(!(QueueManager.getInstance().contains(uuid))) return;
		queue.remove(kwPlayerManager.getInstance().getPlayer(uuid));
		Bukkit.getServer().getPlayer(UUID.fromString(uuid)).sendMessage(kwMessages.msg(kwMessages.QUEUE_CANCEL));
		for(kwPlayer p : kwPlayerManager.getInstance().getPlayer(uuid).getParty()) {
			queue.remove(p);
			Bukkit.getServer().getPlayer(UUID.fromString(p.getUuid())).sendMessage(kwMessages.msg(kwMessages.QUEUE_CANCEL));
		}
	}
	
	public int getSize() {
		return queue.size();
	}
	
	public boolean contains(String uuid) {
		for(kwPlayer p : queue) {
			if(p.getUuid().equals(uuid))
				return true;
		}
		return false;
	}
	
	public kwPlayer getPlayer(String uuid) {
		for(kwPlayer p : queue) {
			if(p.getUuid().equals(uuid)) {
				return p;
			}
		}
		
		return null;
	}
}
