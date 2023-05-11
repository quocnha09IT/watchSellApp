package com.google.dongho;

public  class cart {
    public String name_product,image_product,quantity,price,customer_id,product_id,cart_id;

    public String getName_product() {
        return name_product;
    }

    public void setName_product(String name_product) {
        this.name_product = name_product;
    }

    public String getImage_product() {
        return image_product;
    }

    public void setImage_product(String image_product) {
        this.image_product = image_product;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getCart_id() {
        return cart_id;
    }

    public void setCart_id(String cart_id) {
        this.cart_id = cart_id;
    }

    public cart(String name_product, String image_product, String quantity, String price, String customer_id, String product_id, String cart_id) {
        this.name_product = name_product;
        this.image_product = image_product;
        this.quantity = quantity;
        this.price = price;
        this.customer_id = customer_id;
        this.product_id = product_id;
        this.cart_id = cart_id;
    }
}
