package com.google.dongho;

public class product {
 private String Image,Name,Id,Price,Content,Desc;

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

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public product(String image, String name, String id, String price, String content, String desc) {
        Image = image;
        Name = name;
        Id = id;
        Price = price;
        Content = content;
        Desc = desc;
    }
}
