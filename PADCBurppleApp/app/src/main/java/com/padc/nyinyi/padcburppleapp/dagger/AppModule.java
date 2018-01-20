package com.padc.nyinyi.padcburppleapp.dagger;

import android.content.Context;
import com.padc.nyinyi.padcburppleapp.PADCBurppleApp;
import com.padc.nyinyi.padcburppleapp.data.models.FeatureModel;
import com.padc.nyinyi.padcburppleapp.data.models.GuideModel;
import com.padc.nyinyi.padcburppleapp.data.models.PromotionModel;
import com.padc.nyinyi.padcburppleapp.mvp.presenters.BurppleItemPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by user on 1/6/18.
 */

@Module
public class AppModule {

    private PADCBurppleApp mApp;

    public AppModule(PADCBurppleApp application) {
        mApp = application;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return mApp.getApplicationContext();
    }

    @Provides
    public BurppleItemPresenter provideBurppleItemPresenter() {
        return new BurppleItemPresenter();
    }


    @Provides
    @Singleton
    public PromotionModel providePromotionModel(Context context)  {
        return new PromotionModel(context);
    }

    @Provides
    @Singleton
    public FeatureModel provideFeatureModel(Context context)  {
        return new FeatureModel(context);
    }


    @Provides
    @Singleton
    public GuideModel provideGuideModel(Context context)  {
        return new GuideModel(context);
    }



}
