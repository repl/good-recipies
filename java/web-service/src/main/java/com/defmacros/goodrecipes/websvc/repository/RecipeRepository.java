package com.defmacros.goodrecipes.websvc.repository;

import com.defmacros.goodrecipes.websvc.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
