package pt.ipg.quizzprogramao;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by ruima on 14/06/2018.
 */

public class QuizzContentProvider extends ContentProvider {
    private static final String AUTHORITY = "pt.ipg.quizzprogramao";

    public static final Uri BASE_URI = Uri.parse("content://" + AUTHORITY);
    public static final Uri PLAYER_URI = Uri.withAppendedPath(BASE_URI, DbTablePlayer.TABLE_NAME);

    public static final int PLAYER = 100;
    public static final int PLAYER_ID = 101;
    public static final int SCORE = 200;
    public static final int SCORE_ID = 201;
    public static final int QUESTIONS = 300;
    public static final int QUESTIONS_ID = 301;
    public static final int ANSWERS = 400;
    public static final int ANSWERS_ID = 401;
    public static final String MULTIPLE_ITEMS = "vnd.android.cursor.dir";
    public static final String SINGLE_ITEM = "vnd.android.sursor.item";


    DbQuizzOpenHelper quizzOpenHelper;



        private static UriMatcher getQuizzUriMatcher() {

            UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

            uriMatcher.addURI(AUTHORITY, "player", PLAYER);
            uriMatcher.addURI(AUTHORITY, "player/#", PLAYER_ID);

            uriMatcher.addURI(AUTHORITY, "score", SCORE);
            uriMatcher.addURI(AUTHORITY, "score/#", SCORE_ID);

            uriMatcher.addURI(AUTHORITY, "question", QUESTIONS);
            uriMatcher.addURI(AUTHORITY, "questions/#", QUESTIONS_ID);

            uriMatcher.addURI(AUTHORITY, "answers", ANSWERS);
            uriMatcher.addURI(AUTHORITY, "answers/#", ANSWERS_ID);

            return uriMatcher;

        }

    @Override
    public boolean onCreate() {
            quizzOpenHelper = new DbQuizzOpenHelper(getContext());

            return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db = quizzOpenHelper.getReadableDatabase();

        String id = uri.getLastPathSegment();

        UriMatcher matcher = getQuizzUriMatcher();

        switch (matcher.match(uri)) {
            case PLAYER:
                return new DbTablePlayer(db).query(projection, selection, selectionArgs, null, null, sortOrder);

            case SCORE:
                return new DbTableScore(db).query(projection, selection, selectionArgs, null, null, sortOrder);

            case QUESTIONS:
                return new DbTableQuestions(db).query(projection, selection, selectionArgs, null, null, sortOrder);

            case ANSWERS:
                return new DbTableAnswers(db).query(projection, selection, selectionArgs, null, null, sortOrder);


            case PLAYER_ID:
                return new DbTablePlayer(db).query(projection, DbTablePlayer._ID + "=?", new String[] { id },null, null, null);

            case SCORE_ID:
                return new DbTableScore(db).query(projection, DbTableScore._ID + "=?", new String[] { id },null, null, null);

            case QUESTIONS_ID:
                return new DbTableQuestions(db).query(projection, DbTableQuestions._ID + "=?", new String[] {id}, null, null, null);

            case ANSWERS_ID:
                return new DbTableAnswers(db).query(projection, DbTableAnswers._ID + "=?", new String[] {id}, null, null, null);



            default:
                throw new UnsupportedOperationException("Invalid Uri :" + uri);


        }





    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
            UriMatcher matcher = getQuizzUriMatcher();

            switch (matcher.match(uri)) {
                case PLAYER:
                    return MULTIPLE_ITEMS + "/" + AUTHORITY + "/" + DbTablePlayer.TABLE_NAME;

                case SCORE:
                    return MULTIPLE_ITEMS + "/" + AUTHORITY + "/" + DbTableScore.TABLE_NAME;

                case QUESTIONS:
                    return MULTIPLE_ITEMS + "/" + AUTHORITY + "/" + DbTableQuestions.TABLE_NAME;

                case ANSWERS:
                    return MULTIPLE_ITEMS + "/" + AUTHORITY + "/" + DbTableAnswers.TABLE_NAME;

                case PLAYER_ID:
                    return SINGLE_ITEM + "/" + AUTHORITY + "/" + DbTablePlayer.TABLE_NAME;

                case SCORE_ID:
                    return SINGLE_ITEM + "/" + AUTHORITY + "/" + DbTableScore.TABLE_NAME;

                case QUESTIONS_ID:
                    return SINGLE_ITEM + "/" + AUTHORITY + "/" + DbTableQuestions.TABLE_NAME;

                case ANSWERS_ID:
                    return SINGLE_ITEM + "/" + AUTHORITY + "/" + DbTableAnswers.TABLE_NAME;


                default:
                    throw new UnsupportedOperationException("Unknown URI: " + uri);
            }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        SQLiteDatabase db = quizzOpenHelper.getWritableDatabase();

        UriMatcher matcher = getQuizzUriMatcher();

        long id = -1;

        switch (matcher.match(uri)) {
            case PLAYER:
                id = new DbTablePlayer(db).insert(values);
                break;

            case SCORE:
                id = new DbTableScore(db).insert(values);

            case QUESTIONS:
                id = new DbTableQuestions(db).insert(values);

            case ANSWERS:
                id = new DbTableAnswers(db).insert(values);

            default:
                throw new UnsupportedOperationException("Invalid URI: " + uri);

        }
        if (id > 0) {
            notifyChanges(uri);
            return Uri.withAppendedPath(uri, Long.toString(id));
        } else {
            throw new SQLException("Could not insert record");
        }

    }

    private void notifyChanges(Uri uri) {
        getContext().getContentResolver().notifyChange(uri, null);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = quizzOpenHelper.getWritableDatabase();

        UriMatcher matcher = getQuizzUriMatcher();

        String id = uri.getLastPathSegment();

        int rows = 0;

        switch (matcher.match(uri)) {
            case PLAYER_ID:
                rows = new DbTablePlayer(db).delete(DbTablePlayer._ID + "=?", new String[] {id});
                break;

            case SCORE_ID:
                rows = new DbTableScore(db).delete(DbTableScore._ID + "=?", new String[] {id});
                break;

            case QUESTIONS_ID:
                rows = new DbTableQuestions(db).delete(DbTableQuestions._ID + "=?", new String[] {id});
                break;

            case ANSWERS_ID:
                rows = new DbTableAnswers(db).delete(DbTableAnswers._ID + "=?", new String[] {id});
                break;

            default:
                throw new UnsupportedOperationException("Invalid URI: " + uri);

        }

        if (rows > 0) notifyChanges(uri);

        return rows;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = quizzOpenHelper.getWritableDatabase();

        UriMatcher matcher = getQuizzUriMatcher();

        String id = uri.getLastPathSegment();

        int rows = 0;

        switch (matcher.match(uri)) {
            case PLAYER_ID:
                rows = new DbTablePlayer(db).update(values, DbTablePlayer._ID + "=?", new String[] {id});
                break;

            case SCORE_ID:
                rows = new DbTableScore(db).update(values,DbTableScore._ID + "=?", new String[] {id});
                break;

            case QUESTIONS_ID:
                rows = new DbTableQuestions(db).update(values,DbTableQuestions._ID + "=?", new String[] {id});
                break;

            case ANSWERS_ID:
                rows = new DbTableAnswers(db).update(values,DbTableAnswers._ID + "=?", new String[] {id});
                break;

            default:
                throw new UnsupportedOperationException("Invalid URI: " + uri);

        }

        if (rows > 0) notifyChanges(uri);

        return rows;
    }
}
