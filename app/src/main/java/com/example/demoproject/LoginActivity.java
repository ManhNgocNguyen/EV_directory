package com.example.demoproject;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private Button btLogin;
    private TextView txtSignUp;
    private EditText edtUserName, edtPassword;
    private List<User> userList = new ArrayList<>();
    private boolean flag = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        init();
        txtSignUp.setOnClickListener(v -> openClickSignUp());
        btLogin.setOnClickListener(v -> openClickLogin());

    }


    private void openClickLogin() {
        String userName = edtUserName.getText().toString();
        String password = edtPassword.getText().toString();
        DataBaseConnect data = new DataBaseConnect(this, "User.sql", null, 1);
        data.QueryDate("create table if not exists UserDirectory(id INTEGER primary key autoincrement, userName varchar(40), password varchar(40), name text(40), age int(10), address varchar(40))");
        Cursor cursor = data.GetData("select * from UserDirectory");
        if(cursor == null){
            Toast.makeText(LoginActivity.this,"Haven't user yet",Toast.LENGTH_SHORT).show();
        }
        if (cursor != null) {
            while (cursor.moveToNext()) {
                userList.add(new User(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4), cursor.getString(5)));
            }
            for (User user : userList) {
                if (userName.equals(user.getUserName()) && password.equals(user.getPassword())) {
                    Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    flag = true;
                    startActivity(intent);
                }
            }
        }
        if (userName.equals("admin") && password.equals("admin")) {
            goToAuthenticationActivity();
        } else if (!flag) {
            Toast.makeText(LoginActivity.this, "The User Was Wrong!", Toast.LENGTH_SHORT).show();
        }
    }

    private void goToAuthenticationActivity() {
        startActivity(new Intent(LoginActivity.this, Authentication.class));
    }

    private void openClickSignUp() {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    private void init() {
        btLogin = findViewById(R.id.buttonLogin);
        txtSignUp = findViewById(R.id.textViewSignUp);
        edtUserName = findViewById(R.id.editTextName);
        edtPassword = findViewById(R.id.editTextTextPasswordLogin);

    }
}