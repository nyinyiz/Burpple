package com.padc.nyinyi.padcburppleapp.Persistence;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by user on 1/18/18.
 */

public class BurppleDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "burpple.db";
    private static final int DB_VERSION = 4;

    String SQL_CREATE_FEATURE_TABLE = "CREATE TABLE " + BurppleDBContract.FeaturedEntry.TABLE_NAME
            + " (" + BurppleDBContract.FeaturedEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + BurppleDBContract.FeaturedEntry.COLUMN_BURPPLE_FEATURED_ID + " VARCHAR(256), "
            + BurppleDBContract.FeaturedEntry.COLUMN_BURPPLE_FEATURED_IMAGE + " TEXT, "
            + BurppleDBContract.FeaturedEntry.COLUMN_BURPPLE_FEATURED_TITLE + " TEXT, "
            + BurppleDBContract.FeaturedEntry.COLUMN_BURPPLE_FEATURED_DESC + " TEXT, "
            + BurppleDBContract.FeaturedEntry.COLUMN_BURPPLE_FEATURED_TAG + " TEXT, "
            + " UNIQUE ( "
            + BurppleDBContract.FeaturedEntry.COLUMN_BURPPLE_FEATURED_ID
            + " ) ON CONFLICT REPLACE "
            + ");";

    String SQL_CREATE_PROMOTION_TABLE = "CREATE TABLE " + BurppleDBContract.PromotionEntry.TABLE_NAME
            + " (" + BurppleDBContract.PromotionEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + BurppleDBContract.PromotionEntry.COLUMN_BURPPLE_PROMOTION_ID + " VARCHAR(256), "
            + BurppleDBContract.PromotionEntry.COLUMN_BURPPLE_PROMOTION_IMAGE + " TEXT, "
            + BurppleDBContract.PromotionEntry.COLUMN_BURPPLE_PROMOTION_TITLE + " TEXT, "
           + BurppleDBContract.PromotionEntry.COLUMN_BURPPLE_PROMOTION_UNTIL + " TEXT, "
            + BurppleDBContract.PromotionEntry.COLUMN_BURPPLE_PROMOTION_SHOP_ID + " TEXT, "
            + BurppleDBContract.PromotionEntry.COLUMN_IS_BURPPLE_EXCLUSIVE + " TEXT, "
            + " UNIQUE ( "
            + BurppleDBContract.PromotionEntry.COLUMN_BURPPLE_PROMOTION_ID
            + " ) ON CONFLICT REPLACE "
            + ");";

    String SQL_CREATE_GUIDE_TABLE = "CREATE TABLE " + BurppleDBContract.GuideEntry.TABLE_NAME
            + " (" + BurppleDBContract.GuideEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + BurppleDBContract.GuideEntry.COLUMN_BURPPLE_GUIDE_ID + " VARCHAR(256), "
            + BurppleDBContract.GuideEntry.COLUMN_BURPPLE_GUIDE_IMAGE + " TEXT, "
            + BurppleDBContract.GuideEntry.COLUMN_BURPPLE_GUIDE_TITLE + " TEXT, "
            + BurppleDBContract.GuideEntry.COLUMN_BURPPLE_GUIDE_DESC + " TEXT, "
            + " UNIQUE ( "
            + BurppleDBContract.GuideEntry.COLUMN_BURPPLE_GUIDE_ID
            + " ) ON CONFLICT REPLACE "
            + ");";

    String SQL_CREATE_PROMOTION_SHOP_TABLE = "CREATE TABLE " + BurppleDBContract.BurpplePromotionShopEntry.TABLE_NAME
            + " (" + BurppleDBContract.BurpplePromotionShopEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + BurppleDBContract.BurpplePromotionShopEntry.COLUMN_BURPPLE_SHOP_ID + " VARCHAR(256), "
            + BurppleDBContract.BurpplePromotionShopEntry.COLUMN_BURPPLE_SHOP_NAME + " TEXT, "
            + BurppleDBContract.BurpplePromotionShopEntry.COLUMN_BURPPLE_SHOP_AREA + " TEXT, "
            + " UNIQUE ( "
            + BurppleDBContract.BurpplePromotionShopEntry.COLUMN_BURPPLE_SHOP_ID
            + " ) ON CONFLICT REPLACE "
            + ");";

    String SQL_CREATE_PROMOTION_TERM_TABLE = "CREATE TABLE " + BurppleDBContract.BurpplePromotionTermsEntry.TABLE_NAME
            + " (" + BurppleDBContract.BurpplePromotionTermsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + BurppleDBContract.BurpplePromotionTermsEntry.COLUMN_BURPPLE_TERMS_NAME + " TEXT, "
            + BurppleDBContract.BurpplePromotionTermsEntry.COLUMN_BURPPLE_PROMOTION_ID + " TEXT "
            + ");";

    public BurppleDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_FEATURE_TABLE);
        db.execSQL(SQL_CREATE_PROMOTION_TABLE);
        db.execSQL(SQL_CREATE_GUIDE_TABLE);
        db.execSQL(SQL_CREATE_PROMOTION_SHOP_TABLE);
        db.execSQL(SQL_CREATE_PROMOTION_TERM_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + BurppleDBContract.FeaturedEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + BurppleDBContract.PromotionEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + BurppleDBContract.GuideEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + BurppleDBContract.BurpplePromotionShopEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + BurppleDBContract.BurpplePromotionTermsEntry.TABLE_NAME);

        onCreate(db);

    }
}
