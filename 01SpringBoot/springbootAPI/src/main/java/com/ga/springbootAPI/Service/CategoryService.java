package com.ga.springbootAPI.Service;


import com.ga.springbootAPI.Entity.Category;
import com.ga.springbootAPI.Repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository CategoryRepository;

    public CategoryService(CategoryRepository CategoryRepository) {
        this.CategoryRepository = CategoryRepository;
    }

    public List<Category> getAllCategories(){
        return CategoryRepository.findAll();
    }

    public  Category getCategoryById(Long Id){
        return CategoryRepository.findById(Id).orElse(null);
    }

    public Category CreateCategory(Category model){
        model.setCreatedAt(LocalDateTime.now());
        return CategoryRepository.save(model);
    }

    public Category UpdateCategory(Category model){
        Category updateCategory= getCategoryById(model.getId());
        updateCategory.setName(model.getName());
        updateCategory.setDescription(model.getName());
        updateCategory.setUpdatedAt(LocalDateTime.now());
        return CategoryRepository.save(updateCategory);
    }

    public void DeleteCategoryById(Long Id){
        Category deleteCategory= getCategoryById(Id);
        CategoryRepository.delete(deleteCategory);
    }
}

