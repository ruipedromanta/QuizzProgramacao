package pt.ipg.quizzprogramao;

import android.content.ContentValues;
import android.database.Cursor;
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

    public static ContentValues getContentValues(Player player) {
        ContentValues values = new ContentValues();

        values.put(_ID, player.getId());
        values.put(FIELD_NAME, player.getName());
        values.put(BEST_SCORE, player.getBest_score());

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







