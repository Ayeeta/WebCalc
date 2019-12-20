package com.example.webcalc;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
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

        View itemView = holder.itemView;
        CardView cardView = (CardView)itemView.findViewById(R.id.result_card);
        TextView textView = (TextView) itemView.findViewById(R.id.txt_status);
        if(textView.getText().toString().contains("No")){
            cardView.setCardBackgroundColor(Color.rgb(255, 173, 153));
        }else{
            cardView.setCardBackgroundColor(Color.rgb(255, 212, 128));
        }
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

            textViewNumOne = (TextView) itemView.findViewById(R.id.txt_numOne);
            textViewNumTwo = (TextView)itemView.findViewById(R.id.txt_numTwo);
            textViewResponse = (TextView)itemView.findViewById(R.id.txt_response);
            textViewExpected = (TextView)itemView.findViewById(R.id.txt_expected);
            textViewStatus = (TextView) itemView.findViewById(R.id.txt_status);
        }
    }
}
