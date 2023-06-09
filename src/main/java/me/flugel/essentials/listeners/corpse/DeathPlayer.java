package me.flugel.essentials.listeners.corpse;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathPlayer implements Listener {

    @EventHandler
    public void aoMorrer(PlayerDeathEvent e){
//        Player player = e.getEntity();
//
//        if(!player.getInventory().isEmpty()){
//            ItemStack[] contents = e.getDrops().toArray(new ItemStack[0]);
//            Location location = player.getLocation();
//            e.getDrops().clear();
//
//
//
//
//            if(contents.length < 27){
//                Location locChest1 = location;
//                locChest1.getBlock().setType(Material.CHEST);
//                Chest chest = (Chest) locChest1.getBlock().getState();
//
//                chest.getInventory().setContents(contents);
//            }else{
//                Location locChest1 = location;
//                Location locChest2 = location;
//                locChest1.getBlock().setType(Material.CHEST);
//                Chest chest = (Chest) location.getBlock().getState();
//                locChest2.add(1,0,0).getBlock().setType(Material.CHEST);
//                Chest chest2 = (Chest)  locChest2.getBlock().getState();
//
//                chest.getInventory().setContents(contents);
//            }
//
//
//
//        }

        Location location = e.getEntity().getLocation();
        e.getEntity().sendMessage("\n" +
                "§eVocê morreu na cordenada:\n" +
                "§2X: §a" + location.getX() + "\n" +
                "§2Y: §a" + location.getY() + "\n" +
                "§2Z: §a" + location.getZ() + "\n" +
                "§2Mundo: §a" + location.getWorld().getName() + "\n");

        e.getEntity().sendMessage("§aVocê ganhou efeitos positivos para correr atras dos seus itens, vamos, corra...");

    }
}
