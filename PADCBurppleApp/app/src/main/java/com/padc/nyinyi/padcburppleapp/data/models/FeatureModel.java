package com.padc.nyinyi.padcburppleapp.data.models;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.padc.nyinyi.padcburppleapp.PADCBurppleApp;
import com.padc.nyinyi.padcburppleapp.Persistence.BurppleDBContract;
import com.padc.nyinyi.padcburppleapp.data.vos.FeaturedVO;
import com.padc.nyinyi.padcburppleapp.data.vos.GuideVO;
import com.padc.nyinyi.padcburppleapp.events.RestApiEvents;
import com.padc.nyinyi.padcburppleapp.networks.BurppleDataAgent;
import com.padc.nyinyi.padcburppleapp.utils.AppConstants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by user on 1/20/18.
 */

public class FeatureModel {

//    private static GuideModel objInstance;

    private List<FeaturedVO> mFeatureVOS;

    private int mmPageIndex = 1;

    @Inject
    BurppleDataAgent mDataAgent;

    public FeatureModel(Context context) {
        EventBus.getDefault().register(this);
        mFeatureVOS = new ArrayList<>();

        PADCBurppleApp burppleApp = (PADCBurppleApp) context.getApplicationContext();
        burppleApp.getmAppComponent().inject(this);

    }

    /* public static GuideModel getInstance() {
         if (objInstance == null) {
             objInstance = new GuideModel();
         }
         return objInstance;
     }
 */
    public void startloadingFeature(Context context) {
        mDataAgent.loadFeatured(context, AppConstants.ACCESS_TOKEN,
                mmPageIndex);
    }

    public List<FeaturedVO> getmFeatureVOS() {
        return mFeatureVOS;
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onFeatureDataLoaded(RestApiEvents.FeaturedDataLoadedEvent event) {
        mFeatureVOS.addAll(event.getLoadFeatured());

        //TODO LOGIC TO SAVE THE DATA IN PERSISTENCE LAYER
        ContentValues[] featureCVs = new ContentValues[event.getLoadFeatured().size()];


        for (int index = 0; index < featureCVs.length; index++) {
            featureCVs[index] = event.getLoadFeatured().get(index).parseToContentValues();

        }

        int insertFeature = event.getContext().getContentResolver().bulkInsert(BurppleDBContract.FeaturedEntry.CONTENT_URI, featureCVs);
        Log.d(PADCBurppleApp.LOG_TAG, "Inserted Row : " + insertFeature);


    }
}
