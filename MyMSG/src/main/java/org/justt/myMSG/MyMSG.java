package org.justt.myMSG;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.justt.myMSG.MsgCommand;

public final class MyMSG extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getCommand("msg").setExecutor(new MsgCommand(this));
        getLogger().info("MyMSG has been actived successfully!");
    }

    @Override
    public void onDisable() {

    }

    public String getKey(String key){
        String finalmessage = ChatColor.translateAlternateColorCodes('&', getConfig().getString(key));
        return finalmessage;
    }
}
