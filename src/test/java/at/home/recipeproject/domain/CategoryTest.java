package at.home.recipeproject.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CategoryTest {

  Category category;

  @BeforeEach
  void setUp() {
    category = new Category();
  }

  @Test
  void getId() {
    // setup
    long idValue = 4L;
    category.setId(idValue);
    // execute & verify
    Assertions.assertEquals(idValue, category.getId());
  }

  @Test
  void getDescription() {
  }

  @Test
  void getRecipes() {
  }
}