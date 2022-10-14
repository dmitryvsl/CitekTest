package com.example.citektest.presentation.fragment_list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.citektest.App;
import com.example.citektest.databinding.FragmentListBinding;
import com.example.citektest.presentation.adapter.RecyclerViewAdapter;
import com.example.citektest.presentation.fragment_list.event.ListEventHandler;
import com.example.citektest.presentation.fragment_list.event.ListEventHandlerFactory;
import com.example.citektest.presentation.fragment_list.viewmodel.ListViewModel;
import com.example.citektest.presentation.fragment_list.viewmodel.ListViewModelFactory;

import java.util.ArrayList;

import javax.inject.Inject;

public class ListFragment extends Fragment {

    FragmentListBinding binding;

    @Inject
    ListViewModelFactory factory;

    @Inject
    ListEventHandlerFactory eventHandlerFactory;

    private ListViewModel viewModel;

    private ListEventHandler eventHandler;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentListBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        ((App) requireActivity().getApplicationContext()).getComponent().inject(this);
        viewModel = new ViewModelProvider(this, factory).get(ListViewModel.class);

        setRecyclerView();

        viewModel.getAuthentication();

        viewModel.events.observe(getViewLifecycleOwner(), event -> {
            ListEventHandler oldHandler = eventHandler;
            eventHandler = eventHandlerFactory.create(event);

            //handle event only first time
            if (oldHandler != null && !eventHandler.getClass().equals(oldHandler.getClass()))
                eventHandler.handle(binding, event);
        });

        return view;
    }

    private void setRecyclerView(){
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(requireContext(), new ArrayList<>());
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
        viewModel.cancelCall();
    }

}