package com.example.androidapplication_reto2.project.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

    private Button btSignUpMain;
    private Button btSignUp;
    private Button btLogIn;
    private EditText username;
    private EditText password;
    private boolean justSignUp = false;
    private TextView forgotPassword;
    private User user = null;
    private Switch switchRemember;
    private ImageView imageButtonCall;
    private static String login;

    /**
     * First instance of components from this activity. Including two listeners for that componentes,
     * when the user want interact with the call icon or try to recover his password.
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
        imageButtonCall = findViewById(R.id.imageButtonCall);

        //Open the phone caller with our phone number
        imageButtonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(getString(R.string.telf_number)));
                startActivity(intent);
            }
        });
        //Execute method when user want recover his password
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText input = new EditText(LoginActivity.this);
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                RestUser restUser = UserFactory.getClient();
                                Call<Void> restorePassCall = restUser.restorePassword(input.getText().toString());
                                restorePassCall.enqueue(new Callback<Void>() {
                                    @Override
                                    public void onResponse(Call<Void> call, Response<Void> response) {
                                        switch (response.code()) {
                                            case 204:
                                                Snackbar.make(getWindow().getDecorView().getRootView(), getString(R.string.information_reset_password_correctly), Snackbar.LENGTH_SHORT).show();
                                                break;
                                            case 404:
                                                Snackbar.make(getWindow().getDecorView().getRootView(), getString(R.string.email_not_found), Snackbar.LENGTH_SHORT).show();
                                                break;
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Void> call, Throwable t) {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                        builder.setMessage(getString(R.string.client_error)).show();
                                    }
                                });
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setView(input);
                builder.setMessage(getString(R.string.email_request)).setPositiveButton(getString(R.string.confirmation_reset_password), dialogClickListener).setNegativeButton(getString(R.string.no),dialogClickListener).show();
            }
        });

        //Check if there are any username saved on my local database
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

    }

    /**
     * This method control action onClick when the user press one button in this layout. Controls signup and login buttons.
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
                    startActivity(intent);
                    this.finish();
                }
                Log.i("Login", "Check if the login and password could be correct");
                if (username.getText().toString().trim().length() < 4 || username.getText().toString().trim().length() > 10
                        && password.getText().toString().trim().length() < 8 || password.getText().toString().trim().length() > 14) {

                    Snackbar.make(v, getString(R.string.user_and_pass_no_correct_format), Snackbar.LENGTH_SHORT).show();
                    showHardKeyboard(username);

                } else if (username.getText().toString().trim().length() < 4 || username.getText().toString().trim().length() > 10) {
                    Snackbar.make(v, getString(R.string.user_no_correct_format), Snackbar.LENGTH_SHORT).show();
                    showHardKeyboard(username);

                } else if (password.getText().toString().trim().length() < 8 || password.getText().toString().trim().length() > 14) {
                    Snackbar.make(v, getString(R.string.pass_no_correct_format), Snackbar.LENGTH_SHORT).show();
                    showHardKeyboard(password);
                } else if (!checkNumberUpperPass()) {
                    Snackbar.make(v, getString(R.string.pass_no_correct_format), Snackbar.LENGTH_SHORT).show();
                    showHardKeyboard(password);

                } else {
                    Log.i("Login", "Fields could be correct. Checking connection");
                    if (isConnected()) {

                        RestUser clientUser = UserFactory.getClient();
                        String encryptedPassword = password.getText().toString().trim();
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
                                            setLogin(user.getLogin());

                                        } else {
                                            sqLiteManager.changeToNoRemember();
                                        }
                                        sqLiteManager.close();
                                        Intent intent = new Intent(getApplicationContext(), MainFragmentsController.class);
                                        startActivity(intent);
                                        finish();
                                        break;
                                    case 404:
                                        Snackbar.make(getWindow().getDecorView().getRootView(), getString(R.string.email_not_found), Snackbar.LENGTH_SHORT).show();
                                        break;
                                    case 401:
                                        Snackbar.make(getWindow().getDecorView().getRootView(), getString(R.string.user_wrong_password), Snackbar.LENGTH_SHORT).show();
                                        break;
                                    default:
                                        Log.d("LOGIN", response.message());
                                        Snackbar.make(getWindow().getDecorView().getRootView(), getString(R.string.server_error), Snackbar.LENGTH_SHORT).show();
                                        break;
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage(getString(R.string.client_error)).show();
                            }
                        });

                    } else {
                        Log.i("Login", "User is connected to internet");
                        final Snackbar snackbar = Snackbar.make(v, getString(R.string.no_connection), Snackbar.LENGTH_INDEFINITE);
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
                    final Snackbar snackbar = Snackbar.make(v, getString(R.string.no_connection), Snackbar.LENGTH_INDEFINITE);
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
     * This method check if the password contains at least one upper case and one number for the validation
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

    /**
     * Method that force the keyboard when an action happens
     *
     * @param view that is execute this method
     */
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

    public static String getLogin() {
        return login;
    }

    public static void setLogin(String login) {
        LoginActivity.login = login;
    }
}




