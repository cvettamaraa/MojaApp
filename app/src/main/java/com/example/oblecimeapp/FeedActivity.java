package com.example.oblecimeapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class FeedActivity extends AppCompatActivity {

    private RecyclerView recyclerViewFeed;
    private FeedAdapter feedAdapter;
    private List<String> feedImageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        Log.d("FeedActivity", "Activity started, setting up RecyclerView.");

        recyclerViewFeed = findViewById(R.id.recyclerViewFeed);
        recyclerViewFeed.setLayoutManager(new LinearLayoutManager(this));

        feedImageList = new ArrayList<>();
        feedImageList.add("https://i.pinimg.com/736x/67/d7/b4/67d7b4f0f2069e0a2ed9097ec5efea54.jpg");
        feedImageList.add("https://i.pinimg.com/736x/f6/63/7f/f6637f84ed4dbed7fabb371034a6f0e7.jpg");
        feedImageList.add("https://i.pinimg.com/736x/97/fa/ad/97faadbfbc86817d7f2729d0f5b092e4.jpg");
        Log.d("FeedActivity", "Broj na slika vo feedImageList: " + feedImageList.size());

        feedAdapter = new FeedAdapter(feedImageList);
        recyclerViewFeed.setAdapter(feedAdapter);
    }

    public static class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {

        private List<String> feedImageList;

        public FeedAdapter(List<String> feedImageList) {
            this.feedImageList = feedImageList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            String imageUri = feedImageList.get(position);
            Log.d("FeedAdapter", "Loading image: " + imageUri);

            Glide.with(holder.imageView.getContext())
                    .load(imageUri)
                    .into(holder.imageView);
        }

        @Override
        public int getItemCount() {
            return feedImageList.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;

            public ViewHolder(View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.imageViewFeed);
            }
        }
    }
}
