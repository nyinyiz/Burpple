
package com.padc.nyinyi.padcburppleapp.data.vos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.google.gson.annotations.SerializedName;
import com.padc.nyinyi.padcburppleapp.Persistence.BurppleDBContract;

public class FeaturedVO {

    @SerializedName("burpple-featured-desc")
    private String mBurppleFeaturedDesc;
    @SerializedName("burpple-featured-id")
    private String mBurppleFeaturedId;
    @SerializedName("burpple-featured-image")
    private String mBurppleFeaturedImage;
    @SerializedName("burpple-featured-tag")
    private String mBurppleFeaturedTag;
    @SerializedName("burpple-featured-title")
    private String mBurppleFeaturedTitle;

    public String getmBurppleFeaturedDesc() {
        return mBurppleFeaturedDesc;
    }

    public String getmBurppleFeaturedId() {
        return mBurppleFeaturedId;
    }

    public String getmBurppleFeaturedImage() {
        return mBurppleFeaturedImage;
    }

    public String getmBurppleFeaturedTag() {
        return mBurppleFeaturedTag;
    }

    public String getmBurppleFeaturedTitle() {
        return mBurppleFeaturedTitle;
    }

    public ContentValues parseToContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(BurppleDBContract.FeaturedEntry.COLUMN_BURPPLE_FEATURED_ID, mBurppleFeaturedId);
        contentValues.put(BurppleDBContract.FeaturedEntry.COLUMN_BURPPLE_FEATURED_IMAGE, mBurppleFeaturedImage);
        contentValues.put(BurppleDBContract.FeaturedEntry.COLUMN_BURPPLE_FEATURED_TITLE, mBurppleFeaturedTitle);
        contentValues.put(BurppleDBContract.FeaturedEntry.COLUMN_BURPPLE_FEATURED_DESC, mBurppleFeaturedDesc);
        contentValues.put(BurppleDBContract.FeaturedEntry.COLUMN_BURPPLE_FEATURED_TAG, mBurppleFeaturedTag);
        return contentValues;
    }

    public static FeaturedVO parseFromCursor(Context applicationContext, Cursor data) {

        FeaturedVO guideVO = new FeaturedVO();
        guideVO.mBurppleFeaturedId = data.getString(data.getColumnIndex(BurppleDBContract.FeaturedEntry.COLUMN_BURPPLE_FEATURED_ID));
        guideVO.mBurppleFeaturedImage = data.getString(data.getColumnIndex(BurppleDBContract.FeaturedEntry.COLUMN_BURPPLE_FEATURED_IMAGE));
        guideVO.mBurppleFeaturedTitle = data.getString(data.getColumnIndex(BurppleDBContract.FeaturedEntry.COLUMN_BURPPLE_FEATURED_TITLE));
        guideVO.mBurppleFeaturedDesc = data.getString(data.getColumnIndex(BurppleDBContract.FeaturedEntry.COLUMN_BURPPLE_FEATURED_DESC));

        return guideVO;

    }
}
