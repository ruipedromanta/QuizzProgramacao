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


public class CategoriaPlanetas extends AppCompatActivity {

    Button resposta1, resposta2, resposta3, resposta4;

    TextView score, pergunta;
    private PerguntasPlanetas Perguntas = new PerguntasPlanetas();

        private String mResposta;
        private int mScore;
        private int PerguntasLength = Perguntas.Perguntas.length;
        Random r;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main2);

             r = new Random();

            resposta1 =  findViewById(R.id.resposta1);
            resposta2 =  findViewById(R.id.resposta2);
            resposta3 =  findViewById(R.id.resposta3);
            resposta4 =  findViewById(R.id.resposta4);

            score =  findViewById(R.id.score);
            pergunta =  findViewById(R.id.pergunta);



            /**
             * Aqui poe o Score no ecrã
             */
            score.setText("Score:  " + mScore);

            updatePergunta(r.nextInt(PerguntasLength));


            /**
             * Aqui faz-se a validação da resposta certa ou então faz o game over onde depois vai aparecer o alerta
             * Se a pergunta estiver certa adiciona 1 ao score
             * e faz o update de uma nova pergunta
             * Se errar a pergunta
             * acaba o jogo
             */
            resposta1.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {
                    if(resposta1.getText() == mResposta) {
                        mScore++;
                        score.setText("Score:  " + mScore);
                        updatePergunta(r.nextInt(PerguntasLength));
                    } else {
                        gameOver();
                    }
                }
            });



            /**
             * Aqui faz-se a validação da resposta certa ou então faz o game over onde depois vai aparecer o alerta
             * Se a pergunta estiver certa adiciona 1 ao score
             * e faz o update de uma nova pergunta
             * Se errar a pergunta
             * acaba o jogo
             */
            resposta2.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {
                    if(resposta2.getText() == mResposta) {
                        mScore++;
                        score.setText("Score:  " + mScore);
                        updatePergunta(r.nextInt(PerguntasLength));
                    } else {
                        gameOver();
                    }
                }
            });

            /**
             * Aqui faz-se a validação da resposta certa ou então faz o game over onde depois vai aparecer o alerta
             * Se a pergunta estiver certa adiciona 1 ao score
             * e faz o update de uma nova pergunta
             * Se errar a pergunta
             * acaba o jogo
             */
            resposta3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if(resposta3.getText() == mResposta) {
                        mScore++;
                        score.setText("Score:  " + mScore);
                        updatePergunta(r.nextInt(PerguntasLength));
                    } else {
                        gameOver();
                    }
                }
            });

            /**
             * Aqui faz-se a validação da resposta certa ou então faz o game over onde depois vai aparecer o alerta
             * Se a pergunta estiver certa adiciona 1 ao score
             * e faz o update de uma nova pergunta
             * Se errar a pergunta
             * acaba o jogo
             */
            resposta4.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if(resposta4.getText() == mResposta) {
                        mScore++;
                        score.setText("Score: " + mScore);
                        updatePergunta(r.nextInt(PerguntasLength));
                    } else {
                        gameOver();
                    }
                }
            });

        }



    /**
     * Aqui faz-se o update da pergunta a ser mostrada
     */
    private void updatePergunta(int num) {
        pergunta.setText(Perguntas.getPergunta(num));
        resposta1.setText(Perguntas.getEscolha1(num));
        resposta2.setText(Perguntas.getEscolha2(num));
        resposta3.setText(Perguntas.getEscolha3(num));
        resposta4.setText(Perguntas.getEscolha4(num));

        mResposta = Perguntas.getRespostaCorreta(num);


    }


    /**
     * Aqui faz-se o o alerta que aparece quando se falha uma pergunta
     * Õu
     * se Sai do jogo ou então entra-se num jogo novo da mesma categoria
     */
    private void gameOver(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CategoriaPlanetas.this);
        alertDialogBuilder
                .setMessage("Perdeste! O teu score é " +mScore + " pontos.")
                .setCancelable(false)
                .setPositiveButton("NOVO JOGO",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                startActivity(new Intent(getApplicationContext(),CategoriaPlanetas.class));
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







