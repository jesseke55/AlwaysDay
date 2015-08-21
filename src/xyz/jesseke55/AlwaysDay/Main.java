package xyz.jesseke55.AlwaysDay;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

/**
 * Created by jesse on 21-8-15.
 */
public class Main extends JavaPlugin implements Listener {


    FileConfiguration config = getConfig();



    public void onEnable(){
        getLogger().info("-------------------------");
        getLogger().info("");
        getLogger().info("Name: " + getDescription().getName() );
        getLogger().info("Version: " + getDescription().getVersion() );
        getLogger().info("Authors: " + getDescription().getAuthors() );
        getLogger().info("Website: " + getDescription().getWebsite() );
        getLogger().info("");
        getLogger().info(ChatColor.GREEN + "Please leave a rate if you like the plugin!");
        getLogger().info("");
        getLogger().info("-------------------------");


        config.addDefault("enabled-world:", "world");

        config.options().copyDefaults(true);
        saveConfig();



        BukkitScheduler scheduler = Bukkit.getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            public void run() {
                Bukkit.getWorld(config.getString("enabled-world")).setTime(0L);
            }
        }, 60L, 60L);

    }


}
