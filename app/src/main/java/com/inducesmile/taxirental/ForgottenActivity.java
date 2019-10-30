package com.inducesmile.taxirental;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.inducesmile.taxirental.models.UserObject;
import com.inducesmile.taxirental.network.GsonRequest;
import com.inducesmile.taxirental.utils.Constants;
import com.inducesmile.taxirental.utils.CustomApplication;
import com.inducesmile.taxirental.utils.Helper;

import org.apache.commons.validator.routines.EmailValidator;

import java.util.HashMap;
import java.util.Map;

public class ForgottenActivity extends AppCompatActivity {

    private static final String TAG = ForgottenActivity.class.getSimpleName();

    private EditText passwordRecovery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotten);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ActionBar actionBar = getSupportActionBar();
        if(null != actionBar){
            actionBar.hide();
        }


        passwordRecovery = (EditText)findViewById(R.id.reset_password);

        Button passwordResetButton = (Button)findViewById(R.id.password_reset_button);
        passwordResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EmailValidator validator = EmailValidator.getInstance();
                String resetPassword = passwordRecovery.getText().toString();
                if(TextUtils.isEmpty(resetPassword)){
                    Helper.displayErrorMessage(ForgottenActivity.this, "Email field is empty");
                }
                else if(!validator.isValid(resetPassword)){
                    Helper.displayErrorMessage(ForgottenActivity.this, "You have entered invalid email");
                }else{
                    // submit email address to server.
                    Map params = getParams(resetPassword);
                    GsonRequest<UserObject> serverRequest = new GsonRequest<UserObject>(
                            Request.Method.POST,
                            Constants.PATH_TO_SERVER_RESET_PASSWORD,
                            UserObject.class,
                            params,
                            createRequestSuccessListener(),
                            createRequestErrorListener());

                    ((CustomApplication)getApplication()).getNetworkCall().callToRemoteServer(serverRequest);
                }
            }
        });
    }

    private Map getParams(String email){
        Map<String, String> params = new HashMap<String,String>();
        params.put(Constants.EMAIL, email);
        return params;
    }

    private Response.Listener<UserObject> createRequestSuccessListener() {
        return new Response.Listener<UserObject>() {
            @Override
            public void onResponse(UserObject response) {
                try {
                    if(response != null){

                    }else{
                        Helper.displayErrorMessage(ForgottenActivity.this, "You email was not found in database");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    private Response.ErrorListener createRequestErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        };
    }
}
