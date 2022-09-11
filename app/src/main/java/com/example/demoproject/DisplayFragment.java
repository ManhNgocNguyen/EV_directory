package com.example.demoproject;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class DisplayFragment extends Fragment {
    private List<Directory> directoryList;
    private EnglishDirectoryAdapter adapter;
    private ListView listView;
    private LinearLayout linearLayoutTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_display, container, false);
            init(view);
        DataBaseConnect dataBaseConnect = new DataBaseConnect(getContext(),"EnglishDirectory.sql", null, 1);
        dataBaseConnect.QueryDate("create table if not exists EnglishDirectory (id INTEGER primary key autoincrement,english Text(40),vietnamese Text(40), kind Text(40))");
        Cursor cursor = dataBaseConnect.GetData("select * from EnglishDirectory");
        while (cursor.moveToNext()){
            directoryList.add(new Directory(cursor.getInt(0), cursor.getString(1),cursor.getString(2),cursor.getString(3)));
        }
        adapter = new EnglishDirectoryAdapter(getContext(),R.layout.english_directory_layout,directoryList);
        linearLayoutTitle.setVisibility(View.VISIBLE);
        listView.setAdapter(adapter);
        return view;
    }

    private void init(View view) {
        directoryList = new ArrayList<>();
        listView = view.findViewById(R.id.lisViewDisplay);
        linearLayoutTitle = view.findViewById(R.id.linearLayoutDisplay);
    }
}