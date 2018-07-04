package pt.ipg.quizzprogramao;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by ruima on 21/06/2018.
 */

public class EditPlayerActivity  extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{
    private static final int CATEGORIES_CURSOR_LOADER_ID = 0;

    private EditText editTextName;
        private Player player;
        private Spinner spinnerCategory;




    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_player);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();

        int playerId = intent.getIntExtra(Activity_Score.PLAYER_ID, -1);

        if (playerId == -1) {
            finish();
            return;
        }


        Cursor cursorPlayer = getContentResolver().query(
                Uri.withAppendedPath(QuizzContentProvider.PLAYER_URI, Integer.toString(playerId)),
                DbTablePlayer.ALL_COLUMNS,
                null,
                null,
                null
        );

        if (!cursorPlayer.moveToNext()) {
            Toast.makeText(this, "Player not found", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        editTextName = (EditText) findViewById(R.id.editTextName);
        spinnerCategory = (Spinner) findViewById(R.id.spinnerCategory);


        player = DbTablePlayer.getCurrentPlayerFromCursor(cursorPlayer);

        editTextName.setText(player.getName());

        getSupportLoaderManager().restartLoader(CATEGORIES_CURSOR_LOADER_ID, null,this);


    }
    @Override
    protected void onResume() {
        super.onResume();
        getSupportLoaderManager().restartLoader(CATEGORIES_CURSOR_LOADER_ID, null, this);
    }

      public void Cancel(View view) {
            finish();
          }

    public void Save(View view) {
        // todo: validations

        player.setName(editTextName.getText().toString());
        player.setIdCategory((int) spinnerCategory.getSelectedItemId());


        int recordsAffected = getContentResolver().update(
                Uri.withAppendedPath(QuizzContentProvider.PLAYER_URI, Integer.toString(player.getId())),
                DbTablePlayer.getContentValues(player),
                null,
                null
        );

        if (recordsAffected > 0) {
            Toast.makeText(this, "Player saved successfully", Toast.LENGTH_LONG).show();
            finish();
        }

        Toast.makeText(this, "Could not save Player", Toast.LENGTH_LONG).show();




    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        if (id == CATEGORIES_CURSOR_LOADER_ID) {
            return new CursorLoader(this, QuizzContentProvider.CATEGORIES_URI, DbTableCategories.ALL_COLLUMS, null, null, null);
        }

        return null;
    }

    /**
     * Called when a previously created loader has finished its load.  Note
     * that normally an application is <em>not</em> allowed to commit fragment
     * transactions while in this call, since it can happen after an
     * activity's state is saved.  See {@link FragmentManager#beginTransaction()
     * FragmentManager.openTransaction()} for further discussion on this.
     * <p>
     * <p>This function is guaranteed to be called prior to the release of
     * the last data that was supplied for this Loader.  At this point
     * you should remove all use of the old data (since it will be released
     * soon), but should not do your own release of the data since its Loader
     * owns it and will take care of that.  The Loader will take care of
     * management of its data so you don't have to.  In particular:
     * <p>
     * <ul>
     * <li> <p>The Loader will monitor for changes to the data, and report
     * them to you through new calls here.  You should not monitor the
     * data yourself.  For example, if the data is a {@link Cursor}
     * and you place it in a {@link CursorAdapter}, use
     * the {@link CursorAdapter#CursorAdapter(Context, * Cursor, int)} constructor <em>without</em> passing
     * in either {@link CursorAdapter#FLAG_AUTO_REQUERY}
     * or {@link CursorAdapter#FLAG_REGISTER_CONTENT_OBSERVER}
     * (that is, use 0 for the flags argument).  This prevents the CursorAdapter
     * from doing its own observing of the Cursor, which is not needed since
     * when a change happens you will get a new Cursor throw another call
     * here.
     * <li> The Loader will release the data once it knows the application
     * is no longer using it.  For example, if the data is
     * a {@link Cursor} from a {@link CursorLoader},
     * you should not call close() on it yourself.  If the Cursor is being placed in a
     * {@link CursorAdapter}, you should use the
     * {@link CursorAdapter#swapCursor(Cursor)}
     * method so that the old Cursor is not closed.
     * </ul>
     * <p>
     * <p>This will always be called from the process's main thread.
     *
     * @param loader The Loader that has finished.
     * @param data   The data generated by the Loader.
     */

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {

        SimpleCursorAdapter cursorAdapterCategories = new SimpleCursorAdapter(
                this,
                android.R.layout.simple_list_item_1,
                data,
                new String[]{DbTableCategories.FIELD_NAME},
                new int[]{android.R.id.text1}
        );

        spinnerCategory.setAdapter(cursorAdapterCategories);

        int idCategory = player.getIdCategory();

        for (int i = 0; i < spinnerCategory.getCount(); i++) {
            Cursor cursor = (Cursor) spinnerCategory.getItemAtPosition(i);

            final int posId = cursor.getColumnIndex(DbTableCategories._ID);

            if (idCategory == cursor.getInt(posId)) {
                spinnerCategory.setSelection(i);
                break;
            }
        }
    }

    /**
     * Called when a previously created loader is being reset, and thus
     * making its data unavailable.  The application should at this point
     * remove any references it has to the Loader's data.
     * <p>
     * <p>This will always be called from the process's main thread.
     *
     * @param loader The Loader that is being reset.
     */


    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }
}



