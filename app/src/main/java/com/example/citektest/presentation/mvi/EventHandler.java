package com.example.citektest.presentation.mvi;

import android.content.Context;
import android.view.View;

import androidx.annotation.StringRes;
import androidx.core.content.res.ResourcesCompat;

import com.example.citektest.R;
import com.example.citektest.databinding.FragmentLoginBinding;
import com.example.citektest.presentation.adapter.SpinnerAdapter;
import com.google.android.material.snackbar.Snackbar;

public abstract class EventHandler {

    public abstract void handle(FragmentLoginBinding binding, Event event);

    protected void showSnackbar(Context context, View view, @StringRes int message, boolean isError) {
        int color;
        if (isError)
            color = ResourcesCompat.getColor(context.getResources(), android.R.color.holo_red_light, context.getTheme());
        else
            color = ResourcesCompat.getColor(context.getResources(), android.R.color.darker_gray, context.getTheme());

        Snackbar
                .make(view,  context.getString(message), Snackbar.LENGTH_LONG)
                .setBackgroundTint(color)
                .show();
    }


    public static class OnInitialEvent extends EventHandler{

        @Override
        public void handle(FragmentLoginBinding binding, Event event) {}

    }

    public static class OnUserListLoadingEvent extends EventHandler{

        @Override
        public void handle(FragmentLoginBinding binding, Event event) {
            SpinnerAdapter adapter = (SpinnerAdapter)binding.selectUserSpinner.getAdapter();
            adapter.setLoading(true);
        }
    }


    public static class OnShowUserListEvent extends EventHandler{

        @Override
        public void handle(FragmentLoginBinding binding, Event event) {
            SpinnerAdapter adapter = (SpinnerAdapter)binding.selectUserSpinner.getAdapter();
            adapter.setUsers(((Event.ShowUserList) event).getUsers());
        }
    }


    public static class OnUserListErrorEvent extends EventHandler {
        @Override
        public void handle(FragmentLoginBinding binding, Event event) {

            SpinnerAdapter adapter = (SpinnerAdapter)binding.selectUserSpinner.getAdapter();
            adapter.setHasError(true);
        }
    }


    public static class OnShowNoNetworkConnectionEvent extends EventHandler{

        @Override
        public void handle(FragmentLoginBinding binding, Event event) {
            showSnackbar(binding.getRoot().getContext(), binding.getRoot(), R.string.no_internet_connection, true);
            SpinnerAdapter adapter = (SpinnerAdapter)binding.selectUserSpinner.getAdapter();
            adapter.setHasError(true);
        }
    }


}
