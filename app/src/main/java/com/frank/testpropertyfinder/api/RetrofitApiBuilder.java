package com.frank.testpropertyfinder.api;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class RetrofitApiBuilder {
    private static final String BASE_URL = "https://www.propertyfinder.ae/";
    private static RetrofitApi retrofitApi;
    private RetrofitApiBuilder() {
    }
    public static RetrofitApi getRetrofitApi() {
        init();
        return retrofitApi;
    }
    private static void init() {
        if (retrofitApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(createOkHttpClient())
                    .build();
            retrofitApi = retrofit.create(RetrofitApi.class);
        }
    }

    private static OkHttpClient createOkHttpClient() {
        final OkHttpClient.Builder httpClient =
                new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                final Request original = chain.request();
                final HttpUrl originalHttpUrl = original.url();

                final HttpUrl url = originalHttpUrl.newBuilder()
                        .build();

                final Request.Builder requestBuilder = original.newBuilder()
                        .url(url);
                final Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        return httpClient.build();
    }
}