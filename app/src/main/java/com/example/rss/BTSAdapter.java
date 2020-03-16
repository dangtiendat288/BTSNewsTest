package com.example.rss;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class BTSAdapter extends RecyclerView.Adapter<BTSAdapter.BTSViewHolder> {
    private List<BTSNews> list;
    private Context context;
    public BTSAdapter(List<BTSNews> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public BTSViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_list_news_item,parent,false);
        return new BTSViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BTSViewHolder holder, int position) {
        BTSNews currentNews = list.get(position);
        holder.title.setText(currentNews.getTitle());
        holder.time.setText(currentNews.getPubDate());
        holder.source.setText(currentNews.getNewsSource());
        Glide.with(context)  //2
                .load(currentNews.getNewsImage()) //3
                .centerCrop() //4
//                .placeholder(R.drawable.ic_image_place_holder) //5
//                .error(R.drawable.ic_broken_image) //6
//                .fallback(R.drawable.ic_no_image) //7
                .into(holder.img);
        Glide.with(context)  //2
                .load(currentNews.getNewsImage()) //3
                .centerCrop() //4
                .into(holder.logo);
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class BTSViewHolder extends RecyclerView.ViewHolder{
        TextView title,time,source;
        ImageView img,logo;
        public BTSViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.txt_title);
            time = itemView.findViewById(R.id.txt_time);
            source = itemView.findViewById(R.id.txt_source);
            img = itemView.findViewById(R.id.img);
            logo = itemView.findViewById(R.id.logo);
            itemView.setOnClickListener((view)->{

            });
        }
    }
}
