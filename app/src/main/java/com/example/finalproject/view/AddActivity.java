package com.example.finalproject.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject.R;
import com.example.finalproject.controller.ItemController;
import com.example.finalproject.model.Item;

public class AddActivity extends AppCompatActivity {
    private EditText etTitle, etDescription;
    private Button btnSave, btnCancel;

    private ItemController itemController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        itemController = new ItemController(this);

        etTitle = findViewById(R.id.etTitle);
        etDescription = findViewById(R.id.etDescription);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

        btnSave.setText("Добавить");

        btnSave.setOnClickListener(v -> addItem());
        btnCancel.setOnClickListener(v -> finish());
    }

    private void addItem() {
        String title = etTitle.getText().toString().trim();
        String description = etDescription.getText().toString().trim();

        if (title.isEmpty()) {
            etTitle.setError("Введите заголовок");
            return;
        }

        itemController.addItem(new Item(title, description));
        finish();
    }
}
