package com.nayera.babylon.ui.posts.postsdetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;

import com.nayera.babylon.R;
import com.nayera.babylon.data.models.Post;
import com.nayera.babylon.ui.common.BaseActivity;
import com.nayera.babylon.ui.posts.postslisting.PostsListingFragment;

import static com.nayera.babylon.ui.posts.postsdetails.PostDetailsFragment.POST_KEY;

public class PostDetailsActivity extends BaseActivity {

    public static void startActivity(Context context, Post post) {
        Intent intent = new Intent(context, PostDetailsActivity.class);
        intent.putExtra(POST_KEY, post);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generic);
        initViews();
        setListeners();

        Post selectedPost = null;

        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
            selectedPost = (Post) bundle.get(POST_KEY);
        replaceFragment(R.id.contentLayout, PostDetailsFragment.newInstance(selectedPost), PostDetailsFragment.TAG);
    }


    @Override
    public void initViews() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setupToolbar(toolbar);
        setScreenTitle(getString(R.string.title_activity_post_details));
        showBackButton();
    }

    @Override
    public void setListeners() {

    }
}
