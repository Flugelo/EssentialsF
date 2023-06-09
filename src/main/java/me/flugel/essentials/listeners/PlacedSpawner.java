package me.flugel.essentials.listeners;

import me.flugel.essentials.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class PlacedSpawner implements Listener {

    @EventHandler
    public void placedSpawner(BlockPlaceEvent e){
        ItemStack itemInMainHand = e.getPlayer().getInventory().getItemInMainHand();

        if(itemInMainHand.getType() == Material.SPAWNER){
            if (itemInMainHand.hasItemMeta()) {
                if (itemInMainHand.getItemMeta().hasLore()) {
                    List<String> lore = itemInMainHand.getItemMeta().getLore();
                    String s = lore.get(0);
                    EntityType entityType = EntityType.fromName(s);
                    if(entityType != null){
                        BlockState state = e.getBlock().getState();
                        if(state instanceof CreatureSpawner){
                            ((CreatureSpawner) state).setSpawnedType(entityType);
                        }
                    }
                }
            }
        }
    }
}
