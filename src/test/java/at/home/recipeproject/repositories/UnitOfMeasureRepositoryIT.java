package at.home.recipeproject.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import at.home.recipeproject.domain.UnitOfMeasure;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Slf4j
@ExtendWith(SpringExtension.class)
@DataJpaTest
class UnitOfMeasureRepositoryIT {

  @Autowired
  UnitOfMeasureRepository unitOfMeasureRepository;

  @BeforeEach
  void setUp() {
    log.info("UnitOfMeasureRepositoryIT");
  }

  @Test
  void findByDescriptionTeaspoon() {
    Optional<UnitOfMeasure> teaspoon = unitOfMeasureRepository.findByDescription("Teaspoon");
    assertEquals("Teaspoon", teaspoon.get().getDescription());
  }

  @Test
  void findByDescriptionCup() {
    Optional<UnitOfMeasure> cup = unitOfMeasureRepository.findByDescription("Cup");
    assertEquals("Cup", cup.get().getDescription());
  }
}