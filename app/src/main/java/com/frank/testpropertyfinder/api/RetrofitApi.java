package com.frank.testpropertyfinder.api;

import com.frank.testpropertyfinder.model.MobApiResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitApi {
    @GET("mobileapi")
    Single<MobApiResponse> getProducts(@Query("page") int page, @Query("order") String order);
}
