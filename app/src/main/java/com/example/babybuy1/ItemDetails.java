package com.example.babybuy1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ItemDetails extends AppCompatActivity {
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String PRICE = "price";
    public static final String DESCRIPTION = "description";
    public static final String IMAGE = "image";
    public static final String IS_PURCHASED = "purchased";
    public static final int EDIT_ITEM_REQUEST = 10001;

    public static Intent getIntent(Context context, int id) {
        Intent intent = new Intent(context, ItemDetails.class);
        intent.putExtra(ID, id);

        return intent;
    }

    private Item item;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        item = new Item();
        databaseHelper = new DatabaseHelper(this);
        ImageView imageView = findViewById(R.id.imageViewItem);
        TextView textViewName = findViewById(R.id.textViewName);
        TextView textViewPrice = findViewById(R.id.textViewPrice);
        TextView textViewDescription = findViewById(R.id.textViewDescription);
        Button buttonEdit = findViewById(R.id.buttonEditItem);
        Button buttonShare = findViewById(R.id.buttonShareItem);

        Bundle bundle = getIntent().getExtras();
        int id = bundle.getInt(ItemDetails.ID);
        Log.d("ItemDetails: id:", id+"");

        item = retrieveData(id);
        imageView.setImageURI(item.getImage());
        textViewName.setText(item.getName());
        textViewPrice.setText(item.getPrice().toString());
        textViewDescription.setText(item.getDescription());
        buttonEdit.setOnClickListener(v -> startEditItemActivity(v, item));
        buttonShare.setOnClickListener(this::startShareItemActivity);
    }

    private void startEditItemActivity(View v, Item item) {
        startActivity(EditItem.getIntent(getApplicationContext(), item));
    }

    private Item retrieveData(int id) {
        Cursor cursor = databaseHelper.getElementById(id);
        cursor.moveToNext();

        Item item = new Item();
        item.setId(cursor.getInt(0));
        item.setName(cursor.getString(1));
        item.setPrice(cursor.getDouble(2));
        item.setDescription(cursor.getString(3));
        item.setImage(Uri.EMPTY);
        try {
            Uri imageUri = Uri.parse(cursor.getString(4));
            item.setImage(imageUri);
        } catch (NullPointerException e) {
            Toast.makeText(
                    this,
                    "Error occurred in identifying image resource!",
                    Toast.LENGTH_SHORT
            ).show();
        }
        item.setPurchased(cursor.getInt(5) == 1);

        Log.d("ItemDetails:", item.toString());
        return item;
    }

    private void startShareItemActivity(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "");
        intent.putExtra(Intent.EXTRA_TEXT, "Check ypur cool Application ");
        startActivity(Intent.createChooser(intent, "Share via"));
    }


}