package com.example.webcalc;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<List_Item> list_itemList;
    private Context context;

    public RecyclerViewAdapter(List<List_Item> list_itemList, Context context) {
        this.list_itemList = list_itemList;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v  = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.result_items, parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        List_Item list_item = list_itemList.get(position);

        holder.textViewNumOne.setText(list_item.getNumberOne());
        holder.textViewNumTwo.setText(list_item.getNumberTwo());
        holder.textViewResponse.setText(list_item.getResponse());
        holder.textViewExpected.setText(list_item.getExpected());
        holder.textViewStatus.setText(list_item.getPassed());
    }

    @Override
    public int getItemCount() {
        return list_itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewNumOne;
        public TextView textViewNumTwo;
        public TextView textViewResponse;
        public TextView textViewExpected;
        public TextView textViewStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewNumOne = itemView.findViewById(R.id.txt_numOne);
            textViewNumTwo = itemView.findViewById(R.id.txt_numTwo);
            textViewResponse = itemView.findViewById(R.id.txt_response);
            textViewExpected = itemView.findViewById(R.id.txt_expected);
            textViewStatus = itemView.findViewById(R.id.txt_status);
        }
    }
}
