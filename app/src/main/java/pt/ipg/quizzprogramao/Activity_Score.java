package pt.ipg.quizzprogramao;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity_Score extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    public static final int PLAYER_CURSOR_LOADER_ID = 0;
    private Button button;






    private RecyclerView recyclerViewPlayer;
    private PlayerCursorAdapter playerCursorAdapter;



    Button button7;
    TextView textView, textView2, textView3, textView4, textView5, textView6;




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

        getLoaderManager().initLoader(PLAYER_CURSOR_LOADER_ID, null, this);




    }


    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void clickSAIR(View v){
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        if (id == PLAYER_CURSOR_LOADER_ID) {
            return new CursorLoader(this, QuizzContentProvider.PLAYER_URI, DbTablePlayer.ALL_COLUMNS, null, null, null);

        }



        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}

