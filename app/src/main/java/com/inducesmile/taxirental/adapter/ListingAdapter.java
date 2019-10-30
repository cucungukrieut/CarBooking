package com.inducesmile.taxirental.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inducesmile.taxirental.CarCategoryActivity;
import com.inducesmile.taxirental.R;
import com.inducesmile.taxirental.models.CarCategoryObject;

import java.util.List;

public class ListingAdapter extends RecyclerView.Adapter<com.inducesmile.taxirental.adapter.ListingViewHolder>{

    private static final String TAG = ListingAdapter.class.getSimpleName();

    private Context context;

    private List<CarCategoryObject> carList;


    public ListingAdapter(Context context, List<CarCategoryObject> carList) {
        this.context = context;
        this.carList = carList;
    }

    @Override
    public ListingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_category_layout, parent, false);
        return new ListingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListingViewHolder holder, int position) {
        CarCategoryObject carCategoryObject = carList.get(position);

        holder.carName.setText(carCategoryObject.getImageName());
        //holder.carImage
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent carCategoryIntent = new Intent(context, CarCategoryActivity.class);
                context.startActivity(carCategoryIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }
}
