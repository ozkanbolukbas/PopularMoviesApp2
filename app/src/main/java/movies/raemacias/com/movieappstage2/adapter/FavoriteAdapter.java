package movies.raemacias.com.movieappstage2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import movies.raemacias.com.movieappstage1.R;
import movies.raemacias.com.movieappstage2.model.Result;

//This code came from CodeLabs - Room with a View

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {


        class ViewHolder extends RecyclerView.ViewHolder {
            private final TextView favoriteItemView;

            private ViewHolder(View itemView) {
                super(itemView);
                favoriteItemView = itemView.findViewById(R.id.original_title_tv);
            }
        }

        private final LayoutInflater mInflater;
        private List<Result> favoriteResults; // Cached copy of words

        public FavoriteAdapter(Context context) { mInflater = LayoutInflater.from(context); }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = mInflater.inflate(R.layout.activity_main, parent, false);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            if (favoriteResults != null) {
                Result current = favoriteResults.get(position);
                holder.favoriteItemView.setText(current.getOriginalTitle());
            } else {
                // Covers the case of data not being ready yet.
                holder.favoriteItemView.setText("No favorite.");
            }
        }

        public void setId(List<Result> favorites){
            favoriteResults = favorites;
            notifyDataSetChanged();
        }

        // getItemCount() is called many times, and when it is first called,
        // mWords has not been updated (means initially, it's null, and we can't return null).
        @Override
        public int getItemCount() {
            if (favoriteResults != null)
                return favoriteResults.size();
            else return 0;
        }
    }

