
package com.padc.nyinyi.padcburppleapp.data.vos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;
import com.padc.nyinyi.padcburppleapp.Persistence.BurppleDBContract;

public class PromotionVO {

    @SerializedName("burpple-promotion-id")
    private String mBurpplePromotionId;
    @SerializedName("burpple-promotion-image")
    private String mBurpplePromotionImage;
    @SerializedName("burpple-promotion-shop")
    private BurpplePromotionShop mBurpplePromotionShop;
    @SerializedName("burpple-promotion-terms")
    private List<String> mBurpplePromotionTerms;
    @SerializedName("burpple-promotion-title")
    private String mBurpplePromotionTitle;
    @SerializedName("burpple-promotion-until")
    private String mBurpplePromotionUntil;
    @SerializedName("is-burpple-exclusive")
    private Boolean mIsBurppleExclusive;

    public String getmBurpplePromotionId() {
        return mBurpplePromotionId;
    }

    public String getmBurpplePromotionImage() {
        return mBurpplePromotionImage;
    }

    public BurpplePromotionShop getmBurpplePromotionShop() {
        return mBurpplePromotionShop;
    }

    public List<String> getmBurpplePromotionTerms() {
        return mBurpplePromotionTerms;
    }

    public String getmBurpplePromotionTitle() {
        return mBurpplePromotionTitle;
    }

    public String getmBurpplePromotionUntil() {
        return mBurpplePromotionUntil;
    }

    public Boolean getmIsBurppleExclusive() {
        return mIsBurppleExclusive;
    }

    public ContentValues parseToContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(BurppleDBContract.PromotionEntry.COLUMN_BURPPLE_PROMOTION_ID, mBurpplePromotionId);
        contentValues.put(BurppleDBContract.PromotionEntry.COLUMN_BURPPLE_PROMOTION_IMAGE, mBurpplePromotionImage);
        contentValues.put(BurppleDBContract.PromotionEntry.COLUMN_BURPPLE_PROMOTION_SHOP_ID, mBurpplePromotionShop.getmBurppleShopId());
        contentValues.put(BurppleDBContract.PromotionEntry.COLUMN_BURPPLE_PROMOTION_TITLE, mBurpplePromotionTitle);
        contentValues.put(BurppleDBContract.PromotionEntry.COLUMN_BURPPLE_PROMOTION_UNTIL,mBurpplePromotionUntil);
        contentValues.put(BurppleDBContract.PromotionEntry.COLUMN_IS_BURPPLE_EXCLUSIVE,mIsBurppleExclusive);

        return contentValues;
    }

    public static PromotionVO parseFromCursor(Context context, Cursor data) {

        PromotionVO promotionVO = new PromotionVO();
        promotionVO.mBurpplePromotionId = data.getString(data.getColumnIndex(BurppleDBContract.PromotionEntry.COLUMN_BURPPLE_PROMOTION_ID));
        promotionVO.mBurpplePromotionImage = data.getString(data.getColumnIndex(BurppleDBContract.PromotionEntry.COLUMN_BURPPLE_PROMOTION_IMAGE));
        promotionVO.mBurpplePromotionTitle = data.getString(data.getColumnIndex(BurppleDBContract.PromotionEntry.COLUMN_BURPPLE_PROMOTION_TITLE));
        promotionVO.mBurpplePromotionUntil = data.getString(data.getColumnIndex(BurppleDBContract.PromotionEntry.COLUMN_BURPPLE_PROMOTION_UNTIL));

        promotionVO.mBurpplePromotionShop = BurpplePromotionShop.parseFromCursor(data);
        promotionVO.mIsBurppleExclusive = Boolean.valueOf(data.getString(data.getColumnIndex(BurppleDBContract.PromotionEntry.COLUMN_IS_BURPPLE_EXCLUSIVE)));
        promotionVO.mBurpplePromotionTerms = loadTermInPromotion(context, promotionVO.mBurpplePromotionId);
        return promotionVO;
    }

    private static List<String> loadTermInPromotion(Context context, String mBurpplePromotionId) {
        Cursor termInPromotionCursor = context.getContentResolver().query(BurppleDBContract.BurpplePromotionTermsEntry.CONTENT_URI,
                null,
                BurppleDBContract.BurpplePromotionTermsEntry.COLUMN_BURPPLE_PROMOTION_ID + " = ? ",
                new String[]{mBurpplePromotionId},
                null);

        if (termInPromotionCursor != null && termInPromotionCursor.moveToFirst()) {
            List<String> termInPromtion = new ArrayList<>();
            do {
                termInPromtion.add(
                        termInPromotionCursor.getString(
                                termInPromotionCursor.getColumnIndex(BurppleDBContract.BurpplePromotionTermsEntry.COLUMN_BURPPLE_TERMS_NAME)
                        )
                );
            } while (termInPromotionCursor.moveToNext());
            termInPromotionCursor.close();

            return termInPromtion;

        }
        return null;
    }
}
