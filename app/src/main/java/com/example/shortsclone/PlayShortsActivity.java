package com.example.shortsclone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class PlayShortsActivity extends AppCompatActivity {

    ArrayList<ShortsModel> arrayListShorts;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_shorts);

        final ViewPager2 videosViewPager = findViewById(R.id.viewPagerVideos);

        arrayListShorts = (ArrayList<ShortsModel>) getIntent().getSerializableExtra("shorts");
        position = getIntent().getIntExtra("position",0);

        videosViewPager.setAdapter(new VideosAdapter(arrayListShorts));
        videosViewPager.setCurrentItem(position);
    }
}