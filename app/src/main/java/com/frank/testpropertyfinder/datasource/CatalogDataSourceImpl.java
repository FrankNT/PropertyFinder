package com.frank.testpropertyfinder.datasource;

import android.support.annotation.NonNull;

import com.frank.testpropertyfinder.api.RetrofitApiBuilder;
import com.frank.testpropertyfinder.model.MobApiResponse;
import com.frank.testpropertyfinder.model.Product;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class CatalogDataSourceImpl implements CatalogDataSource {
    @NonNull
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    @Override
    public void getProducts(int page, String order, final DataSourceListener listener) {
        compositeDisposable.add(RetrofitApiBuilder.getRetrofitApi().getProducts(page, order)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<MobApiResponse, List<Product>>() {
                    @Override
                    public List<Product> apply(
                            @NonNull final MobApiResponse mobApiResponse)
                            throws Exception {
                        return mobApiResponse.products;
                    }
                })
                .subscribe(new Consumer<List<Product>>() {

                    @Override
                    public void accept(
                            @NonNull final List<Product> result)
                            throws Exception {
                        if (listener != null) {
                            listener.onSuccess(result);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (listener != null) {
                            listener.onError(throwable.getMessage());
                        }
                    }
                })
        );
    }

    @Override
    public void clear() {
        compositeDisposable.clear();
    }
}
