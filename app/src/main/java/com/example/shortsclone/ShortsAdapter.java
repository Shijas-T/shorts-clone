package com.example.shortsclone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ShortsAdapter extends RecyclerView.Adapter<ShortsAdapter.ViewHolder>{

    //Initialise the list item here
    private ArrayList<ShortsModel> arrayListShorts;
    //Creating context for toast
    private Context context;

    public ShortsAdapter(ArrayList<ShortsModel> arrayListShorts, Context context) {
        this.arrayListShorts = arrayListShorts;
        this.context = context;
    }

    //View holder(it calls the created recycler View)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_thumbnail,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    //All the click listener is done here
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Picasso.with(context).load(arrayListShorts.get(position).getThumbnail()).into(holder.imageViewThumbnail);

        //On click listener
        holder.imageViewThumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(context, ShowDetailActivity.class);
//                intent.putExtra("show_id",arrayListAllShow.get(position).getShowId());
//                intent.putExtra("show_language",arrayListAllShow.get(position).getShowLanguage());
//                intent.putExtra("show_premiered",arrayListAllShow.get(position).getShowPremiered());
//                intent.putExtra("show_summary",arrayListAllShow.get(position).getShowSummary());
//                intent.putExtra("show_img_url",arrayListAllShow.get(position).getShowImageUrl());
//                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayListShorts.size();
    }

    //Every view inside the recycler view is declared and initialised here
    public class ViewHolder extends RecyclerView.ViewHolder{
        //Declaration
        private ImageView imageViewThumbnail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewThumbnail =  itemView.findViewById(R.id.ic_thumbnail);
        }
    }
}
