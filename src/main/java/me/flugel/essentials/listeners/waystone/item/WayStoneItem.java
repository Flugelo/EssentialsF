package me.flugel.essentials.listeners.waystone.item;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class WayStoneItem {

    public static ItemStack wayStoneItemStack(){

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
