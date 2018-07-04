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
        // Context of the app under test.
         Context appContext = getContext();

        DbQuizzOpenHelper dbQuizzOpenHelper = new DbQuizzOpenHelper(appContext);

        SQLiteDatabase db = dbQuizzOpenHelper.getReadableDatabase();
        assertTrue("Could not open/create quizz database", db.isOpen());
        db.close();
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
        score.setIdPlayer((int) idName);

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
        DbQuizzOpenHelper dbQuizzOpenHelper = new DbQuizzOpenHelper(getContext());

        SQLiteDatabase db = dbQuizzOpenHelper.getWritableDatabase();

        DbTableScore tableScore = new DbTableScore(db);

        Score score = new Score();
        score.setScore(1);

        long idScore = insertScore(tableScore, score);

        DbTableQuestions tableQuestions = new DbTableQuestions(db);

        //(C)RUD
        Questions questions = new Questions();

        questions.setQuestion("Quanto é 1+1?");
        questions.setIdScore((int) idScore);

        long id = tableQuestions.insert(
                DbTableQuestions. getContentValues(questions)
        );
        assertNotEquals("Failed to insert Question", -1, id);


        //C(R)UD
        questions = ReadFirstQuestions(tableQuestions, "Quanto é 1+1?", idScore, id);


        //CR(U)D
        questions.setQuestion("Quanto é 1+5?");

        int rowsAffected = tableQuestions.update(
                DbTableQuestions.getContentValues(questions),
                DbTableQuestions._ID + "=?",
                new String[]{Long.toString(id)}
        );
        assertEquals("Failed to update Questions", 1, rowsAffected);

        //C(R)UD
        questions = ReadFirstQuestions(tableQuestions, "Quanto é 1+1", idScore, id);

        //CRU(D)
        rowsAffected = tableQuestions.delete(
                DbTableQuestions._ID + "=?",
                new String[]{Long.toString(id)}
        );
        assertEquals("Failed to delete Questions", 1, rowsAffected);

        Cursor cursor = tableQuestions.query(DbTableQuestions.ALL_COLUMNS, null, null,null,null,null);
        assertEquals("Questions found after delete ???", 0, cursor.getCount());


    }



    @Test

    public void answersCRUDtest () {
        DbQuizzOpenHelper dbQuizzOpenHelper = new DbQuizzOpenHelper(getContext());

        SQLiteDatabase db = dbQuizzOpenHelper.getWritableDatabase();

        DbTableQuestions tableQuestions = new DbTableQuestions(db);

        Questions questions = new Questions();
        questions.setQuestion("Quanto é 1+1?");

        long idquestion = insertQuestions(tableQuestions, questions);


        DbTableAnswers tableAnswers = new DbTableAnswers(db);


        //(C)RUD

        Answers answers = new Answers();

        answers.setAnswer("Três");
        answers.setIdquestion((int) idquestion);

        long id = tableAnswers.insert(
                DbTableAnswers.getContentValues(answers)
        );

        assertNotEquals("Failed to insert answers", -1, id);

        // C(R)UD
        answers = ReadFirstAnswers(tableAnswers, "Três", idquestion, id);

        // CR(U)D
        answers.setAnswer("Dois");


        int rowsAffected = tableAnswers.update(
                DbTableAnswers.getContentValues(answers),
                DbTableAnswers._ID + "=?",
                new String[]{Long.toString(id)}
        );
        assertEquals("Failed to update answers", 1, rowsAffected);

        //C(R)UD
        answers = ReadFirstAnswers(tableAnswers, "Dois", idquestion, id);

        //CRU(D)
        rowsAffected = tableAnswers.delete(
                DbTableAnswers._ID + "=?",
                new String[]{Long.toString(id)}

        );
        assertEquals("Failed to delete answers", 1, rowsAffected);

        Cursor cursor = tableAnswers.query(DbTableAnswers.ALL_COLUMNS ,null, null, null, null, null);
        assertEquals("Answers found after delete ???", 0, cursor.getCount());


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
        player.setBest_score(9);
        int rowsAffected = tablePlayer.update(
                DbTablePlayer.getContentValues(player),
                DbTablePlayer._ID + "=?",
                new String[]{Long.toString(id)}

        );



        assertEquals("Failed to update player", 1, rowsAffected);

        // query/read C(R)UD

        player = ReadFirstPlayer(tablePlayer, 9, "Pedro", id);

        //delete CRU(D)
        rowsAffected = tablePlayer.delete(
                DbTablePlayer._ID + "=?",
                new String[]{Long.toString(id)}
        );

        assertEquals("Failed to delete player", 1, rowsAffected);

        Cursor cursor = tablePlayer.query(DbTablePlayer.ALL_COLUMNS, null,null,null,null,null);
        assertEquals("Players found after delete ????", 0, cursor.getCount());


    }



    private long insertScore(DbTableScore tableScore, Score score) {
        long id = tableScore.insert(
                DbTableScore.getContentValues(score)
        );
        assertNotEquals("Failed to insert Score", -1, id);

        return id;

    }

    private long insertQuestions(DbTableQuestions tableQuestions, Questions questions) {
        long id = tableQuestions.insert(
                DbTableQuestions.getContentValues(questions)
        );
    assertNotEquals("Failed to insert Questions", -1,id);

    return id;
    }

    private Questions ReadFirstQuestions(DbTableQuestions tableQuestions, String expectedQuestion, long expectedScore, long expectedid) {
        Cursor cursor = tableQuestions.query(DbTableQuestions.ALL_COLUMNS, null, null, null, null, null);
    assertEquals("Failed to read questions", 1, cursor.getCount());

    assertTrue("Failed to read the first question", cursor.moveToNext());

    Questions questions = DbTableQuestions.getCurrentQuestionsFromCursor(cursor);

    assertEquals("Incorrect Questions question", expectedQuestion, questions.getQuestion());
    assertEquals("Incorrect questions score", expectedScore, questions.getIdScore());
    assertEquals("Incorrect questions id", expectedid, questions.getId());


    return questions;
    }


    private Answers ReadFirstAnswers(DbTableAnswers tableAnswers, String expectedAnswer , long expectedidquestion, long expectedid) {
        Cursor cursor = tableAnswers.query(DbTableAnswers.ALL_COLUMNS, null,null,null,null,null);
        assertEquals("Failed to read answers", 1, cursor.getCount());

        assertTrue("Failed to read first answer", cursor.moveToNext());

        Answers answers = DbTableAnswers.getCurrentAnswersFromCursor(cursor);

        assertEquals("Incorrect Answers answer", expectedAnswer, answers.getAnswer());
        assertEquals("Incorrect answers ", expectedidquestion, answers.getIdquestion());
        assertEquals("Incorrect answers id", expectedid, answers.getId());

        return answers;
    }


    private Score ReadFirstScore(DbTableScore tableScore, int expectedScore, long expectedidName, long expectedid) {
        Cursor cursor = tableScore.query(DbTableScore.ALL_COLUMNS, null,null,null,null,null);
        assertEquals("Failed to read Score", 1, cursor.getCount());

        assertTrue("Failed to read first score", cursor.moveToNext());

        Score score = DbTableScore.getCurrentScoreFromCursor(cursor);


        assertEquals("Incorrect score", expectedScore, score.getScore());
        assertEquals("Incorrect score player", expectedidName, score.getIdPlayer());
        assertEquals("Incorrect score id", expectedid, score.getId());

        return score;


    }

    private long insertPlayer(DbTablePlayer tablePlayer, Player player) {
        long id = tablePlayer.insert(
                DbTablePlayer.getContentValues(player)
        );

        assertNotEquals("Failed to insert player", -1, id);

        return id;
    }

    @NonNull
    private Player ReadFirstPlayer(DbTablePlayer tablePlayer, long expectedScore, String expectedName, long expectedId) {
        Cursor cursor = tablePlayer.query(DbTablePlayer.ALL_COLUMNS, null, null, null, null, null);
        assertEquals("Failed to read player", 1, cursor.getCount());

        assertTrue("Failed to read the first player", cursor.moveToNext());

        Player player = DbTablePlayer.getCurrentPlayerFromCursor(cursor);
        assertEquals("Incorrect best score", expectedScore, player.getBest_score());
        assertEquals("Incorrect player name", expectedName, player.getName());
        assertEquals("Incorrect player id", expectedId, player.getId());

        return player;
    }

    private Context getContext() {
        return  InstrumentationRegistry.getTargetContext();}
}
