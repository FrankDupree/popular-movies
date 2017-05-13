package com.codeniro.android.movie.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.codeniro.android.movie.R;
import com.codeniro.android.movie.datatypes.Movie;
import com.codeniro.android.movie.utils.NetworkUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Dupree on 12/05/2017.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder> {
    private MovieAdapterOnClickListener mClick;
    private Context mcontext;
    private List<Movie> vmovielist;
    public MovieAdapter(Context vcontext, MovieAdapterOnClickListener click){
        mcontext = vcontext;
        mClick = click;
    }

    public void setMovieData(List<Movie> movielist){
        vmovielist = movielist;
        notifyDataSetChanged();
    }

    @Override
    public MovieAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.movie_list,parent,false);
        return new MovieAdapterViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MovieAdapterViewHolder holder, int position) {
        Picasso.with(mcontext)
                .load(NetworkUtils.PIXURL + vmovielist.get(position).getPoster_path())
                .placeholder(R.drawable.loading)
                .error(R.drawable.missing)
                .into(holder.imgview);
    }

    @Override
    public int getItemCount() {
        return vmovielist != null ? vmovielist.size() : 0;
    }

    public class MovieAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imgview;
        public MovieAdapterViewHolder(View itemView) {
            super(itemView);
            imgview = (ImageView) itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mClick.onclick(vmovielist.get(getPosition()));
        }
    }

    public interface MovieAdapterOnClickListener{
        void onclick(Movie response);
    }
}
