package com.inducesmile.taxirental.fragment;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import com.inducesmile.taxirental.R;
import com.inducesmile.taxirental.adapter.SearchAdapter;
import com.inducesmile.taxirental.models.SearchObject;
import com.inducesmile.taxirental.utils.FullScreenDialog;
import com.inducesmile.taxirental.utils.Helper;

import java.util.ArrayList;
import java.util.List;


public class SearchCarFragment extends Fragment {

    private static final String TAG = SearchCarFragment.class.getSimpleName();

    private LinearLayout filterLayout;
    private LinearLayout sortLayout;

    private RecyclerView carRecyclerView;

    private int selectedPriceAmount;


    public SearchCarFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_car, container, false);

        getActivity().setTitle("Explore Cars");

        filterLayout = (LinearLayout) view.findViewById(R.id.filter_cars);
        filterLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager ft = getActivity().getSupportFragmentManager();
                FullScreenDialog newFragment = FullScreenDialog.newInstance();
                newFragment.show(ft, "dialog");
            }
        });

        sortLayout = (LinearLayout)view.findViewById(R.id.sort_cars);
        sortLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSortDialog();
            }
        });

        carRecyclerView = (RecyclerView)view.findViewById(R.id.all_cars);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        carRecyclerView.setLayoutManager(linearLayoutManager);
        carRecyclerView.setHasFixedSize(true);

        SearchAdapter sAdapter = new SearchAdapter(getActivity(), getTestData());
        carRecyclerView.setAdapter(sAdapter);

        return view;
    }

    private List<SearchObject> getTestData() {
        List<SearchObject> data = new ArrayList<>();
        data.add(new SearchObject("", "VW GOLF", "320 $", "Automatic, 5-seater, Petrol, Wifi", "Daily"));
        data.add(new SearchObject("", "VW GOLF", "320 $", "Automatic, 5-seater, Petrol, Wifi", "Daily"));
        data.add(new SearchObject("", "VW GOLF", "320 $", "Automatic, 5-seater, Petrol, Wifi", "Daily"));
        data.add(new SearchObject("", "VW GOLF", "320 $", "Automatic, 5-seater, Petrol, Wifi", "Daily"));
        data.add(new SearchObject("", "VW GOLF", "320 $", "Automatic, 5-seater, Petrol, Wifi", "Daily"));
        data.add(new SearchObject("", "VW GOLF", "320 $", "Automatic, 5-seater, Petrol, Wifi", "Daily"));
        return data;
    }


    private void openSortDialog(){
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View subView = inflater.inflate(R.layout.sort_layout, null);

        SeekBar seekBar = (SeekBar)subView.findViewById(R.id.seek_bar_price);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                selectedPriceAmount = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("SORT CAR BY DAILY PRICE");
        builder.setView(subView);
        AlertDialog alertDialog = builder.create();

        builder.setPositiveButton("DONE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(selectedPriceAmount == 0){
                    Helper.displayErrorMessage(getActivity(), "Nothing is selected");
                }else{
                    Helper.displayErrorMessage(getActivity(), "Price " + selectedPriceAmount);
                }
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Helper.displayErrorMessage(getActivity(), "Cancel");
            }
        });

        builder.show();
    }

}
