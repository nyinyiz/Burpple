package com.padc.nyinyi.padcburppleapp.data.models;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.padc.nyinyi.padcburppleapp.PADCBurppleApp;
import com.padc.nyinyi.padcburppleapp.Persistence.BurppleDBContract;
import com.padc.nyinyi.padcburppleapp.data.vos.BurpplePromotionShop;
import com.padc.nyinyi.padcburppleapp.data.vos.GuideVO;
import com.padc.nyinyi.padcburppleapp.data.vos.PromotionVO;
import com.padc.nyinyi.padcburppleapp.events.RestApiEvents;
import com.padc.nyinyi.padcburppleapp.networks.BurppleDataAgent;
import com.padc.nyinyi.padcburppleapp.networks.BurppleDataAgentImpl;
import com.padc.nyinyi.padcburppleapp.utils.AppConstants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Nyi Nyi on 1/17/2018.
 */
public class GuideModel {

//    private static GuideModel objInstance;

    private List<GuideVO> mGuideVOS;

    private int mmPageIndex = 1;

    @Inject
    BurppleDataAgent mDataAgent;

    public GuideModel(Context context) {
        EventBus.getDefault().register(this);
        mGuideVOS = new ArrayList<>();

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
    public void startloadingGuide(Context context) {
        mDataAgent.loadGuides(context, AppConstants.ACCESS_TOKEN,
                mmPageIndex);
    }

    public List<GuideVO> getmGuideVOS() {
        return mGuideVOS;
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onGuideDataLoaded(RestApiEvents.GuidedDataLoadedEvent event) {
        mGuideVOS.addAll(event.getLoadGuided());

        //TODO LOGIC TO SAVE THE DATA IN PERSISTENCE LAYER
        ContentValues[] guideCVs = new ContentValues[event.getLoadGuided().size()];


        for (int index = 0; index < guideCVs.length; index++) {
            guideCVs[index] = event.getLoadGuided().get(index).parseToContentValues();

        }

        int insertGuide = event.getContext().getContentResolver().bulkInsert(BurppleDBContract.GuideEntry.CONTENT_URI, guideCVs);
        Log.d(PADCBurppleApp.LOG_TAG, "Inserted Row : " + insertGuide);


    }
}
