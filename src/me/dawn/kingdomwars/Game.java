package me.dawn.kingdomwars;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import me.dawn.kingdomwars.managers.CustomInventoryManager;
import me.dawn.kingdomwars.managers.CustomItemManager;
import me.dawn.kingdomwars.managers.QueueManager;

public class Game {
	private String world;
	private ArrayList<Team> teams;
	private GameState state;
	private long startTime;
	private ScoreboardManager manager = Bukkit.getScoreboardManager();
	private Scoreboard board = manager.getNewScoreboard();
	private Objective obj = board.registerNewObjective("test", "dummy");
	private int countdown = 600;
	private int task1;
	private String time;
	
	public Game(String world) {
		this.world = world;
		teams = new ArrayList<Team>();
		for(TeamType t : TeamType.values()) {
			switch(t) {
			case RED:
				teams.add(new Team(t, new Location(Bukkit.getWorld(world), 6.5, 65, 6.5, 0, 0), 
						new Location(Bukkit.getWorld(world), 10, 66, 10)));
			case BLUE:
				teams.add(new Team(t, new Location(Bukkit.getWorld(world), -5.5, 65, 6.5, 0, 0), 
						new Location(Bukkit.getWorld(world), -10, 66, 10)));
			case GREEN:
				teams.add(new Team(t, new Location(Bukkit.getWorld(world), -5.5, 65, -5.5, 0, 0), 
						new Location(Bukkit.getWorld(world), -10, 66, -10)));
			case YELLOW:
				teams.add(new Team(t, new Location(Bukkit.getWorld(world), 6.5, 65, -5.5, 0, 0), 
						new Location(Bukkit.getWorld(world), 10, 66, -10)));
			}
		}
		setState(GameState.WAITING);
		setStartTime(-1);
	}
	
	public boolean startGame() {
		
		//Checks to make sure game needs/can start
		/*for(Team t : teams)
			if(t.getPlayers().size() < 4) return false;*/
		if(this.getState() != GameState.WAITING) return false;
		
		//Starts the game
		this.state = GameState.PHASE1;
		this.startTime = System.currentTimeMillis();
		
		for(Team t : teams) {
			for(kwPlayer p : t.getPlayers()) {
				Player p2 = Bukkit.getServer().getPlayer(UUID.fromString(p.getUuid()));
				QueueManager.getInstance().removePlayer(p.getUuid());
				if(p2.isOnline()) {
					p2.teleport(t.getSpawn());
					p2.getInventory().clear();
					p2.getInventory().setItem(0, CustomItemManager.getInstance().getItem("WoodenSword"));
					p2.getInventory().setItem(7, CustomItemManager.getInstance().getItem("WoodenAxe"));
					p2.getInventory().setItem(8, CustomItemManager.getInstance().getItem("WoodenPickaxe"));
					p2.setScoreboard(board);
					p.setArmorContents(p2.getInventory().getArmorContents());
					p.setInventoryContents(p2.getInventory().getContents());
					p.setTeam(t);
					p.setGame(this);
					CustomInventoryManager.getInstance().getInventory("ClassSelection").open(p2);
				}
			}
		}
		task1 = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(KingdomWars.getPlugin(), new Runnable() {
			public void run() {
				countdown--;
				int minutes = countdown / 60;
				int seconds = countdown % 60;
				String m = Integer.toString(minutes);
				String s = Integer.toString(seconds);
				if(m.length() == 1) {
					m = "0" + m;
				}
				if(s.length() == 1) {
					s = "0" + s;
				}
				time = m + ":" + s;

				if(countdown == 0) {
					nextPhase();
					if(state.equals(GameState.PHASE5))
						countdown = 1800;
					else
						countdown = 600;
				}
				updateScoreboard();
			}
		}, 0L, 20L);
		
		return true;
	}
	
	public void setScore(Objective objective, String name, int index) {
		Score score = objective.getScore(name);
		score.setScore(index);
	}
	
	public void updateScoreboard() {
		for(String entry : board.getEntries()) {
			board.resetScores(entry);
		}
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		obj.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "NextMC " + ChatColor.GRAY + ChatColor.STRIKETHROUGH + "| " + ChatColor.WHITE + this.getState());
		setScore(obj, ChatColor.WHITE + "" + ChatColor.BOLD + ChatColor.STRIKETHROUGH + "----------------" + ChatColor.RED + "", 7);
		setScore(obj, ChatColor.WHITE + "" + ChatColor.BOLD + ChatColor.STRIKETHROUGH + "----------------", 1);
		for(Team t : this.getTeams()) {
			if(t.getTeamtype().equals(TeamType.RED)) {
				setScore(obj, ChatColor.RED + "Red Throne: " + ChatColor.WHITE + t.getThroneHealth(), 6);
			}
			else if(t.getTeamtype().equals(TeamType.BLUE)) {
				setScore(obj, ChatColor.BLUE + "Blue Throne: " + ChatColor.WHITE + t.getThroneHealth(), 5);
			}
			else if(t.getTeamtype().equals(TeamType.GREEN)) {
				setScore(obj, ChatColor.GREEN + "Green Throne: " + ChatColor.WHITE + t.getThroneHealth(), 4);
			}
			else if(t.getTeamtype().equals(TeamType.YELLOW)) {
				setScore(obj, ChatColor.YELLOW + "Yellow Throne: " + ChatColor.WHITE + t.getThroneHealth(), 3);
			}
		}
		
		if(state.equals(GameState.PHASE5)) {
			setScore(obj, ChatColor.DARK_PURPLE + "Game Ends: " + ChatColor.WHITE + time, 2);
		}
		else {
			setScore(obj, ChatColor.DARK_PURPLE + "Next Phase: " + ChatColor.WHITE + time, 2);
		}
		
		for(Player p : Bukkit.getServer().getWorld(this.world).getPlayers()) {
			p.setScoreboard(this.board);
		}
	}
	
	public void destroyThrone(Team t) {
		t.getNexus().getBlock().setType(Material.BEDROCK);
		t.getNexus().getWorld().playEffect(t.getNexus(), Effect.SMOKE, 20);
		t.setThroneHealth(0);
		this.updateScoreboard();
	}
	
	public void nextPhase() {
		switch(state) {
		case PHASE1:
			state = GameState.PHASE2;
			return;
		case PHASE2:
			state = GameState.PHASE3;
			return;
		case PHASE3:
			state = GameState.PHASE4;
			return;
		case PHASE4:
			state = GameState.PHASE5;
			return;
		default:
			return;
		}
	}

	public GameState getState() {
		return state;
	}

	public void setState(GameState state) {
		this.state = state;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	
	public ArrayList<Team> getTeams() {
		return this.teams;
	}

	public String getWorld() {
		return world;
	}

	public void setWorld(String world) {
		this.world = world;
	}
	
	public GameState getPhase() {
		return this.state;
	}

	public int getTask1() {
		return task1;
	}

	public void setTask1(int task1) {
		this.task1 = task1;
	}
}
