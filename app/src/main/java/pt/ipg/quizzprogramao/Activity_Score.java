package pt.ipg.quizzprogramao;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CursorAdapter;
import android.widget.Button;

public class Activity_Score extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    public static final int PLAYER_CURSOR_LOADER_ID = 0;
    public static final String PLAYER_ID = "PLAYER_ID";

    private Button button;
    private RecyclerView recyclerViewPlayer;
    private PlayerCursorAdapter playerCursorAdapter;

    Button button7;

    @Override



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        button7 = findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainActivity();

            }
        });


        recyclerViewPlayer = (RecyclerView) findViewById(R.id.recyclerViewPlayer);

        recyclerViewPlayer.setLayoutManager(new LinearLayoutManager(this));
        playerCursorAdapter = new PlayerCursorAdapter(this);
        recyclerViewPlayer.setAdapter(playerCursorAdapter);

        playerCursorAdapter.setViewHolderClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editPlayer();
            }
        });


        getSupportLoaderManager().initLoader(PLAYER_CURSOR_LOADER_ID, null, this);

    }

    private void editPlayer() {
        int id = playerCursorAdapter.getLastPlayerClicked();

        Intent intent = new Intent(this, EditPlayerActivity.class);

        intent.putExtra(PLAYER_ID, id);

        startActivity(intent);
    }


    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    protected void onResume() {
        super.onResume();
        getSupportLoaderManager().restartLoader(PLAYER_CURSOR_LOADER_ID, null, this);
    }

    public void clickSAIR(View v){
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        if (id == PLAYER_CURSOR_LOADER_ID) {
            return new CursorLoader(this, QuizzContentProvider.PLAYER_URI, DbTablePlayer.ALL_COLUMNS, null, null,null);
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
        public void onLoadFinished(@NonNull android.support.v4.content.Loader<Cursor> loader, Cursor data) {
            playerCursorAdapter.refreshData(data);
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
        public void onLoaderReset(@NonNull android.support.v4.content.Loader<Cursor> loader) {
            playerCursorAdapter.refreshData(null);
        }


    }




