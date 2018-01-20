package com.padc.nyinyi.padcburppleapp.Persistence;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import com.padc.nyinyi.padcburppleapp.PADCBurppleApp;

/**
 * Created by user on 1/18/18.
 */

public class BurppleDBContract {

    public static final String CONTENT_AUTHORITY = PADCBurppleApp.class.getPackage().getName();
    private static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_FEATURED = "featured";
    public static final String PATH_GUIDE = "guide";
    public static final String PATH_PROMOTION = "promotion";
    public static final String PATH_BURPPLE_PROMOTION_SHOP = "burpplepromotionshop";
    public static final String PATH_BURPPLE_PROMOTION_TERMS = "burpplepromotionterms";

    public static final class FeaturedEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_FEATURED).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FEATURED;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FEATURED;

        public static final String TABLE_NAME = PATH_FEATURED;

        public static final String COLUMN_BURPPLE_FEATURED_ID = "burpple_featured_id";
        public static final String COLUMN_BURPPLE_FEATURED_IMAGE = "burpple_featured_image";
        public static final String COLUMN_BURPPLE_FEATURED_TITLE = "burpple_featured_title";
        public static final String COLUMN_BURPPLE_FEATURED_DESC = "burpple_featured_desc";
        public static final String COLUMN_BURPPLE_FEATURED_TAG = "burpple_featured_tag";

        public static Uri buildContentUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

    }

    public static final class GuideEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_GUIDE).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_GUIDE;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_GUIDE;

        public static final String TABLE_NAME = PATH_GUIDE;

        public static final String COLUMN_BURPPLE_GUIDE_ID = "burpple_guide_id";
        public static final String COLUMN_BURPPLE_GUIDE_IMAGE = "burpple_guide_image";
        public static final String COLUMN_BURPPLE_GUIDE_TITLE = "burpple_guide_title";
        public static final String COLUMN_BURPPLE_GUIDE_DESC = "burpple_guide_desc";

        public static Uri buildContentUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

    }

    public static final class PromotionEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_PROMOTION).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PROMOTION;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PROMOTION;

        public static final String TABLE_NAME = PATH_PROMOTION;

        public static final String COLUMN_BURPPLE_PROMOTION_ID = "burpple_promotion_id";
        public static final String COLUMN_BURPPLE_PROMOTION_IMAGE = "burpple_promotion_image";
        public static final String COLUMN_BURPPLE_PROMOTION_TITLE = "burpple_promotion_title";
        public static final String COLUMN_BURPPLE_PROMOTION_UNTIL = "burpple_promotion_until";
        public static final String COLUMN_BURPPLE_PROMOTION_SHOP_ID = "burpple_promotion_shop_id";
        public static final String COLUMN_IS_BURPPLE_EXCLUSIVE = "is_burpple_exclusive";

        public static Uri buildContentUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    public static final class BurpplePromotionShopEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_BURPPLE_PROMOTION_SHOP).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_BURPPLE_PROMOTION_SHOP;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_BURPPLE_PROMOTION_SHOP;

        public static final String TABLE_NAME = PATH_BURPPLE_PROMOTION_SHOP;

        public static final String COLUMN_BURPPLE_SHOP_ID = "burpple_shop_id";
        public static final String COLUMN_BURPPLE_SHOP_NAME = "burpple_shop_name";
        public static final String COLUMN_BURPPLE_SHOP_AREA = "burpple_shop_area";

        public static Uri buildContentUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }


    public static final class BurpplePromotionTermsEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_BURPPLE_PROMOTION_TERMS).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_BURPPLE_PROMOTION_TERMS;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_BURPPLE_PROMOTION_TERMS;

        public static final String TABLE_NAME = PATH_BURPPLE_PROMOTION_TERMS;

        public static final String COLUMN_BURPPLE_TERMS_NAME = "burpple_terms_name";
        public static final String COLUMN_BURPPLE_PROMOTION_ID = "burpple_promotion_id";

        public static Uri buildContentUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }





}
