package com.padc.nyinyi.padcburppleapp.networks;

import com.padc.nyinyi.padcburppleapp.events.RestApiEvents;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by user on 1/18/18.
 */

public class BurppleCallBack<T extends BurppleResponse> implements Callback<T> {

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        BurppleResponse burppleResponse = response.body();
        if (burppleResponse == null)
        {
            RestApiEvents.ErrorInvokingAPIEvent errorEvent =
                    new RestApiEvents.ErrorInvokingAPIEvent(
                            "No data could be loaded for now. Pls try again later.");
            EventBus.getDefault().post(errorEvent);
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {

    }
}
