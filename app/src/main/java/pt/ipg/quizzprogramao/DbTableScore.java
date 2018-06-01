package pt.ipg.quizzprogramao;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by ruima on 01/06/2018.
 */

public class DbTableScore implements BaseColumns {

    public static final String TABLE_NAME = "score";
    public static final String FIELD_SCORE = TABLE_NAME;
    public static final String FIELD_NAME = "name";
    private SQLiteDatabase db;

    public DbTableScore(SQLiteDatabase db) {
        this.db = db;

    }



    public void create() {
        db.execSQL(
                "CREATE TABLE " + TABLE_NAME + "(" +
                        _ID + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        FIELD_SCORE + "INTEGER," +
                        "FOREIGN KEY(" + FIELD_NAME + ") REFERENCES " +
                        DbTablePlayer.TABLE_NAME +
                        "(" + DbTablePlayer._ID +")" +
                        ")"




        );
    }
}

