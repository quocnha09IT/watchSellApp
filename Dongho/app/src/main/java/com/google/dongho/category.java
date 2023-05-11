package com.google.dongho;

public class category {
    private String name_category,id_category,image_category;

    public String getName_category() {
        return name_category;
    }

    public void setName_category(String name_category) {
        this.name_category = name_category;
    }

    public String getId_category() {
        return id_category;
    }

    public void setId_category(String desc_category) {
        this.id_category = desc_category;
    }

    public String getImage_category() {
        return image_category;
    }

    public void setImage_category(String image_category) {
        this.image_category = image_category;
    }

    public category(String name_category, String id_category, String image_category) {
        this.name_category = name_category;
        this.id_category = id_category;
        this.image_category = image_category;
    }
}
