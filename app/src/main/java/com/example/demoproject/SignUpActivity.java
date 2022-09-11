package com.example.demoproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    private EditText edtUserName, edtPassword, edtName, edtAge, edtAddress;
    private Button btSignUp;
    private TextView txtUserName ,txtPassword, txtName, txtAge, txtAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        init();


        btSignUp.setOnClickListener(v -> openClickSignUp());


    }

    private void openClickSignUp() {

        String userName = edtUserName.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        String name =  edtName.getText().toString().trim();
        String age =  edtAge.getText().toString().trim();
        String address = edtAddress.getText().toString().trim();

        errorColor(userName,password,name,age,address);
        successColor(userName,password,name,age,address);

        if(!userName.equals("")  && !password.equals("")  && !name.equals("") && !age.equals("") && !address.equals("")){
            DataBaseConnect dataBaseConnect = new DataBaseConnect(this,"User.sql",null,1);
            dataBaseConnect.QueryDate("create table if not exists UserDirectory(id INTEGER primary key autoincrement, userName varchar(40), password varchar(40), name text(40), age int(10), address varchar(40))");
            dataBaseConnect.QueryDate("INSERT INTO UserDirectory VALUES(null,"+"'"+userName+"',"+"'"+password+"',"+"'"+name+"',"+"'"+age+"',"+"'"+address+"')");
            Toast.makeText(SignUpActivity.this,"Sign up successfully",Toast.LENGTH_SHORT).show();
            clear();
            goToMainActivity();
        }

    }

    private void clear() {
        edtUserName.getText().clear();
        edtPassword.getText().clear();
        edtName.getText().clear();
        edtAge.getText().clear();
        edtAddress.getText().clear();
    }

    private void successColor(String userName, String password, String name, String age, String address) {
        if (!userName.equals("")) {
            txtUserName.setTextColor(Color.rgb(0,0,0));

        }

        if (!name.equals("")) {
            txtName.setTextColor(Color.rgb(0,0,0));

        }
        if (!age.equals("")) {
            txtAge.setTextColor(Color.rgb(0,0,0));

        } if (!address.equals("")) {
            txtAddress.setTextColor(Color.rgb(0,0,0));

        }
        if (!password.equals("")) {
            txtPassword.setTextColor(Color.rgb(0,0,0));


        }
    }


    private void errorColor(String userName,String password, String name, String age, String address ) {
        if (userName.equals("")) {
            txtUserName.setTextColor(Color.rgb(200,0,0));
            Toast.makeText(SignUpActivity.this,"UserName can't be emptied",Toast.LENGTH_SHORT).show();
        }
        if (name.equals("")) {
            txtName.setTextColor(Color.rgb(200,0,0));
            Toast.makeText(SignUpActivity.this,"Name can't be emptied",Toast.LENGTH_SHORT).show();
        }
        if (age.equals("")) {
            txtAge.setTextColor(Color.rgb(200,0,0));
            Toast.makeText(SignUpActivity.this,"Age can't be emptied",Toast.LENGTH_SHORT).show();
        } if (address.equals("")) {
            txtAddress.setTextColor(Color.rgb(200,0,0));
            Toast.makeText(SignUpActivity.this,"Address can't be emptied",Toast.LENGTH_SHORT).show();
        }
        if (password.equals("")) {
            txtPassword.setTextColor(Color.rgb(200,0,0));
            Toast.makeText(SignUpActivity.this,"Password can't be emptied",Toast.LENGTH_SHORT).show();
        }
    }

    private void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    private void init() {
        edtUserName = findViewById(R.id.editTextUserNameSignUp);
        edtPassword = findViewById(R.id.editTextPasswordSignUp);
        edtName = findViewById(R.id.editTextNameSignUp);
        edtAge = findViewById(R.id.editTextAgeSignUp);
        edtAddress = findViewById(R.id.editTextAddressSignUp);
        btSignUp = findViewById(R.id.buttonSignUp);
        txtUserName = findViewById(R.id.textViewUserName);
        txtPassword = findViewById(R.id.textViewPassword);
        txtName = findViewById(R.id.textViewName);
        txtAge = findViewById(R.id.textViewAge);
        txtAddress = findViewById(R.id.textViewAddress);
    }
}