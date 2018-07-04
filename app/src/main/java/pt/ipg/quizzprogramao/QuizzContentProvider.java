package pt.ipg.quizzprogramao;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by ruima on 14/06/2018.
 */

public class QuizzContentProvider extends ContentProvider {
    private static final String AUTHORITY = "pt.ipg.quizzprogramao";

    public static final Uri BASE_URI = Uri.parse("content://" + AUTHORITY);
    public static final Uri PLAYER_URI = Uri.withAppendedPath(BASE_URI, DbTablePlayer.TABLE_NAME);
    public static final Uri CATEGORIES_URI = Uri.withAppendedPath(BASE_URI, DbTableCategories.TABLE_NAME);


    public static final int PLAYER = 100;
    public static final int PLAYER_ID = 101;
    private static final int CATEGORIES = 200;
    private static final int CATEGORIES_ID = 201;
    public static final int SCORE = 300;
    public static final int SCORE_ID = 301;
    public static final int QUESTIONS = 400;
    public static final int QUESTIONS_ID = 401;
    public static final int ANSWERS = 500;
    public static final int ANSWERS_ID = 501;
    public static final String MULTIPLE_ITEMS = "vnd.android.cursor.dir";
    public static final String SINGLE_ITEM = "vnd.android.sursor.item";


    DbQuizzOpenHelper quizzOpenHelper;



        private static UriMatcher getQuizzUriMatcher() {

            UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

            uriMatcher.addURI(AUTHORITY, "player", PLAYER);
            uriMatcher.addURI(AUTHORITY, "player/#", PLAYER_ID);

            uriMatcher.addURI(AUTHORITY, "categories", CATEGORIES);
            uriMatcher.addURI(AUTHORITY, "categories/#", CATEGORIES_ID);

            uriMatcher.addURI(AUTHORITY, "score", SCORE);
            uriMatcher.addURI(AUTHORITY, "score/#", SCORE_ID);

            uriMatcher.addURI(AUTHORITY, "question", QUESTIONS);
            uriMatcher.addURI(AUTHORITY, "questions/#", QUESTIONS_ID);

            uriMatcher.addURI(AUTHORITY, "answers", ANSWERS);
            uriMatcher.addURI(AUTHORITY, "answers/#", ANSWERS_ID);

            return uriMatcher;

        }

