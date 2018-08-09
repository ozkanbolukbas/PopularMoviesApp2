package movies.raemacias.com.movieappstage2.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import movies.raemacias.com.movieappstage1.R;
import movies.raemacias.com.movieappstage2.FavoriteActivity;
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
    private List<FavoriteEntry> mFavorites; // Cached copy of favorites

    public FavoriteAdapter(FavoriteActivity favoriteActivity) { mInflater = LayoutInflater.from(favoriteActivity); }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.activity_favorite, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (mFavorites != null) {
            FavoriteEntry current = mFavorites.get(position);
            holder.favoriteItemView.setText(current.getId());
        } else {
            // Covers the case of data not being ready yet.
            holder.favoriteItemView.setText("No Favorites Added");
        }
    }

    public void setFavoriteItems(List<FavoriteEntry> favorites){
        mFavorites = favorites;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mFavorites has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mFavorites != null)
            return mFavorites.size();
        else return 0;
    }

}

