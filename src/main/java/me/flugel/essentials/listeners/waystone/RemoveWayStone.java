package me.flugel.essentials.listeners.waystone;

import me.flugel.essentials.Main;
import me.flugel.essentials.objects.WayStone.Cordenadas;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class RemoveWayStone implements Listener {
    @EventHandler
    public void breakWayStone(BlockBreakEvent e){
        Location location = e.getBlock().getLocation();
        Cordenadas byLocation = Main.getInstance().getWayStoneManager().getByCordenadas(location);
        if(byLocation != null){
            Main.getInstance().getWayStoneManager().removeWayStone(byLocation);
            e.getPlayer().sendMessage("§cVocê removeu uma WayStone");
            location.getWorld().dropItem(location, wayStoneItemStack());
            e.setDropItems(false);
        }
    }

    private ItemStack wayStoneItemStack(){

        ItemStack wayStone = new ItemStack(Material.LODESTONE);
        ItemMeta wayStoneMeta = wayStone.getItemMeta();

        wayStoneMeta.setDisplayName("§eWayStone");
        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add("§eFamoso bloquinho :)");
        lore.add("");

        wayStoneMeta.setLore(lore);

        wayStone.setItemMeta(wayStoneMeta);


        return wayStone;
    }
}
