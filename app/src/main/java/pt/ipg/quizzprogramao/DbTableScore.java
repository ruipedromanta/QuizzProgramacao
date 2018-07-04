package pt.ipg.quizzprogramao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by ruima on 01/06/2018.
 */

public class DbTableScore implements BaseColumns {




    public static final String FIELD_SCORE = "score";
    public static final String TABLE_NAME = FIELD_SCORE + "s";
    public static final String FIELD_ID_PLAYER = "idPlayer";

    public static final String [] ALL_COLUMNS = new String[] { _ID, FIELD_SCORE, FIELD_ID_PLAYER };

    private SQLiteDatabase db;

    public DbTableScore(SQLiteDatabase db) {
        this.db = db;

    }



    public void create() {
        db.execSQL(
                "CREATE TABLE " + TABLE_NAME +  "(" +
                        _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        FIELD_SCORE + " INTEGER," +
                       FIELD_ID_PLAYER + " INTEGER," +
                        "FOREIGN KEY (" + FIELD_ID_PLAYER + ") REFERENCES " +
                            DbTablePlayer.TABLE_NAME +
                                "(" + DbTablePlayer._ID + ")" +
                        ")"
        );







    }


    public static ContentValues getContentValues(Score score) {
        ContentValues values = new ContentValues();

        values.put(_ID, score.getId());
        values.put(FIELD_SCORE, score.getScore());
        values.put(FIELD_ID_PLAYER, score.getIdPlayer());

        return values;
    }

    public static Score getCurrentScoreFromCursor (Cursor cursor) {
        final int posId = cursor.getColumnIndex(_ID);
        final int posScore = cursor.getColumnIndex(FIELD_SCORE);
        final int posidPlayer = cursor.getColumnIndex(FIELD_ID_PLAYER);

        Score score = new Score();

        score.setId(cursor.getInt(posId));
        score.setScore(cursor.getInt(posScore));
        score.setIdPlayer(cursor.getInt(posidPlayer));

        return score;
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








    Cursor query(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        return db.query(TABLE_NAME, columns, selection, selectionArgs, groupBy, having, orderBy);
    }

}




