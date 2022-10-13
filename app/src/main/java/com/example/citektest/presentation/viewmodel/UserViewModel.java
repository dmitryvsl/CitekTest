package com.example.citektest.presentation.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.citektest.domain.model.User;
import com.example.citektest.domain.use_cases.GetUsersUseCase;
import com.example.citektest.presentation.mvi.Event;
import com.example.citektest.presentation.utils.ErrorHandler;
import com.example.citektest.presentation.utils.NetworkUtils;

import java.nio.channels.Channel;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import kotlinx.coroutines.flow.MutableStateFlow;

public class UserViewModel extends ViewModel {

    private static final String TAG = "UserViewModel";


    private Disposable disposable;

    private final GetUsersUseCase getUsersUseCase;
    NetworkUtils networkUtils;

    private final MutableLiveData<Event> _events = new MutableLiveData<>(new Event.Initial());
    public LiveData<Event> events = _events;


    @Inject
    public UserViewModel(GetUsersUseCase getUsersUseCase, NetworkUtils networkUtils) {
        this.getUsersUseCase = getUsersUseCase;
        this.networkUtils = networkUtils;
    }

    public void getUsers(String imei) {
        if (!networkUtils.isNetworkConnected()){
            _events.postValue(new Event.ShowNoNetworkConnection());
            return;
        }
        _events.postValue(new Event.UserListLoading());
        disposable = getUsersUseCase.execute(imei)
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<User>>() {
                    @Override
                    public void onSuccess(List<User> users) {
                        _events.postValue(new Event.ShowUserList(users));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                        _events.postValue(new Event.UserListError(ErrorHandler.handleError((RuntimeException) e)));
                    }
                });
    }

    public void cancelCall() {
        disposable.dispose();
    }
}
