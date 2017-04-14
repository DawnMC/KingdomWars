package me.dawn.kingdomwars.managers;

import java.util.ArrayList;

import me.dawn.kingdomwars.kwPlayer;


public class kwPlayerManager {
	/*******************************************************************************************
	 *                                    Singleton setup
	 *******************************************************************************************/
	private kwPlayerManager() { }

	private static kwPlayerManager instance = new kwPlayerManager();
	
	public static kwPlayerManager getInstance() {
		return instance;
	}
	
	ArrayList<kwPlayer> onlinePlayers = new ArrayList<kwPlayer>();;
	
	public ArrayList<kwPlayer> getPlayers() {
		return onlinePlayers;
	}
	public void addPlayer(kwPlayer p) {
		onlinePlayers.add(p);
	}
	
	public void removePlayer(kwPlayer p) {
		onlinePlayers.remove(p);
	}

	public kwPlayer getPlayer(String uuid) {
		for(kwPlayer p : onlinePlayers) {
			if(p.getUuid().equals(uuid))
				return p;
		}
		return null;	
	}
}
