package me.readme.DashCraft;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class CreativeListener implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		// broadcast a greeting to the whole server
		// Bukkit.broadcastMessage("Welcome to the server!");

//		event.setJoinMessage("Welcome, " + event.getPlayer().getName() + " to DashCraft Creative Server!");
		// clear clipboard so /copy command needs to be executed
//		Bukkit.getServer().dispatchCommand(event.getPlayer(), "/schem clear");
		event.getPlayer().performCommand("/schem clear");	// clear clipboard
//		event.setJoinMessage(ChatColor.YELLOW + "Type \"t\" to open Chat and then Enter \"/plot auto\" to claim a plot for building.\nEnter \"/dash\" to view usage for non-fungible-token creation.\nVisit https://github.com/readme55/Dashcraft for more info!" );
	}

	@EventHandler
	public void onCommandEnter(PlayerCommandPreprocessEvent pe) {
		Player p = pe.getPlayer();
		String msg = pe.getMessage();
//		if (msg.startsWith("/f create") || msg.startsWith("/faction create")) {
//			p.sendMessage("Caught in Listener, Canceling");
//			pe.setCancelled(true);
//		}

	}

}
