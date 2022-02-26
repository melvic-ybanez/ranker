package com.melvic.ranker.items;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @RequestMapping("/items")
    public List<Item> listItems() {
        return itemService.listItems();
    }

    @RequestMapping("/items/{id}")
    public Optional<Item> getItem(@PathVariable String id) {
        return itemService.getItem(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/items")
    public void addItem(@RequestBody Item item) {
        itemService.addItem(item);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/items/{id}")
    public void updateItem(@RequestBody Item item) {
        itemService.updateItem(item);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/items/{id}")
    public void deleteItem(@PathVariable String id) {
        itemService.deleteItem(id);
    }
}
