package com.example.demoproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class StartActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private List<Photo> photoList;
    private TextView txtLogin, txtSignUp, txtContact;
    private  Dialog dialog;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        DataBaseConnect dataBaseConnect = new DataBaseConnect(this,"EnglishDirectory.sql", null, 1);
        dataBaseConnect.QueryDate("create table if not exists EnglishDirectory (id INTEGER primary key autoincrement,english Text(40),vietnamese Text(40), kind Text(40))");
        Cursor cursor = dataBaseConnect.GetData("select * from EnglishDirectory");
        if(cursor.getExtras().isEmpty()){
            dataBaseConnect.QueryDate("INSERT INTO EnglishDirectory VALUES(null," + "'" + "car" + "'," + "'" + "ôtô" + "'," + "'" + "noun" + "'),(null," + "'" + "cat" + "'," + "'" + "con mèo" + "'," + "'" + "noun" + "')");
        }
        init();
        addList();
        ViewPagerAdapter adapter = new ViewPagerAdapter(photoList);
        viewPager.setAdapter(adapter);
        circleIndicator.setViewPager(viewPager);

        txtLogin.setOnClickListener(v->openClickLogin());
        txtSignUp.setOnClickListener(v->openClickSignUp());
        txtContact.setOnClickListener(v->openClickContact());

        autoSlideImages();

    }

    private void autoSlideImages() {
        if(photoList == null || photoList.isEmpty()){
            return;
        }

        if(timer == null){
            timer = new Timer();

        }
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                    int currentImage = viewPager.getCurrentItem();
                    int totalImage = photoList.size()-1;
                    if(currentImage < totalImage){
                        currentImage++;
                        viewPager.setCurrentItem(currentImage);
                    }else{
                        viewPager.setCurrentItem(0);
                    }
                    }
                });
            }
        },500,2000);
    }


    private void openClickContact() {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.fragment_blank);
        dialog.show();
    }

    private void openClickSignUp() {
        Intent intent = new Intent(StartActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    private void openClickLogin() {
        Intent intent = new Intent(StartActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    private void init() {
        viewPager = findViewById(R.id.viewPager);
        circleIndicator = findViewById(R.id.circleIndicator);
        txtLogin = findViewById(R.id.textViewLogin);
        txtContact = findViewById(R.id.textViewContact);
        txtSignUp = findViewById(R.id.textViewSignUp);
    }

    private List<Photo> addList(){
        photoList = new ArrayList<>();
        photoList.add(new Photo(R.drawable.img_3));
        photoList.add(new Photo(R.drawable.img_4));
        photoList.add(new Photo(R.drawable.img_5));
        return  photoList;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(timer != null){
            timer.cancel();
            timer = null;
        }
    }
}