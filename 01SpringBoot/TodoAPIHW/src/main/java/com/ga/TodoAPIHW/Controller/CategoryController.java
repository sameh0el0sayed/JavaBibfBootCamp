package com.ga.TodoAPIHW.Controller;

import com.ga.TodoAPIHW.Entity.Category;
import com.ga.TodoAPIHW.Entity.Item;
import com.ga.TodoAPIHW.Service.CategoryService;
import com.ga.TodoAPIHW.Service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    private final CategoryService categoryService;
    private final ItemService itemService;

    public CategoryController(CategoryService categoryService, ItemService itemService) {
        this.categoryService = categoryService;
        this.itemService = itemService;
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
        return ResponseEntity.ok("Category Deleted");
    }

    @GetMapping("/categories/{categoryId}/items")
    public List<Item>  AllListByCategoryId(@PathVariable Long categoryId){
        return categoryService.AllListByCategoryId(categoryId);
    }
    @PostMapping("/categories/{categoryId}/items")
    public Item CreateItem(@PathVariable Long categoryId, @RequestBody Item model){
        return itemService.CreateItem(categoryId,model);
    }
    @GetMapping("/categories/{categoryId}/items/{itemId}")
    public Item getItemByIdByCategoryId(@PathVariable Long categoryId,@PathVariable Long itemId){
        return itemService.getItemByIdByCategoryId(categoryId,itemId);
    }
    @PutMapping("/categories/{categoryId}/items/{itemId}")
    public Item UpdateItemWithCategoryId(@PathVariable Long categoryId,@PathVariable Long itemId, @RequestBody Item model){
        return itemService.UpdateItemWithCategoryId(model,categoryId,itemId);
    }
    @DeleteMapping("/categories/{categoryId}/items/{itemId}")
    public ResponseEntity<?> DeleteItemWithCategoryId(@PathVariable Long categoryId, @PathVariable Long itemId, @RequestBody Item model){
        itemService.DeleteItemWithCategoryId(categoryId,itemId);
        return  ResponseEntity.ok("Item Deleted");
    }
}
