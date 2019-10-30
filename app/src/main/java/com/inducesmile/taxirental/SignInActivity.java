package com.inducesmile.taxirental;


import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

//import com.android.carrental.R;
//import com.android.carrental.view.NearbyStations;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String REQUIRED_FIELDS_MESSAGE = "All fields are mandatory";
    private static final String CREDENTIALS_INCORRECT_MESSAGE = "Email or Password Incorrect";
    private static final String EMAIL_KEY = "email";
    private static final String PASSWORD_KEY = "password";
    private static final String REGISTRATION_SUCCESSFUL_MESSAGE = "Registration Successful. Please Login";
    private Button loginButton;
    private Button registerButton;
    private EditText loginEmail;
    private EditText loginPassword;
    private TextView forgot;
    private FirebaseAuth firebaseAuth;
    private ProgressBar loginUserProgress;
    private RelativeLayout mainContent;
    public static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        loginButton = (Button) findViewById(R.id.btn_login);
        registerButton = (Button) findViewById(R.id.btn_register);
        loginEmail = (EditText) findViewById(R.id.email);
        loginPassword = (EditText) findViewById(R.id.password);
        forgot=(TextView)findViewById(R.id.forgotten_password);

        //loginUserProgress = (ProgressBar) findViewById(R.id.login_user_progress);
        //mainContent = (RelativeLayout) findViewById(R.id.main_content_login);
        firebaseAuth = FirebaseAuth.getInstance();
        loginButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);
        forgot.setOnClickListener(this);
        getSupportActionBar().setTitle("Login");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                loginUser();
                break;
            case R.id.btn_register:
                registerUser();
                break;
            case R.id.forgotten_password:
                Intent forgottenIntent = new Intent(SignInActivity.this, ForgottenActivity.class);
                startActivity(forgottenIntent);
                break;


        }

    }

    private void loginUser() {

        final String eemail = loginEmail.getText().toString().trim();
        String ppassword = loginPassword.getText().toString().trim();


        if (eemail.isEmpty()) {
            loginEmail.setError(getString(R.string.input_error_email));
            loginEmail.requestFocus();
            return;
        }

        if (ppassword.isEmpty()) {
            loginPassword.setError(getString(R.string.input_error_password));
            loginPassword.requestFocus();
            return;
        }
        makeFieldsNonEditable();
        FirebaseAuth.getInstance().signInWithEmailAndPassword(loginEmail.getText().toString(), loginPassword.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            openHomeScreen();
                        } else {
                            makeFieldsEditable();
                            Toast.makeText(getApplicationContext(), CREDENTIALS_INCORRECT_MESSAGE, Toast.LENGTH_SHORT).show();
                        }
                        //loginUserProgress.setVisibility(View.GONE);
                    }
                });
        /*
        if (!loginEmail.getText().toString().isEmpty() && !loginPassword.getText().toString().isEmpty()) {
            makeFieldsNonEditable();
            FirebaseAuth.getInstance().signInWithEmailAndPassword(loginEmail.getText().toString(), loginPassword.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                openHomeScreen();
                            } else {
                                //makeFieldsEditable();
                                Toast.makeText(getApplicationContext(), CREDENTIALS_INCORRECT_MESSAGE, Toast.LENGTH_SHORT).show();
                            }
                            //loginUserProgress.setVisibility(View.GONE);
                        }
                    });
        } else {
            loginUserProgress.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), REQUIRED_FIELDS_MESSAGE, Toast.LENGTH_SHORT).show();
        }

         */
    }

    private void registerUser() {
        //loginUserProgress.setVisibility(View.GONE);
        Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            loginEmail.setText(data.getStringExtra(EMAIL_KEY));
            loginPassword.setText(data.getStringExtra(PASSWORD_KEY));
            Toast.makeText(this, REGISTRATION_SUCCESSFUL_MESSAGE, Toast.LENGTH_SHORT).show();
        }
    }

    private void makeFieldsNonEditable() {
        loginEmail.setEnabled(false);
        loginPassword.setEnabled(false);
        loginButton.setEnabled(false);
    }

    private void makeFieldsEditable() {
        loginEmail.setEnabled(true);
        loginPassword.setEnabled(true);
        loginButton.setEnabled(true);
    }

    private void openHomeScreen() {
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
        finish();
    }
}

/*
import android.content.Intent;
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
import android.widget.TextView;

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

public class SignInActivity extends AppCompatActivity {

    private static final String TAG = SignInActivity.class.getSimpleName();

    private EditText email, password;

    private TextView forgottenPassword, signInLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ActionBar actionBar = getSupportActionBar();
        if(null != actionBar){
            actionBar.hide();
        }

        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);

        forgottenPassword = (TextView)findViewById(R.id.forgotten_password);
        signInLink = (TextView)findViewById(R.id.link_to_registration);

        forgottenPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgottenIntent = new Intent(SignInActivity.this, ForgottenActivity.class);
                startActivity(forgottenIntent);
            }
        });

        signInLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(signInIntent);
            }
        });

        Button loginButton = (Button)findViewById(R.id.btnLogin);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EmailValidator validator = EmailValidator.getInstance();

                String mEmail = email.getText().toString();
                String mPassword = password.getText().toString();

                if(TextUtils.isEmpty(mEmail) || TextUtils.isEmpty(mPassword)){
                    Helper.displayErrorMessage(SignInActivity.this, "Email and password fields must be filled");
                }else if(!validator.isValid(mEmail)){
                        Helper.displayErrorMessage(SignInActivity.this, "You have entered an invalid email");
                }else{
                    if(Helper.isNetworkAvailable(SignInActivity.this)){
                        loginCallToServer(mEmail, mPassword);
                    }else{
                        Helper.displayErrorMessage(SignInActivity.this, "No network available");
                    }
                }

                Intent homeIntent = new Intent(SignInActivity.this, HomeActivity.class);
                startActivity(homeIntent);
            }
        });
    }

    private void loginCallToServer(String email, String password){
        Map params = getParams(email, password);
        GsonRequest<UserObject> serverRequest = new GsonRequest<UserObject>(
                Request.Method.POST,
                Constants.PATH_TO_SERVER_LOGIN,
                UserObject.class,
                params,
                createRequestSuccessListener(),
                createRequestErrorListener());

        ((CustomApplication)getApplication()).getNetworkCall().callToRemoteServer(serverRequest);
    }

    private Map getParams(String email, String password){
        Map<String, String> params = new HashMap<String,String>();
        params.put(Constants.EMAIL, email);
        params.put(Constants.PASSWORD, password);
        return params;
    }

    private Response.Listener<UserObject> createRequestSuccessListener() {
        return new Response.Listener<UserObject>() {
            @Override
            public void onResponse(UserObject response) {
                try {
                    if(response != null){
                        //save user login data to a shared preference
                        String userData = ((CustomApplication)getApplication()).getGsonObject().toJson(response);
                        ((CustomApplication)getApplication()).getShared().setUserData(userData);

                        Intent homeIntent = new Intent(SignInActivity.this, HomeActivity.class);
                        startActivity(homeIntent);
                    } else{
                        Helper.displayErrorMessage(SignInActivity.this, "Login failed");
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
*/