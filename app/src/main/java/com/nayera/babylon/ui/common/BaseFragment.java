package com.nayera.babylon.ui.common;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.nayera.babylon.data.models.Status;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseFragment extends Fragment {

    CompositeDisposable compositeDisposable;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        injectDependencies();
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

    public void addDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    public void unbindViewModel() {
        if (compositeDisposable != null)
            compositeDisposable.clear();
    }

    public abstract void injectDependencies();

    public abstract void initViewModel();

    public abstract void bindViewModel();

    public abstract void initViews(View view);

    public abstract void setListeners();

    public abstract void showProgress();

    public abstract void hideProgress();

    public abstract void showError(Status status);

}
