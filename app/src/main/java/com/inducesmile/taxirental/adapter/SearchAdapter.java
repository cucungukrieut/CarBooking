package com.inducesmile.taxirental.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inducesmile.taxirental.R;
import com.inducesmile.taxirental.models.SearchObject;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder>{

    private static final String TAG = SearchAdapter.class.getSimpleName();

    private Context context;

    private List<SearchObject> searchList;


    public SearchAdapter(Context context, List<SearchObject> searchList) {
        this.context = context;
        this.searchList = searchList;
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_cars_layout, parent, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        SearchObject sObject = searchList.get(position);

        holder.carName.setText(sObject.getCarName());
        holder.carPrice.setText(sObject.getCarPrice());
        holder.carDuration.setText(sObject.getCarDuration());
        holder.carFeature.setText(sObject.getCarFeatures());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return searchList.size();
    }
}
