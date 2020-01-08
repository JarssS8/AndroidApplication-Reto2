package com.example.androidapplication_reto2.project.activities;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;
import androidx.appcompat.app.AppCompatActivity;
import com.example.androidapplication_reto2.R;


public class SignUpActivity extends AppCompatActivity {

    private TextView lbCompleteFields;
    private EditText txtUsername;
    private EditText txtEmail;
    private EditText txtFullName;
    private EditText txtPassword;
    private EditText txtRepeatPassword;
    private ImageButton btHelp;
    private Button btConfirm;
    private Button btGetIt;
    private String errorMessage="";

    /**
     * First instance of components from this activity.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("SignUp","Initilize of sign up layout components");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_activity);
        lbCompleteFields = findViewById(R.id.lbCompleteFields);
        txtUsername = findViewById(R.id.txtUsername);
        txtEmail = findViewById(R.id.txtEmail);
        txtFullName = findViewById(R.id.txtFullName);
        txtPassword = findViewById(R.id.txtPassword);
        txtRepeatPassword = findViewById(R.id.txtRepeatPassword);
        btHelp = findViewById(R.id.btHelp);
        btConfirm = findViewById(R.id.btConfirm);
    }

    /**
     * This method check if the password contains at least one upper case and one number for the validation
     * @return A boolean affirmative if the validations are correct
     */
    public boolean checkPassword(Editable password) {
        boolean capital = false;
        boolean number = false;
        boolean check = false;
        char passwordChar[] = password.toString().trim().toCharArray();
        if(password.toString().trim().length()!=0) {
            for (int i = 0; i < password.toString().trim().length(); i++) {
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
        }
        return check;
    }

    /**
     * This method check if both password fields match
     * @param password A String of one password field
     * @param passwordRepeat A String who should match with the password field
     * @return A boolean affirmative if the validations are correct
     */
    private boolean checkPassRepeat(Editable password, Editable passwordRepeat) {
        boolean checkRepeat = false;
        if (password.toString().trim().equals(passwordRepeat.toString().trim())) {
            checkRepeat = true;
        }
        return checkRepeat;
    }

    /**
     * Checks if the email got the correct format
     * @param email A String of mail field for validate his format
     * @return A boolean affirmative if the validations are correct
     */
     //Todo añadir la comprobacion del email
    private boolean checkEmail(Editable email) {
       // boolean check = Util.validarEmail(email.toString());
        return true;
    }


    /**
     * This method control action onClick when the user press one button in this layout
     * @param v Is a View who controls the event of the onClick action
     */
    public void onClick(View v) {
        Log.i("SignUp","User clicks on one component of the app");
        switch (v.getId()) {
            case R.id.btHelp: {
                Log.i("SignUp", "User click on help button.Creating a layout inflater for the popUp view");
                LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                Log.i("SignUp", "Usign the inflator inflating the layout");
                View popUpView = layoutInflater.inflate(R.layout.popup_signup, null);
                Log.i("SignUp", "Defining pop up componentes");
                final PopupWindow popupWindow = new PopupWindow(popUpView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                btGetIt = popUpView.findViewById(R.id.btGetIt);
                btGetIt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                Log.i("SignUp", "Showing the pop up");
                popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
                break;
            }

            case R.id.btConfirm:
                Log.i("SignUp","User clicks on confirm button");
                errorMessage="";
                boolean passCheck = checkPassword(txtPassword.getText());
                boolean passCheckRepeat = checkPassRepeat(txtPassword.getText(),
                        txtRepeatPassword.getText());
                boolean emailCheck = checkEmail(txtEmail.getText());
                boolean userLength = false;
                boolean passLength = false;
                boolean passRepeat = false;
                boolean passCorrect = false;
                boolean emailCorrect = false;
                Log.i("SignUp","Local comprobations if data could be correct");
                //Check login lenght
                if (txtUsername.length() >= 4 && txtUsername.length() <= 10) {
                    userLength = true;
                }
                else{
                    errorMessage+="Username invalid format\n";
                }
                //Checks password lenght
                if (txtPassword.length() >= 8 && txtPassword.length() <= 14) {
                    passLength = true;
                }else{
                    errorMessage+="Password invalid format\n";
                }
                //Check password format
                if (passCheck) {
                    passCorrect = true;
                } else{
                    errorMessage+="Password must have an upper case and a number\n";
                }
                //Check both password match
                if (passCheckRepeat) {
                    passRepeat = true;
                }else{
                    errorMessage+="Password and repeat password don't match \n";
                }
                //Check email format
                if (emailCheck) {
                    emailCorrect = true;
                }else{
                    errorMessage+="Email invalid format\n";
                }

                if (userLength && passLength && passCorrect && passRepeat && emailCorrect) {
                    Log.i("SignUp","Local comprobations are correct. Creating user with the fields data");

                }
                else{
                    Log.i("SignUp","Creating a pop up with all that is wrong with the sign up");
                    LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                    View popUpView = layoutInflater.inflate(R.layout.popup_signup, null);
                    final PopupWindow popupWindow = new PopupWindow(popUpView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                    btGetIt = popUpView.findViewById(R.id.btGetIt);
                    TextView lbPopUp= popUpView.findViewById(R.id.lbPopUp);
                    lbPopUp.setText(errorMessage);
                    btGetIt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popupWindow.dismiss();
                        }
                    });
                    popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
                }
                break;
        }
    }
}
