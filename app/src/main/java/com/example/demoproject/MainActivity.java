package com.example.demoproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.app.ActionBar;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.demoproject.Adapter;
import com.example.demoproject.FunctionName;
import com.example.demoproject.R;
import com.example.demoproject.TraNhanhFragment;
import com.example.demoproject.VietAnhFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SearchView searchView;
    private List<FunctionName> functionNameList;
    private FunctionAdapter functionAdapter;
    private Adapter adapter;
    private ListView listView;
    private LinearLayout listViewLayout, edtLayout;
    private List<Directory> directoryList;
    private NavHostFragment navHostFragment;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }

    private void init(){
        listViewLayout = findViewById(R.id.listViewLayout);
        edtLayout = findViewById(R.id.editTextLayout);
        listView = findViewById(R.id.listView);
        directoryList = new ArrayList<>();
        functionNameList = new ArrayList<>();
        functionNameList.add(new FunctionName("Từ điển Việt Anh",R.drawable.img));
        functionNameList.add(new FunctionName("Cửa sổ Tra nhanh",R.drawable.img));
        functionNameList.add(new FunctionName("Từ đã tra",R.drawable.img));
        functionNameList.add(new FunctionName("Từ vựng thông dụng",R.drawable.img));

        functionAdapter = new FunctionAdapter(this, R.layout.row_function_name_layout, functionNameList);

        listView.setAdapter(functionAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    listViewLayout.setVisibility(View.GONE);
                    edtLayout.setVisibility(View.GONE);
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new VietAnhFragment()).commit();
                }else if(i ==1){
                    listViewLayout.setVisibility(View.GONE);
                    edtLayout.setVisibility(View.GONE);
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new TraNhanhFragment()).commit();
                }else if(i ==2){
                    listViewLayout.setVisibility(View.GONE);
                    edtLayout.setVisibility(View.GONE);
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new TuDaTraFragment()).commit();
                }else if(i ==3){
                    listViewLayout.setVisibility(View.GONE);
                    edtLayout.setVisibility(View.GONE);
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new TuVungThongDungFragment()).commit();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        DataBaseConnect data = new DataBaseConnect(MainActivity.this,"EnglishDirectory.sql",null,1);
        data.QueryDate("create table if not exists EnglishDirectory (id INTEGER primary key autoincrement,english Text(40),vietnamese Text(40), kind Text(40))");
        Cursor cursor = data.GetData("select * from EnglishDirectory");
        while(cursor.moveToNext()){
            directoryList.add(new Directory(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3)));
        }
        adapter = new Adapter(this, R.layout.search_view_layout,directoryList);
        getMenuInflater().inflate(R.menu.search_menu,menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.search_menu).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                adapter.getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });
        return true;
    }


}