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

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtName;
        TextView txtNum;

        ViewHolder(View view){
            super(view);
            txtName = view.findViewById(R.id.txtName);
            txtNum = view.findViewById(R.id.txtNum);
        }
    }

    private ArrayList<Info> infoList;
    MyAdapter(ArrayList<Info> infoList){
        this.infoList = infoList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ViewHolder ViewHolder = (ViewHolder) holder;

        ViewHolder.txtName.setText(infoList.get(position).getpNum());
        ViewHolder.txtNum.setText(infoList.get(position).getpNum());
    }

    @Override
    public int getItemCount() {
        return infoList.size();
    }
}
