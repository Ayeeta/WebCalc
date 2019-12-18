package com.example.webcalc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public RecyclerView recyclerView;
    public RecyclerView.Adapter adapter;
    public Button btn_calculate;

    public List<List_Item> list_itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        btn_calculate = (Button)findViewById(R.id.btn_calc);

        btn_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list_itemList = new ArrayList<>();

//        for(int i = 1; i<= 2; i++){
//            List_Item list_item = new List_Item(
//                    "Number One:"+(5),
//                    "Number Two:"+(2),
//                    "Response: "+(234),
//                    "Expected: "+(7),
//                    "Passed: "+"No"
//            );
//
//            list_itemList.add(list_item);
//        }
                List_Item list_item = new List_Item(
                        "Number One:"+(5),
                        "Number Two:"+(2),
                        "Response: "+(234),
                        "Expected: "+(7),
                        "Passed: "+"No"
                );

                list_itemList.add(list_item);

                List_Item list_item2 = new List_Item(
                        "Number One:"+(5),
                        "Number Two:"+(2),
                        "Response: "+(7),
                        "Expected: "+(7),
                        "Passed: "+"Yes"
                );
                list_itemList.add(list_item2);
                adapter = new RecyclerViewAdapter(list_itemList, btn_calculate.getContext());
                recyclerView.setAdapter(adapter);
            }
        });










    }
}
