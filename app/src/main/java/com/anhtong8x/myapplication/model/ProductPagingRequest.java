package com.anhtong8x.myapplication.model;

public class ProductPagingRequest {
    int CategoryId, PageIndex, PageSize;

    public ProductPagingRequest(int categoryId, int pageIndex, int pageSize) {
        CategoryId = categoryId;
        PageIndex = pageIndex;
        PageSize = pageSize;
    }

    public int getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(int categoryId) {
        CategoryId = categoryId;
    }

    public int getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(int pageIndex) {
        PageIndex = pageIndex;
    }

    public int getPageSize() {
        return PageSize;
    }

    public void setPageSize(int pageSize) {
        PageSize = pageSize;
    }
}
