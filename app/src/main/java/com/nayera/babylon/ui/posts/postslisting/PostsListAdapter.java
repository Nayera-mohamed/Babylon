package com.nayera.babylon.ui.posts.postslisting;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nayera.babylon.R;
import com.nayera.babylon.data.models.Post;

import java.util.List;

public class PostsListAdapter extends RecyclerView.Adapter<PostsListAdapter.PostsViewHolder> {

    private List<Post> mPostList;
    private OnItemClickListener mOnItemClickListener;

    public PostsListAdapter(List<Post> postList, OnItemClickListener onItemClickListener) {
        this.mPostList = postList;
        this.mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public PostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.posts_list_item, parent, false);
        PostsViewHolder postsViewHolder = new PostsViewHolder(listItem);
        return postsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PostsViewHolder holder, int position) {
        Post currentPost = mPostList.get(position);
        if (currentPost != null)
            holder.bindPostTitle(currentPost.getTitle());
        holder.bindClickListner(currentPost, mOnItemClickListener);
    }

    @Override
    public int getItemCount() {
        return mPostList.size();
    }


    public class PostsViewHolder extends RecyclerView.ViewHolder {

        public TextView txtPostTitle;

        public PostsViewHolder(View itemView) {
            super(itemView);

            txtPostTitle = (TextView) itemView.findViewById(R.id.txtPostTitle);
        }

        public void bindPostTitle(String title) {
            if (!title.isEmpty())
                txtPostTitle.setText(title);
        }

        public void bindClickListner(final Post post, OnItemClickListener onItemClickListener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(post);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Post post);
    }
}
