package pt.ipg.quizzprogramao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;



public class MainActivity extends AppCompatActivity {
    private Button button;

    Button button9, button10, button11;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        button10 = findViewById(R.id.button10);
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openScore();


            }
        });

        button9 = findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openQuizz();


            }
        });
    }


    public void openScore() {
        Intent intent = new Intent(this, Score.class);
        startActivity(intent);
    }


    public void openQuizz() {
        Intent intent = new Intent(this, Quizz.class);
        startActivity(intent);
    }

}





