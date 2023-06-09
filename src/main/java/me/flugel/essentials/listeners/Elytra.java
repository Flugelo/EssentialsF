package me.flugel.essentials.listeners;

import me.flugel.essentials.Main;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.EntityEffect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class Elytra implements Listener {
    @EventHandler
    public void powerjump(PlayerToggleSneakEvent e) {
        if (e.getPlayer().hasPermission("essentialsf.elytra")) {
            Player player = e.getPlayer();
            if (e.getPlayer().getEquipment().getChestplate() != null) {
                Material type = player.getEquipment().getChestplate().getType();
                if (type != null)
                    if (type == Material.ELYTRA)
                        if (Main.getInstance().getElytraPlayer().contains(e.getPlayer().getName())) {
                            Block relative = e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN);
                            if (relative != null &&relative.getBlockData().getMaterial() != Material.AIR)
                                task(e.getPlayer());
                        }

            }
        }
    }

    public void task(Player player) {
        new BukkitRunnable() {
            float power = 0;

            @Override
            public void run() {
                if (player.isSneaking()) {
                    if (power >= 25) {
                        player.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§2§lPOWER: §a" + power));
                        return;
                    }
                    power = power + 0.1f;
                    player.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§2§lPOWER: §a" + power));
                    return;
                }
                player.getPlayer().setVelocity(new Vector(0, power, 0));
                player.getPlayer().playSound(player.getPlayer().getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 10, 0.5f);
                player.playEffect(EntityEffect.VILLAGER_ANGRY);
                this.cancel();
            }
        }.runTaskTimer(Main.getInstance(), 0, 5);
    }
}
