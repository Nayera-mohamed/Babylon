package com.nayera.babylon.ui.common;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initViewModel();
    }

    @Override
    public void onStart() {
        super.onStart();
        bindViewModel();
    }

    @Override
    public void onStop() {
        super.onStop();
        unbindViewModel();
    }

    public abstract void initViewModel();

    public abstract void bindViewModel();

    public abstract void unbindViewModel();

    public abstract void initViews();

    public abstract void setListeners();

}
