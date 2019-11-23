package com.echwood.androidcleanarch.home

import com.echwood.androidcleanarch.models.CityListResponse
import com.echwood.androidcleanarch.networking.NetworkError
import com.echwood.androidcleanarch.networking.Service

import rx.subscriptions.CompositeSubscription

/**
 * @author  UkB.
 * @version v1
 */
class HomePresenter(var service: Service, var view: HomeView) {
    private var subscriptions = CompositeSubscription()

    fun getCityList() {
        view.showWait()

        val subscription = service.getCityList( object: Service.GetCityListCallback {
            override fun onSuccess(cityListResponse: CityListResponse?) {
                view.removeWait()
                view.getCityListSuccess(cityListResponse!!)
            }

            override fun onError(networkError: NetworkError?) {
                view.removeWait()
                view.onFailure(networkError!!.getAppErrorMessage())
            }

        })

        subscriptions.add(subscription)
    }

    fun onStop() {
        subscriptions.unsubscribe()
    }
}
