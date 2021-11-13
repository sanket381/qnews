package com.example.qnews;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class myAdapter extends RecyclerView.Adapter<myAdapter.myviewholder> {
    Context context;
     ArrayList<model> data=new ArrayList<>();

    public myAdapter(Context context) {
        this.context= context;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news,parent,false);

        return new myviewholder(view);
    }

    public void updateNews(ArrayList<model> updatedNews){

       // if(data!=null) {
            data.clear();
        //}
        data.addAll(updatedNews);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {

        model m= data.get(position);
        holder.title.setText(m.getTitle());
        holder.author.setText(m.getAuthor());
        Glide.with(holder.title.getContext()).load(m.getUrlToImage()).into(holder.img);
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,detailActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("title",m.getTitle());
                bundle.putString("author",m.getAuthor());
                bundle.putString("img",m.urlToImage);

                intent.putExtras(bundle);

                context.startActivity(intent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return data.size();
    }

    static class myviewholder extends RecyclerView.ViewHolder {
         ImageView img;
         TextView title;
         TextView author;
         ConstraintLayout constraintLayout;
        public myviewholder(@NonNull View itemView) {
            super(itemView);

            img = (ImageView) itemView.findViewById(R.id.image);
            title= (TextView) itemView.findViewById(R.id.title);
            author = (TextView) itemView.findViewById(R.id.author);
            constraintLayout= (ConstraintLayout) itemView.findViewById(R.id.main_layout);
        }
    }
}
