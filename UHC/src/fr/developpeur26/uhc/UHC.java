package fr.developpeur26.uhc;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.developpeur26.uhc.commands.UHCCommand;
import fr.developpeur26.uhc.listeners.PlayerConnectListener;

/**
 * Class by me
 * @author KingRider
 */
public class UHC extends JavaPlugin {
	
	// Instance of UHC
	public static UHC instance;
	
	// Variable line
	public static String line = "§7---------------------";
	
	public ArrayList<Player> playerInGame = new ArrayList<>();
	
	/**
	 * Get instance by Class UHC
	 * @return
	 */
	public static UHC getInstance() {
		return instance;
	}
	
	// Enable plugin
	@Override
	public void onEnable() {
		instance = this;
		// Events
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(new PlayerConnectListener(), this);
		// Commands
		this.getCommand("uhc").setExecutor(new UHCCommand());
		// Config
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
		UHCConfiguration.load();
		log(ChatColor.YELLOW + "[UHC] is enable with configuration !");
		if (!UHCConfiguration.enable) {
			pm.disablePlugin(this);
			log(ChatColor.DARK_RED + "The plugin is disable by Config");
			return;
		}
	}
	
	// Disable plugin
	@Override
	public void onDisable() {
		log(ChatColor.YELLOW + "[UHC] is disable !");
	}
	
	public static void log(String string) {
		Bukkit.getConsoleSender().sendMessage(string);
	}

}
