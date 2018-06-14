package pt.ipg.quizzprogramao;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by ruima on 14/06/2018.
 */

public class QuizzContentProvider extends ContentProvider {
    public static final int PLAYER = 100;
    public static final String AUTHORITY = "pt.ipg.quizzprogramao";
    public static final int PLAYER_ID = 101;
    public static final int SCORE = 200;
    public static final int SCORE_ID = 201;
    public static final int QUESTIONS = 300;
    public static final int QUESTIONS_ID = 301;
    public static final int ANSWERS = 400;
    public static final int ANSWERS_ID = 401;
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
        }

    @Override
    public boolean onCreate() {
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
