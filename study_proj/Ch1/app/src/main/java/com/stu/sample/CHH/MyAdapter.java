package com.chh.ttt;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    int ip;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        ViewHolder(View view){
            super(view);
            imageView = view.findViewById(R.id.image);
            textView = view.findViewById(R.id.textView);
        }
    }

    private ArrayList<itemData> itemData;
    MyAdapter(ArrayList<itemData> itemData){
        this.itemData = itemData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ViewHolder ViewHolder = (ViewHolder) holder;

        ViewHolder.imageView.setImageResource(itemData.get(position).num);
        ViewHolder.textView.setText(itemData.get(position).text);
        ip = ViewHolder.getAdapterPosition();
    }

    @Override
    public int getItemCount() {
        return itemData.size();
    }
}
