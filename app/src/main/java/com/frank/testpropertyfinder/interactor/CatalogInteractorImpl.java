package com.frank.testpropertyfinder.interactor;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.frank.testpropertyfinder.datasource.CatalogDataSource;
import com.frank.testpropertyfinder.datasource.CatalogDataSourceImpl;
import com.frank.testpropertyfinder.model.OrderType;
import com.frank.testpropertyfinder.model.Product;

import java.util.ArrayList;
import java.util.List;

public class CatalogInteractorImpl implements CatalogInteractor {
    private static final String TAG = "CatalogInteractorImpl";
    private static final int INIT_PAGE = 0;
    private static final CharSequence[] OPTIONS = {"Price Decending", "Price Ascending", "Bedrooms descending", "Bedrooms ascending"};
    @NonNull
    private CatalogDataSource dataSource = new CatalogDataSourceImpl();
    @Nullable
    private OnDataChangedListener listener;
    @NonNull
    private List<Product> products = new ArrayList<>();
    private int pageNo = INIT_PAGE;
    @NonNull
    private OrderType orderType = OrderType.pd;
    private boolean loading;

    @Override
    public void getProducts(int page, @NonNull String order) {
        loading = true;
        dataSource.getProducts(page, order, new CatalogDataSource.DataSourceListener(){
            @Override
            public void onSuccess(List<Product> result) {
                products.addAll(result);
                pageNo++;
                loading = false;
                if (listener != null) {
                    listener.onGetProductSuccessful();
                }
            }

            @Override
            public void onError(String message) {
                loading = false;
                if (listener != null) {
                    listener.onGetProductFail();
                }
                Log.e(TAG, message);
            }
        });
    }

    @Override
    public void unsubscrible() {
        dataSource.clear();
        this.listener = null;
    }

    @Override
    public void subscrible(OnDataChangedListener listener) {
        this.listener = listener;
    }

    @Override
    public int getPageNo() {
        return pageNo;
    }

    @Override
    public String getOrderType() {
        return orderType.name();
    }

    @Override
    @NonNull
    public List<Product> getProducts() {
        return products;
    }

    @Override
    public boolean isLoading() {
        return loading;
    }

    @Override
    public void resetProductList() {
        pageNo = INIT_PAGE;
        products.clear();
    }

    @Override
    public void changeOrderType(@NonNull OrderType option) {
        orderType = option;
    }

    @Override
    public CharSequence[] getSelectOptions() {
        return OPTIONS;
    }
}
