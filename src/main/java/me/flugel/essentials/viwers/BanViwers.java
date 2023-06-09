package me.flugel.essentials.viwers;

import me.flugel.essentials.objects.Ban;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class BanViwers {

    public static void sucessMensageBan(String target, String author, String reason, int idBan) {
        Player targetPlayer = Bukkit.getPlayerExact(target);
        if (targetPlayer != null) {
            targetPlayer.kickPlayer("\n" +
                    "§cVocê foi banido do servidor \n" +
                    "\n" +
                    "§ePelo motivo: §f" + reason + "\n" +
                    "§ePelo staff: §f" + author + "\n" +
                    "\n" +
                    "§6Foi banido injustamente? entre em contato em nosso discord: \n" +
                    "§eDiscord.com");
        }

        Bukkit.broadcastMessage("\n" +
                "§eO jogador §f" + target + " §efoi banido permanentemente pelo jogador: §f" + author + "§e, pelo motivo: §f" + reason + "§e.\n");

        Player authorPlayer = Bukkit.getPlayerExact(author);
        if (authorPlayer != null) {
            authorPlayer.sendMessage("§eEssentialsF: §aJogador banido com sucesso!");

            authorPlayer.sendMessage("\n" +
                    "§6-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-\n" +
                    "§ePara adicionar a prova do banimento, você deve digitar: /addprova <jogador_banido> <prova>\n" +
                    "§eSó deve ser adicionado UMA prova. Exemplos de prova: link de prints ou link de videos.\n" +
                    "§eCaso o jogador banido entrar em contato pedindo provas, iremos verificar as provas.\n" +
                    "§6-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");

            authorPlayer.playSound(authorPlayer.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);


        }

    }

    public static void playerBanExists(Ban ban, String author) {

        Player p = Bukkit.getPlayerExact(author);
        if (p != null) {
            p.sendMessage("§eEssentialsF: §cJogador §f " + ban.getName() + " já se encontra banido.\n" +
                    "§8Nome: §f" + ban.getName() + "\n" +
                    "§8Autor: §f" + ban.getAutor() + "\n" +
                    "§8Razão: §f" + ban.getReason() + "\n");
        }


    }
}