    /**
     * Implement this to initialize your content provider on startup.
     * This method is called for all registered content providers on the
     * application main thread at application launch time.  It must not perform
     * lengthy operations, or application startup will be delayed.
     * <p>
     * <p>You should defer nontrivial initialization (such as opening,
     * upgrading, and scanning databases) until the content provider is used
     * (via {@link #query}, {@link #insert}, etc).  Deferred initialization
     * keeps application startup fast, avoids unnecessary work if the provider
     * turns out not to be needed, and stops database errors (such as a full
     * disk) from halting application launch.
     * <p>
     * <p>If you use SQLite, {@link SQLiteOpenHelper}
     * is a helpful utility class that makes it easy to manage databases,
     * and will automatically defer opening until first use.  If you do use
     * SQLiteOpenHelper, make sure to avoid calling
     * {@link SQLiteOpenHelper#getReadableDatabase} or
     * {@link SQLiteOpenHelper#getWritableDatabase}
     * from this method.  (Instead, override
     * {@link SQLiteOpenHelper#onOpen} to initialize the
     * database when it is first opened.)
     *
     * @return true if the provider was successfully loaded, false otherwise
     */
    @Override
    public boolean onCreate() {
            quizzOpenHelper = new DbQuizzOpenHelper(getContext());

            return true;
    }
    /**
     * Implement this to handle query requests from clients.
     * <p>
     * <p>Apps targeting {@link Build.VERSION_CODES#O} or higher should override
     * {@link #query(Uri, String[], Bundle, CancellationSignal)} and provide a stub
     * implementation of this method.
     * <p>
     * <p>This method can be called from multiple threads, as described in
     * <a href="{@docRoot}guide/topics/fundamentals/processes-and-threads.html#Threads">Processes
     * and Threads</a>.
     * <p>
     * Example client call:<p>
     * <pre>// Request a specific record.
     * Cursor managedCursor = managedQuery(
     * ContentUris.withAppendedId(Contacts.People.CONTENT_URI, 2),
     * projection,    // Which columns to return.
     * null,          // WHERE clause.
     * null,          // WHERE clause value substitution
     * People.NAME + " ASC");   // Sort order.</pre>
     * Example implementation:<p>
     * <pre>// SQLiteQueryBuilder is a helper class that creates the
     * // proper SQL syntax for us.
     * SQLiteQueryBuilder qBuilder = new SQLiteQueryBuilder();
     *
     * // Set the table we're querying.
     * qBuilder.setTables(DATABASE_TABLE_NAME);
     *
     * // If the query ends in a specific record number, we're
     * // being asked for a specific record, so set the
     * // WHERE clause in our query.
     * if((URI_MATCHER.match(uri)) == SPECIFIC_MESSAGE){
     * qBuilder.appendWhere("_id=" + uri.getPathLeafId());
     * }
     *
     * // Make the query.
     * Cursor c = qBuilder.query(mDb,
     * projection,
     * selection,
     * selectionArgs,
     * groupBy,
     * having,
     * sortOrder);
     * c.setNotificationUri(getContext().getContentResolver(), uri);
     * return c;</pre>
     *
     * @param uri           The URI to query. This will be the full URI sent by the client;
     *                      if the client is requesting a specific record, the URI will end in a record number
     *                      that the implementation should parse and add to a WHERE or HAVING clause, specifying
     *                      that _id value.
     * @param projection    The list of columns to put into the cursor. If
     *                      {@code null} all columns are included.
     * @param selection     A selection criteria to apply when filtering rows.
     *                      If {@code null} then all rows are included.
     * @param selectionArgs You may include ?s in selection, which will be replaced by
     *                      the values from selectionArgs, in order that they appear in the selection.
     *                      The values will be bound as Strings.
     * @param sortOrder     How the rows in the cursor should be sorted.
     *                      If {@code null} then the provider is free to define the sort order.
     * @return a Cursor or {@code null}.
     */

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db = quizzOpenHelper.getReadableDatabase();

        String id = uri.getLastPathSegment();

        UriMatcher matcher = getQuizzUriMatcher();

