package pt.ipg.quizzprogramao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ruima on 01/06/2018.
 */

public class DbQuizzOpenHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "quizz.db";
    public static final int DATABASE_VERSION = 1;

    public DbQuizzOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
