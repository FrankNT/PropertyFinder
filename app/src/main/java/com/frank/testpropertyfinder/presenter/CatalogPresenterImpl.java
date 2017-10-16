package com.frank.testpropertyfinder.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.frank.testpropertyfinder.interactor.CatalogInteractor;
import com.frank.testpropertyfinder.model.OrderType;
import com.frank.testpropertyfinder.view.CatalogAdaptedDataModel;
import com.frank.testpropertyfinder.view.CatalogView;

public class CatalogPresenterImpl implements CatalogPresenter,
        CatalogInteractor.OnDataChangedListener,
        CatalogView.OnViewListener {
    private final CatalogView view;
    private final CatalogInteractor interactor;

    public CatalogPresenterImpl(@NonNull CatalogView view,
                                @NonNull CatalogInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void handleOnCreate() {
        view.init();
        view.setOnViewListener(this);
        interactor.subscrible(this);
        fetchMoreDataFromApi();
    }

    @Override
    public void handleOnDestroy() {
        interactor.unsubscrible();
    }

    @Override
    public void onGetProductSuccessful() {
        handleDataChanged();
    }

    @Override
    public void onGetProductFail() {
        view.showErrorMessage();
        view.showLoading(interactor.isLoading());
    }

    @Override
    public void onLoadMore() {
        fetchMoreDataFromApi();
    }

    @Override
    public void onChangeOrderTypeClicked() {
        view.showDialogOptions(interactor.getSelectOptions());
    }

    @Override
    public void onOrderTypeOptionClicked(@Nullable OrderType option) {
        if (option != null && !interactor.isLoading()) {
            interactor.resetProductList();
            handleDataChanged();
            view.resetScrollListenerState();
            interactor.changeOrderType(option);
            fetchMoreDataFromApi();
        }
    }

    private void fetchMoreDataFromApi() {
        if (!interactor.isLoading()) {
            interactor.getProducts(interactor.getPageNo(), interactor.getOrderType());
            view.showLoading(interactor.isLoading());
        }
    }

    private void handleDataChanged() {
        CatalogAdaptedDataModel adaptedModel = CatalogAdaptedDataModel.with(interactor.getProducts()).build();
        view.showProducts(adaptedModel);
        view.showLoading(interactor.isLoading());
    }
}
