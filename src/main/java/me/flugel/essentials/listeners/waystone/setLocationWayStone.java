package me.flugel.essentials.listeners.waystone;

import me.flugel.essentials.Main;
import me.flugel.essentials.objects.WayStone.Cordenadas;
import me.flugel.essentials.objects.WayStone.WayStone;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class setLocationWayStone implements Listener {

    @EventHandler
    public void setWayStone(BlockPlaceEvent e){
        ItemStack itemInHand = e.getItemInHand();
        if(itemInHand.hasItemMeta() && itemInHand.getItemMeta().hasLore() && itemInHand.getItemMeta().getLore().equals(wayStoneItemStack().getItemMeta().getLore())){
            WayStone byOwner = Main.getInstance().getWayStoneManager().getByOwner(e.getPlayer().getName());
            if(byOwner != null){
                if(byOwner.getLocations().size() < 18){
                    byOwner.addWayStone(new Cordenadas(e.getBlock().getLocation(), itemInHand.getItemMeta().getDisplayName()));
                    byOwner.update();
                    e.getPlayer().sendMessage("§aWayStone salva com sucesso. §f" + byOwner.getLocations().size() + "/18");
                    e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_ANVIL_PLACE,1,1);
                }else{
                    e.getPlayer().sendMessage("§cVocê não pode colocar mais WayStone. Quantidade maxima atingida.");
                }
            }else{
                WayStone wayStone = new WayStone(e.getPlayer().getName());
                wayStone.addWayStone(new Cordenadas(e.getBlock().getLocation(), itemInHand.getItemMeta().getDisplayName()));
                wayStone.insert();
                e.getPlayer().sendMessage("§aWayStone salva com sucesso. §f1/18");
                e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_ANVIL_PLACE,1,1);
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
