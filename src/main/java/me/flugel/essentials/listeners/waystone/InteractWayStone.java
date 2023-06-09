package me.flugel.essentials.listeners.waystone;

import me.flugel.essentials.Main;
import me.flugel.essentials.objects.WayStone.Cordenadas;
import me.flugel.essentials.objects.WayStone.WayStone;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InteractWayStone implements Listener {
    //Open WayStone
    @EventHandler
    public void aoInteragir(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (e.getClickedBlock().getType().equals(Material.LODESTONE)) {
                Location location = e.getClickedBlock().getLocation();
                WayStone byLocation = Main.getInstance().getWayStoneManager().getByAllWayStone(location);
                Player p = e.getPlayer();
                if (byLocation != null) {
                    if (byLocation.getOwnerPlayer().equals(p.getName())) {
                        p.openInventory(wayStoneInventory(byLocation));
                        p.playSound(p.getLocation(), Sound.BLOCK_ENDER_CHEST_OPEN, 1, 1);
                        return;

                    } else if (byLocation.isPublico()) {
                        p.openInventory(wayStoneInventory(byLocation));
                        p.playSound(p.getLocation(), Sound.BLOCK_ENDER_CHEST_OPEN, 1, 1);
                        return;

                    } else p.sendMessage("§cEssa WayStone está bloqueada.");
                }
            }
        }
    }

    @EventHandler
    public void guiWayStone(InventoryClickEvent e) {
        if (e.getView().getTitle().contains("WayStone")) {
            e.setCancelled(true);
            ItemStack cursor = e.getCurrentItem();
            Player p = (Player) e.getWhoClicked();
            if (cursor != null) {
                if (cursor.getType() != Material.AIR) {
                    String owner = ChatColor.stripColor(e.getView().getTitle().split(">")[1]);
                    if (cursor.getType().equals(Material.LODESTONE)) {
                        String[] displayName = ChatColor.stripColor(cursor.getItemMeta().getDisplayName()).split(":");

                        int x = Integer.valueOf(displayName[1].replace(" ", ""));
                        int y = Integer.valueOf(displayName[2].replace(" ", ""));
                        int z = Integer.valueOf(displayName[3].replace(" ", ""));
                        String world = displayName[4].replace(" ", "");

                        Location location = new Location(Bukkit.getWorld(world), x, y+1, z);

                        p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT,1,1);
                        p.teleport(location);

                        p.closeInventory();

                    } else if (cursor.getType().equals(Material.RED_BANNER)) {
                        WayStone byOwner = Main.getInstance().getWayStoneManager().getByOwner(owner);
                        if (byOwner != null) {
                            if (byOwner.getOwnerPlayer().equals(p.getName())) {
                                byOwner.setPublico(true);
                                p.playSound(p.getLocation(), Sound.UI_CARTOGRAPHY_TABLE_TAKE_RESULT, 1, 1);
                                p.openInventory(wayStoneInventory(byOwner));
                            } else p.sendMessage("§cVocê não pode fazer essa alteração nessa WayStone");
                        }
                    } else if (cursor.getType().equals(Material.GREEN_BANNER)) {
                        WayStone byOwner = Main.getInstance().getWayStoneManager().getByOwner(owner);
                        if (byOwner != null) {
                            if (byOwner.getOwnerPlayer().equals(p.getName())) {
                                byOwner.setPublico(false);
                                p.playSound(p.getLocation(), Sound.UI_CARTOGRAPHY_TABLE_TAKE_RESULT, 1, 1);
                                p.openInventory(wayStoneInventory(byOwner));
                            } else p.sendMessage("§cVocê não pode fazer essa alteração nessa WayStone");
                        }
                    }
                }
            }
        }
    }

    public Inventory wayStoneInventory(WayStone wayStone) {
        Inventory inv = Bukkit.createInventory(null, 9 * 3, "§2         WayStone§f->§e" + wayStone.getOwnerPlayer());
        for (int i = 18; i < 27; i++) {
            ItemStack itemStack = new ItemStack(Material.BARRIER);
            ItemMeta meta = itemStack.getItemMeta();
            meta.setDisplayName("§7-");
            itemStack.setItemMeta(meta);
            inv.setItem(i, itemStack);
        }
        ItemStack check = null;

        if (wayStone.isPublico()) {
            check = new ItemStack(Material.GREEN_BANNER);
            ItemMeta checkMeta = check.getItemMeta();
            checkMeta.setDisplayName("§2Publico.");
            check.setItemMeta(checkMeta);
            inv.setItem(22, check);
        } else {
            check = new ItemStack(Material.RED_BANNER);
            ItemMeta checkMeta = check.getItemMeta();
            checkMeta.setDisplayName("§cPrivado.");
            check.setItemMeta(checkMeta);
            inv.setItem(22, check);
        }
        int x = 0;
        for (Cordenadas cords : wayStone.getLocations()) {
            ItemStack item = new ItemStack(Material.LODESTONE);

            ItemMeta metaItem = item.getItemMeta();
            metaItem.setDisplayName(ChatColor.GREEN + cords.getNome() + " §2: §a" + (int) cords.getLocation().getX() + " §2: §a" + (int) cords.getLocation().getY() + " §2: §a" + (int) +cords.getLocation().getZ() + " §2: §a" + cords.getLocation().getWorld().getName());
            item.setItemMeta(metaItem);

            inv.setItem(x, item);
            x++;

        }


        return inv;
    }
}
