package com.ga.springbootAPI.Service;

import com.ga.springbootAPI.Entity.Category;
import com.ga.springbootAPI.Entity.Recipe;
import com.ga.springbootAPI.Exception.InformationNotFoundException;
import com.ga.springbootAPI.Repository.CategoryRepository;
import com.ga.springbootAPI.Repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class RecipeService {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;

    public RecipeService(CategoryRepository categoryRepository, RecipeRepository recipeRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
    }

    public Recipe createRecipe(Long categoryId,Recipe model){
        System.out.println("service calling createCategoryRecipe ==>");
        try {
            Optional category = categoryRepository.findById(categoryId);
            model.setCategory((Category) category.get());
            return recipeRepository.save(model);
        } catch (NoSuchElementException e) {
            throw new InformationNotFoundException("category with id " + categoryId + " not found");
        }
    }
    public Recipe updateRecipe(Long categoryId, Recipe model) {
        System.out.println("service calling updateRecipe ==>");

        // Find category
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new InformationNotFoundException(
                        "Category with id " + categoryId + " not found"));

        // Find recipe
        Recipe existingRecipe = recipeRepository.findById(model.getId())
                .orElseThrow(() -> new InformationNotFoundException(
                        "Recipe with id " + model.getId() + " not found"));


        existingRecipe.setName(model.getName());
        existingRecipe.setTime(model.getTime());
        existingRecipe.setPortions(model.getPortions());
        existingRecipe.setIngredients(model.getIngredients());
        existingRecipe.setSteps(model.getSteps());
        existingRecipe.setCategory(category);


        return recipeRepository.save(existingRecipe);
    }

    public void deleteRecipe(Long categoryId, Long recipeId) {
        System.out.println("service calling deleteRecipe ==>");

        // Check category exists
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new InformationNotFoundException(
                        "Category with id " + categoryId + " not found"));

        // Check recipe exists
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new InformationNotFoundException(
                        "Recipe with id " + recipeId + " not found"));

        // Ensure recipe belongs to the given category
        if (!recipe.getCategory().getId().equals(category.getId())) {
            throw new InformationNotFoundException(
                    "Recipe with id " + recipeId + " does not belong to category " + categoryId);
        }

        // Delete recipe
        recipeRepository.delete(recipe);
    }


}
