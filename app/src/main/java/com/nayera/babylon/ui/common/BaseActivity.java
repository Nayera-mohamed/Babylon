package com.nayera.babylon.ui.common;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.nayera.babylon.R;

public abstract class BaseActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    @Override
    public void onCreate(@androidx.annotation.Nullable Bundle savedInstanceState, @androidx.annotation.Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }


    /**
     * Its common use a toolbar within activity, if it exists in the
     * layout this will be configured
     */
    public void setupToolbar(Toolbar toolbar) {
        mToolbar = toolbar;
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }
    }


    public void setScreenTitle(String title) {
        if (mToolbar != null) {
            mToolbar.setTitle(title);
        }
    }

    public void replaceFragment(int layoutContainer, Fragment fragment, String tag, boolean addToBackStack, String backStackTag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(layoutContainer, fragment, tag);
        if (addToBackStack)
            transaction.addToBackStack(backStackTag);
        transaction.commit();
    }

    public void replaceFragment(int layoutContainer, Fragment fragment, String tag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(layoutContainer, fragment, tag);
        transaction.commit();
    }

    public abstract void initViews();

    public abstract void setListeners();

}
