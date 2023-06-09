package me.flugel.essentials.commands;

import me.flugel.essentials.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class Elytra implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
        if(!(sender instanceof Player))
            return false;

        Player p = (Player) sender;
        if(cmd.getName().equalsIgnoreCase("elytra")){
            if(p.hasPermission("essentials.elytra")){
                List<String> elytraPlayer = Main.getInstance().getElytraPlayer();
                if(elytraPlayer.contains(p.getName())){
                    Main.getInstance().getElytraPlayer().remove(p.getName());
                    p.sendMessage("§cVocê desativou o super pulo!");
                    return true;
                }else{
                    Main.getInstance().getElytraPlayer().add(p.getName());
                    p.sendMessage("§aVocê ativou o super pulo!");
                }

            }else p.sendMessage("§cVocê não tem permissão para executar esse comando.");
        }
        return false;
    }
}
