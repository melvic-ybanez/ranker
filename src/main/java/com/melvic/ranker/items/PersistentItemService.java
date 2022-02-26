package com.melvic.ranker.items;

import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersistentItemService implements ItemService {
    private final ItemRepository itemRepository;

    public PersistentItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> listItems() {
        return Streamable.of(itemRepository.findAll()).toList();
    }

    @Override
    public Optional<Item> getItem(String itemId) {
        return itemRepository.findById(itemId);
    }

    @Override
    public void addItem(Item item) {
        itemRepository.save(item);
    }

    @Override
    public void updateItem(Item item) {
        itemRepository.save(item);
    }

    @Override
    public void deleteItem(String itemId) {
        itemRepository.deleteById(itemId);
    }
}
