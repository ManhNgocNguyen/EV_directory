package com.example.demoproject;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class DongNghiaFragment extends Fragment {
    private List<Directory> directoryList;
    private List<Directory> sameMeaningList;
    private DongNghiaAdapter adapter;
    private ListView listView;
    private EditText edtTimKiem;
    private Button btTra;
private boolean flag;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dong_nghia, container, false);
        init(view);
        getInformationFromDatabase();
        btTra.setOnClickListener(v -> clickOpenTra());
        return view;
    }

    private void getInformationFromDatabase() {
        DataBaseConnect data = new DataBaseConnect(getContext(), "EnglishDirectory.sql", null, 1);
        Cursor cursor = data.GetData("select * from EnglishDirectory");
        while (cursor.moveToNext()) {
            directoryList.add(new Directory(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
        }

    }

    private void clickOpenTra() {
        sameMeaningList.clear();
        String tuCanTim = edtTimKiem.getText().toString().trim();
        for (Directory directory : directoryList) {
            if (directory.getVietnamese().contains(tuCanTim)) {
                sameMeaningList.add(new Directory(directory.getId(), directory.getEnglish(), directory.getVietnamese(), directory.getKind()));
                flag = true;
            }
        }
        if(!flag){
            Toast.makeText(getContext(),"This word don't have in directory",Toast.LENGTH_SHORT).show();
        }
        adapter = new DongNghiaAdapter(sameMeaningList, getContext(), R.layout.tu_dong_nghia_layout);
        listView.setAdapter(adapter);
    }

    private void init(View view) {
        directoryList = new ArrayList<>();
        sameMeaningList = new ArrayList<>();
        listView = view.findViewById(R.id.listViewDongNghia);
        edtTimKiem = view.findViewById(R.id.editTextTuCanTimDongNghia);
        btTra = view.findViewById(R.id.buttonTraDongNghia);
    }
}