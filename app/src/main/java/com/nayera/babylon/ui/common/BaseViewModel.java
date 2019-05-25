package com.nayera.babylon.ui.common;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.nayera.babylon.data.models.Status;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
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

    public Observable<Boolean> getLoadingStatus() {
        return loadingSubject.hide();
    }

    public Observable<Status> getErrors() {
        return errorSubject.hide();
    }


}
