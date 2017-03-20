package fr.developpeur26.uhc;

import org.bukkit.configuration.Configuration;

public class UHCConfiguration {
	
	// Variables for Config
	public static int minPlayers;
	public static boolean enable = true;
	
	/**
	 * Load configuration
	 */
	public static void load() {
		UHC.instance.saveDefaultConfig();
		Configuration config = UHC.instance.getConfig();
		enable = config.getBoolean("enable");
		minPlayers = config.getInt("min-player-game");
	}
	

}
