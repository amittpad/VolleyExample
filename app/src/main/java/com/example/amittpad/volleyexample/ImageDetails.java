package com.example.amittpad.volleyexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.amittpad.volleyexample.pojo.Value;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class ImageDetails extends AppCompatActivity {

    private ImageView pImage;
    private String mImagePosition;
    private RecyclerView allImageRecycler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_details);
        initializationView();

        mImagePosition = getIntent().getStringExtra("image_position");
        GlideConnector.getInstance().loadImageDirectly(this, mImagePosition, pImage);


    }
    private void initializationView(){
        pImage = (ImageView) findViewById(R.id.product_image_id);
    }


}
