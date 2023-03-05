package com.example.Final_Project.Adapter_Listener_Dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Final_Project.Models.Level;
import com.example.Final_Project.Models.Util;
import com.example.Final_Project.databinding.ItemLevelBinding;

import java.util.List;

public class AdapterLevel extends RecyclerView.Adapter< AdapterLevel.GameViewHolder> {
    List<Level> levelArrayList;
    Context context;
    MyListener listener;


    public AdapterLevel( Context context, List<Level> levelArrayList, MyListener listener) {
        this.context = context;
        this.levelArrayList = levelArrayList;
        this.listener = listener;
    }
    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemLevelBinding binding = ItemLevelBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new GameViewHolder( binding );
    }
    @Override
    public void onBindViewHolder(@NonNull GameViewHolder holder, int position) {
        int pos = position;
        int score = Util.preferences.getInt( Util.Score,0);
        Level level = levelArrayList.get(holder.getAdapterPosition());
        holder.level_no.setText( String.valueOf(level.getLevel_no()));
//        double x = ((Util.preferences.getInt( Util.Score,1 )  ));

        holder.unlock.setText(String.valueOf(level.getUnlock_points()));
//        holder.progressBar.setMax( level.getUnlock_points() );
        if ( score < level.getUnlock_points() ){
            holder.LL_Gone.setVisibility( View.VISIBLE );
            if(level.getLevel_no() != 1) {
                double x = Util.preferences.getInt( Util.Score , 1 );
                double y = level.getUnlock_points( );
                double z = x / y;
                double r = z * 100;
                holder.LevelAvg.setText( r+"%" );
//                holder.progressBar.setProgress( ( int ) r );
            }
        }else {
        holder.itemView.setOnClickListener( view -> listener.onClick(level.getLevel_no()) );
    }}
    @Override
    public int getItemCount() {
        return levelArrayList.size();
    }

    static class GameViewHolder extends RecyclerView.ViewHolder {

        TextView level_no ,unlock,LevelAvg;
        LinearLayout LL_Gone;
        ProgressBar progressBar;

        public GameViewHolder(ItemLevelBinding binding) {
            super(binding.getRoot());

           level_no = binding.ItemCountQues;
           unlock = binding.ItemUnlock;
           LL_Gone = binding.LLGone;
           LevelAvg = binding.tvAvg;
           progressBar=binding.progressBar;
        }
    }
}