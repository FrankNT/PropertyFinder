package com.frank.testpropertyfinder.view;

import android.support.annotation.NonNull;

import com.frank.testpropertyfinder.model.Product;

import java.util.List;

public class CatalogAdaptedDataModel {

    @NonNull
    private final List<Product> products;

    private CatalogAdaptedDataModel(@NonNull Builder builder) {
        this.products = builder.products;
    }

    int getSize() {
        return products.size();
    }

    public static Builder with(@NonNull List<Product> products) {
        Builder builder = new Builder();
        builder.setProducts(products);
        return builder;
    }

    String getSubject(int position) {
        return (position + 1) + ". " + products.get(position).subject;
    }

    String getPrice(int position) {
        return "Price: " + products.get(position).price;
    }

    String getBedrooms(int position) {
        return "Bedrooms: " + products.get(position).bedrooms;
    }

    public static class Builder {
        private List<Product> products;

        public CatalogAdaptedDataModel build() {
            return new CatalogAdaptedDataModel(this);
        }

        void setProducts(@NonNull List<Product> products) {
            this.products = products;
        }
    }
}
