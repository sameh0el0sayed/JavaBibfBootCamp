package com.ga.TodoAPIHW.Service;


import com.ga.TodoAPIHW.Entity.Category;
import com.ga.TodoAPIHW.Entity.Item;
import com.ga.TodoAPIHW.Repository.CategoryRepository;
import com.ga.TodoAPIHW.Repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final CategoryService categoryService;

    public ItemService(ItemRepository itemRepository, CategoryService categoryService) {
        this.itemRepository = itemRepository;

        this.categoryService = categoryService;
    }

    public List<Item> getAllItems(){
        return itemRepository.findAll();
    }

    public  Item getItemById(Long Id){
        return itemRepository.findById(Id).orElse(null);
    }


    public  Item getItemByIdByCategoryId(Long Id,Long categoryId){
        return itemRepository.findById(Id).filter(f->f.getCategory().getId().equals(categoryId)).orElse(null);
    }
    public Item CreateItem(Long categoryId,Item model){
        Category category=categoryService.getCategoryById(categoryId);
        model.setCategory(category);
        return itemRepository.save(model);
    }

    public Item UpdateItem(Item model){
        Item updateItem= getItemById(model.getId());
        updateItem.setName(model.getName());
        updateItem.setDescription(model.getDescription());
        updateItem.setDueDate(model.getDueDate());
        return itemRepository.save(updateItem);
    }


    public void DeleteItemById(Long Id){
        Item deleteItem= getItemById(Id);
        itemRepository.delete(deleteItem);
    }

    public Item AssignCategoryItem(Item model){
        Item updateItem= getItemById(model.getId());
        Category newCategory=categoryService.getCategoryById(model.getCategory().getId());
        updateItem.setCategory(newCategory);
        return itemRepository.save(updateItem);
    }
    public Item UpdateItemWithCategoryId(Item model,Long categoryId){
        Item updateItem= getItemById(model.getId());
        Category newCategory=categoryService.getCategoryById(categoryId);
        updateItem.setName(model.getName());
        updateItem.setDescription(model.getDescription());
        updateItem.setDueDate(model.getDueDate());
        updateItem.setCategory(newCategory);
        return itemRepository.save(updateItem);
    }
    public void DeleteItemWithCategoryId(Long ItemId,Long categoryId){
    Item itemByIdByCategoryId= getItemByIdByCategoryId(ItemId,categoryId);
        itemRepository.delete(itemByIdByCategoryId);
    }
}

