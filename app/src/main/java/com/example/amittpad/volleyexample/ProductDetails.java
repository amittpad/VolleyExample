package com.example.amittpad.volleyexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.amittpad.volleyexample.adapter.CustomRecyclerAdapter;
import com.example.amittpad.volleyexample.pojo.Value;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProductDetails extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private static final String URL = "http://settingneeds.com/settingneeds/public/api/user_info/all_product_details";
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;
    List<Value> valueList;

      @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        initializationView();

    }

    private void initializationView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_id);
        mRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        valueList = new ArrayList<>();

        //sendRequest();
        getData();

    }
    public void sendRequest(){

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for(int i = 0; i < response.length(); i++){

                    Value value = new Value();

                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        value.setProductName(jsonObject.getString("product_name"));
                        value.setDescription(jsonObject.getString("description"));
                        value.setPrice(jsonObject.getString("price"));
                        //value.setImages(jsonObject.getString("images").charAt(0));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    valueList.add(value);

                }

                mAdapter = new CustomRecyclerAdapter(ProductDetails.this, valueList);

                mRecyclerView.setAdapter(mAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Volley Error: ", String.valueOf(error));
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);

    }
    private void getData() {
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//                for (int i = 0; i < response.length(); i++) {
//                    try {
//                        JSONObject jsonObject = response.getJSONObject(i);
//
//                       Value value = new Value();
//                        value.setProductName(jsonObject.getString("product_name"));
//                        value.setDescription(jsonObject.getString("description"));
//                        value.setPrice(jsonObject.getString("price"));
//
//                        valueList.add(value);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                        progressDialog.dismiss();
//                    }
//                }
//                productDetailsAdapter.notifyDataSetChanged();
//                progressDialog.dismiss();
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e("Volley", error.toString());
//                progressDialog.dismiss();
//            }
//        });
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(jsonArrayRequest);
        StringRequest stringRequest = new StringRequest(Request.Method.GET,URL,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("TAG", "String response=" + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int status = jsonObject.getInt("status");
                    if (status == 0) {
                        //Toast.makeText(ViewDetailsFragment.class,"web response error",Toast.LENGTH_SHORT).show();
                    } else if (status == 1) {
                        JSONArray jArray = jsonObject.getJSONArray("value");
                        Log.d("message", jsonObject.toString());//full API

                        for (int i = 0; i < jArray.length(); i++) {
                            JSONObject json_data = jArray.getJSONObject(i);

                            Value  rowItem = new Value();
                            rowItem.setProductName(json_data.getString("product_name"));
                            rowItem.setDescription(json_data.getString("description"));
                            rowItem.setPrice(json_data.getString("price"));

                            // Image is json array
                            JSONArray imageArray = json_data.getJSONArray("images");
                            ArrayList<String> images = new ArrayList<String>();
                            for (int j = 0; j < imageArray.length(); j++) {
                                images.add((String) imageArray.get(j));
                            }
                            rowItem.setImages(images);

                            valueList.add(rowItem);

                        }
                        mAdapter = new CustomRecyclerAdapter(ProductDetails.this, valueList);
                        mRecyclerView.setAdapter(mAdapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("TAG", "String error=" + error);

            }
        });

         RequestQueue requestQueue = Volley.newRequestQueue(this);
         requestQueue.add(stringRequest);

    }


}
