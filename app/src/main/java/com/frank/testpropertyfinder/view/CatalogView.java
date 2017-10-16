package com.frank.testpropertyfinder.view;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.frank.testpropertyfinder.model.OrderType;

public interface CatalogView {
    void init();

    void showProducts(@NonNull CatalogAdaptedDataModel adaptedModel);

    void showErrorMessage();

    void showLoading(boolean loading);

    void setOnViewListener(OnViewListener listener);

    void resetScrollListenerState();

    void showDialogOptions(@NonNull CharSequence options[]);

    interface OnViewListener {
        void onLoadMore();

        void onChangeOrderTypeClicked();

        void onOrderTypeOptionClicked(@Nullable OrderType orderType);
    }
}
