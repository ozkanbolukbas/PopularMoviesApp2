package movies.raemacias.com.movieappstage2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import movies.raemacias.com.movieappstage1.R;
import movies.raemacias.com.movieappstage2.model.FavoriteEntry;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {


        class ViewHolder extends RecyclerView.ViewHolder {
            private final TextView favoriteItemView;

            private ViewHolder(View itemView) {
                super(itemView);
                favoriteItemView = itemView.findViewById(R.id.original_title_tv);
            }
        }

        private final LayoutInflater mInflater;
        private List<FavoriteEntry> favoriteEntry; // Cached copy of words

        public FavoriteAdapter(Context context) { mInflater = LayoutInflater.from(context); }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = mInflater.inflate(R.layout.activity_favorite, parent, false);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            if (favoriteEntry != null) {
                FavoriteEntry current = favoriteEntry.get(position);
                holder.favoriteItemView.setText(current.getOriginal_title());
            } else {
                // Covers the case of data not being ready yet.
                holder.favoriteItemView.setText("No favorite.");
            }
        }

        public void setId(List<FavoriteEntry> favorites){
            favoriteEntry = favorites;
            notifyDataSetChanged();
        }

        // getItemCount() is called many times, and when it is first called,
        // mWords has not been updated (means initially, it's null, and we can't return null).
        @Override
        public int getItemCount() {
            if (favoriteEntry != null)
                return favoriteEntry.size();
            else return 0;
        }
    }

//    public FavoriteAdapter(Context context) {
//        this.context = context;
//        this.favoriteEntry = favoriteEntry;
//    }
//
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(context).inflate(R.layout.activity_favorite, parent, false);
//        return new ViewHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//        FavoriteEntry f = favoriteEntry.get(position);
//        holder.favoriteItemView.setText(f.getId());
//
//        if (favoriteEntry != null) {
//            FavoriteEntry current = favoriteEntry.get(position);
//            holder.favoriteItemView.setText(current.getId());
////            holder.favoriteItemView.setText(current.getId());
//        } else {
//            // Covers the case of data not being ready yet.
//            holder.favoriteItemView.setText("No Favorites Added");
//        }
//    }
//
//    public void setFavoriteItems(List<FavoriteEntry> favorites){
//        favoriteEntry = favorites;
//        notifyDataSetChanged();
//    }
//
//    // getItemCount() is called many times, and when it is first called,
//    // mFavorites has not been updated (means initially, it's null, and we can't return null).
//    @Override
//    public int getItemCount() {
//        if (favoriteEntry != null)
//            return favoriteEntry.size();
//        else return 0;
//    }
//
//}

