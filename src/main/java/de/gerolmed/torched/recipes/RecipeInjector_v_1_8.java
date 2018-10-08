package de.gerolmed.torched.recipes;

import de.gerolmed.torched.Main;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;


public class RecipeInjector_v_1_8 implements RecipeInjection{


    public void registerRecipes(Main plugin) {
        Server server = plugin.getServer();
        {
            ItemStack item = new ItemStack(Material.TORCH);
            ItemMeta itemMeta = item.getItemMeta();
            itemMeta.setDisplayName(plugin.getDataHolder().getPermaTorch());
            item.setItemMeta(itemMeta);
            ShapedRecipe recipe = new ShapedRecipe(item);
            recipe.shape(
                    "ccc",
                    "csc",
                    "ccc"
            );

            if(plugin.getDataHolder().isExpensiveCrafting())
                recipe.setIngredient('c', Material.COAL_BLOCK);
            else
                recipe.setIngredient('c', Material.COAL);

            recipe.setIngredient('s', Material.STICK);
            server.addRecipe(recipe);

        }
    }
}
