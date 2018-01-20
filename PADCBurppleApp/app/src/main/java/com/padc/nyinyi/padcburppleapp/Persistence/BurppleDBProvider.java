package com.padc.nyinyi.padcburppleapp.Persistence;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import javax.net.ssl.SNIHostName;

/**
 * Created by user on 1/18/18.
 */

public class BurppleDBProvider extends ContentProvider {

    public static final int FEATURED = 100;
    public static final int GUIDE = 200;
    public static final int PROMOTION = 300;
    public static final int BURPPLE_PROMOTION_SHOP = 400;
    public static final int BURPPLE_PROMOTION_TERMS = 500;

    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private static final SQLiteQueryBuilder sPromotionWithShop_IJ;

    static {
        sPromotionWithShop_IJ = new SQLiteQueryBuilder();
        sPromotionWithShop_IJ.setTables(
                BurppleDBContract.PromotionEntry.TABLE_NAME + " INNER JOIN " +
                        BurppleDBContract.BurpplePromotionShopEntry.TABLE_NAME + " ON " +
                        BurppleDBContract.PromotionEntry.TABLE_NAME + "." + BurppleDBContract.PromotionEntry.COLUMN_BURPPLE_PROMOTION_SHOP_ID + " = " +
                        BurppleDBContract.BurpplePromotionShopEntry.TABLE_NAME + "." + BurppleDBContract.BurpplePromotionShopEntry.COLUMN_BURPPLE_SHOP_ID
        );
    }


    private BurppleDBHelper mDBHelper;

    private static UriMatcher buildUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(BurppleDBContract.CONTENT_AUTHORITY, BurppleDBContract.PATH_FEATURED, FEATURED);
        uriMatcher.addURI(BurppleDBContract.CONTENT_AUTHORITY, BurppleDBContract.PATH_GUIDE, GUIDE);
        uriMatcher.addURI(BurppleDBContract.CONTENT_AUTHORITY, BurppleDBContract.PATH_PROMOTION, PROMOTION);
        uriMatcher.addURI(BurppleDBContract.CONTENT_AUTHORITY, BurppleDBContract.PATH_BURPPLE_PROMOTION_SHOP, BURPPLE_PROMOTION_SHOP);
        uriMatcher.addURI(BurppleDBContract.CONTENT_AUTHORITY, BurppleDBContract.PATH_BURPPLE_PROMOTION_TERMS, BURPPLE_PROMOTION_TERMS);

        return uriMatcher;

    }


    private String getTableName(Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case FEATURED:
                return BurppleDBContract.FeaturedEntry.TABLE_NAME;
            case GUIDE:
                return BurppleDBContract.GuideEntry.TABLE_NAME;
            case PROMOTION:
                return BurppleDBContract.PromotionEntry.TABLE_NAME;
            case BURPPLE_PROMOTION_SHOP:
                return BurppleDBContract.BurpplePromotionShopEntry.TABLE_NAME;
            case BURPPLE_PROMOTION_TERMS:
                return BurppleDBContract.BurpplePromotionTermsEntry.TABLE_NAME;

            default:
                return null;
        }

    }

    private Uri getContentUri(Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case FEATURED:
                return BurppleDBContract.FeaturedEntry.CONTENT_URI;
            case GUIDE:
                return BurppleDBContract.GuideEntry.CONTENT_URI;
            case PROMOTION:
                return BurppleDBContract.PromotionEntry.CONTENT_URI;
            case BURPPLE_PROMOTION_SHOP:
                return BurppleDBContract.BurpplePromotionShopEntry.CONTENT_URI;
            case BURPPLE_PROMOTION_TERMS:
                return BurppleDBContract.BurpplePromotionTermsEntry.CONTENT_URI;

            default:
                return null;
        }
    }

    @Override
    public boolean onCreate() {
        mDBHelper = new BurppleDBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        Cursor queryCursor;

        switch (sUriMatcher.match(uri)) {
            case PROMOTION:
                queryCursor = sPromotionWithShop_IJ.query(mDBHelper.getReadableDatabase(),
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
        default:
            queryCursor = mDBHelper.getReadableDatabase().query(getTableName(uri),
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    sortOrder);

        }



        if (getContext() != null) {
            queryCursor.setNotificationUri(getContext().getContentResolver(), uri);
        }

        return queryCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case FEATURED:
                return BurppleDBContract.FeaturedEntry.DIR_TYPE;
            case GUIDE:
                return BurppleDBContract.GuideEntry.DIR_TYPE;
            case PROMOTION:
                return BurppleDBContract.PromotionEntry.DIR_TYPE;
            case BURPPLE_PROMOTION_SHOP:
                return BurppleDBContract.BurpplePromotionShopEntry.DIR_TYPE;
            case BURPPLE_PROMOTION_TERMS:
                return BurppleDBContract.BurpplePromotionTermsEntry.DIR_TYPE;

        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        final SQLiteDatabase db = mDBHelper.getWritableDatabase();
        String tableName = getTableName(uri);
        long _id = db.insert(tableName, null, contentValues);
        if (_id > 0) {
            Uri tableContentUri = getContentUri(uri);
            Uri insertedUri = ContentUris.withAppendedId(tableContentUri, _id);

            if (getContext() != null) {
                getContext().getContentResolver().notifyChange(uri, null);
            }
            return insertedUri;
        }
        return null;
    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        final SQLiteDatabase db = mDBHelper.getWritableDatabase();
        String tableName = getTableName(uri);
        int insertedCount = 0;
        try {
            db.beginTransaction();
            for (ContentValues cv : values) {
                long _id = db.insert(tableName, null, cv);
                if (_id > 0) {
                    insertedCount++;
                }
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
//            db.close();
        }

        Context context = getContext();
        if (context != null) {
            context.getContentResolver().notifyChange(uri, null);
        }

        return insertedCount;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db = mDBHelper.getWritableDatabase();
        int rowDelteted;
        String tableName = getTableName(uri);
        rowDelteted = db.delete(tableName, selection, selectionArgs);
        Context context = getContext();
        if (context != null && rowDelteted > 0) {
            context.getContentResolver().notifyChange(uri, null);
        }
        return rowDelteted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db = mDBHelper.getWritableDatabase();
        int rowUpdated;
        String tableName = getTableName(uri);

        rowUpdated = db.update(tableName, contentValues, selection, selectionArgs);
        Context context = getContext();
        if (context != null && rowUpdated > 0) {
            context.getContentResolver().notifyChange(uri, null);
        }
        return rowUpdated;
    }
}
