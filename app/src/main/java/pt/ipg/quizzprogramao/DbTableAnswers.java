package pt.ipg.quizzprogramao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by ruima on 01/06/2018.
 */

public class DbTableAnswers implements BaseColumns {


    public static final String FILED_ANSWER = "answer";
    public static final String FILED_ID_QUESTION = "idquestion";
    public static final String TABLE_NAME = "answers";
    private SQLiteDatabase db;

    public DbTableAnswers(SQLiteDatabase db) {
        this.db = db;

    }



    public void create() {
        db.execSQL(
                "CREATE TABLE " + TABLE_NAME + " " +   " (" +
                        _ID + "INTEGER PRIMARY KEY AUTOINCREMENT," +
                        FILED_ANSWER + "TEXT NOT NULL," +
                        FILED_ID_QUESTION + "TEXT NOT NULL," +
                        "FOREIGN KEY( " + FILED_ID_QUESTION +") REFERENCES " +
                            DbTableQuestions.TABLE_NAME +
                                "(" + DbTableQuestions._ID+")" +
                        ")"


        );
    }


    public static ContentValues getContentValues(Answers answers) {
        ContentValues values = new ContentValues();

        values.put(_ID, answers.getId());
        values.put(FILED_ANSWER, answers.getAnswer());
        values.put(FILED_ID_QUESTION, answers.getIdquestion());

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



