package com.example.finalproject.view;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.finalproject.R;
import com.example.finalproject.controller.ItemController;
import com.example.finalproject.model.Item;

public class AddEditActivity extends AppCompatActivity {
    private EditText etTitle, etDescription;
    private Button btnSave;

    private ItemController controller;
    private int itemId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        controller = new ItemController(this);

        etTitle = findViewById(R.id.etTitle);
        etDescription = findViewById(R.id.etDescription);
        btnSave = findViewById(R.id.btnSave);

        if (getIntent().hasExtra("item_id")) {
            itemId = getIntent().getIntExtra("item_id", -1);
            Item item = controller.getItem(itemId);

            if (item != null) {
                etTitle.setText(item.getTitle());
                etDescription.setText(item.getDescription());
            }
        }

        btnSave.setOnClickListener(v -> saveItem());
    }

    private void saveItem() {
        String title = etTitle.getText().toString().trim();
        String description = etDescription.getText().toString().trim();

        if (title.isEmpty()) {
            etTitle.setError("Введите заголовок");
            return;
        }

        if (itemId == -1) {
            controller.addItem(new Item(title, description));
        } else {
            controller.updateItem(new Item(itemId, title, description));
        }

        finish();
    }
}
