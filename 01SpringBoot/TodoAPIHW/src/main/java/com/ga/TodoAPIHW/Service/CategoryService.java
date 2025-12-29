package com.ga.TodoAPIHW.Service;


import com.ga.TodoAPIHW.Entity.Category;
import com.ga.TodoAPIHW.Entity.Item;
import com.ga.TodoAPIHW.Entity.User;
import com.ga.TodoAPIHW.Repository.CategoryRepository;
import com.ga.TodoAPIHW.security.MyUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository CategoryRepository ) {
        this.categoryRepository = CategoryRepository;
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

    public Category UpdateCategory(Long Id,Category model){
        Category updateCategory= getCategoryById(Id);
        updateCategory.setName(model.getName());
        updateCategory.setDescription(model.getName());
        updateCategory.setUpdatedAt(LocalDateTime.now());
        return categoryRepository.save(updateCategory);
    }

    public void DeleteCategoryById(Long Id){
        Category deleteCategory= getCategoryById(Id);
        categoryRepository.delete(deleteCategory);
    }

    public List<Item> AllListByCategoryId(Long Id){
        Category category= getCategoryById(Id);
        return  category.getItemList();
    }

}

