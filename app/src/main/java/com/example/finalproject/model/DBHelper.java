package com.example.finalproject.model;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "todo.db";
    private static final int DB_VERSION = 1;

    public static final String TABLE = "items";
    public static final String COL_ID = "id";
    public static final String COL_TITLE = "title";
    public static final String COL_DESC = "description";
    public static final String COL_DONE = "done";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_TITLE + " TEXT, " + COL_DESC + " TEXT, " + COL_DONE + " INTEGER DEFAULT 0)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    // CRUD API
    public void insert(Item item) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_TITLE, item.getTitle());
        cv.put(COL_DESC, item.getDescription());
        cv.put(COL_DONE, item.isDone() ? 1 : 0);
        db.insert(TABLE, null, cv);
        db.close();
    }

    public void update(Item item) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_TITLE, item.getTitle());
        cv.put(COL_DESC, item.getDescription());
        cv.put(COL_DONE, item.isDone() ? 1 : 0);
        db.update(TABLE, cv, COL_ID + "=?", new String[]{String.valueOf(item.getId())});
        db.close();
    }

    public void delete(long id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE, COL_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public Item get(long id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(TABLE, null, COL_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);
        Item item = null;
        if (c != null && c.moveToFirst()) {
            item = new Item(
                    c.getLong(c.getColumnIndexOrThrow(COL_ID)),
                    c.getString(c.getColumnIndexOrThrow(COL_TITLE)),
                    c.getString(c.getColumnIndexOrThrow(COL_DESC)),
                    c.getInt(c.getColumnIndexOrThrow(COL_DONE)) == 1
            );
            c.close();
        }
        db.close();
        return item;
    }

    public List<Item> getAll() {
        List<Item> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(TABLE, null, null, null, null, null, COL_ID + " DESC");
        if (c != null) {
            while (c.moveToNext()) {
                Item item = new Item(
                        c.getLong(c.getColumnIndexOrThrow(COL_ID)),
                        c.getString(c.getColumnIndexOrThrow(COL_TITLE)),
                        c.getString(c.getColumnIndexOrThrow(COL_DESC)),
                        c.getInt(c.getColumnIndexOrThrow(COL_DONE)) == 1
                );
                list.add(item);
            }
            c.close();
        }
        db.close();
        return list;
    }
}
