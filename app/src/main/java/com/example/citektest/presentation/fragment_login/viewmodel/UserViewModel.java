package com.example.citektest.presentation.fragment_login.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.citektest.domain.exceptions.UnknownException;
import com.example.citektest.domain.model.User;
import com.example.citektest.domain.use_cases.AuthUserUseCase;
import com.example.citektest.domain.use_cases.GetUsersUseCase;
import com.example.citektest.presentation.fragment_login.event.LoginEvent;
import com.example.citektest.presentation.utils.ErrorHandler;
import com.example.citektest.presentation.utils.NetworkUtils;
import com.example.citektest.presentation.utils.UserDataValidator;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class UserViewModel extends ViewModel {

    private static final String TAG = "UserViewModel";

    private Disposable disposable;

    private final GetUsersUseCase getUsersUseCase;
    private final AuthUserUseCase authUserUseCase;
    private final NetworkUtils networkUtils;
    private final UserDataValidator userDataValidator;

    private final MutableLiveData<LoginEvent> _events = new MutableLiveData<>(new LoginEvent.Initial());
    public LiveData<LoginEvent> events = _events;

    List<User> userList;

    @Inject
    public UserViewModel(GetUsersUseCase getUsersUseCase, AuthUserUseCase authUserUseCase, NetworkUtils networkUtils, UserDataValidator userDataValidator) {
        this.getUsersUseCase = getUsersUseCase;
        this.authUserUseCase = authUserUseCase;
        this.networkUtils = networkUtils;
        this.userDataValidator = userDataValidator;

        _events.postValue(new LoginEvent.Initial());
    }

    public void getUsers(String imei) {
        if (userList != null) {
            _events.postValue(new LoginEvent.UserListFetchingSuccessful(userList));
            return;
        }

        if (hasNotInternetConnection())
            return;

        _events.postValue(new LoginEvent.UserListLoading());

        disposable = getUsersUseCase.execute(imei)
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<User>>() {
                    @Override
                    public void onSuccess(List<User> users) {
                        userList = users;
                        Log.d("TAG", "getUsers: ");
                        LoginEvent event = new LoginEvent.UserListFetchingSuccessful(users);
                        _events.postValue(event);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                        LoginEvent event = new LoginEvent.UserListError(ErrorHandler.handleError((RuntimeException) e));
                        _events.postValue(event);

                    }
                });
    }

    public void authUser(String imei, String uid, String pass) {
        if (hasNotInternetConnection())
            return;

        if (!isDataValid(uid, pass))
            return;

        disposable = authUserUseCase.execute(imei, uid, pass)
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Boolean>() {
                    @Override
                    public void onSuccess(Boolean isUserAuthed) {
                        LoginEvent event;
                        UnknownException unknownError = new UnknownException();
                        if (isUserAuthed)
                            event = new LoginEvent.UserAuthSuccessful();
                        else
                            event = new LoginEvent.UserAuthError(ErrorHandler.handleError((RuntimeException) unknownError));

                        _events.postValue(event);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getCause());
                        LoginEvent event = new LoginEvent.UserAuthError(ErrorHandler.handleError((RuntimeException) e));
                        _events.postValue(event);

                    }
                });
    }

    public boolean isDataValid(String uid, String pass){
        int validationResult = userDataValidator.validate(uid, pass);

        if (validationResult == -1)
            return true;

        _events.postValue(new LoginEvent.UserAuthError(validationResult));
        return false;
    }

    private boolean hasNotInternetConnection() {
        if (!networkUtils.isNetworkConnected()) {
            _events.postValue(new LoginEvent.NoNetworkConnection());
            return true;
        }
        return false;
    }

    public void cancelCall() {
        disposable.dispose();
    }

}
