package com.frank.testpropertyfinder.interactor;

import android.support.annotation.NonNull;

import com.frank.testpropertyfinder.model.OrderType;
import com.frank.testpropertyfinder.model.Product;

import java.util.List;

public interface CatalogInteractor {
    void getProducts(int page, String order);

    void unsubscrible();

    void subscrible(OnDataChangedListener catalogPresenter);

    int getPageNo();

    String getOrderType();

    @NonNull
    List<Product> getProducts();

    boolean isLoading();

    void resetProductList();

    void changeOrderType(@NonNull OrderType which);

    CharSequence[] getSelectOptions();

    interface OnDataChangedListener {
        void onGetProductSuccessful();

        void onGetProductFail();
    }
}
