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


public class Quizz extends AppCompatActivity {




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
            setContentView(R.layout.activity_main2);

             r = new Random();

            resposta1 = (Button) findViewById(R.id.resposta1);
            resposta2 = (Button) findViewById(R.id.resposta2);
            resposta3 = (Button) findViewById(R.id.resposta3);
            resposta4 = (Button) findViewById(R.id.resposta4);

            score = (TextView) findViewById(R.id.score);
            pergunta = (TextView) findViewById(R.id.pergunta);



            score.setText("Score: " + mScore);

            updatePergunta(r.nextInt(mPerguntasLength));

            resposta1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(resposta1.getText() == mResposta) {
                        mScore++;
                        score.setText("Score: " + mScore);
                        updatePergunta(r.nextInt(mPerguntasLength));
                    } else {
                        gameOver();
                    }
                }
            });
            resposta2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(resposta2.getText() == mResposta) {
                        mScore++;
                        score.setText("Score: " + mScore);
                        updatePergunta(r.nextInt(mPerguntasLength));
                    } else {
                        gameOver();
                    }
                }
            });
            resposta3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(resposta3.getText() == mResposta) {
                        mScore++;
                        score.setText("Score: " + mScore);
                        updatePergunta(r.nextInt(mPerguntasLength));
                    } else {
                        gameOver();
                    }
                }
            });
            resposta4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(resposta4.getText() == mResposta) {
                        mScore++;
                        score.setText("Score: " + mScore);
                        updatePergunta(r.nextInt(mPerguntasLength));
                    } else {
                        gameOver();
                    }
                }
            });

        }

    private void updatePergunta(int num) {
        pergunta.setText(mPerguntas.getPergunta(num));
        resposta1.setText(mPerguntas.getEscolha1(num));
        resposta2.setText(mPerguntas.getEscolha2(num));
        resposta3.setText(mPerguntas.getEscolha3(num));
        resposta4.setText(mPerguntas.getEscolha4(num));

        mResposta = mPerguntas.getRespostaCorreta(num);


    }


    private void gameOver(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Quizz.this);
        alertDialogBuilder
                .setMessage("Perdeste! O teu score é " +mScore + " pontos.")
                .setCancelable(false)
                .setPositiveButton("NOVO JOGO",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                startActivity(new Intent(getApplicationContext(),Quizz.class));
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







