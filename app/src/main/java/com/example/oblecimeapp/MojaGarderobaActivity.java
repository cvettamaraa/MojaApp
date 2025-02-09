package com.example.oblecimeapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;

public class MojaGarderobaActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private RecyclerView recyclerViewClothes;
    private ClothesAdapter clothesAdapter;
    private List<String> clothesImageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moja_garderoba);

        recyclerViewClothes = findViewById(R.id.recyclerViewClothes);
        Button pickImageButton = findViewById(R.id.btnPickImage);
        Button btnDressMe = findViewById(R.id.btnDressMe);

        clothesImageList = new ArrayList<>();

        recyclerViewClothes.setLayoutManager(new GridLayoutManager(this, 2));
        clothesAdapter = new ClothesAdapter(clothesImageList);
        recyclerViewClothes.setAdapter(clothesAdapter);

        pickImageButton.setOnClickListener(v -> pickImage());

        btnDressMe.setOnClickListener(v -> {
            Log.d("MojaGarderoba", "Dress Me button clicked!");
            Intent intent = new Intent(MojaGarderobaActivity.this, FeedActivity.class);
            startActivity(intent);
            Log.d("MojaGarderoba", "Intent to FeedActivity started.");
        });
    }

    private void pickImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            if (imageUri != null) {
                clothesImageList.add(imageUri.toString());
                clothesAdapter.notifyDataSetChanged();
            }
        }
    }

    public static class ClothesAdapter extends RecyclerView.Adapter<ClothesAdapter.ViewHolder> {

        private List<String> clothesImageList;

        public ClothesAdapter(List<String> clothesImageList) {
            this.clothesImageList = clothesImageList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_clothing, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            String imageUri = clothesImageList.get(position);

            if (imageUri != null && !imageUri.isEmpty()) {
                Glide.with(holder.imageView.getContext())
                        .load(Uri.parse(imageUri))
                        .placeholder(R.drawable.outfit6)
                        .into(holder.imageView);
            }
        }

        @Override
        public int getItemCount() {
            return clothesImageList.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;

            public ViewHolder(View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.imageViewClothing);
            }
        }
    }
}
