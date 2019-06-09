package at.home.recipeproject.bootstrap;

import at.home.recipeproject.domain.Category;
import at.home.recipeproject.domain.Difficulty;
import at.home.recipeproject.domain.Ingredient;
import at.home.recipeproject.domain.Notes;
import at.home.recipeproject.domain.Recipe;
import at.home.recipeproject.domain.UnitOfMeasure;
import at.home.recipeproject.repositories.CategoryRepository;
import at.home.recipeproject.repositories.RecipeRepository;
import at.home.recipeproject.repositories.UnitOfMeasureRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

  private final CategoryRepository categoryRepository;
  private final RecipeRepository recipeRepository;
  private final UnitOfMeasureRepository unitOfMeasureRepository;

  public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository,
      UnitOfMeasureRepository unitOfMeasureRepository) {
    this.categoryRepository = categoryRepository;
    this.recipeRepository = recipeRepository;
    this.unitOfMeasureRepository = unitOfMeasureRepository;
  }

  @Override
  @Transactional
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    log.debug("Loading Bootstrap Data");
    recipeRepository.saveAll(getRecipes());
  }

  @SuppressWarnings("all")
  private List<Recipe> getRecipes() {
    ArrayList<Recipe> recipes = new ArrayList<>(2);

    // Units of measure
    UnitOfMeasure teaspoon = unitOfMeasureRepository.findByDescription("Teaspoon").get();
    UnitOfMeasure tablespoon = unitOfMeasureRepository.findByDescription("Tablespoon").get();
    UnitOfMeasure cup = unitOfMeasureRepository.findByDescription("Cup").get();
    UnitOfMeasure each = unitOfMeasureRepository.findByDescription("Each").get();
    UnitOfMeasure dash = unitOfMeasureRepository.findByDescription("Dash").get();
    UnitOfMeasure pint = unitOfMeasureRepository.findByDescription("Pint").get();
    UnitOfMeasure clove = unitOfMeasureRepository.findByDescription("Clove").get();

    // Categories
    Category american = categoryRepository.findByDescription("American").get();
    Category mexican = categoryRepository.findByDescription("Mexican").get();

    // Yummy Guac
    Recipe guacRecipe = new Recipe();
    guacRecipe.setDescription("Perfect Guacamole");
    guacRecipe.getCategories().addAll(Arrays.asList(american, mexican));
    guacRecipe.setPrepTime(10);
    guacRecipe.setCookTime(0);
    guacRecipe.setDifficulty(Difficulty.EASY);
    guacRecipe.setDirections(
        "1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n"
            + "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n"
            + "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n"
            + "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n"
            + "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n"
            + "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n"
            + "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n"
            + "\n"
            + "Read more: https://www.simplyrecipes.com/recipes/perfect_guacamole/");

    Notes guacNotes = new Notes();
    guacNotes.setRecipeNotes(
        "For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n"
            + "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries (see our Strawberry Guacamole).\n"
            + "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n"
            + "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n"
            + "\n"
            + "Read more: https://www.simplyrecipes.com/recipes/perfect_guacamole/");

    guacRecipe.setNotes(guacNotes);

    guacRecipe.addIngredient(
        Ingredient.builder().description("ripe avocados").amount(new BigDecimal(2))
            .unitOfMeasure(each).build());
    guacRecipe.addIngredient(
        Ingredient.builder().description("Kosher salt").amount(new BigDecimal(".5"))
            .unitOfMeasure(teaspoon).build());
    guacRecipe.addIngredient(Ingredient.builder().description("fresh lime juice or lemon juice")
        .amount(new BigDecimal(1)).unitOfMeasure(tablespoon).build());
    guacRecipe.addIngredient(
        Ingredient.builder().description("minced red onion or thinly sliced green onion")
            .amount(new BigDecimal(2)).unitOfMeasure(tablespoon).build());
    guacRecipe.addIngredient(
        Ingredient.builder().description("serrano chiles, stems and seeds removed, minced")
            .amount(new BigDecimal(2)).unitOfMeasure(each).build());
    guacRecipe.addIngredient(
        Ingredient.builder().description("cilantro (leaves and tender stems), finely chopped")
            .amount(new BigDecimal(2)).unitOfMeasure(tablespoon).build());
    guacRecipe.addIngredient(
        Ingredient.builder().description("freshly grated black pepper").amount(new BigDecimal(1))
            .unitOfMeasure(dash).build());
    guacRecipe.addIngredient(
        Ingredient.builder().description("ripe tomato, seeds and pulp removed, chopped")
            .amount(new BigDecimal(".5")).unitOfMeasure(each).build());

    recipes.add(guacRecipe);

    // Yummy Tacos
    Recipe tacosRecipe = new Recipe();
    tacosRecipe.setDescription("Spicy Grilled Chicken Tacos");
    tacosRecipe.getCategories().addAll(Arrays.asList(american, mexican));
    tacosRecipe.setCookTime(9);
    tacosRecipe.setPrepTime(20);
    tacosRecipe.setDifficulty(Difficulty.MODERATE);

    tacosRecipe.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n"
        + "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n"
        + "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n"
        + "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n"
        + "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n"
        + "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n"
        + "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.\n"
        + "\n"
        + "Read more: https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");

    Notes tacosNotes = new Notes();
    tacosNotes.setRecipeNotes(
        "We have a family motto and it is this: Everything goes better in a tortilla.\n"
            + "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n"
            + "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n"
            + "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n"
            + "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!\n"
            + "\n"
            + "Read more: https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");

    tacosRecipe.setNotes(tacosNotes);

    tacosRecipe.addIngredient(
        Ingredient.builder().description("ancho chili powder").amount(new BigDecimal(2))
            .unitOfMeasure(tablespoon).build());
    tacosRecipe.addIngredient(
        Ingredient.builder().description("dried oregano").amount(new BigDecimal(1))
            .unitOfMeasure(teaspoon).build());
    tacosRecipe.addIngredient(
        Ingredient.builder().description("dried cumin").amount(new BigDecimal(1))
            .unitOfMeasure(teaspoon).build());
    tacosRecipe.addIngredient(
        Ingredient.builder().description("sugar").amount(new BigDecimal(1)).unitOfMeasure(teaspoon)
            .build());
    tacosRecipe.addIngredient(Ingredient.builder().description("salt").amount(new BigDecimal(".5"))
        .unitOfMeasure(teaspoon).build());
    tacosRecipe.addIngredient(
        Ingredient.builder().description("garlic, finely chopped").amount(new BigDecimal(1))
            .unitOfMeasure(clove).build());
    tacosRecipe.addIngredient(
        Ingredient.builder().description("finely grated orange zest").amount(new BigDecimal(1))
            .unitOfMeasure(tablespoon).build());
    tacosRecipe.addIngredient(
        Ingredient.builder().description("fresh-squeezed orange juice").amount(new BigDecimal(3))
            .unitOfMeasure(tablespoon).build());
    tacosRecipe.addIngredient(
        Ingredient.builder().description("olive oil").amount(new BigDecimal(2))
            .unitOfMeasure(tablespoon).build());
    tacosRecipe.addIngredient(
        Ingredient.builder().description("skinless, boneless chicken thighs (1 1/4 pounds)")
            .amount(new BigDecimal(4)).unitOfMeasure(each).build());
    tacosRecipe.addIngredient(
        Ingredient.builder().description("small corn tortillas").amount(new BigDecimal(8))
            .unitOfMeasure(each).build());
    tacosRecipe.addIngredient(
        Ingredient.builder().description("packed baby arugula (3 ounces)").amount(new BigDecimal(3))
            .unitOfMeasure(cup).build());
    tacosRecipe.addIngredient(
        Ingredient.builder().description("medium ripe avocados, sliced").amount(new BigDecimal(2))
            .unitOfMeasure(each).build());
    tacosRecipe.addIngredient(
        Ingredient.builder().description("radishes, thinly sliced").amount(new BigDecimal(4))
            .unitOfMeasure(each).build());
    tacosRecipe.addIngredient(
        Ingredient.builder().description("cherry tomatoes, halved").amount(new BigDecimal(".5"))
            .unitOfMeasure(pint).build());
    tacosRecipe.addIngredient(
        Ingredient.builder().description("red onion, thinly sliced").amount(new BigDecimal(".25"))
            .unitOfMeasure(each).build());
    tacosRecipe.addIngredient(
        Ingredient.builder().description("Roughly chopped cilantro").amount(new BigDecimal(1))
            .unitOfMeasure(each).build());
    tacosRecipe.addIngredient(
        Ingredient.builder().description("sour cream thinned with 1/4 cup milk")
            .amount(new BigDecimal(".5")).unitOfMeasure(cup).build());
    tacosRecipe.addIngredient(
        Ingredient.builder().description("lime, cut into wedges").amount(new BigDecimal(1))
            .unitOfMeasure(each).build());

    recipes.add(tacosRecipe);

    return recipes;
  }

}
