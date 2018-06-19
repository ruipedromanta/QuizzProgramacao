package pt.ipg.quizzprogramao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;


public class Quizz extends AppCompatActivity {




        Button resposta1, resposta2, resposta3, resposta4;

        TextView score, pergunta;


        private Perguntas mPerguntas = new Perguntas();

        private String mResposta;



        Random r;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main2);
        }
}



