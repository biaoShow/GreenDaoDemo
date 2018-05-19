package com.example.biao.greendaodemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView 适配器
 * Created by biao on 2018/5/19.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private List<Uaer> list = new ArrayList<>();
    private LayoutInflater mLayout;

    public RecyclerViewAdapter(Context context,List<Uaer> list) {
        this.context = context;
        this.list = list;
        this.mLayout = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayout.inflate(R.layout.recyclerview_item,null);
        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder)holder).show_user.setText("id:" + String.valueOf(list.get(position).getId()) + "  name:" + list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView show_user;

        public MyViewHolder(View itemView) {
            super(itemView);

            show_user = itemView.findViewById(R.id.show_user);
        }
    }

}
