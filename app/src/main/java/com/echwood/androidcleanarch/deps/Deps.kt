package com.echwood.androidcleanarch.deps;


import com.echwood.androidcleanarch.home.HomeActivity;
import com.echwood.androidcleanarch.networking.NetworkModule


import javax.inject.Singleton;

import dagger.Component;

/**
 * @author  UkB.
 * @version v1
 */
@Singleton
@Component(modules = arrayOf(NetworkModule::class))
public interface Deps {
    fun inject(homeActivity: HomeActivity);
}
