package com.newlag.poster.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.newlag.poster.R;
import com.newlag.poster.model.Post;
import com.newlag.poster.utils.DateUtil;
import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostHolder> {

    private ArrayList<Post> postList;
    private Context context;

    public PostAdapter(Context context, ArrayList<Post> postList) {
        this.context = context;
        this.postList = postList;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_post, parent, false);
        return new PostHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
        holder.Bind(postList.get(position));
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class PostHolder extends RecyclerView.ViewHolder {

        TextView fullname, username, text, date;
        ImageView avatar;

        public PostHolder(@NonNull View itemView) {
            super(itemView);
            fullname = itemView.findViewById(R.id.fullname_title);
            username = itemView.findViewById(R.id.username_title);
            text = itemView.findViewById(R.id.post_text);
            date = itemView.findViewById(R.id.date_title);
            avatar = itemView.findViewById(R.id.user_avatar);
        }
        public void Bind(Post post) {
            fullname.setText(post.getFullname());
            username.setText("@" + post.getUsername());
            text.setText(post.getContent());
            Glide.with(context)
                .load(post.getUserImage())
                .into(avatar);
            date.setText(DateUtil.getDate(post.getDate()) + context.getString(R.string.days_ago_title));
        }
    }
}
