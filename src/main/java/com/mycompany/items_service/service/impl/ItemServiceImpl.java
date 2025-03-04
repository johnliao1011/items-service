package com.mycompany.items_service.service.impl;

import com.mycompany.items_service.dao.ItemDao;
import com.mycompany.items_service.entity.Item;
import com.mycompany.items_service.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemDao itemDao;

    @Override
    public Item addItem(Item item) {
        return itemDao.addItem(item);
    }

    @Override
    public List<Item> allItems() {
        return itemDao.allItems();
    }

    @Override
    public List<Item> searchItemByTitle(String title) {
        return itemDao.searchItemByTitle(title);
    }

    @Override
    public Item getDetail(String id) {
        return itemDao.getDetail(id);
    }

    @Override
    public Item updateItem(String id, Item item) {
        return itemDao.updateItem(id, item);
    }

    @Override
    public Item updateItemTitle(String id, String title) {
        return itemDao.updateItemTitle(id, title);
    }

    @Override
    public Void deleteItem(String id) {
        return itemDao.deleteItem(id);
    }
}
