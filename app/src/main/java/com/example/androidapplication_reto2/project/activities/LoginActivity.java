package com.example.androidapplication_reto2.project.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidapplication_reto2.R;
import com.example.androidapplication_reto2.project.beans.Category;
import com.example.androidapplication_reto2.project.beans.User;
import com.example.androidapplication_reto2.project.interfaces.RestCategory;
import com.example.androidapplication_reto2.project.interfaces.RestUser;
import com.example.androidapplication_reto2.project.retrofitcalls.UserCalls;
import com.google.android.material.snackbar.Snackbar;

import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class LoginActivity extends AppCompatActivity{

    public Button btSignUpMain;
    private Button btSignUp;
    private Button btLogIn;
    private EditText username;
    private EditText password;
    private boolean justSignUp = false;

    /**
     * First instance of components from this activity.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("LogIn","Initilize of log in layout components");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        btSignUp = findViewById(R.id.btSignUpMain);
        btLogIn = findViewById(R.id.btLogInMain);
        username = findViewById(R.id.txtUsernameMain);
        password = findViewById(R.id.txtPasswordMain);

        username.setText("jarsss8");
        password.setText("Absd123123");
        Log.i("Login","Try to get user from sign up activity");
        /*Todo user = (User) getIntent().getSerializableExtra("user");
        if (user != null) {
            Log.i("Login","Gets user from sign up activity. Putting values on login and password fields.");
            Snackbar.make(getWindow().getDecorView().getRootView(),"Sign Up completed, now you can LogIn",Snackbar.LENGTH_LONG).show();
            username.setText(user.getLogin());
            password.setText(user.getPassword());
            justSignUp = true;
        }
        */

    }

    /**
     * This method control action onClick when the user press one button in this layout
     * @param v Is a View who controls the event of the onClick action
     */
    public void onClick(View v) {
        Log.i("Login","User clicks on one component of the app");
        Intent intent = null;
        switch (v.getId()) {
            case R.id.btLogInMain:
                Log.i("Login","Click on login main button");
                if (justSignUp) {
                    Log.i("Login","User just sign up. Intent to logout don't going to DataBase");
                    intent = new Intent(this, MainFragmentsController.class);
                    //Todo enviar el user
                    startActivity(intent);
                    this.finish();
                }
                Log.i("Login","Check if the login and password could be correct");
                if (username.getText().toString().trim().length() < 4 || username.getText().toString().trim().length() > 10
                        && password.getText().toString().trim().length() < 8 || password.getText().toString().trim().length() > 14) {

                    Snackbar.make(v, "Username and password format are not correct", Snackbar.LENGTH_SHORT).show();

                } else if (username.getText().toString().trim().length() < 4 || username.getText().toString().trim().length() > 10) {
                    Snackbar.make(v, "Username format it's not correct", Snackbar.LENGTH_SHORT).show();

                } else if (password.getText().toString().trim().length() < 8 || password.getText().toString().trim().length() > 14) {
                    Snackbar.make(v, "Password format it's not correct", Snackbar.LENGTH_SHORT).show();
                } else if (!checkNumberUpperPass()) {
                    Snackbar.make(v, "Password format it's not correct", Snackbar.LENGTH_SHORT).show();

                } else {
                    Log.i("Login","Fields could be correct. Checking connection");
                    if (isConnected()) {
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("http://192.168.20.91:8080/ServerApplication-Reto2/webresources/")
                                .addConverterFactory(SimpleXmlConverterFactory.create())
                                .build();

                        RestCategory restUser =  retrofit.create(RestCategory.class);

                        Call<Category> logInCall = restUser.findCategoryById(1L);
                        logInCall.enqueue(new Callback<Category>() {
                            @Override
                            public void onResponse(Call<Category> call, Response<Category> response) {
                                Category cat = response.body();
                                Log.d("Response", cat.getName());
                                Intent intent = new Intent(getApplicationContext(), MainFragmentsController.class);
                                startActivity(intent);
                            }

                            @Override
                            public void onFailure(Call<Category> call, Throwable t) {
                                Log.d("Failure","mierda");

                            }
                        });

                    } else {
                        Log.i("Login","User is connected to internet");
                        final Snackbar snackbar = Snackbar.make(v, "NO CONNECTION, CHECK YOUR CONNECTION", Snackbar.LENGTH_INDEFINITE);
                        snackbar.setAction("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                snackbar.dismiss();
                            }
                        });
                        snackbar.show();
                    }
                }

                break;

            case R.id.btSignUpMain:
                Log.i("Login","Click on SignUp button");
                if (isConnected()) {
                    Log.i("Login","User has internet connection. Going to sign up window");
                    intent = new Intent(this, SignUpActivity.class);
                    startActivity(intent);
                } else {
                    Log.i("Login","User hasn't internet connection");
                    final Snackbar snackbar = Snackbar.make(v, "NO CONNECTION, CHECK YOUR CONNECTION", Snackbar.LENGTH_INDEFINITE);
                    snackbar.setAction("OK", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            snackbar.dismiss();
                        }
                    });
                    snackbar.show();
                }
                break;
        }

    }

    /**
     * This methos check if the password contains at least one upper case and one number for the validation
     * @return A boolean affirmative if the validations are correct
     */
    private boolean checkNumberUpperPass() {
        boolean capital = false;
        boolean number = false;
        boolean check = false;
        char[] passwordChar = password.getText().toString().trim().toCharArray();
        for (int i = 0; i < password.getText().toString().trim().length(); i++) {
            if (!number)
                if (Character.isDigit(passwordChar[i])) {
                    number = true;
                }
            if (!capital)
                if (Character.isUpperCase(passwordChar[i])) {
                    capital = true;
                }
            if (capital && number)
                break;
        }
        if (capital && number) {
            check = true;
        }
        return check;
    }

    /**
     * This method checks if the connection with internet is available
     * @return A boolean affirmative if the validations are correct
     */
    public boolean isConnected() {
        boolean connection = false;
        try {
            ConnectivityManager connectivityManager
                    = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            connection = activeNetworkInfo != null && activeNetworkInfo.isConnected();


        } catch (Exception e) {
            Log.e("Connection", e.getMessage());
        }
        return connection;
    }
}




