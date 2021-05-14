package com.anhtong8x.myapplication.model;

public class ProductPagingRequest {
    String languageId;
    int CategoryId, PageIndex, PageSize;

    public ProductPagingRequest(String languageId, int categoryId, int pageIndex, int pageSize) {
        this.languageId = languageId;
        CategoryId = categoryId;
        PageIndex = pageIndex;
        PageSize = pageSize;
    }

    public String getLanguageId() {
        return languageId;
    }

    public void setLanguageId(String languageId) {
        this.languageId = languageId;
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
