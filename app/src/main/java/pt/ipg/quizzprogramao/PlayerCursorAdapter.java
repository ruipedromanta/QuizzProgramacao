package pt.ipg.quizzprogramao;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
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


    /**
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     * <p>
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     * <p>
     * The new ViewHolder will be used to display items of the adapter using
     * {@link #onBindViewHolder(ViewHolder, int, List)}. Since it will be re-used to display
     * different items in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary {@link View#findViewById(int)} calls.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     * @see #getItemViewType(int)
     * @see #onBindViewHolder(ViewHolder, int)
     */

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(context).inflate(R.layout.item_player, parent, false);

        return new PlayerViewHolder(item);
    }


    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
     * position.
     * <p>
     * Note that unlike {@link ListView}, RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the <code>position</code> parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use {@link ViewHolder#getAdapterPosition()} which will
     * have the updated adapter position.
     * <p>
     * Override {@link #onBindViewHolder(ViewHolder, int, List)} instead if Adapter can
     * handle efficient partial bind.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */

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

