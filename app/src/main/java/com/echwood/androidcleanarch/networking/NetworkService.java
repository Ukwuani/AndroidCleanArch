package com.echwood.androidcleanarch.networking;


import com.echwood.androidcleanarch.models.CityListResponse;

import retrofit2.http.GET;
import rx.Observable;

/**
 * @author  UkB.
 * @version v1
 */
public interface NetworkService {

    @GET("v1/city")
    Observable<CityListResponse> getCityList();

}
