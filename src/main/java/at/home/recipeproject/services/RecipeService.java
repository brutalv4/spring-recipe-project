package at.home.recipeproject.services;

import at.home.recipeproject.domain.Recipe;
import java.util.Set;

public interface RecipeService {

  Set<Recipe> getRecipes();

  Recipe findById(long id);
}
