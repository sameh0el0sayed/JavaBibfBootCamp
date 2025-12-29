package com.ga.food.service;

import com.ga.food.exception.InformationExistException;
import com.ga.food.exception.InformationNotFoundException;
import com.ga.food.model.Category;
import com.ga.food.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(Category categoryObject) {
        System.out.println("Service Calling createCategory ==>");

        Category category = categoryRepository.findByName(categoryObject.getName());

        if(category != null){
            throw new InformationExistException("category with name "+ category.getName() + " already exists");
        }
        else
        {
            return categoryRepository.save(categoryObject);
        }
    }

    public List<Category> getCategories() {
        System.out.println("Service calling getCategories ==> ");
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategory(Long categoryId) {
        System.out.println("Service Calling getCategory ==>");
        Optional<Category> category = categoryRepository.findById(categoryId);
        if(category.isPresent()) {
            return category;
        }
        else {
            throw new InformationNotFoundException("Category with Id " + categoryId + " not found");
        }
    }

    public Category updateCategory(Long categoryId, Category categoryObject) {
        System.out.println("service calling updateCategory ==>");

        Category existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new InformationNotFoundException(
                                "category with id " + categoryId + " not found"
                        )
                );

        if (categoryObject.getName().equals(existingCategory.getName())) {
            throw new InformationExistException(
                    "category " + existingCategory.getName() + " already exists"
            );
        }

        existingCategory.setName(categoryObject.getName());
        existingCategory.setDescription(categoryObject.getDescription());

        return categoryRepository.save(existingCategory);
    }

    public Category deleteCategory(Long categoryId) {
        System.out.println("service calling deleteCategory ==>");

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new InformationNotFoundException(
                                "category with id " + categoryId + " not found"
                        )
                );

        categoryRepository.delete(category);
        return category;
    }
}
