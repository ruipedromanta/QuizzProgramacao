package pt.ipg.quizzprogramao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu2 extends AppCompatActivity {

    Button button13, button4, button5, button6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu2);





        /**
         * Aqui faz-se o encaminhamento do button13
         * Para abrir a MainActivity
         */
        button13 = findViewById(R.id.button13);
        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainAvtivity();


            }
        });


        /**
         * Aqui faz-se o encaminhamento do button4
         * Para abrir o CategoriaPlanetas
         */
        button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCategoriaPlanetas();
            }
        });



        /**
         * Aqui faz-se o encaminhamento do button5
         * Para abrir a CategoriaHistoria
         */
        button5 = findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCategoriaHistoria();
            }
        });


        /**
         * Aqui faz-se o encaminhamento do button6
         * Para abrir a CategoriaDesporto
         */
        button6 = findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCategoriaDesporto();
            }
        });

    }



    /**
     * Aqui faz-se o encaminhamento do openCategoriaDesporto
     * Para abrir a CategoriaDesporto.class
     */
    private void openCategoriaDesporto() {
        Intent intent = new Intent(this, CategoriaDesporto.class);
        startActivity(intent);
    }



    /**
     * Aqui faz-se o encaminhamento do openCategoriaPlanetas
     * Para abrir a CategoriaPlanetas.class
     */
    public void openCategoriaPlanetas() {
        Intent intent = new Intent(this, CategoriaPlanetas.class);
        startActivity(intent);
    }


    /**
     * Aqui faz-se o encaminhamento do openCategoriaDesporto
     * Para abrir a CategoriaDesporto.class
     */
    public void openMainAvtivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }



    /**
     * Aqui faz-se o encaminhamento do openCategoriaHistoria
     * Para abrir a CategoriaHistoria.class
     */
    public void openCategoriaHistoria() {
        Intent intent = new Intent(this, CategoriaHistoria.class);
        startActivity(intent);
    }


}
