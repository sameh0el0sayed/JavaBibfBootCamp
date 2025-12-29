package com.ga.springbootAPI.Controller;

import com.ga.springbootAPI.Entity.Recipe;
import com.ga.springbootAPI.Service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RecipeController {

    private RecipeService recipeService;

    @Autowired
    public void setRecipeService(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping("/categories/{categoryId}/recipes")
    public Recipe createCategoryRecipe(
            @PathVariable(value = "categoryId") Long categoryId, @RequestBody Recipe recipeObject) {
        System.out.println("calling createCategoryRecipe ==>");
        return recipeService.createRecipe(categoryId, recipeObject);
    }
    @PutMapping("/updateCategoryRecipe/{categoryId}/recipes")
    public Recipe updateCategoryRecipe(
            @PathVariable(value = "categoryId") Long categoryId, @RequestBody Recipe recipeObject) {
        System.out.println("calling updateCategoryRecipe ==>");
        return recipeService.updateRecipe(categoryId, recipeObject);
    }
    @DeleteMapping("/categories/{categoryId}/recipes/{recipeId}")
    public ResponseEntity<String> deleteRecipe(
            @PathVariable(value = "categoryId") Long categoryId,@PathVariable(value = "categoryId") Long recipeId) {
        System.out.println("calling deleteRecipe ==>");
         recipeService.deleteRecipe(categoryId, recipeId);
         return ResponseEntity.ok(recipeId+"Recipe deleted");
    }

}
