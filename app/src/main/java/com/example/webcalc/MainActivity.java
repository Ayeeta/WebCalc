package com.example.webcalc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

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
    String res;
    CardView cardView;
    Operations operations;


    public List<WebCalculator> webCalculatorList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNumOne = findViewById(R.id.numberOneTxt);
        editTextNumTwo = findViewById(R.id.numberTwoTxt);
        math_operator = findViewById(R.id.op_spinner);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        btn_calculate = findViewById(R.id.btn_calc);
        cardView = findViewById(R.id.result_card);


        btn_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    final double numOne = Float.valueOf(editTextNumOne.getText().toString());
                    final double numTwo = Float.valueOf(editTextNumTwo.getText().toString());
                    final double expected_result;

                    //list_itemList = new ArrayList<>();
                    webCalculatorList = new ArrayList<>();
                    operations = new Operations(numOne, numTwo);
                    //Thread thread = new Thread();
                    String operator = CalculatorOperations.getOperationSymbol(math_operator.getSelectedItem().toString());
                    if (!operator.isEmpty()) {

                        //expected_result = numOne + numTwo;

                        expected_result = operations.add();
                        Random random = new Random();
                        double randomNumber = random.nextDouble();
                        int rounded_number = (int) Math.round(randomNumber);

                        if (rounded_number == 1) {

                            double another_number = random.nextDouble();
                            double numResponse = Math.ceil(another_number * 4000);

                            String num_one = editTextNumOne.getText().toString();
                            String num_two = editTextNumTwo.getText().toString();
                            String numResp = Double.toString(numResponse);
                            String numExpected = Double.toString(expected_result);
                            String status = "No";
                            WebCalculator webCalculator = new WebCalculator(
                                    num_one, num_two, numResp, numExpected, status);
                            webCalculator.save();

                            //webCalculatorList.add(webCalculator);
                            webCalculatorList = WebCalculator.listAll(WebCalculator.class);
                            adapter = new RecyclerViewAdapter(webCalculatorList, btn_calculate.getContext());
                            recyclerView.setAdapter(adapter);

                        } else {
                            compute(operator);
                        }
                    }

                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Please enter number", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void compute(String operator) {

        webCalculatorList = new ArrayList<>();
        try {
            final String numOne = editTextNumOne.getText().toString();
            final String numTwo = editTextNumTwo.getText().toString();

            String URL = "http://api.mathjs.org/v4/";

            final JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("expr", numOne + operator + numTwo);
            } catch (JSONException je) {
                Toast.makeText(this, "Server Error", Toast.LENGTH_LONG).show();
            }
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonObject,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                operations = new Operations(Float.valueOf(numOne), Float.valueOf(numTwo));
                                //double expected_result = Float.valueOf(numOne) + Float.valueOf(numTwo);
                                double expected_result = operations.add();
                                res = response.getString("result");

                                String result_expected = Double.toString(expected_result);
                                WebCalculator webCalculator = new WebCalculator(
                                        numOne, numTwo, res, result_expected, "Yes");
                                webCalculator.save();
                                //webCalculatorList.add(webCalculator2);
                                webCalculatorList = WebCalculator.listAll(WebCalculator.class);
                                adapter = new RecyclerViewAdapter(webCalculatorList, btn_calculate.getContext());
                                recyclerView.setAdapter(adapter);


                            } catch (JSONException je) {
                                Toast.makeText(getApplicationContext(), "Unable to submit", Toast.LENGTH_LONG).show();

                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
                }
            });
            requestQueue.add(jsonObjectRequest);


        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), "Please enter number", Toast.LENGTH_LONG).show();
        }
    }
}
