package pt.ipg.quizzprogramao;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ruima on 19/06/2018.
 */

public class PlayerCursorAdapter extends RecyclerView.Adapter<PlayerCursorAdapter.ScoreViewHolder> {
    private Context context;
    private Cursor cursor = null;
    private View.OnClickListener viewHolderClickListener = null;
    private int lastScoreClicked = -1;

    public PlayerCursorAdapter(Context context) {
        this.context = context;

    }

    public void refreshData (Cursor cursor) {
        if (this.cursor!= cursor) {
            this.cursor = cursor;
            notifyDataSetChanged();
        }
    }



    @NonNull
    @Override
    public PlayerCursorAdapter.ScoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerCursorAdapter.ScoreViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
