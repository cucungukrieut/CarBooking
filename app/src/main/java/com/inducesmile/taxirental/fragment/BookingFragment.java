package com.inducesmile.taxirental.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inducesmile.taxirental.R;
import com.inducesmile.taxirental.adapter.ListingAdapter;
import com.inducesmile.taxirental.models.CarCategoryObject;

import java.util.ArrayList;
import java.util.List;


public class BookingFragment extends Fragment {

    private static final String TAG = BookingFragment.class.getSimpleName();

    private RecyclerView bookingRecyclerView;


    public BookingFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_booking, container, false);

        bookingRecyclerView = (RecyclerView)view.findViewById(R.id.car_list);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        bookingRecyclerView.setLayoutManager(gridLayoutManager);
        bookingRecyclerView.setHasFixedSize(true);

        com.inducesmile.taxirental.adapter.ListingAdapter mAdapter = new com.inducesmile.taxirental.adapter.ListingAdapter(getActivity(), getTextData());
        bookingRecyclerView.setAdapter(mAdapter);

        return view;
    }

    private List<com.inducesmile.taxirental.models.CarCategoryObject> getTextData() {

        List<com.inducesmile.taxirental.models.CarCategoryObject> carData = new ArrayList<>();
        carData.add(new com.inducesmile.taxirental.models.CarCategoryObject("", "BMW"));
        carData.add(new com.inducesmile.taxirental.models.CarCategoryObject("", "TOYOTA"));
        carData.add(new com.inducesmile.taxirental.models.CarCategoryObject("", "FORD"));
        carData.add(new com.inducesmile.taxirental.models.CarCategoryObject("", "NISSAN"));
        carData.add(new com.inducesmile.taxirental.models.CarCategoryObject("", "SAAB"));
        carData.add(new com.inducesmile.taxirental.models.CarCategoryObject("", "VOLVO"));

        return carData;
    }

}
