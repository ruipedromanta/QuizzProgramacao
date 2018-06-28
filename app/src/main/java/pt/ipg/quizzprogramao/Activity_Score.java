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



        @Override
        public void onLoadFinished(@NonNull android.support.v4.content.Loader<Cursor> loader, Cursor data) {
            playerCursorAdapter.refreshData(data);
        }

        @Override
        public void onLoaderReset(@NonNull android.support.v4.content.Loader<Cursor> loader) {
            playerCursorAdapter.refreshData(null);
        }


    }


