package com.mycompany.items_service.dao;

import com.mycompany.items_service.entity.Item;

import java.util.List;

public interface ItemDao {
    Item addItem(Item item);

    List<Item> allItems();

    List<Item> searchItemByTitle(String title);

    Item getDetail(String id);

    Item updateItem(String id, Item item);

    Item updateItemTitle(String id, String title);

    Void deleteItem(String id);
}
