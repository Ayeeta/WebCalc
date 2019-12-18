package com.example.webcalc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public RecyclerView recyclerView;
    public RecyclerView.Adapter adapter;
    public Button btn_calculate;
    public EditText editTextNumOne;
    public EditText editTextNumTwo;
    Spinner math_operator;

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

                editTextNumOne = (EditText)findViewById(R.id.numberOneTxt);
                editTextNumTwo = (EditText)findViewById(R.id.numberTwoTxt);
                math_operator = (Spinner)findViewById(R.id.op_spinner);
                double numOne = Float.valueOf(editTextNumOne.getText().toString());
                double numTwo = Float.valueOf(editTextNumTwo.getText().toString());

                double expected_result;

                list_itemList = new ArrayList<>();

                if (math_operator.getSelectedItem().toString().equals("Add")){
                    expected_result = numOne + numTwo;
                    Random random = new Random();
                    double randomNumber = random.nextDouble();
                    int rounded_number = (int) Math.round(randomNumber);
                    if(rounded_number == 1){
                        double another_number = random.nextDouble();
                        double numResponse = Math.ceil(another_number * 4000);
                        List_Item list_item = new List_Item(
                                "Number One:"+(numOne),
                                "Number Two:"+(numTwo),
                                "Response: "+(Double.toString(numResponse)),
                                "Expected: "+(Double.toString(expected_result)),
                                "Passed: "+"No"
                        );

                        list_itemList.add(list_item);

                        List_Item list_item2 = new List_Item(
                                "Number One:"+(Double.toString(numOne)),
                                "Number Two:"+(Double.toString(numTwo)),
                                "Response: "+(Double.toString(expected_result)),
                                "Expected: "+(Double.toString(expected_result)),
                                "Passed: "+"Yes"
                        );

                        list_itemList.add(list_item2);
                    }else{
                        List_Item list_item2 = new List_Item(
                                "Number One:"+(Double.toString(numOne)),
                                "Number Two:"+(Double.toString(numTwo)),
                                "Response: "+(Double.toString(expected_result)),
                                "Expected: "+(Double.toString(expected_result)),
                                "Passed: "+"Yes"
                        );

                        list_itemList.add(list_item2);
                    }

                }else if(math_operator.getSelectedItem().toString().equals("Subtract")){
                    expected_result = numOne - numTwo;
                    List_Item list_item = new List_Item(
                            "Number One:"+(numOne),
                            "Number Two:"+(numTwo),
                            "Response: "+(234),
                            "Expected: "+(expected_result),
                            "Passed: "+"No"
                    );

                    list_itemList.add(list_item);
                }
                else if(math_operator.getSelectedItem().toString().equals("Divide")){
                    expected_result = numOne / numTwo;
                    List_Item list_item = new List_Item(
                            "Number One:"+(numOne),
                            "Number Two:"+(numTwo),
                            "Response: "+(234),
                            "Expected: "+(expected_result),
                            "Passed: "+"No"
                    );

                    list_itemList.add(list_item);
                }
                else{
                    expected_result = numOne * numTwo;
                    List_Item list_item = new List_Item(
                            "Number One:"+(numOne),
                            "Number Two:"+(numTwo),
                            "Response: "+(234),
                            "Expected: "+(expected_result),
                            "Passed: "+"No"
                    );

                    list_itemList.add(list_item);
                }

                adapter = new RecyclerViewAdapter(list_itemList, btn_calculate.getContext());
                recyclerView.setAdapter(adapter);

                editTextNumOne.getText().clear();
                editTextNumTwo.getText().clear();

            }
        });










    }
}
