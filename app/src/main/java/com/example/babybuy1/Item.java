package com.example.babybuy1;

import android.net.Uri;

import androidx.annotation.Nullable;

public class Item {
    private int id;
    private String name, description;
    private Uri image;
    private double price;
    private boolean purchased;



    public Item() {}

//    public Item(String name, double price, String description, String location, @Nullable Uri image) {
//        this.id = 0;
//        this.image = image;
//        this.name = name;
//        this.price = price;
//        this.description = description;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Uri getImage() {
        return image;
    }

    public void setImage(Uri image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(double price) {
         this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPurchased() {
        return purchased;
    }

    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image=" + image +
                ", price=" + price +
                ", purchased=" + purchased +
                '}';
    }
}
