package com.example.android.flyingapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


public class SpecificChecklistAdapter extends RecyclerView.Adapter<SpecificChecklistAdapter.ViewHolder>{
    private String[] titles;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private LinearLayout layout;

        private ViewHolder(View v){
            super(v);
            title = v.findViewById(R.id.specific_checklist_title);
            layout = v.findViewById(R.id.specific_checklist_layout);
        }
    }

    public SpecificChecklistAdapter (String[] list){
        titles=list;
    }

    @NonNull
    @Override
    public SpecificChecklistAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View v =  LayoutInflater.from(parent.getContext()).inflate(R.layout.specific_checklist_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SpecificChecklistAdapter.ViewHolder holder, int position) {
        holder.title.setText(titles[position]);
        holder.layout.setId(position);
    }

    @Override
    public int getItemCount(){
        return titles.length;
    }

}