package me.flugel.essentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BroadCast implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (cmd.getName().equalsIgnoreCase("bc")) {
                if (p.hasPermission("essentialsf.broadcast")) {
                    if (args.length == 0)
                        p.sendMessage("\n" +
                                "§eEssentialsF §cUse o comando §f/bc <mensagem>" +
                                "\n");




                    if (args.length >= 1) {
                        String bc = getBc(args);

                        Bukkit.broadcastMessage("");
                        Bukkit.broadcastMessage("§4BC: §e" + bc.replace("&", "§"));
                        Bukkit.broadcastMessage("");

                        return true;
                    }
                }
            }
        }


        return false;
    }

    private String getBc(String[] args) {
        StringBuilder builder = new StringBuilder();
        for (String arg : args)
            builder.append(arg + " ");


        return builder.toString();
    }
}
