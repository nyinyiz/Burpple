
package com.padc.nyinyi.padcburppleapp.data.vos;

import android.content.ContentValues;
import android.database.Cursor;

import com.google.gson.annotations.SerializedName;
import com.padc.nyinyi.padcburppleapp.Persistence.BurppleDBContract;

public class BurpplePromotionShop {

    @SerializedName("burpple-shop-area")
    private String mBurppleShopArea;
    @SerializedName("burpple-shop-id")
    private String mBurppleShopId;
    @SerializedName("burpple-shop-name")
    private String mBurppleShopName;

    public String getmBurppleShopArea() {
        return mBurppleShopArea;
    }

    public String getmBurppleShopId() {
        return mBurppleShopId;
    }

    public String getmBurppleShopName() {
        return mBurppleShopName;
    }

    public ContentValues parseToContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(BurppleDBContract.BurpplePromotionShopEntry.COLUMN_BURPPLE_SHOP_ID,mBurppleShopId);
        contentValues.put(BurppleDBContract.BurpplePromotionShopEntry.COLUMN_BURPPLE_SHOP_AREA,mBurppleShopArea);
        contentValues.put(BurppleDBContract.BurpplePromotionShopEntry.COLUMN_BURPPLE_SHOP_NAME,mBurppleShopName);
        return contentValues;
    }

    public static BurpplePromotionShop parseFromCursor(Cursor data) {
        BurpplePromotionShop mBurppleShopArea = new BurpplePromotionShop();
        mBurppleShopArea.mBurppleShopId = data.getString(data.getColumnIndex(BurppleDBContract.BurpplePromotionShopEntry.COLUMN_BURPPLE_SHOP_ID));
        mBurppleShopArea.mBurppleShopName = data.getString(data.getColumnIndex(BurppleDBContract.BurpplePromotionShopEntry.COLUMN_BURPPLE_SHOP_NAME));
        mBurppleShopArea.mBurppleShopArea = data.getString(data.getColumnIndex(BurppleDBContract.BurpplePromotionShopEntry.COLUMN_BURPPLE_SHOP_AREA));

        return mBurppleShopArea;
    }
}
