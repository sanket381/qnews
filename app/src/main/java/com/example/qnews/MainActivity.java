package com.example.qnews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

  //private static final String url="https://newsapi.org/v2/top-headlines?country=in&apiKey=1d108bbe3e8d4966924a2d01d9e8c930";
   //public String api="1d108bbe3e8d4966924a2d01d9e8c930";
  //RecyclerView recview;
    //myAdapter adapter;
    //ArrayList<model> newsArray;
    //private RequestQueue requestQueue;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager= (ViewPager) findViewById(R.id.viewpager);

        categoryAdapter categoryAdapter = new categoryAdapter(this,getSupportFragmentManager());

        viewPager.setAdapter(categoryAdapter);

        TabLayout tabLayout= (TabLayout) findViewById(R.id.tablayout1);


      tabLayout.setupWithViewPager(viewPager);

        /*recview= (RecyclerView) findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));

        requestQueue= VolleySingleton.getmInstance(this).getRequestQueue();
         newsArray = new ArrayList<>();

         adapter= new myAdapter(this);
         recview.setAdapter(adapter);



        processData();*/


    }


   /* private void processData() {
        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
               try {
                   JSONArray newsJsonArray= response.getJSONArray("articles");


                   for (int i=0;i<newsJsonArray.length();i++){
                       JSONObject newJsonObject= newsJsonArray.getJSONObject(i);
                       model news= new model(
                               newJsonObject.getString("title"),
                               newJsonObject.getString("author"),
                               newJsonObject.getString("url"),
                               newJsonObject.getString("urlToImage")
                       );

                       newsArray.add(news);
                   }
                     adapter.updateNews(newsArray);
                    //adapter= new myAdapter(MainActivity.this,newsArray);
                    //recview.setAdapter(adapter);
                   //adapter.notifyDataSetChanged();


               }catch (JSONException e){
                    e.printStackTrace();
               }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                 Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                //headers.put("Content-Type", "application/json");
                headers.put("User-Agent", "Mozilla/5.0");
                //headers.put("apikey", api);
                return headers;
            }
        };
          requestQueue.add(jsonObjectRequest);
    }*/

}