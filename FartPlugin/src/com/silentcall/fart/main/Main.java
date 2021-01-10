package com.silentcall.fart.main;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.silentcall.fart.events.CrouchEvent;
	
	public class Main extends JavaPlugin {
		
		@Override
		public void onEnable() {
			getLogger().info("Farty mc farty started..");
			
			PluginManager pm = getServer().getPluginManager();
			
			pm.registerEvents(new CrouchEvent(), this);
			
			this.getCommand("fart").setExecutor(new CmdHandler());
			
			
		}
		
		@Override
		public void onDisable() {
			getLogger().info("Farty mc farty closing..");
		}

	}