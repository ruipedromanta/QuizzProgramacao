package pt.ipg.quizzprogramao;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by ruima on 19/06/2018.
 */

public class PlayerCursorAdapter extends RecyclerView.Adapter<PlayerCursorAdapter.PlayerViewHolder> {
    private Context context;
    private Cursor cursor = null;
    private View.OnClickListener viewHolderClickListener = null;
    private int lastPlayerClicked = -1;

    public PlayerCursorAdapter(Context context) {
        this.context = context;

    }

    public void refreshData (Cursor cursor) {
        if (this.cursor!= cursor) {
            this.cursor = cursor;
            notifyDataSetChanged();
        }
    }

    public void setViewHolderClickListener(View.OnClickListener viewHolderClickListener) {
        this.viewHolderClickListener = viewHolderClickListener;
    }

    public int getLastPlayerClicked() {
        return lastPlayerClicked;
    }


    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(context).inflate(R.layout.item_player, parent, false);

        return new PlayerViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerCursorAdapter.PlayerViewHolder holder, int position) {
        cursor.moveToPosition(position);
        Player player = DbTablePlayer.getCurrentPlayerFromCursor(cursor);
        holder.setPlayer(player);
    }

    @Override
    public int getItemCount() {
       if (cursor == null)return 0;

       return cursor.getCount();
    }

    public class PlayerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textViewPlayer;
        private TextView textViewScore;
        private int playerId;


        public PlayerViewHolder (View itemView) {
            super(itemView);

            textViewPlayer = (TextView) itemView.findViewById(R.id.textViewPlayer);
            textViewScore = (TextView) itemView.findViewById(R.id.textViewScore);

            itemView.setOnClickListener(this);
        }

        public void setPlayer (Player player) {
            textViewPlayer.setText(player.getName());
            textViewScore.setText(String.format("%2f", player.getBest_score()));
            playerId = player.getId();

        }


        public void onClick (View v) {
            int position = getAdapterPosition();

            if (position == RecyclerView.NO_POSITION) {
                return;
            }

            if (viewHolderClickListener != null) {
                lastPlayerClicked = playerId;
                viewHolderClickListener.onClick(v);
            }
        }




    }


}

