package com.mycompany.items_service.mapper;

import com.mycompany.items_service.entity.Item;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemRowMapper implements RowMapper<Item> {

    @Override
    public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
        Item item = new Item();
        item.setId(rs.getString("id"));
        item.setTitle(rs.getString("title"));
        item.setDescription(rs.getString("description"));
        item.setCreatedAt(rs.getString("createdAt"));
        item.setUpdatedAt(rs.getString("updatedAt"));
        return item;
    }
}
