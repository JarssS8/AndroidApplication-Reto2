package com.example.androidapplication_reto2.project.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.example.androidapplication_reto2.R;
import com.example.androidapplication_reto2.project.beans.LocalUser;
import com.example.androidapplication_reto2.project.beans.User;
import com.example.androidapplication_reto2.project.utilities.Encryptation;
import com.example.androidapplication_reto2.project.utilities.SQLiteManager;
import com.example.androidapplication_reto2.project.factories.UserFactory;
import com.example.androidapplication_reto2.project.interfaces.RestUser;
import com.google.android.material.snackbar.Snackbar;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    public Button btSignUpMain;
    private Button btSignUp;
    private Button btLogIn;
    private EditText username;
    private EditText password;
    private boolean justSignUp = false;
    private TextView forgotPassword;
    private User user = null;
    private Switch switchRemember;

    /**
     * First instance of components from this activity.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("LogIn", "Initilize of log in layout components");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        Encryptation.getKey();
        btSignUp = findViewById(R.id.btSignUpMain);
        btLogIn = findViewById(R.id.btLogInMain);
        username = findViewById(R.id.txtUsernameMain);
        password = findViewById(R.id.txtPasswordMain);
        switchRemember = findViewById(R.id.switchRemember);
        forgotPassword = findViewById(R.id.txtForgotPassword);

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_NEUTRAL:
                                Intent intent = new Intent(Intent.ACTION_MAIN);
                                startActivity(Intent.createChooser(intent, "Open your Mail Provider..."));
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                final EditText input = new EditText(LoginActivity.this);
                builder.setView(input);
                builder.setMessage(getString(R.string.email_request)).setPositiveButton("OK", dialogClickListener)
                        .setNeutralButton("OK and open mail", dialogClickListener).show();
            }
        });

        SQLiteManager manager = new SQLiteManager(this);
        LocalUser localUser = manager.getUser();
        manager.close();
        if (localUser != null) {
            if (localUser.getActive() == 1) {
                switchRemember.setChecked(true);
                username.setText(localUser.getLogin());
                password.setText(localUser.getPassword());
            }
        }

        Log.i("Login", "Try to get user from sign up activity");
        user = (User) getIntent().getSerializableExtra("user");
        if (user != null) {
            Log.i("Login", "Gets user from sign up activity. Putting values on login and password fields.");
            Snackbar.make(getWindow().getDecorView().getRootView(), "Sign Up completed, now you can LogIn", Snackbar.LENGTH_LONG).show();
            username.setText(user.getLogin());
            password.setText(user.getPassword());
            justSignUp = true;
        }

    }

    /**
     * This method control action onClick when the user press one button in this layout
     *
     * @param v Is a View who controls the event of the onClick action
     */
    public void onClick(View v) throws Exception {
        Log.i("Login", "User clicks on one component of the app");
        Intent intent = null;
        switch (v.getId()) {
            case R.id.btLogInMain:
                Log.i("Login", "Click on login main button");
                if (justSignUp) {
                    Log.i("Login", "User just sign up. Intent to logout don't going to DataBase");
                    intent = new Intent(this, MainFragmentsController.class);
                    //Todo enviar el user
                    startActivity(intent);
                    this.finish();
                }
                Log.i("Login", "Check if the login and password could be correct");
                if (username.getText().toString().trim().length() < 4 || username.getText().toString().trim().length() > 10
                        && password.getText().toString().trim().length() < 8 || password.getText().toString().trim().length() > 14) {

                    Snackbar.make(v, "Username and password format are not correct", Snackbar.LENGTH_SHORT).show();
                    showHardKeyboard(username);

                } else if (username.getText().toString().trim().length() < 4 || username.getText().toString().trim().length() > 10) {
                    Snackbar.make(v, "Username format it's not correct", Snackbar.LENGTH_SHORT).show();
                    showHardKeyboard(username);

                } else if (password.getText().toString().trim().length() < 8 || password.getText().toString().trim().length() > 14) {
                    Snackbar.make(v, "Password format it's not correct", Snackbar.LENGTH_SHORT).show();
                    showHardKeyboard(password);
                } else if (!checkNumberUpperPass()) {
                    Snackbar.make(v, "Password format it's not correct", Snackbar.LENGTH_SHORT).show();
                    showHardKeyboard(password);

                } else {
                    Log.i("Login", "Fields could be correct. Checking connection");
                    if (isConnected()) {

                        RestUser clientUser = UserFactory.getClient();
                        String encryptedPassword=password.getText().toString().trim();
                        encryptedPassword = Encryptation.encrypt(encryptedPassword);
                        Call<ResponseBody> callLogIn = clientUser.logIn(username.getText().toString(), encryptedPassword);
                        callLogIn.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                switch (response.code()) {
                                    case 200:
                                        SQLiteManager sqLiteManager = new SQLiteManager(getApplicationContext());
                                        if (switchRemember.isChecked()) {
                                            LocalUser user = new LocalUser();
                                            user.setLogin(username.getText().toString().trim());
                                            user.setPassword(password.getText().toString().trim());
                                            user.setActive(1);
                                            sqLiteManager.insertUser(user);

                                        } else {
                                            sqLiteManager.changeToNoRemember();
                                        }
                                        sqLiteManager.close();
                                        Intent intent = new Intent(getApplicationContext(), MainFragmentsController.class);
                                        startActivity(intent);
                                        finish();
                                        break;
                                    case 500:
                                        Log.d("LOGIN", response.message());
                                        break;
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {

                            }
                        });

                    } else {
                        Log.i("Login", "User is connected to internet");
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
                Log.i("Login", "Click on SignUp button");
                if (isConnected()) {
                    Log.i("Login", "User has internet connection. Going to sign up window");
                    intent = new Intent(this, SignUpActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
                } else {
                    Log.i("Login", "User hasn't internet connection");
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
     *
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
     *
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

    public void showHardKeyboard(View view) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                if (view.requestFocus()) {
                    InputMethodManager imm = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
                }
            }
        }, 1000);
    }
}




