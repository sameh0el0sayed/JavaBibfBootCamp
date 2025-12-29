package com.ga.ToDoHW.Controller;

import com.ga.ToDoHW.Entity.Category;
import com.ga.ToDoHW.Entity.Item;
import com.ga.ToDoHW.Service.CategoryService;
import com.ga.ToDoHW.Service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final ItemService itemService;
    private final CategoryService categoryService;

    public AdminController(ItemService itemService, CategoryService categoryService) {
        this.itemService = itemService;
        this.categoryService = categoryService;
    }

    @GetMapping("/dashboard")
    public ResponseEntity<String> dashboard(){
        return ResponseEntity.ok("Welcome Admin");
    }

    @GetMapping("/getAllItem")
    public List<Item> getAllItem(){
        return itemService.getAllItems();
    }
    @GetMapping("/getItemById/{id}")
    public Item getItemById(@PathVariable Long id){
        return itemService.getItemById(id);
    }
    @PostMapping("/CreateItem")
    public Item CreateItem(@RequestBody Item model){
        return itemService.CreateItem(model);
    }
    @PutMapping("/UpdateItem")
    public Item UpdateItem(@RequestBody Item model){
        return itemService.UpdateItem(model);
    }

    @PutMapping("/AssignCategoryItem")
    public Item AssignCategoryItem(@RequestBody Item model){
        return itemService.AssignCategoryItem(model);
    }

    @DeleteMapping("/DeleteItemById/{id}")
    public ResponseEntity<String> DeleteItemById(@PathVariable Long id){
         itemService.DeleteItemById(id);
         return ResponseEntity.ok("Item Deleted");
    }


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
