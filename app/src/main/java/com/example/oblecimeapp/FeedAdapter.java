package com.example.oblecimeapp;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ImageViewHolder> {

    private List<String> imageUris;

    public FeedAdapter(List<String> imageUris) {
        this.imageUris = imageUris;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        String imageUri = imageUris.get(position);

        if (imageUri != null && !imageUri.isEmpty()) {
            Glide.with(holder.itemView.getContext())
                    .load(Uri.parse(imageUri))
                    .apply(new RequestOptions().placeholder(R.drawable.outfit6))
                    .into(holder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return imageUris.size();
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ImageViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewFeed);
        }
    }
}
