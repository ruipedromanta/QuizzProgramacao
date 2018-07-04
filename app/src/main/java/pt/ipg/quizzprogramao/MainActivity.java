package pt.ipg.quizzprogramao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    private Button button;

    Button button9, button10, button11;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        /**
         * Aqui faz-se o encaminhamento do button10
         * Para abrir o Score
         */
        button10 = findViewById(R.id.button10);
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openScore();


            }
        });


        /**
         * Aqui faz-se o encaminhamento do button10
         * Para abrir o Quizz
         */
        button9 = findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openQuizz();


            }
        });}








    /**
     * Aqui faz-se o encaminhamento do button exit
     * Para fechhar a apalicação
     */
    public void clickexit(View v){
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }


    /**
     * Aqui faz-se o encaminhamento do openScore
     * Para abrir a Activity_Score.class
     */
    public void openScore() {
        Intent intent = new Intent(this, Activity_Score.class);
        startActivity(intent);
    }



    /**
     * Aqui faz-se o encaminhamento do openQuizz
     * Para abrir a Menu2.class
     */
    public void openQuizz() {
        Intent intent = new Intent(this, Menu2.class);
        startActivity(intent);
    }

}





