package pt.ipg.quizzprogramao;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class CategoriaDesporto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_desporto);



        Button resposta7, resposta6, resposta5, resposta;
        TextView pergunta2 , score2;


        private PerguntasDesporto PerguntasDesporto = new PerguntasDesporto();

        private final String RespostaDesporto;
        private final int DScore;
        private final int PerguntasDesportoLength = PerguntasDesporto.PerguntaDesporto.length;
        Random r;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_categoria_historia);


            r = new Random();

            resposta7 = findViewById(R.id.resposta7);
            resposta6 = findViewById(R.id.resposta6);
            resposta5 = findViewById(R.id.resposta5);
            resposta = findViewById(R.id.resposta);

            score2 = findViewById(R.id.score2);
            pergunta2 = findViewById(R.id.pergunta2);

            score2.setText("Score:  " + DScore);

            updatePerguntaHistoria(r.nextInt(PerguntasDesportoLength));

            resposta7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (resposta7.getText() == RespostaDesporto) {
                        DScore++;
                        score2.setText("Score:  " + DScore);
                        updatePerguntaHistoria(r.nextInt(PerguntasDesportoLength));
                    } else {
                        gameOver();
                    }
                }
            });

            resposta6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (resposta6.getText() == RespostaHistoria) {
                        HScore++;
                        score2.setText("Score:  " + HScore);
                        updatePerguntaHistoria(r.nextInt(PerguntasHistoriaLength));
                    } else {
                        gameOver();
                    }
                }
            });


            resposta5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (resposta5.getText() == RespostaHistoria) {
                        HScore++;
                        score2.setText("Score:  " + HScore);
                        updatePerguntaHistoria(r.nextInt(PerguntasHistoriaLength));
                    } else {
                        gameOver();
                    }
                }
            });

            resposta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (resposta.getText() == RespostaHistoria) {
                        HScore++;
                        score2.setText("Score:  " + HScore);
                        updatePerguntaHistoria(r.nextInt(PerguntasHistoriaLength));
                    } else {
                        gameOver();
                    }
                }
            });






        }



    private void updatePerguntaHistoria(int num) {
        pergunta2.setText(PerguntasHistoria.getPerguntaHistoria(num));
        resposta7.setText(PerguntasHistoria.getEscolha0(num));
        resposta6.setText(PerguntasHistoria.getEscolha1(num));
        resposta5.setText(PerguntasHistoria.getEscolha2(num));
        resposta.setText(PerguntasHistoria.getEscolha3(num));

        RespostaHistoria = PerguntasHistoria.getRespostaHistoriaCorreta(num);
    }


    private void gameOver(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CategoriaHistoria.this);
        alertDialogBuilder
                .setMessage("Perdeste! O teu score é " +HScore + " pontos.")
                .setCancelable(false)
                .setPositiveButton("NOVO JOGO",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                startActivity(new Intent(getApplicationContext(),CategoriaHistoria.class));
                            }
                        })
                .setNegativeButton("SAIR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }
}

    }
}
