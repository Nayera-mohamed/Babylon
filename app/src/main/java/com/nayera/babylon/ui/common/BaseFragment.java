package com.nayera.babylon.ui.common;

import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {


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

    public abstract void bindViewModel();

    public abstract void unbindViewModel();

    public abstract void initViews();

    public abstract void setListeners();

}
