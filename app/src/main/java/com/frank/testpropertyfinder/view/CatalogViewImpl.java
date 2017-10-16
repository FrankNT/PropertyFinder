package com.frank.testpropertyfinder.view;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.frank.testpropertyfinder.R;
import com.frank.testpropertyfinder.model.OrderType;
import com.frank.testpropertyfinder.utils.EndlessRecyclerViewScrollListener;

public class CatalogViewImpl implements CatalogView, View.OnClickListener {
    @NonNull
    private final Activity activity;
    @NonNull
    private final View loading;
    @NonNull
    private final View changeOrderType;
    @NonNull
    private RecyclerView productList;
    @NonNull
    private CatalogViewAdapter adapter = new CatalogViewAdapter();
    @Nullable
    private EndlessRecyclerViewScrollListener scrollListener;
    @Nullable
    private OnViewListener listener;

    public CatalogViewImpl(@NonNull Activity activity) {
        this.activity = activity;
        this.productList = activity.findViewById(R.id.product_list);
        this.loading = activity.findViewById(R.id.loading);
        this.changeOrderType = activity.findViewById(R.id.change_order_type);
    }

    @Override
    public void init() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        productList.setLayoutManager(linearLayoutManager);
        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                if (listener != null) {
                    listener.onLoadMore();
                }
            }
        };
        productList.addOnScrollListener(scrollListener);
        productList.setHasFixedSize(false);
        productList.setAdapter(adapter);
        changeOrderType.setOnClickListener(this);
    }

    @Override
    public void showProducts(@NonNull CatalogAdaptedDataModel adaptedModel) {
        adapter.setDataModel(adaptedModel);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorMessage() {
        Toast.makeText(activity, "Can not fetch data from API", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoading(boolean isLoading) {
        loading.setVisibility(isLoading ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setOnViewListener(OnViewListener listener) {
        this.listener = listener;
    }

    @Override
    public void resetScrollListenerState() {
        if (scrollListener != null) {
            this.scrollListener.resetState();
        }
    }

    @Override
    public void showDialogOptions(@NonNull CharSequence options[]) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setCancelable(false);
        builder.setTitle("Select your option:");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int option) {
                if (listener != null) {
                    OrderType orderType = null;
                    switch (option) {
                        case 0:
                            orderType = OrderType.pd;
                            break;
                        case 1:
                            orderType = OrderType.pa;
                            break;
                        case 2:
                            orderType = OrderType.bd;
                            break;
                        case 3:
                            orderType = OrderType.ba;
                            break;
                        default:
                            break;
                    }
                    listener.onOrderTypeOptionClicked(orderType);
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //the user clicked on Cancel
            }
        });
        builder.show();
    }

    @Override
    public void onClick(View view) {
        if (listener != null) {
            listener.onChangeOrderTypeClicked();
        }
    }
}