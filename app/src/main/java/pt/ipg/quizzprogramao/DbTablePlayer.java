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
    private static final String FIELD_ID_CATEGORY = "idCategory";

    public static final String [] ALL_COLUMNS = new String[] { _ID, FIELD_NAME, BEST_SCORE, FIELD_ID_CATEGORY };

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
                        FIELD_ID_CATEGORY + " INTEGER," +
                        "FOREIGN KEY (" + FIELD_ID_CATEGORY + ") REFERENCES " +
                        DbTableCategories.TABLE_NAME +
                        "(" + DbTableCategories._ID +")" +
                        ")"

        );
    }

    public static ContentValues getContentValues(Player player) {
        ContentValues values = new ContentValues();


        values.put(FIELD_NAME, player.getName());
        values.put(BEST_SCORE, player.getBest_score());
        values.put(FIELD_ID_CATEGORY, player.getIdCategory());

        return values;
    }

    public static Player getCurrentPlayerFromCursor(Cursor cursor) {
        final int posId = cursor.getColumnIndex(_ID);
        final int posName = cursor.getColumnIndex(FIELD_NAME);
        final int posBest_Score = cursor.getColumnIndex(BEST_SCORE);
        final int posIdCategory = cursor.getColumnIndex(FIELD_ID_CATEGORY);

        Player player = new Player();

        player.setId(cursor.getInt(posId));
        player.setName(cursor.getString(posName));
        player.setBest_score(cursor.getInt(posBest_Score));
        player.setIdCategory(cursor.getInt(posIdCategory));


        return player;

    }
    /**
     * Convenience method for inserting a row into the categories table.
     *
     * @param values this map contains the initial column values for the
     *            row. The keys should be the column names and the values the
     *            column values
     * @return the row ID of the newly inserted row, or -1 if an error occurred
     */





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
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, groupBy, having, orderBy);
            return cursor;
    }

}







