package com.codeniro.android.movie.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codeniro.android.movie.R;
import com.codeniro.android.movie.datatypes.Trailer;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dupree on 12/05/2017.
 */
public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewHolder> {
    private Context mcontext;
    private List<Trailer> reviewlist;
    public ReviewAdapter(Context vcontext){
        mcontext = vcontext;
    }

    @Override
    public ReviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.review,parent,false);
        return new ReviewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReviewHolder holder, int position) {
        holder.review.setText(reviewlist.get(position).getAuthor() + " : " + reviewlist.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return reviewlist != null ? reviewlist.size() : 0;
    }

    public void setReviewListData(List<Trailer> mreviewlist){
        reviewlist = mreviewlist;
        notifyDataSetChanged();
    }

    public class ReviewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.movieoverview)
        TextView review;
        public ReviewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
