package com.mycompany.items_service.dao.impl;

import com.mycompany.items_service.dao.ItemDao;
import com.mycompany.items_service.entity.Item;
import com.mycompany.items_service.mapper.ItemRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ItemDaoImply implements ItemDao {

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Item addItem(Item item) {
        String sql = "INSERT INTO Item(title, description, createdAt, updatedAt) VALUE(:item_title, :item_description, :createAt, :updatedAt)";

        Map<String, Object> map = new HashMap<>();
        map.put("item_title", item.getTitle());
        map.put("item_description", item.getDescription());
        map.put("createAt", LocalDateTime.now());
        map.put("updatedAt", LocalDateTime.now());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        sql = "SELECT* FROM Item WHERE title = :item_title AND description = :item_description";
        map.remove("createAt");
        map.remove("updatedAt");

        List<Item> list = namedParameterJdbcTemplate.query(sql, map, new ItemRowMapper());
        return list.isEmpty()? null: list.get(0);
    }

    @Override
    public List<Item> allItems() {
        String sql = "SELECT * FROM Item";
        Map<String, Object> map = new HashMap<>();

        return namedParameterJdbcTemplate.query(sql, map, new ItemRowMapper());
    }

    @Override
    public List<Item> searchItemByTitle(String title) {
        String sql = "SELECT * FROM Item WHERE title = :itemTitle";
        Map<String, Object> map = new HashMap<>();
        map.put("itemTitle", title);

        return namedParameterJdbcTemplate.query(sql, map, new ItemRowMapper());
    }

    @Override
    public Item getDetail(String id) {
        String sql = "SELECT * FROM Item WHERE id = :id";
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);

        List<Item> list = namedParameterJdbcTemplate.query(sql, map, new ItemRowMapper());
        return list.isEmpty()? null: list.get(0);
    }

    @Override
    public Item updateItem(String id, Item item) {
        String sql = "UPDATE Item Set title = :title, description = :description, updatedAt = :updatedAt WHERE id = :id";
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("title", item.getTitle());
        map.put("description", item.getDescription());
        map.put("updatedAt", LocalDateTime.now());

        namedParameterJdbcTemplate.update(sql, map);

        sql = "SELECT * FROM Item WHERE id = :id";
        map.remove("title");
        map.remove("description");
        map.remove("updatedAt");
        List<Item> list = namedParameterJdbcTemplate.query(sql, map, new ItemRowMapper());
        return list.isEmpty()? null: list.get(0);
    }

    @Override
    public Item updateItemTitle(String id, String title) {
        String sql = "UPDATE Item Set title = :title WHERE id = :id";
        Map<String, Object> map = new HashMap<>();
        map.put("title", title);
        map.put("id", id);
        namedParameterJdbcTemplate.update(sql, map);

        sql = "SELECT * FROM Item WHERE id = :id";
        map.remove("title");
        List<Item> list = namedParameterJdbcTemplate.query(sql, map, new ItemRowMapper());
        return list.isEmpty()? null: list.get(0);
    }

    @Override
    public Void deleteItem(String id) {
        String sql = "DELETE FROM Item WHERE id = :id";
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        namedParameterJdbcTemplate.update(sql, map);
        return null;
    }
}
