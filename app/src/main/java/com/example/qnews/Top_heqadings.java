package com.example.qnews;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

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
 * Use the {@link Top_heqadings} factory method to
 * create an instance of this fragment.
 */
public class Top_heqadings extends Fragment {


    private  static final String url="https://newsapi.org/v2/top-headlines?country=in&apiKey=1d108bbe3e8d4966924a2d01d9e8c930";
    public String api="1d108bbe3e8d4966924a2d01d9e8c930";
    RecyclerView recviewTH;
    myAdapter adapter;
    ArrayList<model> newsArray;
    private RequestQueue requestQueue;
    Context context;
    ProgressBar progress;
    public Top_heqadings() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.context= getContext();
        View v= inflater.inflate(R.layout.fragment_top_heqadings, container, false);
         recviewTH= (RecyclerView) v.findViewById(R.id.recviewTH);
         progress= (ProgressBar)v.findViewById(R.id.progress);
         progress.setVisibility(View.VISIBLE);
         recviewTH.setLayoutManager(new LinearLayoutManager(context));
        newsArray= new ArrayList<>();
        requestQueue= VolleySingleton.getmInstance(context).getRequestQueue();
        adapter= new myAdapter(context);
        recviewTH.setAdapter(adapter);

        processData();


       return v;
    }

    private void processData() {
        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                   progress.setVisibility(View.GONE);
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