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



    /**
     * Cria a tabela Scores
     * Com os parametros Id, score e o id player
     * Onde o _ID é a chave Primaria
     * e
     * o id player é a chave estrangeira.
     */
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

    /**
     * Convenience method for updating rows in the categories table.
     *
     * @param values a map from column names to new column values. null is a
     *            valid value that will be translated to NULL.
     * @param whereClause the optional WHERE clause to apply when updating.
     *            Passing null will update all rows.
     * @param whereArgs You may include ?s in the where clause, which
     *            will be replaced by the values from whereArgs. The values
     *            will be bound as Strings.
     * @return the number of rows affected
     */

    public int update(ContentValues values, String whereClause, String[] whereArgs) {
        return db.update(TABLE_NAME, values, whereClause, whereArgs);

    }
    /**
     * Convenience method for deleting rows in the categories table.
     *
     * @param whereClause the optional WHERE clause to apply when deleting.
     *            Passing null will delete all rows.
     * @param whereArgs You may include ?s in the where clause, which
     *            will be replaced by the values from whereArgs. The values
     *            will be bound as Strings.
     * @return the number of rows affected if a whereClause is passed in, 0
     *         otherwise. To remove all rows and get a count pass "1" as the
     *         whereClause.
     *         */

    public int delete(String whereClause, String[] whereArgs) {
        return db.delete(TABLE_NAME, whereClause, whereArgs);
    }

    /**
     * Query the categories table, returning a {@link Cursor} over the result set.
     *
     * @param columns A list of which columns to return. Passing null will
     *            return all columns, which is discouraged to prevent reading
     *            data from storage that isn't going to be used.
     * @param selection A filter declaring which rows to return, formatted as an
     *            SQL WHERE clause (excluding the WHERE itself). Passing null
     *            will return all rows for the given table.
     * @param selectionArgs You may include ?s in selection, which will be
     *         replaced by the values from selectionArgs, in order that they
     *         appear in the selection. The values will be bound as Strings.
     * @param groupBy A filter declaring how to group rows, formatted as an SQL
     *            GROUP BY clause (excluding the GROUP BY itself). Passing null
     *            will cause the rows to not be grouped.
     * @param having A filter declare which row groups to include in the cursor,
     *            if row grouping is being used, formatted as an SQL HAVING
     *            clause (excluding the HAVING itself). Passing null will cause
     *            all row groups to be included, and is required when row
     *            grouping is not being used.
     * @param orderBy How to order the rows, formatted as an SQL ORDER BY clause
     *            (excluding the ORDER BY itself). Passing null will use the
     *            default sort order, which may be unordered.
     * @return A {@link Cursor} object, which is positioned before the first entry. Note that
     * {@link Cursor}s are not synchronized, see the documentation for more details.
     * @see Cursor
     */
    Cursor query(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        return db.query(TABLE_NAME, columns, selection, selectionArgs, groupBy, having, orderBy);
    }

}




