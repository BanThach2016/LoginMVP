package com.anhtong8x.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class ProductImageResult {
    @SerializedName("id")
    int id;

    @SerializedName("productId")
    int productId;

    @SerializedName("imagePath")
    String imagePath;

    @SerializedName("caption")
    String caption;

    @SerializedName("isDefault")
    boolean isDefault;

    @SerializedName("dateCreated")
    String dateCreated;

    @SerializedName("sortOrder")
    int sortOrder;

    @SerializedName("fileSize")
    byte fileSize;

    public ProductImageResult(int id, int productId, String imagePath, String caption, boolean isDefault, String dateCreated, int sortOrder, byte fileSize) {
        this.id = id;
        this.productId = productId;
        this.imagePath = imagePath;
        this.caption = caption;
        this.isDefault = isDefault;
        this.dateCreated = dateCreated;
        this.sortOrder = sortOrder;
        this.fileSize = fileSize;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    public byte getFileSize() {
        return fileSize;
    }

    public void setFileSize(byte fileSize) {
        this.fileSize = fileSize;
    }
}
