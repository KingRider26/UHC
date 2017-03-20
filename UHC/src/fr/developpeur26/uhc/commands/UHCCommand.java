package fr.developpeur26.uhc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.developpeur26.uhc.UHC;
import fr.developpeur26.uhc.manager.GameManager;

public class UHCCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (!(sender instanceof Player)) {
			UHC.log("Command not possible !");
			return true;
		}

		Player player = (Player) sender;

		// Egal à 0
		if (args.length == 0) {
			displayHelp(player);
		}

		// Argument plus grand que 0
		if (args.length > 0) {
			String subCommand = args[0];
			if (subCommand.equalsIgnoreCase("start")) {
				GameManager.forcedTime = true;
				GameManager.start(player);
			}
		}

		return true;
	}

	/**
	 * Affiche l'aide
	 * 
	 * @param player
	 */
	private void displayHelp(Player player) {
		player.sendMessage(UHC.line);
		player.sendMessage("§e/uhc start | §fThe game start is fast");
	}

}
