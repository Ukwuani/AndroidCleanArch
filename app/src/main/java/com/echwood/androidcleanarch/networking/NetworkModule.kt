package com.echwood.androidcleanarch.networking;


import com.echwood.androidcleanarch.BuildConfig
import java.io.File;
import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
/**
 * @author  UkB.
 * @version v1
 */
@Module
public class NetworkModule {
    lateinit var cacheFile: File;

    fun NetworkModule(cacheFile: File) {
        this.cacheFile = cacheFile;
    }

    @Provides
    @Singleton
    fun provideCall(): Retrofit{
        var cache: Cache? = null;
        try {
            cache =  Cache(cacheFile, 10 * 1024 * 1024);
        } catch (e: Exception) {
            e.printStackTrace();
        }

        val okHttpClient: OkHttpClient =  OkHttpClient.Builder()
                .addInterceptor( Interceptor() {

                        val original = it.request();

                        // Customize the request
                        val request = original.newBuilder()
                                .header("Content-Type", "application/json")
                                .removeHeader("Pragma")
                                .header("Cache-Control", String.format("max-age=%d", BuildConfig.CACHETIME))
                                .build();

                       val response = it.proceed(request);
                        response.cacheResponse();

                })
                .cache(cache)

                .build();


        return  Retrofit.Builder()
                .baseUrl(BuildConfig.BASEURL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())

                .build();
    }

    @Provides
    @Singleton
    @SuppressWarnings("unused")
    fun providesNetworkService(retrofit: Retrofit): NetworkService {
        return retrofit.create(NetworkService::class.java)
    }
    @Provides
    @Singleton
    @SuppressWarnings("unused")
    fun providesService(
            networkService: NetworkService): Service {
        return Service(networkService)
    }

}
