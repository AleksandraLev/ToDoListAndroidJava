package com.example.finalproject.controller;
import android.content.Context;
import com.example.finalproject.model.DBHelper;
import com.example.finalproject.model.Item;
import java.util.List;

public class ItemController {
    private DBHelper db;

    public ItemController(Context ctx) {
        db = new DBHelper(ctx);
    }

    public long addItem(Item item) { return db.insert(item); }
    public int updateItem(Item item) { return db.update(item); }
    public int deleteItem(long id) { return db.delete(id); }
    public Item getItem(long id) { return db.get(id); }
    public List<Item> getAll() { return db.getAll(); }
}
