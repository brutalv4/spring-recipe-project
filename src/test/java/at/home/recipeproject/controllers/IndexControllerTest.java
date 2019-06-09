package at.home.recipeproject.controllers;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import at.home.recipeproject.domain.Recipe;
import at.home.recipeproject.services.RecipeService;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

class IndexControllerTest {

  @Mock
  RecipeService recipeService;

  @Mock
  Model model;

  IndexController controller;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
    controller = new IndexController(recipeService);
  }

  @Test
  void testMockMVC() throws Exception {
    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

    mockMvc.perform(get("/"))
        .andExpect(status().isOk())
        .andExpect(view().name("index"));
  }

  @Test
  void getIndexPage() {
    // given
    HashSet<Recipe> recipes = new HashSet<>();
    recipes.add(Recipe.builder().id(1L).build());
    recipes.add(Recipe.builder().id(2L).build());

    when(recipeService.getRecipes()).thenReturn(recipes);

    ArgumentCaptor<Set> argumentCaptor = ArgumentCaptor.forClass(Set.class);

    // when
    String viewName = controller.getIndexPage(model);

    // then
    Assertions.assertEquals("index", viewName);
    verify(recipeService, times(1)).getRecipes();
    verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
    Assertions.assertEquals(2, argumentCaptor.getValue().size());
  }
}