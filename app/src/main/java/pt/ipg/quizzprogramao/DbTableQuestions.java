package pt.ipg.quizzprogramao;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by ruima on 01/06/2018.
 */

public class DbTableQuestions implements BaseColumns {
    public static final String FILED_QUESTION = "question";
    public static final String FILED_ID_SCORE = "score";
    public static final String FIELD_ID_SCORE = FILED_ID_SCORE;
    public static final String FILED_SCORE = FIELD_ID_SCORE;
    public static final String TABLE_NAME = "questions";
    private SQLiteDatabase db;

    public DbTableQuestions(SQLiteDatabase db) {
        this.db = db;

    }




    public void create() {
        db.execSQL(
                "CREATE TABLE " + TABLE_NAME + " " +   " (" +
                        _ID + "INTEGER PRIMARY KEY AUTOINCREMENT," +
                        FILED_QUESTION + "TEXT NOT NULL," +
                        FILED_ID_SCORE + "INTEGER," +
                        "FOREIGN KEY(" + FIELD_ID_SCORE +")REFERENCES " +
                        DbTableScore.TABLE_NAME +
                        "(" + DbTableScore._ID+")" +

                        ")"


        );
    }

}

