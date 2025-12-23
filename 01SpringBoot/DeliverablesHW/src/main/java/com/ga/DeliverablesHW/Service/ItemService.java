package com.ga.DeliverablesHW.Service;

import com.ga.DeliverablesHW.Entity.Category;
import com.ga.DeliverablesHW.Entity.Item;
import com.ga.DeliverablesHW.Repository.CategoryRepository;
import com.ga.DeliverablesHW.Repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;

    public ItemService(ItemRepository itemRepository, CategoryRepository categoryRepository) {
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Item> getAllItems(){
        return itemRepository.findAll();
    }

    public  Item getItemById(Long Id){
        return itemRepository.findById(Id).orElse(null);
    }

    public Item CreateItem(Item model){
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
        Category newCategory=categoryRepository.findById(model.getCategory().getId()).orElse(null);
        updateItem.setCategory(newCategory);
        return itemRepository.save(updateItem);
    }
}
