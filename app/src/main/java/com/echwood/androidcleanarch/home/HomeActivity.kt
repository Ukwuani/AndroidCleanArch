package com.echwood.androidcleanarch.home;

import android.content.ClipData
import android.os.Bundle;
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide.init
import com.echwood.androidcleanarch.BaseApp
import com.echwood.androidcleanarch.R
import com.echwood.androidcleanarch.models.CityListData
import com.echwood.androidcleanarch.models.CityListResponse
import com.echwood.androidcleanarch.networking.Service
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject


public class HomeActivity : BaseApp(), HomeView {

    @Inject
    lateinit var service: Service;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        getDeps().inject(this);
        setContentView(R.layout.activity_home);
        init();

        val presenter = HomePresenter(service, this);
        presenter.getCityList();
    }

    fun init(){
        list.setLayoutManager( LinearLayoutManager(this));
    }

    override fun showWait() {
        progress.setVisibility(View.VISIBLE);
    }

    override fun removeWait() {
        progress.setVisibility(View.GONE);
    }

    override fun onFailure(appErrorMessage: String) {

    }

    override fun getCityListSuccess(cityListResponse: CityListResponse) {

        val adapter = HomeAdapter(getApplicationContext(), cityListResponse.data,
                 object: HomeAdapter.OnItemClickListener {
                    override fun onClick(Item: CityListData) {
                        Toast.makeText(getApplicationContext(), "ClipData.Item",
                                Toast.LENGTH_LONG).show();
                    }
                });

        list.setAdapter(adapter);

    }
}
