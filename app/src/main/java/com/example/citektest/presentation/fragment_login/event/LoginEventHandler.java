package com.example.citektest.presentation.fragment_login.event;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.StringRes;
import androidx.core.content.res.ResourcesCompat;
import androidx.navigation.Navigation;

import com.example.citektest.R;
import com.example.citektest.databinding.FragmentLoginBinding;
import com.example.citektest.presentation.adapter.SpinnerAdapter;
import com.google.android.material.snackbar.Snackbar;

public abstract class LoginEventHandler {

    public abstract void handle(FragmentLoginBinding binding, LoginEvent event);

    protected void showSnackbar(Context context, View view, @StringRes int message, boolean isError) {
        int color;
        if (isError)
            color = ResourcesCompat.getColor(context.getResources(), android.R.color.holo_red_light, context.getTheme());
        else
            color = ResourcesCompat.getColor(context.getResources(), android.R.color.darker_gray, context.getTheme());

        Snackbar
                .make(view, context.getString(message), Snackbar.LENGTH_LONG)
                .setBackgroundTint(color)
                .show();
    }

    protected void disableEnableControllers(ViewGroup rootLayout, boolean enable){
        for (int i = 0; i < rootLayout.getChildCount(); i++) {
            rootLayout.getChildAt(i).setEnabled(enable);
        }
    }


    public static class OnInitialEvent extends LoginEventHandler {

        @Override
        public void handle(FragmentLoginBinding binding, LoginEvent event) {
        }

    }

    public static class OnUserListLoadingEvent extends LoginEventHandler {

        @Override
        public void handle(FragmentLoginBinding binding, LoginEvent event) {
            SpinnerAdapter adapter = (SpinnerAdapter) binding.selectUserSpinner.getAdapter();
            adapter.setLoading(true);
        }
    }


    public static class OnUserListFetchingSuccessfulEvent extends LoginEventHandler {

        @Override
        public void handle(FragmentLoginBinding binding, LoginEvent event) {
            SpinnerAdapter adapter = (SpinnerAdapter) binding.selectUserSpinner.getAdapter();
            adapter.setUsers(((LoginEvent.UserListFetchingSuccessful) event).getUsers());
        }
    }


    public static class OnUserListErrorEvent extends LoginEventHandler {

        @Override
        public void handle(FragmentLoginBinding binding, LoginEvent event) {

            SpinnerAdapter adapter = (SpinnerAdapter) binding.selectUserSpinner.getAdapter();
            String errorMessage = binding.getRoot().getContext().getString(((LoginEvent.UserListError) event).getError());
            adapter.setErrorMessage(errorMessage);
        }
    }


    public static class OnNoNetworkConnectionEvent extends LoginEventHandler {

        @Override
        public void handle(FragmentLoginBinding binding, LoginEvent event) {
            showSnackbar(binding.getRoot().getContext(), binding.getRoot(), R.string.no_internet_connection, true);
            SpinnerAdapter adapter = (SpinnerAdapter) binding.selectUserSpinner.getAdapter();
            String errorMessage = binding.getRoot().getContext().getString(R.string.no_internet_connection);
            adapter.setErrorMessage(errorMessage);
        }
    }


    public static class OnUserAuthLoadingEvent extends LoginEventHandler {
        @Override
        public void handle(FragmentLoginBinding binding, LoginEvent event) {
            binding.overlayLayout.setVisibility(View.VISIBLE);

            disableEnableControllers(binding.rootLayout, false);
        }
    }



    public static class OnUserAuthErrorEvent extends LoginEventHandler {

        @Override
        public void handle(FragmentLoginBinding binding, LoginEvent event) {
            int errorMessage = ((LoginEvent.UserAuthError)event).getError();
            showSnackbar(binding.getRoot().getContext(), binding.getRoot(), errorMessage, true);

            disableEnableControllers(binding.rootLayout, true);
        }
    }



    public static class OnUserAuthSuccessfulEvent extends LoginEventHandler {

        @Override
        public void handle(FragmentLoginBinding binding, LoginEvent event) {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_loginFragment_to_userListFragment);
        }
    }


}
