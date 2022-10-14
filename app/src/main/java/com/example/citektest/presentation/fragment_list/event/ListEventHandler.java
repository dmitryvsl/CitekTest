package com.example.citektest.presentation.fragment_list.event;

import android.view.View;

import com.example.citektest.databinding.FragmentListBinding;
import com.example.citektest.domain.model.UserAuth;
import com.example.citektest.presentation.adapter.RecyclerViewAdapter;

import java.util.List;

public abstract class ListEventHandler {

    public abstract void handle(FragmentListBinding binding, ListEvent event);


    public static class OnInitialEvent extends ListEventHandler{
        @Override
        public void handle(FragmentListBinding binding, ListEvent event) {}
    }


    public static class OnDataFetchingSuccessfulEvent extends ListEventHandler{

        @Override
        public void handle(FragmentListBinding binding, ListEvent event) {
            binding.progressCircular.setVisibility(View.GONE);
            binding.recyclerView.setVisibility(View.VISIBLE);
            List<UserAuth> userAuths = ((ListEvent.DataFetchingSuccessful)event).getUserAuthList();
            RecyclerViewAdapter adapter = (RecyclerViewAdapter) binding.recyclerView.getAdapter();

            if (adapter != null)
                adapter.setUserAuths(userAuths);
        }
    }

    public static class OnDataFetchingFailedEvent extends ListEventHandler {

        @Override
        public void handle(FragmentListBinding binding, ListEvent event) {
            binding.errorLayout.setVisibility(View.VISIBLE);
            binding.progressCircular.setVisibility(View.GONE);

            int errorMessage = ((ListEvent.DataFetchingFailed)event).getErrorMessage();
            binding.errorMessageTv.setText(binding.getRoot().getContext().getString(errorMessage));
        }
    }

}
