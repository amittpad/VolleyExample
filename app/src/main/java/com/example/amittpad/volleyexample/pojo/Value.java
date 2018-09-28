package com.example.amittpad.volleyexample.pojo;
import java.util.List;

public class Value {
    private String productId;
    private String sellerId;
    private String productName;
    private String description;
    private String price;
    private List<String> images = null;

    public Value() {
    }

    public Value(String productId, String sellerId, String productName, String description, String price, List<String> images) {
        this.productId = productId;
        this.sellerId = sellerId;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.images = images;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}