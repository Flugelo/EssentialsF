package me.flugel.essentials.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class spawn implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
        if(!(sender instanceof Player))
            return false;


        Player p = (Player) sender;

        if(cmd.getName().equalsIgnoreCase("spawn")){
            
        }
        return false;
    }
}
