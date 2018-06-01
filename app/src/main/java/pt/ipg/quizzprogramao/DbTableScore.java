package pt.ipg.quizzprogramao;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by ruima on 01/06/2018.
 */

public class DbTableScore implements BaseColumns {

    public static final String FIELD_SCORE = "score";
    public static final String FIELD_NAME = "name";
    private SQLiteDatabase db;

    public DbTableScore(SQLiteDatabase db) {
        this.db = db;

    }



    public void create() {
        db.execSQL(
                "CREATE TABLE score(" +
                        _ID + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        FIELD_SCORE + "INTEGER," +
                        "FOREIGN KEY(" + FIELD_NAME + ") REFERENCES " +
                        DbTablePlayer.TABLE_NAME +
                        "(" + DbTablePlayer._ID +")" +
                        ")"




        );
    }
}