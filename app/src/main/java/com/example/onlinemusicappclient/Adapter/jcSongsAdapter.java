package com.example.onlinemusicappclient.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.example.jean.jcplayer.view.JcPlayerView;
import com.example.onlinemusicappclient.Model.Utility;
import com.example.onlinemusicappclient.Model.getSongs;
import com.example.onlinemusicappclient.R;

import java.util.List;

public class jcSongsAdapter extends RecyclerView.Adapter<jcSongsAdapter.SongsAdapterViewHolder>{
    private int selectedPosition;
    Context context;
    List<getSongs> arraylistSongs;
    private RecyclerItemClickListener listener;
    public jcSongsAdapter(android.content.Context context, List<getSongs> arraylistSongs, RecyclerItemClickListener listener) {
        this.context = context;
        this.arraylistSongs = arraylistSongs;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SongsAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.songs_row, parent, false);
        return new SongsAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongsAdapterViewHolder holder, int position) {

        getSongs getSongs = arraylistSongs.get(position);
        if (getSongs!=null){
            holder.iv_play_active.setVisibility(View.VISIBLE);
        }
        else {
            holder.iv_play_active.setVisibility(View.INVISIBLE);
        }
        holder.tv_title.setText(getSongs.getSongTitle());
        holder.tv_artist.setText(getSongs.getArtist());
        String duration = Utility.convertDuration(Long.parseLong(getSongs.getSongDuration()));
        holder.tv_duration.setText(duration);
        holder.bind(getSongs, listener);
    }

    @Override
    public int getItemCount() {
        return arraylistSongs.size();
    }

    public class SongsAdapterViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_title, tv_artist, tv_duration;
        ImageView iv_play_active;
        public SongsAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
             tv_title = itemView.findViewById(R.id.tv_title);
             tv_artist = itemView.findViewById(R.id.tv_artist);
             tv_duration = itemView.findViewById(R.id.tv_duration);
             iv_play_active = itemView.findViewById(R.id.iv_play_active);
        }

        public void bind(getSongs getSongs, RecyclerItemClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClickListener(getSongs,getAdapterPosition());
                }
            });
        }
    }

    public interface RecyclerItemClickListener{
        void onClickListener(getSongs songs, int position);
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }
}
