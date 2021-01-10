package com.silentcall.fart.main;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.silentcall.fart.events.CrouchEvent;

public class CmdHandler implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender ply, Command cmd, String desc, String[] args) {
		
		if(ply instanceof Player) {
			Player player = (Player) ply;
			
			if(cmd.getName().equals("fart") && !(CrouchEvent.FartRequest.get(player) != null) && !(CrouchEvent.timers.get(player) != null)) { // should inverse so null -> true
				CrouchEvent.FartRequest.put(player, true); // player has run /fart, we can edit the hashmap to allow to fart.
				player.sendMessage(ChatColor.GREEN + "Fart ready. Crouch to release the kraken!");
			} else {
				player.sendMessage(ChatColor.GREEN + "You have no gas left! Please wait.");
			}
			
			
		}
		
		return true;
	}

}
