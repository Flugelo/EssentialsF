package me.flugel.essentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Kick implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (cmd.getName().equalsIgnoreCase("kick")) {
                if (p.hasPermission("essentialsf.kick")) {
                    if (args.length == 0){
                        p.sendMessage("§eEssentialsF: §cUse o comando §f/kick <player> <motivo>");
                        return false;
                    }

                    if (args.length == 1) {
                        Player target = Bukkit.getPlayerExact(args[0]);
                        if (target != null) {
                            target.kickPlayer("§cVocê foi kickado do servidor \n" +
                                    "§ePelo jogador: §f" + p.getName() + "\n" +
                                    "§eMotivo: §fSem motivo.");
                            Bukkit.broadcastMessage("\n" +
                                    "§eJogador §f" + target.getName() + " §efoi kickado pelo jogador §f" + p.getName() + " §epelo motivo: §fSem motivo.");

                            return true;
                        }
                    }else {
                        Player target = Bukkit.getPlayerExact(args[0]);
                        String razao = getRazao(args);
                        if (target != null) {
                            target.kickPlayer("§cVocê foi kickado do servidor \n" +
                                    "§ePelo jogador: §f" + p.getName() + "\n" +
                                    "§eMotivo: §f" + razao);
                            Bukkit.broadcastMessage("\n" +
                                    "§eJogador §f" + target.getName() + " §efoi kickado pelo jogador §f" + p.getName() + " §epelo motivo: §f" + razao);

                            return true;
                        }
                    }


                }else p.sendMessage("§eEssentialsF: §cVocê não tem permissão para executar esse comando.");
            }else p.sendMessage("§cSua cama foi destruida ou não foi setada o spawn.");
        }
        return false;
    }

    private String getRazao(String[] args) {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        for (String arg : args) {
            if(i != 0){
                builder.append(arg + " ");
            }
            i=1;
        }

        return builder.toString();
    }
}
