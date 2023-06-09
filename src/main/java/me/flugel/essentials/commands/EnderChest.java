package me.flugel.essentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EnderChest implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
        if(!(sender instanceof Player))
            return false;

        Player p = (Player) sender;
        if(cmd.getName().equalsIgnoreCase("enderchest")){
            if(args.length == 0){
                if(p.hasPermission("essentialsf.enderchest")){
                    p.openInventory(p.getEnderChest());
                    p.playSound(p.getLocation(), Sound.BLOCK_ENDER_CHEST_OPEN,1,1);
                    return true;
                }else p.sendMessage("§cVocê não tem acesso a esse comando.");
            } else if(args.length == 1){
                if(p.hasPermission("essentialsf.enderchest.staff")){
                    Player target = Bukkit.getPlayerExact(args[0]);

                    if(target != null ){
                        p.openInventory(target.getEnderChest());
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
