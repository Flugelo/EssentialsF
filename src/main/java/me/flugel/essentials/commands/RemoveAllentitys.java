package me.flugel.essentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

public class RemoveAllentitys implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {


        if(cmd.getName().equalsIgnoreCase("removeallentites")){
            if(sender.hasPermission("essentialsf.removeallentites")){
                int x = 0;
                EntityType[] death = {EntityType.CREEPER, EntityType.ENDERMAN, EntityType.SILVERFISH, EntityType.SKELETON, EntityType.ZOMBIE, EntityType.PIGLIN};
                for (World world : Bukkit.getWorlds()) {
                    for (Entity entity : world.getEntities()) {
                        for (EntityType entityType : death) {
                            if(entity.getType() == entityType){
                                entity.remove();
                                x++;
                            }

                        }
                    }
                }
                sender.sendMessage("§cForam removidas §e" + x  +" §centidades!");
            }
        }

        if(cmd.getName().equalsIgnoreCase("checkallentites")){
            if(sender.hasPermission("essentialsf.removeallentites")){
                int x = 0;
                EntityType[] death = {EntityType.CREEPER, EntityType.ENDERMAN, EntityType.SILVERFISH, EntityType.SKELETON, EntityType.ZOMBIE, EntityType.PIGLIN};
                for (World world : Bukkit.getWorlds()) {
                    for (Entity entity : world.getEntities()) {
                        for (EntityType entityType : death) {
                            if(entity.getType() == entityType){
                                x++;
                            }

                        }
                    }
                }
                sender.sendMessage("§cHá §e" + x  +" §centidades vivas!");
            }
        }
        return false;
    }
}
