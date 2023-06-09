package me.flugel.essentials.objects.WayStone.crafts;

import me.flugel.essentials.Main;
import me.flugel.essentials.listeners.waystone.item.WayStoneItem;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class WayStoneCraft {

    public static ShapedRecipe getRecipe() {
        ItemStack superEnderPearl = new ItemStack(Material.ENDER_PEARL);
        ItemMeta superEnderMeta = superEnderPearl.getItemMeta();
        superEnderMeta.addEnchant(Enchantment.SILK_TOUCH, 1,false);
        superEnderMeta.setDisplayName("§bSuper Enderpearl");
        superEnderPearl.setItemMeta(superEnderMeta);

        NamespacedKey key = new NamespacedKey(Main.getInstance(), "lodestone");

        ShapedRecipe recipe = new ShapedRecipe(key, WayStoneItem.wayStoneItemStack());

        recipe.shape("DSD","SLS","DSD");

        recipe.setIngredient('D', Material.DIAMOND);
        recipe.setIngredient('S', new RecipeChoice.ExactChoice(superEnderPearl));
        recipe.setIngredient('L', Material.LODESTONE);

        return recipe;
    }
}
