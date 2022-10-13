package com.example.citektest.presentation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.citektest.App;
import com.example.citektest.databinding.FragmentLoginBinding;
import com.example.citektest.presentation.adapter.SpinnerAdapter;
import com.example.citektest.presentation.mvi.EventHandlerFactory;
import com.example.citektest.presentation.mvi.EventHandler;
import com.example.citektest.presentation.utils.OnSpinnerRetryClickListenerCallback;
import com.example.citektest.presentation.utils.KeyboardUtils;
import com.example.citektest.presentation.utils.TelephonyManagerUtils;
import com.example.citektest.presentation.viewmodel.UserViewModel;
import com.example.citektest.presentation.viewmodel.UserViewModelFactory;

import java.util.ArrayList;

import javax.inject.Inject;

public class LoginFragment extends Fragment implements OnSpinnerRetryClickListenerCallback {

    FragmentLoginBinding binding;

    @Inject
    UserViewModelFactory factory;

    @Inject
    EventHandlerFactory eventFactory;

    UserViewModel viewModel;

    SpinnerAdapter adapter;

    EventHandler eventHandler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        ((App) requireActivity().getApplicationContext()).getComponent().inject(this);
        viewModel = new ViewModelProvider(this, factory).get(UserViewModel.class);

        adapter = new SpinnerAdapter(requireContext(), 0, this, new ArrayList<>());
        binding.selectUserSpinner.setAdapter(adapter);

        viewModel.getUsers(TelephonyManagerUtils.getIMEI(requireContext()));

        setListenersAndObservers();

        return view;
    }

    @Override
    public void onSpinnerRetryClick() {
        viewModel.getUsers(TelephonyManagerUtils.getIMEI(requireContext()));
    }


    private void setListenersAndObservers() {
        //for best UX hide keyboard on non Edit text click
        for (int i = 0; i < binding.rootLayout.getChildCount(); i++) {
            if (!(binding.rootLayout.getChildAt(i) instanceof EditText))
                binding.rootLayout.getChildAt(i).setOnTouchListener(onTouchListener);
        }

        binding.cancelIv.setOnClickListener(onCancelClickListener);

        viewModel.events.observe(getViewLifecycleOwner(), event -> {
            eventHandler = eventFactory.create(event);
            eventHandler.handle(binding, event);
        });

    }

    View.OnTouchListener onTouchListener = (view, motionEvent) -> {
        KeyboardUtils.hideKeyboard(requireContext(), view);
        return view.performClick();
    };

    View.OnClickListener onCancelClickListener = view -> {
        viewModel.cancelCall();
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
        viewModel.cancelCall();
    }

//
//    private void disableEnableControllers(boolean enable) {
//        for (int i = 0; i < binding.rootLayout.getChildCount(); i++) {
//            binding.rootLayout.getChildAt(i).setEnabled(enable);
//        }
//    }
//
//
//

//
//
//
//    View.OnClickListener onSelectUserClickListener = view -> {
//        viewModel.getUsers(TelephonyManagerUtils.getIMEI(requireContext()));
//        binding.selectUserSpinner.showDropDown();
//    };
//
//    Observer<Boolean> loadingObserver = isLoading -> {
//        if (isLoading) {
//            // show overlay
//            binding.overlayLayout.setVisibility(View.VISIBLE);
//            //disable controllers to prevent user click on them when overlay is shown
//            disableEnableControllers(false);
//        } else {
//            //hide overlay
//            binding.overlayLayout.setVisibility(View.GONE);
//            //enable disabled controllers
//            disableEnableControllers(true);
//        }
//    };
//
//    Observer<Boolean> loadingUsersObserver = isLoading -> {
//        if (isLoading) {
//            users.clear();
//            users.add(new User("", "", ""));
//            binding.selectUserSpinner.setAdapter(new ArrayAdapter<>(requireContext(), R.layout.item_spinner_loading, users));
//        }
//    };
//
//    Observer<Integer> errorUsersObserver = error -> {
//        if (error != null) {
//            users.clear();
//            users.add(new User("", "", ""));
//            binding.selectUserSpinner.setAdapter(new ArrayAdapter<>(requireContext(), R.layout.item_spinner_error, users));
//        }
//    };
//
//    Observer<List<User>> usersObserver = users -> {
//        binding.selectUserSpinner.setAdapter(new ArrayAdapter<User>(requireContext(), R.layout.item_spinner, users));
//    };
//
//
//    Observer<Integer> errorObserver = error -> {
//        if (error != null)
//            showSnackbar(error, true);
//    };
//
//    private void onUserListLoading(){
//        // show overlay
//        binding.overlayLayout.setVisibility(View.VISIBLE);
//        //disable controllers to prevent user click on them when overlay is shown
//        disableEnableControllers(false);
//    }
//
//    private void onUserListLoadingError(int message){
//
//    }
}