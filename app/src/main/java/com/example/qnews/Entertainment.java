package com.example.qnews;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Entertainment#} factory method to
 * create an instance of this fragment.
 */
public class Entertainment extends Fragment {

    private  static final String url="https://newsapi.org/v2/top-headlines?country=in&category=entertainment&apiKey=1d108bbe3e8d4966924a2d01d9e8c930";
    RecyclerView recviewEntertainment;
    myAdapter adapter;
    ArrayList<model> newsArray;
    private RequestQueue requestQueue;
    ProgressBar progressEntertainment;
    Context context;

    public Entertainment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.context= getContext();
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_entertainment, container, false);
        progressEntertainment= (ProgressBar)v.findViewById(R.id.progressEntertainment);
        progressEntertainment.setVisibility(View.VISIBLE);
        recviewEntertainment= (RecyclerView) v.findViewById(R.id.recviewEntertainment);
        recviewEntertainment.setLayoutManager(new LinearLayoutManager(context));
        newsArray= new ArrayList<>();
        requestQueue= VolleySingleton.getmInstance(context).getRequestQueue();
        adapter= new myAdapter(context);
        recviewEntertainment.setAdapter(adapter);

        processData();

        return v;
    }

    private void processData() {
        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    progressEntertainment.setVisibility(View.GONE);
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
    }

}