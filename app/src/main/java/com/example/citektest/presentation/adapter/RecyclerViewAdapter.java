package com.example.citektest.presentation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.citektest.R;
import com.example.citektest.domain.model.UserAuth;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewVH> {

    List<UserAuth> userAuths;
    LayoutInflater inflater;

    public RecyclerViewAdapter(Context context, List<UserAuth> userAuths) {
        this.userAuths = userAuths;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerViewVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_recyclerview, parent, false);
        return new RecyclerViewVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewVH holder, int position) {

        UserAuth userAuth = userAuths.get(position);

        holder.response.setText(String.valueOf(userAuth.isResponse()));
        holder.continueWork.setText(String.valueOf(userAuth.isContinueWork()));
        holder.photoHash.setText(userAuth.getPhotoHash());
        holder.currentDate.setText(String.valueOf(userAuth.getCurrentDate()));
    }

    public void setUserAuths(List<UserAuth> userAuths){
        this.userAuths = userAuths;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return userAuths.size();
    }

    protected class RecyclerViewVH extends RecyclerView.ViewHolder {
        TextView response;
        TextView continueWork;
        TextView photoHash;
        TextView currentDate;

        public RecyclerViewVH(@NonNull View itemView) {
            super(itemView);

            response = itemView.findViewById(R.id.response_tv);
            continueWork = itemView.findViewById(R.id.continue_work_tv);
            photoHash = itemView.findViewById(R.id.photo_hash_tv);
            currentDate = itemView.findViewById(R.id.current_date_tv);
        }
    }
}
