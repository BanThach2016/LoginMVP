package com.anhtong8x.myapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductResult {
    @SerializedName("totalRecord")
    private int totalRecord;
    @SerializedName("items")
    private List<Item> items;

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    class Item{
        @SerializedName("id")
        int id;
        @SerializedName("price")
        double price;
        @SerializedName("originalPrice")
        double originalPrice;
        @SerializedName("stock")
        int stock;
        @SerializedName("viewCount")
        int viewCount;
        @SerializedName("dateCreated")
        String dateCreated;
        @SerializedName("name")
        String name;
        @SerializedName("description")
        String description;
        @SerializedName("details")
        String details;
        @SerializedName("seoDescription")
        String seoDescription;
        @SerializedName("seoTitle")
        String seoTitle;
        @SerializedName("seoAlias")
        String seoAlias;
        @SerializedName("languageId")
        String languageId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public double getOriginalPrice() {
            return originalPrice;
        }

        public void setOriginalPrice(double originalPrice) {
            this.originalPrice = originalPrice;
        }

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }

        public int getViewCount() {
            return viewCount;
        }

        public void setViewCount(int viewCount) {
            this.viewCount = viewCount;
        }

        public String getDateCreated() {
            return dateCreated;
        }

        public void setDateCreated(String dateCreated) {
            this.dateCreated = dateCreated;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public String getSeoDescription() {
            return seoDescription;
        }

        public void setSeoDescription(String seoDescription) {
            this.seoDescription = seoDescription;
        }

        public String getSeoTitle() {
            return seoTitle;
        }

        public void setSeoTitle(String seoTitle) {
            this.seoTitle = seoTitle;
        }

        public String getSeoAlias() {
            return seoAlias;
        }

        public void setSeoAlias(String seoAlias) {
            this.seoAlias = seoAlias;
        }

        public String getLanguageId() {
            return languageId;
        }

        public void setLanguageId(String languageId) {
            this.languageId = languageId;
        }
    }

}
