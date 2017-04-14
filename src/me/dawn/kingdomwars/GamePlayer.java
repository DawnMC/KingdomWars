package me.dawn.kingdomwars;

public class GamePlayer {
	private String uuid;
	private int deaths;
	private int nexusDamage;
	private int oresMined;
	private int bowKills;
	private int meleeKills;
	
	public GamePlayer(kwPlayer p) {
		this.uuid = p.getUuid();
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public int getDeaths() {
		return deaths;
	}

	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}

	public int getNexusDamage() {
		return nexusDamage;
	}

	public void setNexusDamage(int nexusDamage) {
		this.nexusDamage = nexusDamage;
	}

	public int getOresMined() {
		return oresMined;
	}

	public void setOresMined(int oresMined) {
		this.oresMined = oresMined;
	}

	public int getBowKills() {
		return bowKills;
	}

	public void setBowKills(int bowKills) {
		this.bowKills = bowKills;
	}

	public int getMeleeKills() {
		return meleeKills;
	}

	public void setMeleeKills(int meleeKills) {
		this.meleeKills = meleeKills;
	}
}
