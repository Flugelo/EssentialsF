package me.flugel.essentials.objects.WayStone.crafts;

import me.flugel.essentials.Main;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class SuperEnderpearl {
    public static ShapedRecipe getRecipe() {
        ItemStack superEnderPearl = new ItemStack(Material.ENDER_PEARL);
        ItemMeta superEnderMeta = superEnderPearl.getItemMeta();
        superEnderMeta.addEnchant(Enchantment.SILK_TOUCH,1,false);
        superEnderMeta.setDisplayName("§bSuper Enderpearl");
        superEnderPearl.setItemMeta(superEnderMeta);

        NamespacedKey key = new NamespacedKey(Main.getInstance(), "ender_pearl");

        ShapedRecipe recipe = new ShapedRecipe(key, superEnderPearl);

        recipe.shape("DED","EBE","DED");

        recipe.setIngredient('D', Material.DIAMOND);
        recipe.setIngredient('E', Material.ENDER_PEARL);
        recipe.setIngredient('B', Material.COMPASS);

        return recipe;
    }
}