package com.anhtong8x.myapplication.model;

public class ProductDownloadImageRequest {
    int productId, imageId;

    public ProductDownloadImageRequest(int productId, int imageId) {
        this.productId = productId;
        this.imageId = imageId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
