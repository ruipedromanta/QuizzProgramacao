package pt.ipg.quizzprogramao;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by ruima on 01/06/2018.
 */

public class DbTablePlayer implements BaseColumns {


    public static final String TABLE_NAME = "player";
    public static final String FIELD_NAME = "name";
    public static final String BEST_SCORE = "best_score";
    private SQLiteDatabase db;


    public DbTablePlayer (SQLiteDatabase db) {
        this.db = db;


    }



    public void create () {
        db.execSQL(
                "CREATE TABLE " + TABLE_NAME + "(" +
                        _ID + "INTEGGER PRIMARY KEY AUTOINCREMENT, " +
                        FIELD_NAME + "TEXT NOT NULL, " +
                        BEST_SCORE + "INTEGER" +
                        ")"

        );
    }
}