        switch (matcher.match(uri)) {
            case PLAYER:
                return new DbTablePlayer(db).query(projection, selection, selectionArgs, null, null, sortOrder);

            case CATEGORIES:
                return new DbTableCategories(db).query(projection, selection, selectionArgs, null, null, sortOrder);


            case SCORE:
                return new DbTableScore(db).query(projection, selection, selectionArgs, null, null, sortOrder);

            case QUESTIONS:
                return new DbTableQuestions(db).query(projection, selection, selectionArgs, null, null, sortOrder);

            case ANSWERS:
                return new DbTableAnswers(db).query(projection, selection, selectionArgs, null, null, sortOrder);


            case PLAYER_ID:
                return new DbTablePlayer(db).query(projection, DbTablePlayer._ID + "=?", new String[] { id },null, null, null);

            case CATEGORIES_ID:
                return new DbTableCategories(db).query(projection, DbTableCategories._ID + "=?", new String[] { id }, null, null, null);


            case SCORE_ID:
                return new DbTableScore(db).query(projection, DbTableScore._ID + "=?", new String[] { id },null, null, null);

            case QUESTIONS_ID:
                return new DbTableQuestions(db).query(projection, DbTableQuestions._ID + "=?", new String[] {id}, null, null, null);

            case ANSWERS_ID:
                return new DbTableAnswers(db).query(projection, DbTableAnswers._ID + "=?", new String[] {id}, null, null, null);



            default:
                throw new UnsupportedOperationException("Invalid Uri :" + uri);


        }





    }
    /**
     * Implement this to handle requests for the MIME type of the data at the
     * given URI.  The returned MIME type should start with
     * <code>vnd.android.cursor.item</code> for a single record,
     * or <code>vnd.android.cursor.dir/</code> for multiple items.
     * This method can be called from multiple threads, as described in
     * <a href="{@docRoot}guide/topics/fundamentals/processes-and-threads.html#Threads">Processes
     * and Threads</a>.
     * <p>
     * <p>Note that there are no permissions needed for an application to
     * access this information; if your content provider requires read and/or
     * write permissions, or is not exported, all applications can still call
     * this method regardless of their access permissions.  This allows them
     * to retrieve the MIME type for a URI when dispatching intents.
     *
     * @param uri the URI to query.
     * @return a MIME type string, or {@code null} if there is no type.
     */

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
            UriMatcher matcher = getQuizzUriMatcher();

            switch (matcher.match(uri)) {
                case PLAYER:
                    return MULTIPLE_ITEMS + "/" + AUTHORITY + "/" + DbTablePlayer.TABLE_NAME;


                case CATEGORIES:
                    return MULTIPLE_ITEMS + "/" + AUTHORITY + "/" + DbTableCategories.TABLE_NAME;

                case SCORE:
                    return MULTIPLE_ITEMS + "/" + AUTHORITY + "/" + DbTableScore.TABLE_NAME;

                case QUESTIONS:
                    return MULTIPLE_ITEMS + "/" + AUTHORITY + "/" + DbTableQuestions.TABLE_NAME;

                case ANSWERS:
                    return MULTIPLE_ITEMS + "/" + AUTHORITY + "/" + DbTableAnswers.TABLE_NAME;

                case PLAYER_ID:
                    return SINGLE_ITEM + "/" + AUTHORITY + "/" + DbTablePlayer.TABLE_NAME;

                case CATEGORIES_ID:
                    return SINGLE_ITEM  + "/" + AUTHORITY + "/" + DbTableCategories.TABLE_NAME;

                case SCORE_ID:
                    return SINGLE_ITEM + "/" + AUTHORITY + "/" + DbTableScore.TABLE_NAME;

                case QUESTIONS_ID:
                    return SINGLE_ITEM + "/" + AUTHORITY + "/" + DbTableQuestions.TABLE_NAME;

                case ANSWERS_ID:
                    return SINGLE_ITEM + "/" + AUTHORITY + "/" + DbTableAnswers.TABLE_NAME;


                default:
                    throw new UnsupportedOperationException("Unknown URI: " + uri);
            }
    }

    /**
     * Implement this to handle requests to insert a new row.
     * As a courtesy, call {@link ContentResolver#notifyChange(Uri, ContentObserver) notifyChange()}
     * after inserting.
     * This method can be called from multiple threads, as described in
     * <a href="{@docRoot}guide/topics/fundamentals/processes-and-threads.html#Threads">Processes
     * and Threads</a>.
     *
     * @param uri    The content:// URI of the insertion request. This must not be {@code null}.
     * @param values A set of column_name/value pairs to add to the database.
     *               This must not be {@code null}.
     * @return The URI for the newly inserted item.
     */

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        SQLiteDatabase db = quizzOpenHelper.getWritableDatabase();

        UriMatcher matcher = getQuizzUriMatcher();

        long id = -1;

        switch (matcher.match(uri)) {
            case PLAYER:
                id = new DbTablePlayer(db).insert(values);
                break;

            case CATEGORIES:
                id = new DbTableCategories(db).insert(values);
                break;

            case SCORE:
                id = new DbTableScore(db).insert(values);

            case QUESTIONS:
                id = new DbTableQuestions(db).insert(values);

            case ANSWERS:
                id = new DbTableAnswers(db).insert(values);

            default:
                throw new UnsupportedOperationException("Invalid URI: " + uri);

        }
        if (id > 0) {
            notifyChanges(uri);
            return Uri.withAppendedPath(uri, Long.toString(id));
        } else {
            throw new SQLException("Could not insert record");
        }

    }

    private void notifyChanges(Uri uri) {
        getContext().getContentResolver().notifyChange(uri, null);
    }

    /**
     * Implement this to handle requests to delete one or more rows.
     * The implementation should apply the selection clause when performing
     * deletion, allowing the operation to affect multiple rows in a directory.
     * As a courtesy, call {@link ContentResolver#notifyChange(Uri, ContentObserver) notifyChange()}
     * after deleting.
     * This method can be called from multiple threads, as described in
     * <a href="{@docRoot}guide/topics/fundamentals/processes-and-threads.html#Threads">Processes
     * and Threads</a>.
     * <p>
     * <p>The implementation is responsible for parsing out a row ID at the end
     * of the URI, if a specific row is being deleted. That is, the client would
     * pass in <code>content://contacts/people/22</code> and the implementation is
     * responsible for parsing the record number (22) when creating a SQL statement.
     *
     * @param uri           The full URI to query, including a row ID (if a specific record is requested).
     * @param selection     An optional restriction to apply to rows when deleting.
     * @param selectionArgs
     * @return The number of rows affected.
     * @throws SQLException
     */

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = quizzOpenHelper.getWritableDatabase();

        UriMatcher matcher = getQuizzUriMatcher();

        String id = uri.getLastPathSegment();

        int rows = 0;

        switch (matcher.match(uri)) {
            case PLAYER_ID:
                rows = new DbTablePlayer(db).delete(DbTablePlayer._ID + "=?", new String[] {id});
                break;

            case CATEGORIES_ID:
                rows = new DbTableCategories(db).delete(DbTableCategories._ID +"=?", new String [] { id });
                break;

            case SCORE_ID:
                rows = new DbTableScore(db).delete(DbTableScore._ID + "=?", new String[] {id});
                break;

            case QUESTIONS_ID:
                rows = new DbTableQuestions(db).delete(DbTableQuestions._ID + "=?", new String[] {id});
                break;

            case ANSWERS_ID:
                rows = new DbTableAnswers(db).delete(DbTableAnswers._ID + "=?", new String[] {id});
                break;

            default:
                throw new UnsupportedOperationException("Invalid URI: " + uri);

        }

        if (rows > 0) notifyChanges(uri);

        return rows;
    }

    /**
     * Implement this to handle requests to update one or more rows.
     * The implementation should update all rows matching the selection
     * to set the columns according to the provided values map.
     * As a courtesy, call {@link ContentResolver#notifyChange(Uri, ContentObserver) notifyChange()}
     * after updating.
     * This method can be called from multiple threads, as described in
     * <a href="{@docRoot}guide/topics/fundamentals/processes-and-threads.html#Threads">Processes
     * and Threads</a>.
     *
     * @param uri           The URI to query. This can potentially have a record ID if this
     *                      is an update request for a specific record.
     * @param values        A set of column_name/value pairs to update in the database.
     *                      This must not be {@code null}.
     * @param selection     An optional filter to match rows to update.
     * @param selectionArgs
     * @return the number of rows affected.
     */

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = quizzOpenHelper.getWritableDatabase();

        UriMatcher matcher = getQuizzUriMatcher();

        String id = uri.getLastPathSegment();

        int rows = 0;

        switch (matcher.match(uri)) {
            case PLAYER_ID:
                rows = new DbTablePlayer(db).update(values, DbTablePlayer._ID + "=?", new String[] {id});
                break;

            case CATEGORIES_ID:
                rows = new DbTableCategories(db).delete(DbTableCategories._ID +"=?", new String [] { id });
                break;

            case SCORE_ID:
                rows = new DbTableScore(db).update(values,DbTableScore._ID + "=?", new String[] {id});
                break;

            case QUESTIONS_ID:
                rows = new DbTableQuestions(db).update(values,DbTableQuestions._ID + "=?", new String[] {id});
                break;

            case ANSWERS_ID:
                rows = new DbTableAnswers(db).update(values,DbTableAnswers._ID + "=?", new String[] {id});
                break;

            default:
                throw new UnsupportedOperationException("Invalid URI: " + uri);

        }

        if (rows > 0) notifyChanges(uri);

        return rows;
    }
}
