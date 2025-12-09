package com.example.finalproject.controller;
import android.content.Context;
import com.example.finalproject.model.DBHelper;
import com.example.finalproject.model.Item;
import java.util.List;

public class ItemController {
    private final DBHelper dbHelper;

    public ItemController(Context ctx) {
        dbHelper = new DBHelper(ctx);
    }

    public void addItem(Item item) { dbHelper.insert(item); }
    public void updateItem(Item item) { dbHelper.update(item); }
    public void deleteItem(long id) { dbHelper.delete(id); }
    public Item getItem(long id) { return dbHelper.get(id); }
    public List<Item> getAll() { return dbHelper.getAll(); }
}
