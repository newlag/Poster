package com.newlag.poster.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.newlag.poster.R;
import com.newlag.poster.interfaces.PhotoAdapterInterface;
import com.newlag.poster.model.Image;

import java.util.ArrayList;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoHolder> {

    private ArrayList<Image> images;
    private Context context;
    private boolean selected = false;
    private PhotoAdapterInterface callback;

    public PhotoAdapter(ArrayList<Image> images, PhotoAdapterInterface callback) {
        this.images = images;
        this.callback = callback;
    }

    @NonNull
    @Override
    public PhotoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(context).inflate(R.layout.item_photo, parent, false);

        return new PhotoHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoHolder holder, int position) {
        holder.Bind(images.get(position));
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class PhotoHolder extends RecyclerView.ViewHolder {

        ImageView avatar;
        boolean clicked = false;

        public PhotoHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.image);
        }

        public void Bind(final Image image) {
            Glide.with(context)
                .load(image.getUrl())
                .into(avatar);
            avatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!selected) {
                        avatar.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
                        clicked = true;
                        selected = true;
                        callback.onSelected(image.getUrl());
                    } else if (clicked) {
                        avatar.setBackgroundColor(context.getResources().getColor(android.R.color.transparent));
                        clicked = false;
                        selected = false;
                        callback.unSelected();
                    }
                }
            });
        }
    }
}
