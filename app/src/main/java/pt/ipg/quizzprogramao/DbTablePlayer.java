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

    public static final String [] ALL_COLUMNS = new String[] { _ID, FIELD_NAME, BEST_SCORE };

    private SQLiteDatabase db;


    public DbTablePlayer (SQLiteDatabase db) {
        this.db = db;


    }



    public void create () {
            db.execSQL(
                "CREATE TABLE " + TABLE_NAME + "(" +
                        _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        FIELD_NAME + "TEXT NOT NULL, " +
                        BEST_SCORE + "INTEGER" +
                        ")"

        );
    }

    public static ContentValues getContentValues(Player player) {
        ContentValues values = new ContentValues();


        values.put(FIELD_NAME, player.getName());
        values.put(BEST_SCORE, player.getBest_score());

        return values;
    }

    public static Player getCurrentPlayerFromCursor(Cursor cursor) {
        final int posId = cursor.getColumnIndex(_ID);
        final int posName = cursor.getColumnIndex(FIELD_NAME);
        final int posBest_Score = cursor.getColumnIndex(BEST_SCORE);

        Player player = new Player();

        player.setId(cursor.getInt(posId));
        player.setName(cursor.getString(posName));
        player.setBest_score(cursor.getInt(posBest_Score));

        return player;

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








    public Cursor query (String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        return db.query(TABLE_NAME, columns, selection, selectionArgs, groupBy, having, orderBy);
    }

}







