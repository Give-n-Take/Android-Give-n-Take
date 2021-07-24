package com.theo.giventake_android;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.theo.giventake_android.model.Model;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText emailField = findViewById(R.id.register_mail);
        EditText passwordField = findViewById(R.id.register_password);
        Button connectLogin = findViewById(R.id.login_btn);

        connectLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = emailField.getText().toString();
                String password = passwordField.getText().toString();

                Model model = Model.getInstance(LoginActivity.this.getApplication());
                model.login(username, password);


            }
        });
    }
}