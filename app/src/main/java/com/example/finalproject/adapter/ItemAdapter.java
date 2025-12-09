package com.example.finalproject.adapter;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.finalproject.R;
import com.example.finalproject.model.Item;

import java.util.List;

import android.app.AlertDialog;
import android.widget.ImageButton;

import com.example.finalproject.controller.ItemController;
import com.example.finalproject.view.EditActivity;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>{
    private final List<Item> itemList;
    private final OnItemClickListener listener;
    private final Context context;
    private final ItemController itemController;

    public interface OnItemClickListener {
        void onItemClick(Item item);
        void onItemLongClick(Item item);
    }

    public ItemAdapter(Context context, List<Item> itemList, OnItemClickListener listener) {
        this.itemList = itemList;
        this.listener = listener;
        this.context = context;
        itemController = new ItemController(context);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        ImageButton btnEdit, btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.rowTitle);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }

    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_row, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.tvTitle.setText(item.getTitle());

        holder.itemView.setOnClickListener(v -> listener.onItemClick(item));

        holder.itemView.setOnLongClickListener(v -> {
            listener.onItemLongClick(item);
            return true;
        });

        holder.btnEdit.setOnClickListener(v -> {
            Intent i = new Intent(context, EditActivity.class);
            i.putExtra("item_id", item.getId());
            context.startActivity(i);
        });

        holder.btnDelete.setOnClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Удалить?")
                    .setMessage("Вы точно хотите удалить эту заметку?")
                    .setPositiveButton("Удалить", (dialog, which) -> {
                        itemController.deleteItem(item.getId());
                        itemList.remove(position);
                        notifyItemRemoved(position);
                    })
                    .setNegativeButton("Отмена", null)
                    .show();
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
