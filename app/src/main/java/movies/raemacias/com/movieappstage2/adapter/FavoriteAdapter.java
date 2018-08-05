package movies.raemacias.com.movieappstage2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import movies.raemacias.com.movieappstage1.R;
import movies.raemacias.com.movieappstage2.model.FavoriteEntry;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {

    private List<FavoriteEntry> mFavorites;
    final private ItemClickListener mItemClickListener;
    private Context mContext;

    public List<FavoriteEntry> getFavorites() {
        return mFavorites;
    }

    public void setFavorites(List<FavoriteEntry> favorites) {
        mFavorites = favorites;
    }

    public FavoriteAdapter(Context context, ItemClickListener listener) {
        mContext = context;
        mItemClickListener = listener;
    }

    @NonNull
    @Override
    public FavoriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_favorite, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final FavoriteEntry favoriteEntry = mFavorites.get(holder.getAdapterPosition());

    }


    @Override
    public int getItemCount() {
        return 0;
    }

    public interface ItemClickListener {
        void onItemClickListener(String movie_id);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}

