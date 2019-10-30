package com.inducesmile.taxirental.fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.inducesmile.taxirental.R;
import com.inducesmile.taxirental.utils.Helper;


public class BusinessFragment extends Fragment {

    private static final String TAG = BusinessFragment.class.getSimpleName();

    private TextView businessName;
    private TextView businessAddress;
    private TextView name;
    private TextView description;
    private TextView email;
    private TextView phone;


    public BusinessFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_business, container, false);
        getActivity().setTitle("About Us");

        businessName = (TextView)view.findViewById(R.id.business_name);
        businessAddress = (TextView)view.findViewById(R.id.business_address);
        name = (TextView)view.findViewById(R.id.name);
        description = (TextView)view.findViewById(R.id.description);
        email = (TextView)view.findViewById(R.id.email);
        phone = (TextView)view.findViewById(R.id.phone);

        Button contactUsButton = (Button)view.findViewById(R.id.contact_us);
        contactUsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        Button callUsButton = (Button)view.findViewById(R.id.call_us);
        callUsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initiatePhoneCall();
            }
        });

        return view;
    }


    private void openDialog(){
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View subView = inflater.inflate(R.layout.float_layout, null);

        final EditText mSubject = (EditText)subView.findViewById(R.id.subject);
        final EditText mMessage = (EditText)subView.findViewById(R.id.message);


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Contact Us");
        builder.setView(subView);
        AlertDialog alertDialog = builder.create();

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String subject = mSubject.getText().toString();
                String message = mMessage.getText().toString();

                if(TextUtils.isEmpty(subject) || TextUtils.isEmpty(message)){
                    Helper.displayErrorMessage(getActivity(), "All fields must be filled");
                }
                // send the information to remote server.
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

    private void initiatePhoneCall(){
        Intent callUsIntent = new Intent(Intent.ACTION_CALL);
        callUsIntent.setData(Uri.parse("tel:" + "0720177156"));
        startActivity(callUsIntent);
    }

}
