package com.example.finalproject.view;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.finalproject.R;
import com.example.finalproject.controller.ItemController;
import com.example.finalproject.model.Item;

public class DetailActivity extends AppCompatActivity {
    private TextView tvTitle, tvDescription;
    private ItemController itemController;
    private long itemId;
    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        itemController = new ItemController(this);

        tvTitle = findViewById(R.id.tvDetailTitle);
        tvDescription = findViewById(R.id.tvDetailDescription);

        itemId = getIntent().getLongExtra("item_id", -1);

        item = itemController.getItem(itemId);
        if (item != null) {
            tvTitle.setText(item.getTitle());
            tvDescription.setText(item.getDescription());
        }
    }
}
