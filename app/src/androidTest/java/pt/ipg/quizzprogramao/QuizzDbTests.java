package pt.ipg.quizzprogramao;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getContext;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */


@RunWith(AndroidJUnit4.class)
public class QuizzDbTests {
    @Before
    public void setUp(){ getContext().deleteDatabase(DbQuizzOpenHelper.DATABASE_NAME);}

    @Test
    public  void openQuizzDbTests() {
        
         Context appContext = getContext();

        DbQuizzOpenHelper dbQuizzOpenHelper = new DbQuizzOpenHelper(appContext);

        SQLiteDatabase db = dbQuizzOpenHelper.getReadableDatabase();
        assertTrue("Could not open/create quizz database", db.isOpen());
        db.close();
    }

    @Test
    public void playerCRUDtest() {
        DbQuizzOpenHelper dbQuizzOpenHelper = new DbQuizzOpenHelper(getContext());

        SQLiteDatabase db = dbQuizzOpenHelper.getWritableDatabase();

        DbTablePlayer tablePlayer = new DbTablePlayer(db);

        Player player = new Player();
        player.setName("Pedr");
        player.setBest_score(7);

        //Insert/create (C)RUD

        long id = insertPlayer(tablePlayer, player);

        //query/read C(R)UD

        player = ReadFirstPlayer(tablePlayer, 7,"Pedr", id,);

        //update CR(U)D
        player.setName("Pedro");
        int rowsAffected = tablePlayer.update(
                DbTablePlayer.getContentValues(player),
                DbTablePlayer._ID + "=?",
                new String[]{Long.toString(id)}

        );

        assertEquals("Failed to update player", 1, rowsAffected);

        //delete CRU(D)
        rowsAffected = tablePlayer.delete(
                DbTablePlayer._ID + "=?",
                new String[]{Long.toString(id)}
        );

        assertEquals("Failed to delete player", 1, rowsAffected);

        Cursor cursor = tablePlayer.query(DbTablePlayer.ALL_COLUMNS, null,null,null,null,null);
        assertEquals("Players found after delete ????",0, cursor.getCount());


    }


    @Test

    public void scoreCRUDtest () {
        DbQuizzOpenHelper dbQuizzOpenHelper = new DbQuizzOpenHelper(getContext());

        SQLiteDatabase db = dbQuizzOpenHelper.getWritableDatabase();

        DbTablePlayer tablePlayer = new DbTablePlayer(db);

        Player player = new Player();
        player.setName("Rui");


    }

    private long insertPlayer(DbTablePlayer tablePlayer,Player player) {
        long id = tablePlayer.insert(
                DbTablePlayer.getContentValues(player)
        );

        assertNotEquals("Failed to insert a player", -1, id);

        return id;
    }

    @NonNull
    private Player ReadFirstPlayer(DbTablePlayer tablePlayer, long expected, String expectedName, long expectedId) {
        Cursor cursor = tablePlayer.query(DbTablePlayer.ALL_COLUMNS, null, null, null, null, null);
        assertEquals("Failed to read player", 1, cursor.getCount());

        assertTrue("Failed to read the first player", cursor.moveToNext());

        Player player = DbTablePlayer.getCurrentPlayerFromCursor(cursor);
        assertEquals("Incorret best score", expected, player.getBest_score());
        assertEquals("Incorrect player name", expectedName, player.getName());
        assertEquals("Incorrect player id", expectedId, player.getId());

        return player;
    }
}
