package com.padc.nyinyi.padcburppleapp;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.padc.nyinyi.padcburppleapp.dagger.AppComponent;
import com.padc.nyinyi.padcburppleapp.dagger.AppModule;
import com.padc.nyinyi.padcburppleapp.dagger.DaggerAppComponent;
import com.padc.nyinyi.padcburppleapp.dagger.NetworkModule;
import com.padc.nyinyi.padcburppleapp.data.models.FeatureModel;
import com.padc.nyinyi.padcburppleapp.data.models.GuideModel;
import com.padc.nyinyi.padcburppleapp.data.models.PromotionModel;

import javax.inject.Inject;

/**
 * Created by user on 1/6/18.
 */

public class PADCBurppleApp extends Application {

    public static final String LOG_TAG = "PADCBurppleApp";
    private AppComponent mAppComponent;

    @Inject
    Context mContext;

    @Inject
    PromotionModel mPromotionModel;

    @Inject
    GuideModel mGuideModel;

    @Inject
    FeatureModel mFeatureModel;


    @Override
    public void onCreate() {
        super.onCreate();


        mAppComponent = initDagger();//dagger init

        mAppComponent.inject(this);//register consumer

        mPromotionModel.startloadingMMNews(getApplicationContext());

        mGuideModel.startloadingGuide(getApplicationContext());

        mFeatureModel.startloadingFeature(getApplicationContext());

        Log.d(LOG_TAG,"mContext : "+ mContext);

    }
    public AppComponent initDagger()
    {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .build();
    }

    public AppComponent getmAppComponent() {
        return mAppComponent;
    }
}
