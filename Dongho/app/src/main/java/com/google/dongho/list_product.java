package com.google.dongho;

public class list_product {
    private String Image,Name,Id,Price;

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public list_product(String image, String name, String id, String price) {
        Image = image;
        Name = name;
        Id = id;
        Price = price;
    }
}
