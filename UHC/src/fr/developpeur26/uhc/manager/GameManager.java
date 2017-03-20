package fr.developpeur26.uhc.manager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.developpeur26.uhc.UHC;
import fr.developpeur26.uhc.states.UHCState;

/**
 * Class by me
 * @author KingRider26
 */
public class GameManager extends BukkitRunnable {
	
	// Variables for Timer System
	public static int timer = 120;
	public static boolean start = false;
	
	// Variables for Timer start begin
	public static int time = 11;
	public static boolean forcedTime = false;
	public static int task;
	
	/* Constructor for GameManager*/
	public GameManager() {}
	
	@Override
	public void run() {
		
		/* SECURITE STATUS */
		if(!(UHCState.isState(UHCState.WAIT))) {
			timer = 60;
			start = false;
			this.cancel();
			return;
		}
		
		if (forcedTime) {
			this.cancel();
			return;
		}
		
		/* MANQUE DE JOUEURS */
		if(Bukkit.getOnlinePlayers().size() < UHC.getInstance().getConfig().getInt("min-player-game")) {
			Bukkit.broadcastMessage(ChatColor.RED + "There is not enough player to start.");
			timer = 60;
			start = false;
			sendXPExtern();
			this.cancel();
			return;
		}
		
		/* DEBUT DE GAME */
		if(timer == 0){
			this.cancel();
			return;
		}
		
		/* JEU DE TIMER */
		if((timer == 60) || (timer == 30) || (timer == 15) || (timer == 10) || (timer <= 5 && timer != 0)){
			Bukkit.broadcastMessage("§7[§eUHC§7] §6The game start in §e" + timer + getSecond());
		}
		sendXPExtern();
		timer--;
	}
	
	/**
	 * Method for begin game fast
	 * @param player
	 */
	public static void start(Player player) {
		task = Bukkit.getScheduler().scheduleSyncRepeatingTask(UHC.getInstance(), new Runnable() {
			
			@Override
			public void run() {
				time--;
				
				if ((time == 10) || (time <= 5 && time != 0)) {
					Bukkit.broadcastMessage("§7[§eUHC§7] §6The game start in §e" + time + getSecond());
				}
				sendXPExtern();
				
				if (time == 0) {
			
					Bukkit.getScheduler().cancelTask(task);
					return;
				}
				
			}
		}, 20, 20);
	}
	
	/**
	 * System of XP
	 */
	public static void sendXPExtern() {
		for(Player player : Bukkit.getOnlinePlayers()) {
			player.setLevel(timer);
		}
	}
	
	/**
	 * Getter the second
	 * @return
	 */
	private static String getSecond(){
        return timer == 1 ? " second" : " seconds";
    }

}
