package com.nayera.babylon.ui.posts.postslisting;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.nayera.babylon.R;
import com.nayera.babylon.ui.common.BaseActivity;

public class PostsListingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts_listing);
        initViews();
        setListeners();

        replaceFragment(R.id.contentLayout, PostsListingFragment.newInstance(), PostsListingFragment.TAG);
    }


    @Override
    public void initViews() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setupToolbar(toolbar);
        setScreenTitle(getString(R.string.title_activity_posts_listing));
    }

    @Override
    public void setListeners() {

    }
}
