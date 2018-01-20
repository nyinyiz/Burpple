package com.padc.nyinyi.padcburppleapp.dagger;

import com.padc.nyinyi.padcburppleapp.PADCBurppleApp;
import com.padc.nyinyi.padcburppleapp.activities.MainActivity;
import com.padc.nyinyi.padcburppleapp.data.models.FeatureModel;
import com.padc.nyinyi.padcburppleapp.data.models.GuideModel;
import com.padc.nyinyi.padcburppleapp.data.models.PromotionModel;
import com.padc.nyinyi.padcburppleapp.mvp.presenters.BurppleItemPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by user on 1/6/18.
 */


@Component(modules = {AppModule.class,NetworkModule.class})
@Singleton
public interface AppComponent {

    void inject(PADCBurppleApp app);

    void inject(PromotionModel promotionModel);

    void inject(GuideModel guideModel);

    void inject(FeatureModel featureModel);

    void inject(BurppleItemPresenter burppleItemPresenter);

    void inject(MainActivity mainActivity);


}
