package com.melvic.ranker.items;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    List<Item> listItems();

    Optional<Item> getItem(String itemId);

    void addItem(Item item);

    void updateItem(Item item);

    void deleteItem(String itemId);
}
