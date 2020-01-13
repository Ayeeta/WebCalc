package com.example.webcalc;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<WebCalculator> webCalculatorList;
    private Context context;

    public RecyclerViewAdapter(List<WebCalculator> webCalculatorList, Context context) {
        this.webCalculatorList = webCalculatorList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v  = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.result_items, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        final WebCalculator webCalculator = webCalculatorList.get(position);

        holder.textViewNumOne.setText("Number One: "+webCalculator.getNumberOne());
        holder.textViewNumTwo.setText("Number Two: "+webCalculator.getNumberTwo());
        holder.textViewResponse.setText("Response: "+webCalculator.getResponse());
        holder.textViewExpected.setText("Expected: "+webCalculator.getExpected());
        holder.textViewStatus.setText("Passed: "+webCalculator.getPassed());



        if (holder.textViewStatus.getText().toString().contains("No")){
            holder.cardView.setCardBackgroundColor(Color.rgb(255, 173, 153));
        }else{
            holder.cardView.setCardBackgroundColor(Color.rgb(255, 212, 128));
        }

        holder.delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(webCalculatorList != null){
                    WebCalculator webCalculator1 = WebCalculator.findById(WebCalculator.class,webCalculator.getId());
                    webCalculator1.delete();
                    webCalculatorList.remove(holder.getAdapterPosition());
                    notifyItemRemoved(holder.getAdapterPosition());
                    notifyItemChanged(holder.getAdapterPosition(), webCalculatorList.size());
                }
            }
        });



    }

    @Override
    public int getItemCount() {
        return webCalculatorList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textViewNumOne;
        TextView textViewNumTwo;
        TextView textViewResponse;
        TextView textViewExpected;
        TextView textViewStatus;
        ImageView delete_btn;
        CardView cardView;
//        ProgressBar progressBar;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewNumOne = itemView.findViewById(R.id.txt_numOne);
            textViewNumTwo = itemView.findViewById(R.id.txt_numTwo);
            textViewResponse = itemView.findViewById(R.id.txt_response);
            textViewExpected = itemView.findViewById(R.id.txt_expected);
            textViewStatus =  itemView.findViewById(R.id.txt_status);
            delete_btn =  itemView.findViewById(R.id.btn_delete);
            cardView = itemView.findViewById(R.id.result_card);
//            progressBar = itemView.findViewById(R.id.indeterminateBar);
        }

    }

}
