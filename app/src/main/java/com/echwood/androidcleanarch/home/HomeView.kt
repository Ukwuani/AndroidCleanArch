package com.echwood.androidcleanarch.home;

import com.echwood.androidcleanarch.models.CityListResponse


/**
 * @author  UkB.
 * @version v1
 */
public interface HomeView {
    fun showWait();

    fun removeWait();

    fun onFailure(appErrorMessage: String);

    fun getCityListSuccess(cityListResponse: CityListResponse);

}
