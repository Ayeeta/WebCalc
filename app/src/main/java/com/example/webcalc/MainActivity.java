package com.example.webcalc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<List_Item> list_itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list_itemList = new ArrayList<>();

        for(int i = 0; i<= 10; i++){
            List_Item list_item = new List_Item(
                    "Number One:"+(5),
                    "Number Two:"+(2),
                    "Response: "+(234),
                    "Expected: "+(7),
                    "Passed: "+"No"
            );

            list_itemList.add(list_item);
        }

        adapter = new RecyclerViewAdapter(list_itemList, this);
        recyclerView.setAdapter(adapter);
    }
}
