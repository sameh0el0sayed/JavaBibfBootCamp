package com.ga.springbootAPI.Controller;

import com.ga.springbootAPI.Entity.Category;
import com.ga.springbootAPI.Service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    private final CategoryService categoryService;
    @GetMapping("/getAllCategories")
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }
    @GetMapping("/getCategoryById/{id}")
    public Category getCategoryById(@PathVariable Long id){
        return categoryService.getCategoryById(id);
    }
    @PostMapping("/CreateCategory")
    public Category CreateCategory(@RequestBody Category model){
        return categoryService.CreateCategory(model);
    }
    @PutMapping("/UpdateCategory")
    public Category  UpdateCategory(@RequestBody Category model){
        return categoryService.UpdateCategory(model);
    }

    @DeleteMapping("/DeleteCategoryById/{id}")
    public ResponseEntity<String> DeleteCategoryById(@PathVariable Long id){
        categoryService.DeleteCategoryById(id);
        return ResponseEntity.ok("Item Deleted");
    }
}
