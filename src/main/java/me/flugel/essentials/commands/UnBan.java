package me.flugel.essentials.commands;

import me.flugel.essentials.Main;
import me.flugel.essentials.objects.Ban;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UnBan implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
        if(sender instanceof Player){
            if(cmd.getName().equalsIgnoreCase("unban")){
                if(sender.hasPermission("essentialsf.unban")){
                    if(args.length == 0){
                        sender.sendMessage("§eEssentialsF: §cUse o comando /unban <jogador> para desbanir um jogador.");
                        return true;
                    }else if(args.length == 1){
                        String target = args[0];
                        Ban byBan = Main.getInstance().getBanManager().getByBan(target);
                        if(byBan != null){
                            Main.getInstance().getBanManager().deletedForAll(byBan.getName());
                            Main.getInstance().getBanManager().getBanArrayList().remove(byBan);

                            sender.sendMessage("§aPlayer desbanido com sucesso!");

                            Bukkit.broadcastMessage("\n" +
                                    "§eO jogador §f" + target + " §efoi desbanido do servidor pelo jogador: §f" + sender.getName() +
                                    "\n");

                            return true;
                        }else sender.sendMessage("§cJogador não encontrado na lista de banidos.");
                    }
                }
            }
        }else{
            if(args.length == 0){
                sender.sendMessage("§eEssentialsF: §cUse o comando /unban <jogador> para desbanir um jogador.");
                return true;
            }else if(args.length == 1){
                String target = args[0];
                Ban byBan = Main.getInstance().getBanManager().getByBan(target);
                if(byBan != null){
                    Main.getInstance().getBanManager().deletedForAll(byBan.getName());
                    Main.getInstance().getBanManager().getBanArrayList().remove(byBan);

                    sender.sendMessage("§aPlayer desbanido com sucesso!");

                    Bukkit.broadcastMessage("\n" +
                            "§eO jogador §f" + target + " §efoi desbanido do servidor pelo jogador: §f" + sender.getName() +
                            "\n");

                    return true;
                }else sender.sendMessage("§cJogador não encontrado na lista de banidos.");
            }
        }
        return false;
    }
}
