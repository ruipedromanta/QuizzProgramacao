package pt.ipg.quizzprogramao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    Button resposta1, resposta2, resposta3, resposta4;

    TextView score, pergunta;

    private Perguntas mPerguntas = new Perguntas();

    private String mResposta;
    private int mScore;
    private int mPerguntasLength = mPerguntas.mPerguntas.length;

    Random r;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
