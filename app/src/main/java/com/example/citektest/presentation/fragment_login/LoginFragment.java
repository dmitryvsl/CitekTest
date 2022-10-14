package com.example.citektest.presentation.fragment_login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.citektest.App;
import com.example.citektest.databinding.FragmentLoginBinding;
import com.example.citektest.domain.model.User;
import com.example.citektest.presentation.adapter.SpinnerAdapter;
import com.example.citektest.presentation.fragment_login.event.LoginEventHandler;
import com.example.citektest.presentation.fragment_login.event.LoginEventHandlerFactory;
import com.example.citektest.presentation.fragment_login.viewmodel.UserViewModel;
import com.example.citektest.presentation.fragment_login.viewmodel.UserViewModelFactory;
import com.example.citektest.presentation.utils.KeyboardUtils;
import com.example.citektest.presentation.utils.TelephonyManagerUtils;

import java.util.ArrayList;

import javax.inject.Inject;

public class LoginFragment extends Fragment implements OnSpinnerRetryClickListenerCallback {

    FragmentLoginBinding binding;

    @Inject
    UserViewModelFactory factory;

    @Inject
    LoginEventHandlerFactory eventFactory;

    UserViewModel viewModel;

    SpinnerAdapter adapter;

    LoginEventHandler eventHandler;


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
        binding.loginBtn.setOnClickListener(onLoginClickListener);

        viewModel.events.observe(getViewLifecycleOwner(), event -> {
            LoginEventHandler oldHandler = eventHandler;
            eventHandler = eventFactory.create(event);

            //handle event only first time
            if (oldHandler != null && !eventHandler.getClass().equals(oldHandler.getClass()))
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

    View.OnClickListener onLoginClickListener = view -> {
        String imei = TelephonyManagerUtils.getIMEI(requireContext());
        User user = adapter.getUserByPosition(binding.selectUserSpinner.getSelectedItemPosition());
        String pass = binding.passwordEt.getText().toString();
        viewModel.authUser(imei, user.getUid(), pass);
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
        viewModel.cancelCall();
    }

}