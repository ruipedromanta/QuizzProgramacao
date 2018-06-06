package pt.ipg.quizzprogramao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by ruima on 01/06/2018.
 */

public class DbTableQuestions implements BaseColumns {
    public static final String FILED_QUESTION = "question";
    public static final String FILED_ID_SCORE = "idScore";
    public static final String FIELD_ID_SCORE = FILED_ID_SCORE;
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



    public static ContentValues getContentValues(Questions questions) {
        ContentValues values = new ContentValues();

        values.put(_ID, questions.getId());
        values.put(FILED_QUESTION, questions.getQuestion());
        values.put(FILED_ID_SCORE, questions.getIdScore());

        return values;

    }

    public long insert(ContentValues values){
        return db.insert(TABLE_NAME, null, values);
    }






    public int update(ContentValues values, String whereClause, String[] whereArgs) {
        return db.update(TABLE_NAME, values, whereClause, whereArgs);

    }








    public int delete(String whereClause, String[] whereArgs) {
        return db.delete(TABLE_NAME, whereClause, whereArgs);
    }








    private Cursor query (String[] colums, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        return db.query(TABLE_NAME, colums, selection, selectionArgs, groupBy, having, orderBy);
    }

}

