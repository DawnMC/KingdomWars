package me.dawn.kingdomwars;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.dawn.kingdomwars.managers.QueueManager;
import me.dawn.kingdomwars.managers.SettingsManager;
import me.dawn.kingdomwars.managers.kwPlayerManager;

public class kwPlayer {
	private String uuid;
	private HashMap<String, Object> data;
	private HashMap<String, Integer> inventory;
	
	private HashMap<Integer, String> knight;
	private HashMap<Integer, String> elf;
	private HashMap<Integer, String> dwarf;
	private HashMap<Integer, String> support;
	private HashMap<Integer, String> assassin;
	
	private ArrayList<kwPlayer> party;
	private boolean isPartyLeader;
	private boolean isPartyMember;
	private Game game;
	private Team team;
	private ClassType currentClass = ClassType.DEFAULT;
	
	private ItemStack[] inventoryContents;
	private ItemStack[] armorContents;
	
	public kwPlayer(Player p) throws SQLException {
		this.uuid = p.getUniqueId().toString();		
		this.data = new HashMap<String, Object>();
		this.inventory = new HashMap<String, Integer>();
		data.put("name", p.getName());
		data.put("level", (Double) 1.0);
		data.put("elo", 1500);
		data.put("playTime", 0);
		data.put("lastLogin", System.currentTimeMillis());
		
		data.put("warriorLevel", 1);
		data.put("elfLevel", 1);
		data.put("dwarfLevel", 1);
		data.put("mageLevel", 1);
		data.put("assassinLevel", 1);
		
		//stats
		data.put("coins", 0);
		data.put("wins", 0);
		data.put("loses", 0);
		data.put("meleeKills", 0);
		data.put("archerKills", 0);
		data.put("deaths", 0);
		data.put("oresMined", 0);
		data.put("throneDamage", 0);
		
		//king stats
		data.put("kingDescription", "");
		data.put("kingWins", 0);
		data.put("kingLosses", 0);
		data.put("kingRating", 0);
		
		save();
		
		
		this.party = new ArrayList<kwPlayer>();
		this.isPartyLeader = true;
		this.isPartyMember = false;
		this.game = null;
		this.armorContents = null;
		this.inventoryContents = null;
		this.team = null;
	}
	
	@SuppressWarnings("unchecked")
	public kwPlayer(Player p, ResultSet res) throws SQLException {
		this.uuid = p.getUniqueId().toString();
		this.data = (HashMap<String, Object>) SettingsManager.getInstance().jsonToMap(res.getString("data"));
		this.inventory = (HashMap<String, Integer>) SettingsManager.getInstance().jsonToMap(res.getString("inventory"));
		
		
		this.party = new ArrayList<kwPlayer>();
		this.isPartyLeader = true;
		this.isPartyMember = false;
		this.game = null;
		this.armorContents = null;
		this.inventoryContents = null;
		this.team = null;
	}

	public void save() throws SQLException {
		Statement statement = SettingsManager.getInstance().getConnection().createStatement();
		statement.executeUpdate(saveMap("data", this.data));
		statement.executeUpdate(saveMap("inventory", this.inventory));
	}
	
	public boolean addPartyMember(String uuid) {
		if(party.size() >= 8) 
			return false;
		
		party.add(kwPlayerManager.getInstance().getPlayer(uuid));
		kwPlayerManager.getInstance().getPlayer(uuid).setPartyMember(true);
		return true;
	}
	
	public boolean removePartyMember(String uuid) {
		for(kwPlayer k : party) {
			if(k.getUuid().equals(uuid)) {
				party.remove(kwPlayerManager.getInstance().getPlayer(uuid));
				kwPlayerManager.getInstance().getPlayer(uuid).setPartyMember(false);
				if(QueueManager.getInstance().contains(uuid)) {
					QueueManager.getInstance().removePlayer(uuid);
					
				}
				return true;
			}
		}
		return false;
	}
	
	public String saveMap(String name, HashMap<String, ?> map) {
		return "UPDATE KingdomWars SET " + name + " = '" + SettingsManager.getInstance().mapToJson(map) + "' WHERE uuid = '" + this.uuid + "';";
	}
	
	public String getUuid() {
		return this.uuid;
	}
	
	public Object getData(String key) {
		return data.get(key);
	}
	
	public void setData(String key, Object value) {
		data.put(key, value);
	}
	
	public ArrayList<kwPlayer> getParty() {
		return this.party;
	}

	public boolean isPartyLeader() {
		return isPartyLeader;
	}

	public void setPartyLeader(boolean isPartyLeader) {
		this.isPartyLeader = isPartyLeader;
	}

	public boolean isPartyMember() {
		return isPartyMember;
	}

	public void setPartyMember(boolean isPartyMember) {
		this.isPartyMember = isPartyMember;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public ItemStack[] getArmorContents() {
		return armorContents;
	}

	public void setArmorContents(ItemStack[] armorContents) {
		this.armorContents = armorContents;
	}

	public ItemStack[] getInventoryContents() {
		return inventoryContents;
	}

	public void setInventoryContents(ItemStack[] inventoryContents) {
		this.inventoryContents = inventoryContents;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public ClassType getCurrentClass() {
		return currentClass;
	}

	public void setCurrentClass(ClassType currentClass) {
		this.currentClass = currentClass;
	}

	public HashMap<Integer, String> getKnight() {
		return knight;
	}

	public void setKnight(HashMap<Integer, String> knight) {
		this.knight = knight;
	}

	public HashMap<Integer, String> getElf() {
		return elf;
	}

	public void setElf(HashMap<Integer, String> elf) {
		this.elf = elf;
	}

	public HashMap<Integer, String> getDwarf() {
		return dwarf;
	}

	public void setDwarf(HashMap<Integer, String> dwarf) {
		this.dwarf = dwarf;
	}

	public HashMap<Integer, String> getSupport() {
		return support;
	}

	public void setSupport(HashMap<Integer, String> support) {
		this.support = support;
	}

	public HashMap<Integer, String> getAssassin() {
		return assassin;
	}

	public void setAssassin(HashMap<Integer, String> assassin) {
		this.assassin = assassin;
	}
}
