package me.flugel.essentials.objects.WayStone;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class RanameWayStone implements Listener {
    @EventHandler
    public void rename(PrepareAnvilEvent e){
        if(e.getResult() != null){
            ItemStack result = e.getResult();
            if(result.getType().equals(Material.LODESTONE)) {
                if (result.hasItemMeta() && result.getItemMeta().hasLore()) {
                    if(result.getItemMeta().getLore().equals(wayStoneItemStack().getItemMeta().getLore())){
                        String displayName = result.getItemMeta().getDisplayName();
                        if(displayName.indexOf(" ") > 0){
                            e.setResult(new ItemStack(Material.AIR));
                        }else if(displayName.indexOf(">") > 0 || displayName.indexOf("<") > 0){
                            e.setResult(new ItemStack(Material.AIR));
                        }
                    }
                }
            }
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
