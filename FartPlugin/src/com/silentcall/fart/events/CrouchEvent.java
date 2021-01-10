package com.silentcall.fart.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class CrouchEvent implements Listener {
	
	
	
	static public HashMap<Player, Timer> timers = new HashMap<Player, Timer>();
	
	static public HashMap<Player, Boolean> FartRequest = new HashMap<Player, Boolean>();
	
	public CrouchEvent() {
		
	};
	
	private void Shart(Player ply) {
		List <String> list= new ArrayList<String>();
		list.add("Uh oh stinky haha..");
		double x = Math.random() * 10;
		
		if(x < 0.75) {
			ItemStack nugget = new ItemStack(Material.COAL);
			ItemMeta meta = nugget.getItemMeta();
			
			meta.setDisplayName(ply.getDisplayName() + "'s poop.");
			meta.setLore(list);
			nugget.setItemMeta(meta);
			ply.getInventory().addItem(nugget);
		}
		
	};
	
	PotionEffect x = new PotionEffect(PotionEffectType.CONFUSION, 100, 5);
	
	@EventHandler
	public void onToggleSneak(PlayerToggleSneakEvent ply) {
		
		Player player = ply.getPlayer();
		
		if(ply.isSneaking() && CheckFart(player)) {
			
		player.playSound(player.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1, 3);
		Shart(player);
		EnableFartTimer(player);
		
		for(Player other: Bukkit.getOnlinePlayers()) {
			if(other.getLocation().distance(player.getPlayer().getLocation()) < 4 && other != player && other.getWorld().equals(player.getWorld())) {
				other.sendMessage(ChatColor.GREEN + player.getDisplayName() + " has farted on you!");
				other.addPotionEffect(x);
			}
		};
	};
	};
	
	private void EnableFartTimer(Player ply) {
		Timer x = new Timer();
		TimerTask y = new TimerTask() {

			public void run() {
				timers.remove(ply);
			}
			
		};
		timers.put(ply, x);
		x.schedule(y, 300000); // 5min timer for fart
	}
	
	private boolean CheckFart(Player ply) {
		if(timers.get(ply) != null) {
			return false; // fart exists
		}else if(FartRequest.containsKey(ply)) {
			FartRequest.remove(ply); // Removes player from active command.
			return true;
		} 
		return false; // user has not set
	}
	
	
}
