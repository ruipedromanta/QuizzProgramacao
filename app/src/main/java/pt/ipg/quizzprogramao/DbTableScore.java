package pt.ipg.quizzprogramao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by ruima on 01/06/2018.
 */

public class DbTableScore implements BaseColumns {



    public static final String FILED_NAME = "name";
    public static final String FIELD_NAME = FILED_NAME;
    public static final String FIELD_SCORE = "score";
    public static final String TABLE_NAME = FIELD_SCORE + "s";
    private SQLiteDatabase db;

    public DbTableScore(SQLiteDatabase db) {
        this.db = db;

    }



    public void create() {
        db.execSQL(
                "CREATE TABLE " + TABLE_NAME +   "(" +
                        _ID + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        FIELD_SCORE + "INTEGER," +
                        FILED_NAME + "TEXT NOT NULL," +
                        "FOREIGN KEY(" + FIELD_NAME + ") REFERENCES " +
                        DbTablePlayer.TABLE_NAME +
                        "(" + DbTablePlayer._ID +")" +
                        ")"




        );
    }


    public static ContentValues getContentValues(Score score) {
        ContentValues values = new ContentValues();

        values.put(_ID, score.getId());
        values.put(FIELD_SCORE, score.getScore());
        values.put(FILED_NAME, score.getName());

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




