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




        Button resposta11, resposta10, resposta9, resposta8;
        TextView pergunta3, score3;


        private PerguntasDesporto PerguntasDesporto = new PerguntasDesporto();

        private  String RespostaDesporto;
        private  int DScore;
        private int PerguntasDesportoLength = PerguntasDesporto.PerguntasDesporto.length;
        Random r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_desporto);



            r = new Random();

            resposta11 = findViewById(R.id.resposta11);
            resposta10 = findViewById(R.id.resposta10);
            resposta9 = findViewById(R.id.resposta9);
            resposta8 = findViewById(R.id.resposta8);

            score3 = findViewById(R.id.score3);
            pergunta3 = findViewById(R.id.pergunta3);

            score3.setText("Score:  " + DScore);

            updatePerguntaDesporto(r.nextInt(PerguntasDesportoLength));

            resposta11.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (resposta11.getText() == RespostaDesporto) {
                        DScore++;
                        score3.setText("Score:  " + DScore);
                        updatePerguntaDesporto(r.nextInt(PerguntasDesportoLength));
                    } else {
                        gameOver();
                    }
                }
            });

            resposta10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (resposta10.getText() == RespostaDesporto) {
                        DScore++;
                        score3.setText("Score:  " + DScore);
                        updatePerguntaDesporto(r.nextInt(PerguntasDesportoLength));
                    } else {
                        gameOver();
                    }
                }
            });


            resposta9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (resposta9.getText() == RespostaDesporto) {
                        DScore++;
                        score3.setText("Score:  " + DScore);
                        updatePerguntaDesporto(r.nextInt(PerguntasDesportoLength));
                    } else {
                        gameOver();
                    }
                }
            });

            resposta8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (resposta8.getText() == RespostaDesporto) {
                        DScore++;
                        score3.setText("Score:  " + DScore);
                        updatePerguntaDesporto(r.nextInt(PerguntasDesportoLength));
                    } else {
                        gameOver();
                    }
                }
            });


        }


    private void updatePerguntaDesporto(int num) {
        pergunta3.setText(PerguntasDesporto.getPerguntaDesporto(num));
        resposta11.setText(PerguntasDesporto.getEscolha0(num));
        resposta10.setText(PerguntasDesporto.getEscolha1(num));
        resposta9.setText(PerguntasDesporto.getEscolha2(num));
        resposta8.setText(PerguntasDesporto.getEscolha3(num));

        RespostaDesporto = PerguntasDesporto.getRespostaDesportoCorreta(num);
    }




    private void gameOver(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CategoriaDesporto.this);
        alertDialogBuilder
                .setMessage("Perdeste! O teu score é " +DScore + " pontos.")
                .setCancelable(false)
                .setPositiveButton("NOVO JOGO",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                startActivity(new Intent(getApplicationContext(),CategoriaDesporto.class));
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



