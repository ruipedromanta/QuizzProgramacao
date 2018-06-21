package pt.ipg.quizzprogramao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by ruima on 21/06/2018.
 */

public class EditPlayerActivity  extends AppCompatActivity{



    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_player);

    }

      public void Cancel(View view) {
            finish();
          }

    public void Save(View view) {

    }
}



