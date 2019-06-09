package at.home.recipeproject.services;

import at.home.recipeproject.domain.Recipe;
import at.home.recipeproject.repositories.RecipeRepository;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class RecipeServiceImplTest {

  RecipeServiceImpl recipeService;

  @Mock
  RecipeRepository recipeRepository;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
    recipeService = new RecipeServiceImpl(recipeRepository);
  }

  @Test
  void getRecipes() {
    // setup
    Recipe recipe = new Recipe();
    HashSet<Recipe> recipesData = new HashSet<>();
    recipesData.add(recipe);

    Mockito
        .when(recipeRepository.findAll())
        .thenReturn(recipesData);

    // execute
    Set<Recipe> recipes = recipeService.getRecipes();
    // verify
    Assertions.assertEquals(1, recipes.size());
    Mockito.verify(recipeRepository, Mockito.times(1)).findAll();
  }
}