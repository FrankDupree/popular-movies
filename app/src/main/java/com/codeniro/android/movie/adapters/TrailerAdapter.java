package com.codeniro.android.movie.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codeniro.android.movie.R;
import com.codeniro.android.movie.datatypes.Trailer;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dupree on 12/05/2017.
 */
public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.TrailerHoldeer> {
    private Context mcontex;
    private TrailerAdapterOnClickListener mClickListener;
    private List<Trailer> trailerlist;
    private int count =0;
    public TrailerAdapter(Context context,TrailerAdapterOnClickListener clickListener){
        mcontex = context;
        mClickListener = clickListener;
    }
    public void setTrailerlistData(List<Trailer> vtrailerlist){
        trailerlist = vtrailerlist;
        count =0;
        notifyDataSetChanged();
    }

    @Override
    public TrailerHoldeer onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.trailer_list,parent,false);
        return new TrailerHoldeer(view);
    }

    @Override
    public void onBindViewHolder(TrailerHoldeer holder, int position) {
        holder.trailertitle.setText("Trailer" + ++count );
    }

    @Override
    public int getItemCount() {
        return trailerlist != null ? trailerlist.size() : 0;
    }

    public interface TrailerAdapterOnClickListener{
        void onclick(Trailer response);
    }

    public class TrailerHoldeer extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.trailertitle)
        TextView trailertitle;
        @BindView(R.id.trailericon)
        ImageView trailericon;
        public TrailerHoldeer(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            trailericon.setOnClickListener(this);
            trailertitle.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mClickListener.onclick(trailerlist.get(getPosition()));
        }
    }
}
