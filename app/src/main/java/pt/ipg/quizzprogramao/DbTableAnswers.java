package pt.ipg.quizzprogramao;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by ruima on 01/06/2018.
 */

public class DbTableAnswers implements BaseColumns {


    public static final String FILED_ANSWER = "answer";
    private SQLiteDatabase db;

    public DbTableAnswers(SQLiteDatabase db) {
        this.db = db;

    }



    public void create() {
        db.execSQL(
                "CREATE TABLE " + FILED_ANSWER + "s (" +
                        _ID + "INTEGER PRIMARY KEY AUTOINCREMENT," +
                        FILED_ANSWER + "TEXT NOT NULL," +
                        "FOREIGN KEY(" +
                        ""
        );
    }
}
