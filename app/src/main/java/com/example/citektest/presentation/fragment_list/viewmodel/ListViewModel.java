package com.example.citektest.presentation.fragment_list.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.citektest.domain.exceptions.UnknownException;
import com.example.citektest.domain.model.UserAuth;
import com.example.citektest.domain.use_cases.GetAuthenticationUseCase;
import com.example.citektest.presentation.fragment_list.event.ListEvent;
import com.example.citektest.presentation.utils.ErrorHandler;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ListViewModel extends ViewModel {

    private final GetAuthenticationUseCase getAuthenticationUseCase;

    private final MutableLiveData<ListEvent> _events = new MutableLiveData<>(new ListEvent.Initial());
    public LiveData<ListEvent> events = _events;

    private Disposable disposable;

    @Inject
    public ListViewModel(GetAuthenticationUseCase getAuthenticationUseCase) {
        this.getAuthenticationUseCase = getAuthenticationUseCase;
    }

    public void getAuthentication() {
        disposable = getAuthenticationUseCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<UserAuth>>() {
                    @Override
                    public void onSuccess(List<UserAuth> userAuths) {
                        _events.postValue(new ListEvent.DataFetchingSuccessful(userAuths));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("ListViewModel", "onError: " + e.getMessage());
                        UnknownException unknownException = new UnknownException();
                        _events.postValue(new ListEvent.DataFetchingFailed(ErrorHandler.handleError(unknownException)));
                    }
                });
    }

    public void cancelCall(){
        disposable.dispose();
    }

}
