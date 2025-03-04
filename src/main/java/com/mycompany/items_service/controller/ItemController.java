package com.mycompany.items_service.controller;

import com.mycompany.items_service.entity.Item;
import com.mycompany.items_service.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("/addItem")
    public ResponseEntity<Item> addItem(@RequestBody Item itemDTO){
        Item item = itemService.addItem(itemDTO);
        return ResponseEntity.status(201).body(item);
    }

    @PostMapping("/allItems")
    public ResponseEntity<List<Item>> allItems(){
        List<Item> items = itemService.allItems();
        return ResponseEntity.status(200).body(items);
    }

    @GetMapping("/search-items-by-title")
    public ResponseEntity<List<Item>> searchByTitle(@RequestParam String title){
        List<Item> items = itemService.searchItemByTitle(title);
        return ResponseEntity.status(200).body(items);
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<Item> getDetail(@PathVariable String id){
        Item item = itemService.getDetail(id);
        return ResponseEntity.status(200).body(item);
    }

    @PutMapping("/items/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable String id, @RequestBody Item item){
        item = itemService.updateItem(id, item);
        return ResponseEntity.status(200).body(item);
    }

    @PatchMapping("/items/title/{id}")
    public ResponseEntity<Item> updateItemTitle(@PathVariable String id, @RequestBody Item item){
        item = itemService.updateItemTitle(id, item.getTitle());
        return ResponseEntity.status(200).body(item);
    }

    @DeleteMapping("/items")
    public ResponseEntity<Void> deleteItem(@RequestParam String id){
        itemService.deleteItem(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
