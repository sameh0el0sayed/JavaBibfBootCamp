package com.ga.TodoAPIHW.Controller;

import com.ga.TodoAPIHW.Entity.Category;
import com.ga.TodoAPIHW.Entity.Item;
import com.ga.TodoAPIHW.Service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService ) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }
    @PostMapping("/categories")
    public Category CreateCategory(@RequestBody Category model){
        return categoryService.CreateCategory(model);
    }
    @GetMapping("/categories/{id}")
    public Category getCategoryById(@PathVariable Long id){
        return categoryService.getCategoryById(id);
    }
    @PutMapping("/categories/{id}")
    public Category  UpdateCategory(@PathVariable Long id,@RequestBody Category model){
        return categoryService.UpdateCategory(id,model);
    }
    @DeleteMapping("/categories/{id}")
    public ResponseEntity<String> DeleteCategoryById(@PathVariable Long id){
        categoryService.DeleteCategoryById(id);
        return ResponseEntity.ok("Item Deleted");
    }

    @PutMapping("/categories/{categoryId}/items")
    public List<Item>  AllListByCategoryId(@PathVariable Long id){
        return categoryService.AllListByCategoryId(id);
    }


}
