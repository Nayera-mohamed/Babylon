package com.nayera.babylon.ui.common;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.nayera.babylon.data.models.Status;

import io.reactivex.subjects.PublishSubject;

public class BaseViewModel extends ViewModel {

    PublishSubject<Boolean> loadingSubject;
    PublishSubject<Status> errorSubject;

    public BaseViewModel() {
        loadingSubject = PublishSubject.create();
        errorSubject = PublishSubject.create();
    }

    public void showProgressBar() {
        loadingSubject.onNext(true);
    }

    public void hideProgressBar() {
        loadingSubject.onNext(false);
    }

    public void showError(Status status) {
        errorSubject.onNext(status);
    }

    public PublishSubject<Boolean> getLoadingStatus() {
        return loadingSubject;
    }

    public PublishSubject<Status> getErrors() {
        return errorSubject;
    }
}
