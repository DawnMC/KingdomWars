package me.dawn.kingdomwars;

import java.util.ArrayList;

import org.bukkit.Location;

public class Team {
	private TeamType teamtype;
	private ArrayList<kwPlayer> players;
	private int throneHealth;
	private int developmentLevel;
	private int gatheringLevel;
	private int combatLevel;
	private int defenseLevel;
	private int throneLevel;
	private Location spawn;
	private Location nexus;
	
	public Team(TeamType tt, Location spawn, Location nexus) {
		this.teamtype = tt;
		this.players = new ArrayList<kwPlayer>();
		this.throneHealth = 75;
		this.developmentLevel = 1;
		this.setGatheringLevel(1);
		this.combatLevel = 1;
		this.defenseLevel = 1;
		this.throneLevel =1;
		this.setSpawn(spawn);
		this.nexus = nexus;
	}
	
	public void addPlayer(kwPlayer p) {
		players.add(p);
	}
	
	public void removePlayer(kwPlayer p) {
		players.remove(p);
	}
	
	public ArrayList<kwPlayer> getPlayers() {
		return players;
	}
	public void setPlayers(ArrayList<kwPlayer> players) {
		this.players = players;
	}
	public int getDevelopmentLevel() {
		return developmentLevel;
	}
	public void setDevelopmentLevel(int developmentLevel) {
		this.developmentLevel = developmentLevel;
	}
	public int getCombatLevel() {
		return combatLevel;
	}
	public void setCombatLevel(int combatLevel) {
		this.combatLevel = combatLevel;
	}
	public int getDefenseLevel() {
		return defenseLevel;
	}
	public void setDefenseLevel(int defenseLevel) {
		this.defenseLevel = defenseLevel;
	}

	public TeamType getTeamtype() {
		return teamtype;
	}

	public void setTeamtype(TeamType teamtype) {
		this.teamtype = teamtype;
	}

	public int getGatheringLevel() {
		return gatheringLevel;
	}

	public void setGatheringLevel(int gatheringLevel) {
		this.gatheringLevel = gatheringLevel;
	}

	public Location getNexus() {
		return nexus;
	}

	public void setNexus(Location nexus) {
		this.nexus = nexus;
	}

	public Location getSpawn() {
		return spawn;
	}

	public void setSpawn(Location spawn) {
		this.spawn = spawn;
	}

	public int getThroneHealth() {
		return throneHealth;
	}

	public void setThroneHealth(int throneHealth) {
		this.throneHealth = throneHealth;
	}

	public int getThroneLevel() {
		return throneLevel;
	}

	public void setThroneLevel(int throneLevel) {
		this.throneLevel = throneLevel;
	}
	
	
	
}
