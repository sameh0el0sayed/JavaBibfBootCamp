package com.ga.ToDoHW.Service;

import com.ga.ToDoHW.Entity.Category;
import com.ga.ToDoHW.Repository.CategoryRepository;
import org.springframework.stereotype.Service;

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
        return CategoryRepository.save(model);
    }

    public Category UpdateCategory(Category model){
        Category updateCategory= getCategoryById(model.getId());
        updateCategory.setName(model.getName());
        updateCategory.setDescription(model.getName());
        return CategoryRepository.save(updateCategory);
    }

    public void DeleteCategoryById(Long Id){
        Category deleteCategory= getCategoryById(Id);
        CategoryRepository.delete(deleteCategory);
    }
}
