package com.example.citektest.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.citektest.R;
import com.example.citektest.domain.model.User;
import com.example.citektest.presentation.mvi.OnSpinnerRetryClickListenerCallback;

import java.util.List;

public class SpinnerAdapter extends ArrayAdapter<User> {

    public SpinnerAdapter(@NonNull Context context, int resource, OnSpinnerRetryClickListenerCallback callback, List<User> users) {
        super(context, resource);

        this.callback = callback;
        layoutInflater = LayoutInflater.from(context);
        this.users = users;
        //Empty item for "Select User" label;
        addPlaceHolderItem();
    }

    private final LayoutInflater layoutInflater;
    private final OnSpinnerRetryClickListenerCallback callback;
    private boolean isLoading = false;
    private List<User> users;
    private boolean hasError;


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        if (convertView == null)
            view = layoutInflater.inflate(R.layout.item_spinner, parent, false);
        else
            view = convertView;

        setItem(view, getItem(position));

        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.item_spinner, parent, false);

        setItem(view, getItem(position));

        return view;
    }

    @Nullable
    @Override
    public User getItem(int position) {
        if (position == 0) {
            return null;
        }
        return users.get(position);
    }

    @Override
    public int getCount() {
        return users.size();
    }

    private void setItem(View view, User user) {
        TextView tv = view.findViewById(R.id.user_name);
        ProgressBar progressBar = view.findViewById(R.id.progress_circular);
        LinearLayout errorLayout = view.findViewById(R.id.error_layout);
        Button retryBtn = view.findViewById(R.id.retry_btn);

        retryBtn.setOnClickListener(view1 -> callback.onSpinnerRetryClick());

        if (user == null) {
            tv.setText(view.getContext().getString(R.string.select_user));
            return;
        }

        if (isLoading) {
            tv.setVisibility(View.GONE);
            errorLayout.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            return;
        }

        if (hasError) {
            tv.setVisibility(View.GONE);
            errorLayout.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            return;
        }

        tv.setVisibility(View.VISIBLE);
        errorLayout.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);

        tv.setText(user.getUser());
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
        users.clear();
        //item for showing "Select User" view
        addPlaceHolderItem();
        // item for showing Progress Bar
        addPlaceHolderItem();

        notifyDataSetChanged();
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
        users.clear();
        //item for showing "Select User" view
        addPlaceHolderItem();
        // item for showing Error Layout
        addPlaceHolderItem();

        notifyDataSetChanged();
    }

    public void setUsers(List<User> users) {
        hasError = false;
        isLoading = false;

        this.users.clear();
        addPlaceHolderItem();
        this.users.addAll(users);
        notifyDataSetChanged();
    }

    private void addPlaceHolderItem() {
        users.add(0, new User("", "", ""));
    }
}
