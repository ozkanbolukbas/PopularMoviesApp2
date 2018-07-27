package movies.raemacias.com.movieappstage2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;
import movies.raemacias.com.movieappstage1.R;
import movies.raemacias.com.movieappstage2.model.ReviewResult;


public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {

        private List<ReviewResult> mResults;
        private Context mContext;

        public ReviewAdapter(Context context, List<ReviewResult> items) {
            this.mContext = context;
            this.mResults = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            View itemView = inflater.inflate(R.layout.activity_content, parent, false);
            ViewHolder viewHolder = new ViewHolder(itemView);
            return new ViewHolder(itemView);
        }

    @Override
    public void onBindViewHolder(@NonNull ReviewAdapter.ViewHolder holder, int position) {
        ReviewResult item = mResults.get(position);

        holder.textViewContent.setText(item.getContent());
        holder.textViewAuthor.setText(item.getAuthor());
    }

        @Override
        public int getItemCount() {
            return mResults.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

           TextView textViewContent, textViewAuthor;

           public ViewHolder(View itemView) {
                super(itemView);

                textViewContent = itemView.findViewById(R.id.itemContent);
                textViewAuthor = itemView.findViewById(R.id.itemAuthor);
            }
        }
    }