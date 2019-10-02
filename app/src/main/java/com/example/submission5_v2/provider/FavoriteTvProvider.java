//package com.example.submission5_v2.provider;
//
//import android.content.ContentProvider;
//import android.content.ContentValues;
//import android.content.UriMatcher;
//import android.database.Cursor;
//import android.net.Uri;
//import android.support.annotation.NonNull;
//
//import com.example.submission5_v2.database.DatabaseContract;
//import com.example.submission5_v2.database.FavoriteTvHelper;
//
//import static com.example.submission5_v2.database.DatabaseContract.AUTHORITY;
//import static com.example.submission5_v2.database.DatabaseContract.CONTENT_URI;
//
//public class FavoriteTvProvider extends ContentProvider {
//
//    private static final int MOVIE = 1;
//    private static final int MOVIE_ID = 2;
//
//    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
//
//    static {
//
//        sUriMatcher.addURI(AUTHORITY, DatabaseContract.TABLE_FAVORITE_TV, MOVIE);
//
//        sUriMatcher.addURI(AUTHORITY,
//                DatabaseContract.TABLE_FAVORITE_TV+ "/#",
//                MOVIE_ID);
//    }
//
//    private FavoriteTvHelper favoriteHelper;
//
//    @Override
//    public boolean onCreate() {
//        favoriteHelper = new FavoriteTvHelper(getContext());
//        favoriteHelper.open();
//        return true;
//    }
//
//    @Override
//    public Cursor query(@NonNull Uri uri, String[] strings, String s, String[] strings1, String s1) {
//        Cursor cursor;
//        switch(sUriMatcher.match(uri)){
//            case MOVIE:
//                cursor = favoriteHelper.queryProvider();
//                break;
//            case MOVIE_ID:
//                cursor = favoriteHelper.queryByIdProvider(uri.getLastPathSegment());
//                break;
//            default:
//                cursor = null;
//                break;
//        }
//
//        if (cursor != null){
//            cursor.setNotificationUri(getContext().getContentResolver(),uri);
//        }
//
//        return cursor;
//    }
//
//    @Override
//    public String getType(@NonNull Uri uri) {
//        return null;
//    }
//
//    @Override
//    public Uri insert(@NonNull Uri uri, ContentValues contentValues) {
//
//        long added ;
//
//        switch (sUriMatcher.match(uri)){
//            case MOVIE:
//                added = favoriteHelper.insertProvider(contentValues);
//                break;
//            default:
//                added = 0;
//                break;
//        }
//
//        if (added > 0) {
//            getContext().getContentResolver().notifyChange(uri, null);
//        }
//        return Uri.parse(CONTENT_URI + "/" + added);
//    }
//
//
//    @Override
//    public int update(@NonNull Uri uri, ContentValues contentValues, String s, String[] strings) {
//        int updated ;
//        switch (sUriMatcher.match(uri)) {
//            case MOVIE_ID:
//                updated =  favoriteHelper.updateProvider(uri.getLastPathSegment(),contentValues);
//                break;
//            default:
//                updated = 0;
//                break;
//        }
//
//        if (updated > 0) {
//            getContext().getContentResolver().notifyChange(uri, null);
//        }
//        return updated;
//    }
//
//    @Override
//    public int delete(@NonNull Uri uri, String s, String[] strings) {
//        int deleted;
//        switch (sUriMatcher.match(uri)) {
//            case MOVIE_ID:
//                deleted =  favoriteHelper.deleteProvider(uri.getLastPathSegment());
//                break;
//            default:
//                deleted = 0;
//                break;
//        }
//
//        if (deleted > 0) {
//            getContext().getContentResolver().notifyChange(uri, null);
//        }
//
//        return deleted;
//    }
//
//}
