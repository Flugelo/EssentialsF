package me.flugel.essentials.commands;

import me.flugel.essentials.controllers.BanController;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Ban implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;


            if (cmd.getName().equalsIgnoreCase("ban")) {
                if (p.hasPermission("essentialsf.ban")) {
                    if (args.length == 0)
                        p.sendMessage("\n§eEssentialsF: §6Utilize o comando §f/ban §c<jogador> <razão> §6para banir um jogador \n");

                    if (args.length == 1)
                        p.sendMessage("\n§eEssentialsF: §6Utilize o comando §f/ban <jogador> §c<razão> §6para banir um jogador \n");

                    if (args.length >= 2) {
                        String target = args[0];
                        String razao = getRazao(args);

                        BanController.banPlayer(target, p.getName(), razao);
                        return true;
                    }
                }
            }

            if (cmd.getName().equalsIgnoreCase("addprova")) {
                if (p.hasPermission("essentialsf.addprova")) {
                    if (args.length == 0){
                        p.sendMessage("\n" +
                                "§eEssentialsF: §6Utilize o comando §f/addban §c<jogador_banido> <link> §6para para adicionar a prova." +
                                " \n");
                        return false;
                    }


                    if (args.length == 1){
                        p.sendMessage("\n" +
                                "§eEssentialsF: §6Utilize o comando §f/addban §f<jogador_banido> §c<link> §6para para adicionar a prova." +
                                " \n");
                        return false;
                    }


                    if (args.length == 2) {
                        String banedPlayer = args[0];
                        String link = args[1];

                        BanController.addproof(p, banedPlayer, link);
                        return true;
                    }
                }
            }
        }

        if (cmd.getName().equalsIgnoreCase("ban")) {
            if (args.length == 0)
                sender.sendMessage("\n§eEssentialsF: §6Utilize o comando §f/ban §c<jogador> <razão> §6para banir um jogador \n");

            if (args.length == 1)
                sender.sendMessage("\n§eEssentialsF: §6Utilize o comando §f/ban <jogador> §c<razão> §6para banir um jogador \n");

            if (args.length >= 2) {
                String target = args[0];
                String razao = getRazao(args);

                BanController.banPlayer(target, "CONSOLE", razao);
                return true;
            }

        }

        return false;
    }

    private String getRazao(String[] args) {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        for (String arg : args) {
            if (i != 0) {
                builder.append(arg + " ");
            }
            i = 1;
        }

        return builder.toString();
    }
}
