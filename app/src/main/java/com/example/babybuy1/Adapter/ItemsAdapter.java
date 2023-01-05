package com.example.babybuy1.Adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.babybuy1.Item;
import com.example.babybuy1.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemViewHolder> {
    private final RecyclerViewItemClickListener itemClickListener;
    private final ArrayList<Item> items;

    public ItemsAdapter(ArrayList<Item> items, RecyclerViewItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
        this.items = items;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item item = items.get(position);
        holder.textViewName.setText(item.getName());
        if(item.isPurchased()) {
            holder.textViewName.setCompoundDrawablesWithIntrinsicBounds(0,0, R.drawable.ic_check, 0);
        }
        holder.textViewPrice.setText(String.valueOf(item.getPrice()));
        holder.textViewDescription.setText(item.getDescription());
        Uri imageUri = item.getImage();
        if(imageUri != null) {
            holder.imageView.setImageURI(imageUri);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        CircleImageView imageView;
        TextView textViewName, textViewPrice, textViewDescription;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.itemImage);
            textViewName = itemView.findViewById(R.id.itemName);
            textViewPrice = itemView.findViewById(R.id.itemPrice);
            textViewDescription = itemView.findViewById(R.id.itemDescription);

            itemView.setOnClickListener(this::itemViewOnClick);
        }

        private void itemViewOnClick(View view) {
            itemClickListener.onItemClick(view, getAdapterPosition());
        }
    }
}
