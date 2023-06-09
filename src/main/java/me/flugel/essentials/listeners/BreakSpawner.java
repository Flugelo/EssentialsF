package me.flugel.essentials.listeners;

import net.minecraft.world.level.SpawnerCreature;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BreakSpawner implements Listener {
    @EventHandler
    public void breaking(BlockBreakEvent e) {
        Location location = e.getBlock().getLocation();

        Player player = e.getPlayer();


        ItemStack itemInMainHand = player.getInventory().getItemInMainHand();

        if(e.getBlock().getType() == Material.SPAWNER ){
            if (itemInMainHand != null) {
                Material type = player.getInventory().getItemInMainHand().getType();
                if (type == Material.IRON_PICKAXE || type == Material.DIAMOND_PICKAXE || type == Material.NETHERITE_PICKAXE) {

                    if(itemInMainHand.containsEnchantment(Enchantment.SILK_TOUCH)){

                        BlockState state = e.getBlock().getState();

                        if(state instanceof CreatureSpawner){
                            CreatureSpawner spawner = ((CreatureSpawner) state);

                            EntityType spawnedType = spawner.getSpawnedType();

                            ItemStack itemStack = new ItemStack(Material.SPAWNER);

                            ItemMeta itemMeta = itemStack.getItemMeta();
                            List<String> lore = new ArrayList<>();
                            lore.add(spawnedType.toString());

                            itemMeta.setLore(lore);

                            itemStack.setItemMeta(itemMeta);

                            e.setDropItems(false);

                            location.getWorld().dropItem(location,itemStack);
                        }
                    }
                }
            }
        }
    }
}
