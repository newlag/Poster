package com.newlag.poster.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.newlag.poster.R;
import com.newlag.poster.activities.ProfileActivity;
import com.newlag.poster.model.User;
import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchHolder> {

    private ArrayList<User> users;
    private Context context;

    public SearchAdapter(ArrayList<User> users) {
        this.users = users;
    }

    public void updateUsersList(ArrayList<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(context).inflate(R.layout.item_search, parent, false);
        return new SearchHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchHolder holder, int position) {
        holder.Bind(users.get(position));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class SearchHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView username, fullname;
        ImageView avatar;
        String name;

        public SearchHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username_title);
            fullname = itemView.findViewById(R.id.fullname_title);
            avatar = itemView.findViewById(R.id.user_avatar);
        }

        public void Bind(User user) {
            Log.e("Кек", "name: " + user.getName());
            username.setText("@" + user.getName());
            fullname.setText(user.getFullname());
            Glide.with(context)
                .load(user.getAvatar())
                .into(avatar);
            name = user.getName();
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            context.startActivity(ProfileActivity.newInstance(context, name));
        }
    }
}
