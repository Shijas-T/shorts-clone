package com.example.shortsclone;

import static com.google.android.material.color.utilities.MaterialDynamicColors.error;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Declaration
    private RecyclerView recyclerViewThumbnail;
    private ArrayList<ShortsModel> arrayListShorts;
    private ShortsAdapter shortsAdapter;

    private ProgressBar loadingPB;
    private NestedScrollView nestedSV;

    int page = 0, limit = 9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewThumbnail = findViewById(R.id.recyclerview_thumbnail);
        arrayListShorts = new ArrayList<>();

        loadingPB = findViewById(R.id.idPBLoading);
        nestedSV = findViewById(R.id.idNestedSV);

        getDataFromAPI(page, limit);

        // adding on scroll change listener method for our nested scroll view.
        nestedSV.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                // on scroll change we are checking when users scroll as bottom.
                if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                    // in this method we are incrementing page number,
                    // making progress bar visible and calling get data method.
                    page++;
                    loadingPB.setVisibility(View.VISIBLE);
                    getDataFromAPI(page, limit);
                }
            }
        });
    }

    private void getDataFromAPI(int page, int limit) {
        if (page > limit) {
            // checking if the page number is greater than limit.
            // displaying toast message in this case when page>limit.
            Toast.makeText(this, "Reached end", Toast.LENGTH_SHORT).show();

            // hiding our progress bar.
            loadingPB.setVisibility(View.GONE);
            return;
        }

        // creating a string variable for url .
        String url = "https://internship-service.onrender.com/videos?page=" + page;

        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("--->",response+"");
                try {

                    JSONArray jsonArray = response.getJSONObject("data").getJSONArray("posts");

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject info = jsonArray.getJSONObject(i);

                        arrayListShorts.add(new ShortsModel(
                                info.getJSONObject("submission").getString("mediaUrl"),
                                info.getJSONObject("submission").getString("thumbnail")
                        ));
                    }
                    if(response.length()>0){
                        shortsAdapter = new ShortsAdapter(arrayListShorts, MainActivity.this);
                        recyclerViewThumbnail.setHasFixedSize(true);
                        recyclerViewThumbnail.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
                        recyclerViewThumbnail.setAdapter(shortsAdapter);
                        shortsAdapter.notifyDataSetChanged();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "NO DATA FOUND", Toast.LENGTH_SHORT).show();
                    }
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Fail to get data..", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jsonObjectRequest);
    }
}