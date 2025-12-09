package com.example.finalproject.view;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.finalproject.R;
import com.example.finalproject.controller.ItemController;
import com.example.finalproject.model.Item;
import com.example.finalproject.adapter.ItemAdapter;
import com.example.finalproject.util.NotificationUtils;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
public class MainActivity extends AppCompatActivity implements ItemAdapter.OnItemClickListener{
    private ItemController itemController;
    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;

    private final String[] notificationPermission = {
            android.Manifest.permission.POST_NOTIFICATIONS
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemController = new ItemController(this);

        recyclerView = findViewById(R.id.recyclerViewItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        findViewById(R.id.fabAdd).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AddActivity.class));
        });
        requestNotificationPermission();
        NotificationUtils.showNotification(this, "Приложение запущено", "Добро пожаловать в Заметки");
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadItems();
    }

    private void loadItems() {
        List<Item> items = itemController.getAll();
        itemAdapter = new ItemAdapter(this, items, this); // Передаём MainActivity как listener
        recyclerView.setAdapter(itemAdapter);
    }

    @Override
    public void onItemClick(Item item) {
        Intent i = new Intent(this, DetailActivity.class);
        i.putExtra("item_id", item.getId());
        startActivity(i);
    }

    @Override
    public void onItemLongClick(Item item) { }

    private void requestNotificationPermission() {
        if (checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(notificationPermission, 100);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 100) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Уведомления включены", Toast.LENGTH_SHORT).show();
                NotificationUtils.showNotification(this, "Приложение запущено", "Добро пожаловать в Заметки");
            } else {
                Toast.makeText(this, "Разрешение на уведомления отклонено", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
