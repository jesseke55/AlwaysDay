package xyz.jesseke55.AlwaysDay;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.ArrayList;

/**
 * Created by jesse on 21-8-15.
 */
public class Main extends JavaPlugin implements Listener {



    String prefix = ChatColor.translateAlternateColorCodes('&', "&a[&bAlwaysDay&a] &6");

    FileConfiguration config = getConfig();

    public void onEnable() {
        getLogger().info("-------------------------");
        getLogger().info("");
        getLogger().info("Name: " + getDescription().getName());
        getLogger().info("Version: " + getDescription().getVersion());
        getLogger().info("Authors: " + getDescription().getAuthors());
        getLogger().info("Website: " + getDescription().getWebsite());
        getLogger().info("");
        getLogger().info(ChatColor.GREEN + "Please leave a rate if you like the plugin!");
        getLogger().info("");
        getLogger().info("-------------------------");

        if (!getConfig().contains("worlds")) {
            getConfig().set("worlds", new ArrayList<>());
            saveConfig();
        }

        saveDefaultConfig();


        Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, () -> {
            for (String worldName : getConfig().getStringList("worlds")) {
                if (Bukkit.getWorld(worldName) != null) {
                    Bukkit.getWorld(worldName).setTime(0L);
                }
            }
        }, 60L, 60L);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        {
            if (cmd.getName().equalsIgnoreCase("alwaysday")) {
                if (args.length == 0) {
                    if (sender.hasPermission("alwaysday.main")) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a" + getDescription().getName() + " " + getDescription().getVersion() + " Made by: " + getDescription().getAuthors() + " , website: " + getDescription().getWebsite()));
                    } else {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou dont`t have permission!"));
                    }
                } else if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("reload")) {
                        if (sender.hasPermission("alwaysday.main.reload")) {
                            reloadConfig();
                            sender.sendMessage(prefix + "Has been reloaded!");
                        } else {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou dont`t have permission!"));
                        }
                    }

                }
            } else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("help")) {
                    if (sender.hasPermission("alwaysday.main.help")) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a-----------------------"));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ""));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6/alwaysday - &eMain command"));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6/alwaysday reload    (Alias: /alwaysreload and /day4reload )   - &eReload the config"));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ""));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a-----------------------"));
                    } else {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou dont`t have permission!"));
                    }
                }

            }


        }
        if (cmd.getName().equalsIgnoreCase("alwaysreload")) {
            getServer().dispatchCommand(sender, "alwaysday reload");
        }
        else if (cmd.getName().equalsIgnoreCase("alwayshelp")){
            getServer().dispatchCommand(sender, "alwaysday reload");
        }

            return true;
        }
    }
