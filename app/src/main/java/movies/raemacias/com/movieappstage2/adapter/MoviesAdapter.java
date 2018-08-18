package movies.raemacias.com.movieappstage2.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.like.LikeButton;
import com.like.OnLikeListener;
import com.squareup.picasso.Picasso;

import java.util.List;

import movies.raemacias.com.movieappstage1.R;
import movies.raemacias.com.movieappstage2.DetailActivity;
import movies.raemacias.com.movieappstage2.model.Result;


public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieListViewHolder> implements OnLikeListener{

    // This code has been adapted from www.learn2crack.com
    // and Simplified Coding

    private String RESULTS = "results";

    private final Context context;
    private List<Result> results;

    public MoviesAdapter(Context context, List<Result> results) {
        this.context = context;
        this.results = results;
    }

    //    //This adapter code is based on Simplified Coding tutorials.
    @NonNull
    @Override
    public MoviesAdapter.MovieListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_poster, null);

        MovieListViewHolder holder = new MovieListViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapter.MovieListViewHolder holder, int position) {
        holder.textViewOriginalTitle.setText(results.get(position).getOriginalTitle());
        String poster = "https://image.tmdb.org/t/p/w500" + results.get(position).getPosterPath();

        Picasso.get()
                .load(poster)
                .placeholder(R.drawable.popcorn)
                .error(R.drawable.error_placeholder)
                .into(holder.imageViewMovieListItem);
    }

    public void setId(List<Result> favoriteResults){
        results = favoriteResults;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        //We need the object model to be passed to this layer.
        return results.size();
    }

    @Override
    public void liked(LikeButton likeButton) {

    }

    @Override
    public void unLiked(LikeButton likeButton) {

    }

    class MovieListViewHolder extends RecyclerView.ViewHolder {

        final TextView textViewOriginalTitle;
        final TextView textViewVoteAverage;
        final TextView textViewPlotSynopsis;
        final TextView textViewReleaseDate;
        final ImageView imageViewMovieListItem;
        final ImageView imageViewMoviethumb;
        final CardView cardViewMovieList;
        final LinearLayout linearLayout;

        MovieListViewHolder(final View viewItem) {
            super(viewItem);
            textViewOriginalTitle = viewItem.findViewById(R.id.original_title_tv);
            cardViewMovieList = viewItem.findViewById(R.id.card_view);
            imageViewMovieListItem = viewItem.findViewById(R.id.movie_poster_iv);
            textViewVoteAverage = viewItem.findViewById(R.id.vote_average_tv);
            imageViewMoviethumb = viewItem.findViewById(R.id.movie_thumb_iv);
            textViewPlotSynopsis = viewItem.findViewById(R.id.plot_synopsis_tv);
            textViewReleaseDate = viewItem.findViewById(R.id.release_tv);
            linearLayout = viewItem.findViewById(R.id.linear_layout);

            viewItem.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        Result clickedDataItem = results.get(pos);
                        Intent intent = new Intent(context, DetailActivity.class);
                        intent.putExtra("original_title", results.get(pos).getOriginalTitle());
                        intent.putExtra("poster_path", results.get(pos).getPosterPath());
                        intent.putExtra("overview", results.get(pos).getOverview());
                        intent.putExtra("vote_average", Double.toString(results.get(pos).getVoteAverage()));
                        intent.putExtra("release_date", results.get(pos).getReleaseDate());
                        intent.putExtra("movie_id", results.get(pos).getId());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        Toast.makeText(v.getContext(), "You clicked " + clickedDataItem.getOriginalTitle(), Toast.LENGTH_LONG).show();
                    }

                }

            });

        }
    }
}

