package com.pro.salon.cattocdi;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.pro.salon.cattocdi.utils.MyContants;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin, btnSignup ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.login_activity_login_btn);
        btnSignup = findViewById(R.id.login_activity_register_btn);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkLogin()){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    showDialogLoginFail();
                }
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

    }

    private boolean checkLogin(){
        boolean result = false;
        EditText etPhone = findViewById(R.id.login_activity_username_et);
        EditText etPassword= findViewById(R.id.login_activity_password_et);
        if(etPhone.getText().toString().equals(MyContants.USERNAME_TEST) && etPassword.getText().toString().equals(MyContants.PASSWORD_TEST) ){
            result = true;
        }
        return result;
    }

    private void showDialogLoginFail(){
        final AlertDialog dialog = new AlertDialog.Builder(LoginActivity.this).create();
        dialog.setTitle("Không chính xác");
        dialog.setMessage("Tên đăng nhập hoặc mật khẩu không chính xác");
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


}
