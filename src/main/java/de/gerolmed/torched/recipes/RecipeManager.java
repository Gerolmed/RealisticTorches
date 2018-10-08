package de.gerolmed.torched.recipes;

import de.gerolmed.torched.Main;
import org.bukkit.Bukkit;

public class RecipeManager {

	RecipeInjection recipeInjection;

	public RecipeManager(Main main) {
		chooseVersion();
		registerRecipes(main);
	}

	private void chooseVersion() {


		String version = Bukkit.getVersion();

		if(version.contains("1.8"))
			recipeInjection = new RecipeInjector_v_1_8();
		else
		// >1.9 fallback to 1.12 version
			recipeInjection = new RecipeInjector_v_1_12();
	}

	private void registerRecipes(Main main) {
		recipeInjection.registerRecipes(main);
	}
	
}
