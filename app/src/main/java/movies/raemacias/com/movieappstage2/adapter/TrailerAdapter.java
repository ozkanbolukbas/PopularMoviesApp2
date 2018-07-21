package movies.raemacias.com.movieappstage2.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import movies.raemacias.com.movieappstage1.R;
import movies.raemacias.com.movieappstage2.model.TrailerResult;

import static android.content.Intent.ACTION_VIEW;

public class TrailerAdapter extends RecyclerView.Adapter<movies.raemacias.com.movieappstage2.adapter.TrailerAdapter.MyViewHolder> {

        private Context mContext;
        private List<TrailerResult> trailerResult;


        public TrailerAdapter(Context mContext, List<TrailerResult> trailerResult) {
            this.mContext = mContext;
            this.trailerResult = trailerResult;

        }

        @NonNull
        @Override
        public TrailerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.activity_trailer, viewGroup, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull movies.raemacias.com.movieappstage2.adapter.TrailerAdapter.MyViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return trailerResult.size();

        }
        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView title;
            public ImageView thumbnail;

            public MyViewHolder(View itemView) {
                super(itemView);
                title = itemView.findViewById(R.id.movie_trailer);
                thumbnail = itemView.findViewById(R.id.movie_thumb_iv);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION) {
                            TrailerResult clickedDataItem = trailerResult.get(pos);
                            String videoId = trailerResult.get(pos).getKey();
                            Intent intent = new Intent (ACTION_VIEW, Uri.parse("vnd.youtube:"+videoId));
                            intent.putExtra("VIDEO_ID", videoId);
                            mContext.startActivity(intent);
                            Toast.makeText(v.getContext(), "You clicked " + clickedDataItem.getName(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        }
    }
