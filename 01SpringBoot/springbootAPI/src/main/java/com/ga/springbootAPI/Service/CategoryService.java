package com.ga.springbootAPI.Service;


import com.ga.springbootAPI.Entity.Category;
import com.ga.springbootAPI.Entity.Recipe;
import com.ga.springbootAPI.Entity.User;
import com.ga.springbootAPI.Exception.InformationExistException;
import com.ga.springbootAPI.Exception.InformationNotFoundException;
import com.ga.springbootAPI.Repository.CategoryRepository;
import com.ga.springbootAPI.Repository.RecipeRepository;
import com.ga.springbootAPI.security.MyUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;

    public CategoryService(CategoryRepository CategoryRepository, RecipeRepository recipeRepository) {
        this.categoryRepository = CategoryRepository;
        this.recipeRepository = recipeRepository;
    }

    public static User getCurrentLoggedInUser() {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return userDetails.getUser();
    }

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public  Category getCategoryById(Long Id){
        return categoryRepository.findById(Id).orElse(null);
    }

    public Category CreateCategory(Category model){
        model.setCreatedAt(LocalDateTime.now());
        return categoryRepository.save(model);
    }

    public Category UpdateCategory(Category model){
        Category updateCategory= getCategoryById(model.getId());
        updateCategory.setName(model.getName());
        updateCategory.setDescription(model.getName());
        updateCategory.setUpdatedAt(LocalDateTime.now());
        return categoryRepository.save(updateCategory);
    }

    public void DeleteCategoryById(Long Id){
        Category deleteCategory= getCategoryById(Id);
        categoryRepository.delete(deleteCategory);
    }

    public Recipe createCategoryRecipe(Long categoryId, Recipe recipeObject) {
        Category category = categoryRepository.findByIdAndUserId(categoryId, CategoryService.getCurrentLoggedInUser().getId());
        if (category == null) {
            throw new InformationNotFoundException(
                    "category with id " + categoryId + " not belongs to this user or category does not exist");
        }
        Recipe recipe = recipeRepository.findByNameAndUserId(recipeObject.getName(), CategoryService.getCurrentLoggedInUser().getId());
        if (recipe != null) {
            throw new InformationExistException("recipe with name " + recipe.getName() + " already exists");
        }
        recipeObject.setUser(CategoryService.getCurrentLoggedInUser());
        recipeObject.setCategory(category);
        return recipeRepository.save(recipeObject);
    }


    public List<Recipe> getCategoryRecipes(Long categoryId) {
        Category category = categoryRepository.findByIdAndUserId(categoryId, CategoryService.getCurrentLoggedInUser().getId());
        if (category == null) {
            throw new InformationNotFoundException("category with id " + categoryId + " " +
                    "not belongs to this user or category does not exist");
        }
        return category.getRecipeList();
    }

    public Recipe getCategoryRecipe(Long categoryId, Long recipeId) {
        Category category = categoryRepository.findByIdAndUserId(categoryId, CategoryService.getCurrentLoggedInUser().getId());
        if (category == null) {
            throw new InformationNotFoundException("category with id " + categoryId +
                    " not belongs to this user or category does not exist");
        }
        Optional<Recipe> recipe = recipeRepository.findByCategoryId(
                categoryId).stream().filter(p -> p.getId().equals(recipeId)).findFirst();
        if (recipe.isEmpty()) {
            throw new InformationNotFoundException("recipe with id " + recipeId +
                    " not belongs to this user or recipe does not exist");
        }
        return recipe.get();
    }


    public Recipe updateCategoryRecipe(Long categoryId, Long recipeId, Recipe recipeObject) {
        Category category = categoryRepository.findByIdAndUserId(categoryId, CategoryService.getCurrentLoggedInUser().getId());
        if (category == null) {
            throw new InformationNotFoundException("category with id " + categoryId +
                    " not belongs to this user or category does not exist");
        }
        Optional<Recipe> recipe = recipeRepository.findByCategoryId(
                categoryId).stream().filter(p -> p.getId().equals(recipeId)).findFirst();
        if (recipe.isEmpty()) {
            throw new InformationNotFoundException("recipe with id " + recipeId +
                    " not belongs to this user or recipe does not exist");
        }
        Recipe oldRecipe = recipeRepository.findByNameAndUserIdAndIdIsNot(
                recipeObject.getName(), CategoryService.getCurrentLoggedInUser().getId(), recipeId);
        if (oldRecipe != null) {
            throw new InformationExistException("recipe with name " + oldRecipe.getName() + " already exists");
        }
        recipe.get().setName(recipeObject.getName());
        recipe.get().setIngredients(recipeObject.getIngredients());
        recipe.get().setSteps(recipeObject.getSteps());
        recipe.get().setTime(recipeObject.getTime());
        recipe.get().setPortions(recipeObject.getPortions());
        return recipeRepository.save(recipe.get());
    }

    public void deleteCategoryRecipe(Long categoryId, Long recipeId) {
        Category category = categoryRepository.findByIdAndUserId(categoryId, CategoryService.getCurrentLoggedInUser().getId());
        if (category == null) {
            throw new InformationNotFoundException("category with id " + categoryId +
                    " not belongs to this user or category does not exist");
        }
        Optional<Recipe> recipe = recipeRepository.findByCategoryId(
                categoryId).stream().filter(p -> p.getId().equals(recipeId)).findFirst();
        if (recipe.isEmpty()) {
            throw new InformationNotFoundException("recipe with id " + recipeId +
                    " not belongs to this user or recipe does not exist");
        }
        recipeRepository.deleteById(recipe.get().getId());
    }
}

