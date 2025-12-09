package com.example.finalproject.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject.R;
import com.example.finalproject.controller.ItemController;
import com.example.finalproject.model.Item;

public class EditActivity extends AppCompatActivity {
    private EditText etTitle, etDescription;
    private Button btnSave, btnCancel;

    private ItemController itemController;
    private long itemId = -1;
    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        itemController = new ItemController(this);

        etTitle = findViewById(R.id.etTitle);
        etDescription = findViewById(R.id.etDescription);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

        btnSave.setText("Сохранить изменения");

        itemId = getIntent().getLongExtra("item_id", -1);
        item = itemController.getItem(itemId);

        if (item != null) {
            etTitle.setText(item.getTitle());
            etDescription.setText(item.getDescription());
        }

        btnSave.setOnClickListener(v -> editItem());
        btnCancel.setOnClickListener(v -> finish());
    }

    private void editItem() {
        String title = etTitle.getText().toString().trim();
        String description = etDescription.getText().toString().trim();

        if (title.isEmpty()) {
            etTitle.setError("Введите заголовок");
            return;
        }

        item.setTitle(title);
        item.setDescription(description);

        itemController.updateItem(item);
        finish();
    }
}
