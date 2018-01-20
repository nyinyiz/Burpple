package com.padc.nyinyi.padcburppleapp.networks;

import android.content.Context;

/**
 * Created by user on 1/18/18.
 */

public interface BurppleDataAgent {

    void loadFeatured(Context context, String accessToken , int pageNo);

    void loadPromotions(Context context,String accessToken, int pageNo);

    void loadGuides(Context context,String accessToken, int pageNo);
}
