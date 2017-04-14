package me.dawn.kingdomwars.managers;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import me.dawn.kingdomwars.MySQL;

public class SettingsManager {
	
	/*******************************************************************************************
	 *                                    Singleton setup
	 *******************************************************************************************/
	private SettingsManager() { }

	private static SettingsManager instance = new SettingsManager();
	
	public static SettingsManager getInstance() {
		return instance;
	}
	
	/*******************************************************************************************
	 *                           Data base methods and variables
	 *******************************************************************************************/
	MySQL MySQL = new MySQL("nutbuster.bot.nu", "3306", "minecraft", "paperspigot", "ilovenut");
	
	public Connection c = null;
	
	public void openConnection()
	{
		try {
			c = MySQL.openConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection()
	{
		return c;
	}
	
	public ResultSet getRow(String uuid) throws SQLException
	{
		Statement statement = c.createStatement();
		ResultSet res = statement.executeQuery("SELECT * FROM KingdomWars WHERE uuid = '" + uuid + "';");
		
		return res;
	}
	
	/*******************************************************************************************
	 *                                     Config Setup
	 *******************************************************************************************/
	public void setupConfig(Plugin p) {
		if (!p.getDataFolder().exists()) p.getDataFolder().mkdir();
		
		file = new File(p.getDataFolder(), "config.yml");
		
		if (!file.exists()) {
			try { file.createNewFile(); }
			catch (Exception e) { e.printStackTrace(); }
		}
		
		config = YamlConfiguration.loadConfiguration(file);
	}
	
	private File file;
	private FileConfiguration config;
	
	public void set(String path, Object value) {
		config.set(path, value);
		saveConfig();
	}
	
	public ConfigurationSection createConfigurationSection(String path) {
		ConfigurationSection cs = config.createSection(path);
		saveConfig();
		return cs;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T configGet(String path) {
		return (T) config.get(path);
	}
	
	public boolean configContains(String path) {
		return config.contains(path);
	}
	
	public void saveConfig() {
		try { config.save(file); }
		catch (Exception e) { e.printStackTrace(); }
	}

	public void reloadConfig() {
		config = YamlConfiguration.loadConfiguration(file);
	}
	
	/*******************************************************************************************
	 *                           Hashmap/Json conversion methods
	 *******************************************************************************************/
	
	public HashMap<String, ?> jsonToMap(String json) {
		return new Gson().fromJson(json, new TypeToken<HashMap<String, Object>>(){}.getType());
    }
	
	public String mapToJson(HashMap<String, ?> map) {
		Gson gson = new Gson(); 
		String json = gson.toJson(map).toString();
		
		return json;
	}
}
