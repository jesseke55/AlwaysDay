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

        if(!getConfig().contains("worlds")) {
            getConfig().set("worlds", new ArrayList<>());
            saveConfig();
        }

        Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, () -> {
            for(String worldName : getConfig().getStringList("worlds")) {
                if(Bukkit.getWorld(worldName) != null) {
                   Bukkit.getWorld(worldName).setTime(0L); 
                }
            }
        }, 60L, 60L);
    }
}
