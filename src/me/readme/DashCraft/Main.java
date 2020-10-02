package me.readme.DashCraft;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

	// retrieve the configuration object
	FileConfiguration config = this.getConfig();

	// Fired when plugin is first enabled
	@Override
	public void onEnable() {

		// add some options to the config
		config.addDefault("useDashCraft", true);
		config.addDefault("useWorldEdit", true);
		config.addDefault("useFAWE", true);
		config.options().copyDefaults(true);
		saveConfig();

		// Enable our class to check for new players using onPlayerJoin()
		getServer().getPluginManager().registerEvents(this, this);

		// kit command
//		this.getCommand("kit").setExecutor(new CommandKit());
		// dash command
		this.getCommand("dash").setExecutor(new CreativeCommand(config));
		this.getCommand("dash").setTabCompleter(new CreativeTab());

		// MyListener (external file)
		getServer().getPluginManager().registerEvents(new CreativeListener(), this);

	}

	// Fired when plugin is disabled
	@Override
	public void onDisable() {
		saveConfig();
	}

}
