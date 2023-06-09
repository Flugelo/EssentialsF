package me.flugel.essentials.commands;

import me.flugel.essentials.Main;
import me.flugel.essentials.objects.Vanish;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class vanish implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {

        if (!(sender instanceof Player))
            return false;

        Player p = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("vanish")) {
            if (p.hasPermission("essentialsf.vanish")) {
                Vanish byPlayer = Main.getInstance().getInvseeManager().getByPlayer(p);
                System.out.println(byPlayer);
                if (byPlayer == null) {

                    new Vanish(p);

                    for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                        onlinePlayer.hidePlayer(Main.getInstance(), p);
                    }

                    p.sendMessage("§eEssentialsF: §aVocê estrou no modo invisivel.");
                    return true;

                }

                Main.getInstance().getInvseeManager().remove(byPlayer);

                for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                    onlinePlayer.showPlayer(Main.getInstance(), p);
                }
                p.sendMessage("§eEssentialsF: §cVocê saiu do modo invisivel.");
                return true;

            }else p.sendMessage("§cSua cama foi destruida ou não foi setada o spawn.");
        }
        return false;
    }
}
