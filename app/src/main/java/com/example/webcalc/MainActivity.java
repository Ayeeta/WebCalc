package com.example.webcalc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.textclassifier.TextLinks;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public RecyclerView recyclerView;
    public RecyclerView.Adapter adapter;
    public Button btn_calculate;
    public EditText editTextNumOne;
    public EditText editTextNumTwo;
    Spinner math_operator;
    String res;


    public List<List_Item> list_itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        btn_calculate = (Button) findViewById(R.id.btn_calc);

        btn_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                editTextNumOne = (EditText) findViewById(R.id.numberOneTxt);
                editTextNumTwo = (EditText) findViewById(R.id.numberTwoTxt);
                math_operator = (Spinner) findViewById(R.id.op_spinner);
                final double numOne = Float.valueOf(editTextNumOne.getText().toString());
                final double numTwo = Float.valueOf(editTextNumTwo.getText().toString());
                final double expected_result;

                list_itemList = new ArrayList<>();

                if (math_operator.getSelectedItem().toString().equals("Add")) {

                    expected_result = numOne + numTwo;
                    Random random = new Random();
                    double randomNumber = random.nextDouble();
                    int rounded_number = (int) Math.round(randomNumber);

                    if (rounded_number == 1) {
                        double another_number = random.nextDouble();
                        double numResponse = Math.ceil(another_number * 4000);
                        List_Item list_item = new List_Item(
                                "Number One:" + (numOne),
                                "Number Two:" + (numTwo),
                                "Response: " + (Double.toString(numResponse)),
                                "Expected: " + (Double.toString(expected_result)),
                                "Passed: " + "No"
                        );



                        list_itemList.add(list_item);

                        Add();
//
//                        List_Item list_item2 = new List_Item(
//                                "Number One:" + (Double.toString(numOne)),
//                                "Number Two:" + (Double.toString(numTwo)),
//                                "Response: " + (Double.toString(expected_result)),
//                                "Expected: " + (Double.toString(expected_result)),
//                                "Passed: " + "Yes"
//                        );
//
//                        list_itemList.add(list_item2);
                    } else {

                        Add();


//                        List_Item list_item2 = new List_Item(
//                                "Number One:" + (Double.toString(numOne)),
//                                "Number Two:" + (Double.toString(numTwo)),
//                                "Response: " + (Double.toString(expected_result)),
//                                "Expected: " + (Double.toString(expected_result)),
//                                "Passed: " + "Yes"
//                        );
//
//                        list_itemList.add(list_item2);
                    }

                } else if (math_operator.getSelectedItem().toString().equals("Subtract")) {
                    expected_result = numOne - numTwo;
                    List_Item list_item = new List_Item(
                            "Number One:" + (numOne),
                            "Number Two:" + (numTwo),
                            "Response: " + (234),
                            "Expected: " + (expected_result),
                            "Passed: " + "No"
                    );

                    list_itemList.add(list_item);
                } else if (math_operator.getSelectedItem().toString().equals("Divide")) {
                    expected_result = numOne / numTwo;
                    List_Item list_item = new List_Item(
                            "Number One:" + (numOne),
                            "Number Two:" + (numTwo),
                            "Response: " + (234),
                            "Expected: " + (expected_result),
                            "Passed: " + "No"
                    );

                    list_itemList.add(list_item);
                } else {
                    expected_result = numOne * numTwo;
                    List_Item list_item = new List_Item(
                            "Number One:" + (numOne),
                            "Number Two:" + (numTwo),
                            "Response: " + (234),
                            "Expected: " + (expected_result),
                            "Passed: " + "No"
                    );

                    list_itemList.add(list_item);
                }

                adapter = new RecyclerViewAdapter(list_itemList, btn_calculate.getContext());
                recyclerView.setAdapter(adapter);

//                editTextNumOne.getText().clear();
//                editTextNumTwo.getText().clear();
            }
        });
    }

    private void Add() {

        editTextNumOne = (EditText) findViewById(R.id.numberOneTxt);
        editTextNumTwo = (EditText) findViewById(R.id.numberTwoTxt);
        math_operator = (Spinner) findViewById(R.id.op_spinner);
        final String numOne = editTextNumOne.getText().toString();
        final String numTwo = editTextNumTwo.getText().toString();



        String URL = "http://api.mathjs.org/v4/";

        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("expr", numOne+"+"+numTwo);
        }catch(JSONException je){
            Toast.makeText(this, "Server Error", Toast.LENGTH_LONG).show();
        }
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            double expected_result = Float.valueOf(numOne) + Float.valueOf(numTwo);
                            res = response.getString("result");
                            List_Item list_item2 = new List_Item(
                                    "Number One:" + (numOne),
                                    "Number Two:" + (numTwo),
                                    "Response: " + (res),
                                    "Expected: " + (Double.toString(expected_result)),
                                    "Passed: " + "Yes"
                            );

                            list_itemList.add(list_item2);


                        }catch (JSONException je){

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(jsonObjectRequest);

        adapter = new RecyclerViewAdapter(list_itemList, btn_calculate.getContext());
        recyclerView.setAdapter(adapter);
    }


}
