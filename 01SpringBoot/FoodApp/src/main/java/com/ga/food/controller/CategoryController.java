package com.ga.food.controller;

import com.ga.food.exception.InformationExistException;
import com.ga.food.model.Category;
import com.ga.food.repository.CategoryRepository;
import com.ga.food.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // CRUD - C - HTTP POST - To create a record (Category).
    @PostMapping("/categories/")
    public Category createCategory(@RequestBody Category categoryObject) {
        System.out.println("Calling createCategory ==>");
        return categoryService.createCategory(categoryObject);
    }

    // CRUD - R - HTTP GET - To read all record (categoryList)/ record by Id(category) from a database
    @GetMapping("/categories/")
    public List<Category> getCategories() {
        System.out.println("Calling getCategories ==> ");
        return categoryService.getCategories();
    }

    // Read One
    @GetMapping(path = "/categories/{categoryId}")
    public Optional<Category> getCategory(@PathVariable Long categoryId) {
        System.out.println("Calling getCategory ==>");
        return categoryService.getCategory(categoryId);
    }

    // CRUD - U - Update - HTTP PUT - will update a category.
    @PutMapping("/categories/{categoryId}")
    public Category updateCategory(@PathVariable(value = "categoryId") Long categoryId, @RequestBody Category categoryObject) {
        System.out.println("calling updateCategory ==>");
        return categoryService.updateCategory(categoryId, categoryObject);
    }

    // CRUD - D - DELETE - HTTP DELETE - will remove the category from the database.
    @DeleteMapping("/categories/{categoryId}")
    public Category deleteCategory(@PathVariable(value = "categoryId") Long categoryId) {
        System.out.println("calling deleteCategory ==>");
        return categoryService.deleteCategory(categoryId);
    }

}
