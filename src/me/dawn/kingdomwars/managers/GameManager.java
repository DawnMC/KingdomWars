package me.dawn.kingdomwars.managers;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.plugin.Plugin;

import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.api.MultiverseWorld;

import me.dawn.kingdomwars.Game;
import me.dawn.kingdomwars.KingdomWars;


public class GameManager {
	/*******************************************************************************************
	 *                                    Singleton setup
	 *******************************************************************************************/
	private GameManager() { }

	private static GameManager instance = new GameManager();
	
	public static GameManager getInstance() {
		return instance;
	}
	
	ArrayList<Game> games = new ArrayList<Game>();
	
	public void setup() {
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(KingdomWars.getPlugin(), new Runnable() {
			public void run() { 
				for(MultiverseWorld w : getMultiverseCore().getMVWorldManager().getMVWorlds()) {
					if(w.getName().contains("beta"))
						games.add(new Game(w.getName()));
				}
				
				System.out.println("--------------------------------");
				for(Game g : games) {
					System.out.println(g.getWorld());
					System.out.println("--------------------------------");
				}
			}
		}, 20L);
	}
	
	//Unloading maps, to rollback maps. Will delete all player builds until last server save
    public static void unloadMap(String world){
        if(Bukkit.getServer().unloadWorld(Bukkit.getServer().getWorld(world), false)){
            System.out.println("Unloaded " + world + " successfully");
        }else{
            System.out.println("Could not unload " + world);
        }
    }
    //Loading maps (MUST BE CALLED AFTER UNLOAD MAPS TO FINISH THE ROLLBACK PROCESS)
    public static void loadMap(String world){
        Bukkit.getServer().createWorld(new WorldCreator(world));
    }
 
    //Maprollback method, because were too lazy to type 2 lines
    public static void rollback(String world){
        unloadMap(world);
        loadMap(world);
    }
	
	public ArrayList<Game> getGames() {
		return games;
	}
	public void addGame(Game g) {
		games.add(g);
	}
	
	public void removeGame(Game g) {
		games.remove(g);
	}
	
	public Game getGame(String world) {
		for(Game g : games) {
			if(g.getWorld() == world)
				return g;
		}
		return null;	
	}
	
	public MultiverseCore getMultiverseCore() {
        Plugin plugin = KingdomWars.getPlugin().getServer().getPluginManager().getPlugin("Multiverse-Core");
 
        if (plugin instanceof MultiverseCore) {
            return (MultiverseCore) plugin;
        }
 
        throw new RuntimeException("MultiVerse not found!");
    }
}
