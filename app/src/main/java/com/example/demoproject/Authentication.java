package com.example.demoproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Authentication extends AppCompatActivity {
    private List<Directory> directoryList;
    private Button btThem, btSua, btXoa, btReset, btHienThi,btUser;
    private ListView listView;
    private LinearLayout linearLayoutSubTitle;
    private List<User> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        init();
        btReset.setOnClickListener(v -> openClickReset());
        btThem.setOnClickListener(v -> openClickAdd());
        btSua.setOnClickListener(v->openClickSua());
        btXoa.setOnClickListener(v->openClickXoa());
        btHienThi.setOnClickListener(v->openClickHienThi());
        btUser.setOnClickListener(v->openClickUser());
    }

    private void openClickUser() {
        FragmentTransaction fragmentTransaction =  getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutAuthentication,new DisplayUserFragment());
        fragmentTransaction.commit();
    }

    private void openClickXoa() {
        FragmentTransaction fragmentTransaction =  getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutAuthentication,new DeleteFragment());
        fragmentTransaction.commit();
    }

    private void openClickHienThi() {
        FragmentTransaction fragmentTransaction =  getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutAuthentication,new DisplayFragment());
        fragmentTransaction.commit();
    }

    private void openClickSua() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutAuthentication,new UpdateFragment());
        fragmentTransaction.commit();
    }

    private void openClickReset() {
        DataBaseConnect data = new DataBaseConnect(this, "EnglishDirectory.sql", null, 1);
        data.QueryDate("create table if not exists EnglishDirectory (id INTEGER primary key autoincrement,english Text(40),vietnamese Text(40), kind Text(40))");
        Toast.makeText(Authentication.this,"Successfully",Toast.LENGTH_SHORT).show();
    }

    private void openClickAdd() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutAuthentication,new AddWordFragment());
        fragmentTransaction.commit();
    }

    private void init() {
        directoryList = new ArrayList<>();
        btThem = findViewById(R.id.buttonThem);
        btSua = findViewById(R.id.buttonSua);
        btXoa = findViewById(R.id.buttonXoa);
        btReset = findViewById(R.id.buttonReset);
        listView = findViewById(R.id.listViewEnglishTense);
        linearLayoutSubTitle = findViewById(R.id.linearLayoutSubTitle);
        btHienThi = findViewById(R.id.buttonHienThi);
        btUser = findViewById(R.id.buttonUser);

    }

}