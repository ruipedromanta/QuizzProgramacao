package pt.ipg.quizzprogramao;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

/**
 * Created by ruima on 21/06/2018.
 */

public class EditPlayerActivity  extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{
        private EditText editTextName;
        private Player player;



    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_player);

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


        player = DbTablePlayer.getCurrentPlayerFromCursor(cursorPlayer);
        editTextName.setText(player.getName());


    }

      public void Cancel(View view) {
            finish();
          }

    public void Save(View view) {

        player.setName(editTextName.getText().toString());

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
        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }
}



