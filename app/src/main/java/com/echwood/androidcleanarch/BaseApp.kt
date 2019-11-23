package com.echwood.androidcleanarch;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity
import com.echwood.androidcleanarch.deps.Deps
import com.echwood.androidcleanarch.networking.NetworkModule



import java.io.File;

/**
 * @author  UkB.
 * @version v1
 */
public open class BaseApp: AppCompatActivity() {
    private lateinit var deps: Deps;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var cacheFile = File(getCacheDir(), "responses");
//        deps = DaggerDeps.builder().networkModule( NetworkModule(cacheFile)).build();
    }

    fun getDeps(): Deps {
        return deps;
    }
}
