
package com.padc.nyinyi.padcburppleapp.data.vos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.google.gson.annotations.SerializedName;
import com.padc.nyinyi.padcburppleapp.Persistence.BurppleDBContract;

public class GuideVO {

    @SerializedName("burpple-guide-desc")
    private String mBurppleGuideDesc;
    @SerializedName("burpple-guide-id")
    private String mBurppleGuideId;
    @SerializedName("burpple-guide-image")
    private String mBurppleGuideImage;
    @SerializedName("burpple-guide-title")
    private String mBurppleGuideTitle;

    public String getmBurppleGuideDesc() {
        return mBurppleGuideDesc;
    }

    public String getmBurppleGuideId() {
        return mBurppleGuideId;
    }

    public String getmBurppleGuideImage() {
        return mBurppleGuideImage;
    }

    public String getmBurppleGuideTitle() {
        return mBurppleGuideTitle;
    }

    public ContentValues parseToContentValues() {

        ContentValues contentValues = new ContentValues();
        contentValues.put(BurppleDBContract.GuideEntry.COLUMN_BURPPLE_GUIDE_ID, mBurppleGuideId);
        contentValues.put(BurppleDBContract.GuideEntry.COLUMN_BURPPLE_GUIDE_IMAGE, mBurppleGuideImage);
        contentValues.put(BurppleDBContract.GuideEntry.COLUMN_BURPPLE_GUIDE_TITLE, mBurppleGuideTitle);
        contentValues.put(BurppleDBContract.GuideEntry.COLUMN_BURPPLE_GUIDE_DESC, mBurppleGuideDesc);

        return contentValues;

    }

    public static GuideVO parseFromCursor(Context context, Cursor data) {
        GuideVO guideVO = new GuideVO();
        guideVO.mBurppleGuideId = data.getString(data.getColumnIndex(BurppleDBContract.GuideEntry.COLUMN_BURPPLE_GUIDE_ID));
        guideVO.mBurppleGuideImage = data.getString(data.getColumnIndex(BurppleDBContract.GuideEntry.COLUMN_BURPPLE_GUIDE_IMAGE));
        guideVO.mBurppleGuideTitle = data.getString(data.getColumnIndex(BurppleDBContract.GuideEntry.COLUMN_BURPPLE_GUIDE_TITLE));
        guideVO.mBurppleGuideDesc = data.getString(data.getColumnIndex(BurppleDBContract.GuideEntry.COLUMN_BURPPLE_GUIDE_DESC));

        return guideVO;
    }
}
