package pt.ipg.quizzprogramao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */


@RunWith(AndroidJUnit4.class)
public class QuizzDbTests {
    @Before
    public void setUp(){
        getContext().deleteDatabase(DbQuizzOpenHelper.DATABASE_NAME); }

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

        player = ReadFirstPlayer(tablePlayer, 7,"Pedr", id);

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

        long idName = insertPlayer(tablePlayer, player);
        DbTableScore tableScore = new DbTableScore(db);

        //insert/create (C)RUD

        Score score = new Score();

        score.setScore(14);
        score.setIdName((int) idName);

        long id = tableScore.insert(
                DbTableScore.getContentValues(score)
        );

        assertNotEquals("Failed to insert score", -1, id);
        
        
        // query/read C(R)UD 
        score = ReadFirstScore(tableScore, 14, idName, id);


        // update CR(U)D

        score.setScore(15);

        int rowsAffected = tableScore.update(
                DbTableScore.getContentValues(score),
                DbTableScore._ID + "=?",
                new String[]{Long.toString(id)}
        );

        //query/read C(R)UD
        score = ReadFirstScore(tableScore, 15 , idName, id);


        //delete CRU(D)
        rowsAffected = tableScore.delete(
                DbTableScore._ID + "=?",
                new String[]{Long.toString(id)}
        );
        assertEquals("Failed to update Score",1, rowsAffected);


        Cursor cursor = tableScore.query(DbTableScore.ALL_COLUMNS, null,null,null,null,null);
        assertEquals("Score found after delete ???", 0, cursor.getCount());


    }

    @Test
    public void questionsCRUDtest() {





    };

    DbTableQuestions tableQuestions = new DbTableQuestions(db);


    //create/Insert

    Questions questions = new Questions();

    questions.set


























    private Score ReadFirstScore(DbTableScore tableScore, int expectedScore, long expectedidName, long expectedid) {
        Cursor cursor = tableScore.query(DbTableScore.ALL_COLUMNS, null,null,null,null,null);
        assertEquals("Failed to read Score", 1, cursor.getCount());

        assertTrue("Failed to read first score", cursor.moveToNext());

        Score score = DbTableScore.getCurrentScoreFromCursor(cursor);


        assertEquals("Incorrect score", expectedScore, score.getScore());
        assertEquals("Incorrect score player", expectedidName, score.getIdName());
        assertEquals("Incorrect score id", expectedid, score.getId());

        return score;


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

    private Context getContext() {return  InstrumentationRegistry.getTargetContext();}
}
