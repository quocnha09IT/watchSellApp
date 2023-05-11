package com.google.dongho;

public class search_jv {
    public String image_product,name_product,price_product;

    public String getImage_product() {
        return image_product;
    }

    public void setImage_product(String image_product) {
        this.image_product = image_product;
    }

    public String getName_product() {
        return name_product;
    }

    public void setName_product(String name_product) {
        this.name_product = name_product;
    }

    public String getPrice_product() {
        return price_product;
    }

    public void setPrice_product(String price_product) {
        this.price_product = price_product;
    }

    public search_jv(String image_product, String name_product, String price_product) {
        this.image_product = image_product;
        this.name_product = name_product;
        this.price_product = price_product;
    }
}
