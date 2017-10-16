package com.frank.testpropertyfinder.datasource;

import com.frank.testpropertyfinder.model.Product;

import java.util.List;

public interface CatalogDataSource {
    void getProducts(int page, String order, DataSourceListener listener);

    void clear();

    interface DataSourceListener {
        void onSuccess(List<Product> result);

        void onError(String message);
    }
}
