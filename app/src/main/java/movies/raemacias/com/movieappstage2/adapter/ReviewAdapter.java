package movies.raemacias.com.movieappstage2.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import movies.raemacias.com.movieappstage1.R;
import movies.raemacias.com.movieappstage2.DetailActivity;
import movies.raemacias.com.movieappstage2.model.ReviewResult;

public class ReviewAdapter extends RecyclerView.Adapter<movies.raemacias.com.movieappstage2.adapter.ReviewAdapter.MyViewHolder> {


        private Context mContext;
    private List<ReviewResult> mResults;


    public ReviewAdapter(Context mContext, List<ReviewResult> mResults) {
        this.mContext = mContext;
        this.mResults = mResults;

    }

    @NonNull
    @Override
    public ReviewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.activity_content, viewGroup, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull movies.raemacias.com.movieappstage2.adapter.ReviewAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mResults.size();

    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView review;
//        public ImageView thumbnail;

        public MyViewHolder(View itemView) {
            super(itemView);
            review = itemView.findViewById(R.id.reviews_tv);
//            thumbnail = itemView.findViewById(R.id.movie_thumb_iv);
//
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {

                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        Intent intent = new Intent(mContext, DetailActivity.class);
//                        ReviewResult clickedDataItem = mResults.get(pos);
                        intent.putExtra("review", mResults.get(pos).getContent());
                        intent.putExtra("author", mResults.get(pos).getAuthor());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);


                    }
                }

        }
    }

