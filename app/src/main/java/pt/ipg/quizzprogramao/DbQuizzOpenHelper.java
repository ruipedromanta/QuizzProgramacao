package pt.ipg.quizzprogramao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ruima on 01/06/2018.
 */

public class DbQuizzOpenHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "quizz.db";
    public static final int DATABASE_VERSION = 1;
    public static final boolean PRODUCTION = false;

    public DbQuizzOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        DbTableScore dbTableScore = new DbTableScore(db);
        dbTableScore.create();

        DbTablePlayer dbTablePlayer = new DbTablePlayer(db);
        dbTablePlayer.create();

        DbTableQuestions dbTableQuestions = new DbTableQuestions(db);
        dbTableQuestions.create();

        DbTableAnswers dbTableAnswers = new DbTableAnswers(db);
        dbTableAnswers.create();




        if (!PRODUCTION) {
            seed(db);
        }
    }

    private void seed(SQLiteDatabase db) {
        DbTablePlayer dbTablePlayer = new DbTablePlayer(db);


        Player player = new Player();
        player.setName("Rui");
        player.setBest_score(8);
        int idPlayerRui = (int) dbTablePlayer.insert(DbTablePlayer.getContentValues(player));

        player = new Player();
        player.setName("Pedro");
        player.setBest_score(6);
        int idPlayerPedro = (int) dbTablePlayer.insert(DbTablePlayer.getContentValues(player));

        player = new Player();
        player.setName("Juliana");
        player.setBest_score(7);
        int idPlayerJuliana = (int) dbTablePlayer.insert(DbTablePlayer.getContentValues(player));


        DbTableScore dbTableScore = new DbTableScore(db);

        Score score = new Score();
        score.setScore(8);
        score.setIdPlayer(idPlayerRui);
        dbTableScore.insert(DbTableScore.getContentValues(score));

        score = new Score();
        score.setScore(6);
        score.setIdPlayer(idPlayerPedro);
        dbTableScore.insert(DbTableScore.getContentValues(score));

        score = new Score();
        score.setScore(7);
        score.setIdPlayer(idPlayerJuliana);
        dbTableScore.insert(DbTableScore.getContentValues(score));

        score = new Score();
        score.setScore(1);
        int idScore1 = (int) dbTableScore.insert(DbTableScore.getContentValues(score));


        DbTableQuestions dbTableQuestions = new DbTableQuestions(db);

        Questions questions = new Questions();
        questions.setQuestion("Qual é a capital de Portugal?");
        questions.setIdScore(idScore1);
        int idQuestion1 = (int) dbTableQuestions.insert(DbTableQuestions.getContentValues(questions));

        questions = new Questions();
        questions.setQuestion("Qual foi o primeiro rei de Portugal?");
        questions.setIdScore(idScore1);
        int idQuestion2 = (int) dbTableQuestions.insert(DbTableQuestions.getContentValues(questions));

        questions = new Questions();
        questions.setQuestion("25 de Abril, representa que feriado?");
        questions.setIdScore(idScore1);
        int idQuestion3 = (int)dbTableQuestions.insert(DbTableQuestions.getContentValues(questions));


        DbTableAnswers dbTableAnswers = new DbTableAnswers(db);

        Answers answers = new Answers();
        answers.setAnswer("Guimarães");
        answers.setIdquestion(idQuestion1);

        answers = new Answers();
        answers.setAnswer("Lisboa");
        answers.setIdquestion(idQuestion1);


        answers = new Answers();
        answers.setAnswer("Porto");
        answers.setIdquestion(idQuestion1);

        answers = new Answers();
        answers.setAnswer("Coimbra");
        answers.setIdquestion(idQuestion1);

        answers = new Answers();
        answers.setAnswer("D.Sebastião");
        answers.setIdquestion(idQuestion2);

        answers = new Answers();
        answers.setAnswer("D.João");
        answers.setIdquestion(idQuestion2);

        answers = new Answers();
        answers.setAnswer("D.Afonso Henriques");
        answers.setIdquestion(idQuestion2);

        answers = new Answers();
        answers.setAnswer("D.Sancho");
        answers.setIdquestion(idQuestion2);








    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
