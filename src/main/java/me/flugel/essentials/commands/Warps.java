package me.flugel.essentials.commands;

import me.flugel.essentials.Main;
import me.flugel.essentials.objects.SolicitationWarp;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Warps implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
        if (!(sender instanceof Player))
            return false;


        Player p = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("warp")) {
            if (args.length == 0) {
                p.sendMessage("§eWarps disponiveis:");
                for (me.flugel.essentials.objects.Warps warp : Main.getInstance().getWarpsManager().getWarps()) {
                    p.sendMessage("  §f- §7" + warp.getNome());
                }
                p.sendMessage("§cDigite /warp <nome> para se teleportar à ela");
            } else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("set")) {
                    if (p.hasPermission("essentials.setwarp")) {
                        p.sendMessage("§cUse /warp set <nome> para setar uma warp.");
                    }
                    return false;
                } else if (args[0].equalsIgnoreCase("remove")) {
                    if (p.hasPermission("essentials.setwarp")) {
                        p.sendMessage("§cUse /warp remove <nome> para setar uma warp.");
                        return false;
                    }
                } else {
                    String warp = args[0];
                    me.flugel.essentials.objects.Warps warpsByName = Main.getInstance().getWarpsManager().getWarpsByName(warp);

                    if (warpsByName != null) {
                        if (Main.getInstance().getSolicitationWarpManager().checkSolicitation(p)) {

                            p.sendMessage("§aVocê sera teleportado em 5 segundos....");

                            SolicitationWarp solicitationWarp = new SolicitationWarp(p.getName(), warpsByName);

                            Main.getInstance().getSolicitationWarpManager().onTeleport(solicitationWarp);


                        } else p.sendMessage("§cVocê já tem uma solicitado de telporte, aguarde.");
                    }else p.sendMessage("§cEssa warp não existe. Digite /warp para ver todas as warps disponiveis.");
                }
            } else if (args.length == 2) {
                if (args[0].equalsIgnoreCase("set")) {
                    if (p.hasPermission("essentials.setwarp")) {
                        String warp = args[1];
                        if (warp != "set" && warp != "remove") {
                            me.flugel.essentials.objects.Warps warpsByName = Main.getInstance().getWarpsManager().getWarpsByName(warp);
                            if (warpsByName == null) {
                                me.flugel.essentials.objects.Warps warps = new me.flugel.essentials.objects.Warps(warp, p.getLocation());
                                warps.insert();
                                p.sendMessage("§aWarp salva com sucesso.");
                            } else {
                                warpsByName.setLocation(p.getLocation());
                                warpsByName.update();
                                p.sendMessage("§aLocalização da warp modificada com sucesso.");
                            }
                        } else p.sendMessage("§cVocê não pode colocar o nome da warp de 'SET' ou 'REMOVE'");
                    }
                } else if (args[0].equalsIgnoreCase("remove")) {
                    if (p.hasPermission("essentials.setwarp")) {
                        String warp = args[1];
                        if (warp != "set" && warp != "remove") {
                            me.flugel.essentials.objects.Warps warpsByName = Main.getInstance().getWarpsManager().getWarpsByName(warp);
                            if (warpsByName != null) {
                                warpsByName.delet();
                                Main.getInstance().getWarpsManager().removeWarps(warpsByName);
                                p.sendMessage("§cWarp removida com sucesso.");
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
