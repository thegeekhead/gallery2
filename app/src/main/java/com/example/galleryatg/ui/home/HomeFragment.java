package com.example.galleryatg.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.galleryatg.Gallery;
import com.example.galleryatg.R;

public class HomeFragment extends RecyclerView.Adapter<HomeFragment.GalleryViewHolder>{//Fragment

    Context mCtx;
    Gallery gallery;

    public HomeFragment(Context mCtx, Gallery gallery) {
        this.mCtx = mCtx;
        this.gallery = gallery;
    }

    @NonNull
    @Override
    public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.fragment_home, parent, false);
        return new GalleryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryViewHolder holder, int position) {
        //Gallery gallery = (Gallery) galleryList.get(position);

        Glide.with(mCtx)
                .load(gallery.getPhotos().getPhoto().get(position).getUrlS())
                .into(holder.imageView);


        holder.textView.setText(gallery.getPhotos().getPhoto().get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return gallery.getPhotos().getPhoto().size();
    }

    public class GalleryViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public GalleryViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
        }
    }



    /*private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }**/
}
