package me.flugel.essentials.listeners;

import me.flugel.essentials.Main;
import me.flugel.essentials.objects.Passivo;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.projectiles.ProjectileSource;

public class PvpCheckPassivel implements Listener {

    @EventHandler
    public void hitCheck(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player) {
            if (e.getDamager() instanceof Player) {
                Player target = (Player) e.getEntity();
                Player damager = (Player) e.getDamager();

                Passivo passivo = Main.getInstance().getPassivoManager().isPassivo(target.getName());

                if (passivo != null) {
                    if (passivo.isActive()) {
                        damager.sendMessage("§cJogador §e" + target.getName() + " §cestá em modo passivo.");
                        e.setCancelled(true);
                        return;
                    }
                }

                Passivo passivo1 = Main.getInstance().getPassivoManager().isPassivo(damager.getName());

                if (passivo1 != null) {
                    if (passivo1.isActive()) {
                        e.setCancelled(true);
                        damager.sendMessage("§cVocê está em modo passivo. Você não pode dar dano em nem um jogador");
                    }
                }
            } else {
                if (e.getDamager() instanceof Arrow) {
                    Player target = (Player) e.getEntity();
                    Arrow arrow = (Arrow) e.getDamager();

                    ProjectileSource shooter = arrow.getShooter();
                    if (shooter instanceof Player) {
                        Passivo passivo = Main.getInstance().getPassivoManager().isPassivo(target.getName());
                        Passivo passivo1 = Main.getInstance().getPassivoManager().isPassivo(((Player) shooter).getName());

                        if (passivo != null) {
                            if (passivo.isActive()) {
                                ((Player) shooter).sendMessage("§cJogador §e" + target.getName() + " §cestá em modo passivo.");
                                e.setCancelled(true);
                                return;
                            }
                        }
                        if (passivo1 != null) {
                            if (passivo1.isActive()) {
                                e.setCancelled(true);
                                Bukkit.getPlayerExact(passivo1.getPlayer()).sendMessage("§cVocê está em modo §4PASSIVO");
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void aoteleportar(PlayerTeleportEvent event) {

        Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
            World world = event.getPlayer().getLocation().getWorld();

            if (world.getName().equals("world_nether") || world.getName().equals("world_the_end")) {
                Player player = event.getPlayer();
                Passivo passivo = Main.getInstance().getPassivoManager().isPassivo(player.getName());

                if (passivo != null) {
                    if (passivo.isActive()) {
                        passivo.setActive(false);
                        player.sendMessage("§cModo passivo §4DESATIVADO. §cNo mundo Nether/The end, o modo passivo e desativado.");
                    }
                }
            }

            if (world.getName().equals("world")) {
                Player player = event.getPlayer();
                Passivo passivo = Main.getInstance().getPassivoManager().isPassivo(player.getName());

                if (passivo != null) {
                    if (!passivo.isActive()) {
                        passivo.setActive(true);
                        player.sendMessage("§aModo passivo §2ATIVO §aautomaticamente.");
                    }
                }
            }
        }, 20 * 3);

    }
}
