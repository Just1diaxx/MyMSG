package org.justt.myMSG;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.justt.myMSG.MyMSG;

public class MsgCommand implements CommandExecutor {
    private final MyMSG plugin;

    public MsgCommand(MyMSG plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command.");
            return true;
        }

        Player player = (Player) sender;


        if (args.length < 2) {
            player.sendMessage(plugin.getKey("msg_usage"));
            return true;
        }


        String send_player = args[0];
        Player to_send_player = plugin.getServer().getPlayer(send_player);

        if(player.getName() == to_send_player.getName()){
            player.sendMessage(plugin.getKey("same_player"));
            return false;
        }


        if (to_send_player == null || !to_send_player.isOnline()) {
            player.sendMessage(plugin.getKey("player_not_online"));
            return true;
        }


        StringBuilder message = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            message.append(args[i]).append(" ");
        }


        String format_sender = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("format_sender"));
        String format_receiver = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("format_receiver"));


        String senderMessage = format_sender.replace("%player%", to_send_player.getName()).replace("%message%", message.toString().trim());
        String receiverMessage = format_receiver.replace("%player%", player.getName()).replace("%message%", message.toString().trim());


        player.sendMessage(senderMessage);
        to_send_player.sendMessage(receiverMessage);

        return true;
    }
}
