package de.gerolmed.torched.recipes;

import de.gerolmed.torched.Main;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Server;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class RecipeManager {

	public RecipeManager(Main main) {
		if(main.getDataHolder().isEnableCrafting())
			registerRecipes(main, main.getServer());
	}
	
	@SuppressWarnings("deprecation")
	private void registerRecipes(Main main, Server server) {
		{
			ItemStack item = new ItemStack(Material.TORCH);
			ItemMeta itemMeta = item.getItemMeta();
			itemMeta.setDisplayName(main.getDataHolder().getPermaTorch());
			item.setItemMeta(itemMeta);
			ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(main, "permatorch"), item);
			recipe.shape(
					  "ccc"
					, "csc"
					, "ccc"
					);

			if(main.getDataHolder().isExpensiveCrafting())
				recipe.setIngredient('c', Material.COAL_BLOCK);
			else
				recipe.setIngredient('c', Material.COAL);

			recipe.setIngredient('s', Material.STICK);
			server.addRecipe(recipe);
			
		}
	}
	
}
