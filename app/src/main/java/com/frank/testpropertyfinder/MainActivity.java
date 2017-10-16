package com.frank.testpropertyfinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.frank.testpropertyfinder.interactor.CatalogInteractor;
import com.frank.testpropertyfinder.interactor.CatalogInteractorImpl;
import com.frank.testpropertyfinder.presenter.CatalogPresenter;
import com.frank.testpropertyfinder.presenter.CatalogPresenterImpl;
import com.frank.testpropertyfinder.view.CatalogView;
import com.frank.testpropertyfinder.view.CatalogViewImpl;

public class MainActivity extends AppCompatActivity {

    private CatalogPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CatalogView view = new CatalogViewImpl(this);
        CatalogInteractor interactor = new CatalogInteractorImpl();
        presenter = new CatalogPresenterImpl(view, interactor);
        presenter.handleOnCreate();
    }

    @Override
    protected void onDestroy() {
        presenter.handleOnDestroy();
        super.onDestroy();
    }
}
