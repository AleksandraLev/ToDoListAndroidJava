package com.example.finalproject.view;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.finalproject.R;
import com.example.finalproject.controller.ItemController;
import com.example.finalproject.model.Item;
import com.example.finalproject.adapter.ItemAdapter;
import com.example.finalproject.util.NotificationUtils;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemAdapter.OnItemClickListener{
    private ItemController controller;
    private RecyclerView rv;
    private ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        controller = new ItemController(this);
        rv = findViewById(R.id.recyclerViewItems);
        rv.setLayoutManager(new LinearLayoutManager(this));
        findViewById(R.id.fabAdd).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AddEditActivity.class));
        });
        NotificationUtils.showNotification(this, "Приложение запущено", "Добро пожаловать в ToDo MVC");
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadItems();
    }

    private void loadItems() {
        List<Item> items = controller.getAll();
        adapter = new ItemAdapter(items, this);
        rv.setAdapter(adapter);
    }

    @Override
    public void onItemClick(Item item) {
        Intent i = new Intent(this, DetailActivity.class);
        i.putExtra("item_id", item.getId());
        startActivity(i);
    }

    @Override
    public void onItemLongClick(Item item) {
        item.setDone(!item.isDone());
        controller.updateItem(item);
        loadItems();
        Toast.makeText(this, "Статус изменён", Toast.LENGTH_SHORT).show();
    }
}
