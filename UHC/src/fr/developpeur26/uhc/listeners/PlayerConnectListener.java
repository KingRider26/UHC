package fr.developpeur26.uhc.listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.developpeur26.uhc.UHC;
import fr.developpeur26.uhc.manager.GameManager;
import fr.developpeur26.uhc.states.UHCState;

public class PlayerConnectListener implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		
		int count = Bukkit.getOnlinePlayers().size();
		int max = Bukkit.getMaxPlayers();
		
		// State is Wait
		if (UHCState.isState(UHCState.WAIT)) {
			event.setJoinMessage("§7[§eUHC§7] §6" + player.getName() + " §ea join the game §a(" + count + "/" + max + ")");
			UHC.getInstance().playerInGame.add(player);
			player.setGameMode(GameMode.ADVENTURE);
			player.setMaxHealth(20);
			player.setHealth(20);
			player.setFoodLevel(20);
			player.getInventory().clear();
			if((Bukkit.getOnlinePlayers().size() >= UHC.getInstance().getConfig().getInt("min-player-game")) && (!(GameManager.start))) {
				new GameManager().runTaskTimer(UHC.instance, 0L, 20L);
				GameManager.start = true;
			}
			// Else if state is in Game, the player is Spectator
		} else if (!UHCState.isState(UHCState.WAIT)) {
			player.setGameMode(GameMode.SPECTATOR);
		}
		
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		int count = Bukkit.getOnlinePlayers().size() - 1;
		int max = Bukkit.getMaxPlayers();
		UHC.getInstance().playerInGame.remove(event.getPlayer());
		event.setQuitMessage("§7[§eUHC§7] §6" + event.getPlayer().getName() + " §ahas disconnected ! §a(" + count + "/" + max + ")");
	}

}
