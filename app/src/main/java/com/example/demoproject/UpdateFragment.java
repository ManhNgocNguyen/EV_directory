package com.example.demoproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class UpdateFragment extends Fragment {
    private List<Directory> directoryList;
    private EnglishDirectoryAdapter adapter;
    private ListView listView;
    private LinearLayout linearLayoutTitle;
    private DataBaseConnect data;
    private EditText edtEnglish, edtVietnamese, edtKind;
    private Button btnUpdate;
    private Dialog dialog;
    private int vitri;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update, container, false);
        init(view);
        data = new DataBaseConnect(getContext(), "EnglishDirectory.sql", null, 1);
        data.QueryDate("create table if not exists EnglishDirectory (id INTEGER primary key autoincrement,english Text(40),vietnamese Text(40), kind Text(40))");
        Cursor cursor = data.GetData("Select * from EnglishDirectory");
        while (cursor.moveToNext()) {
            directoryList.add(new Directory(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
        }
        adapter = new EnglishDirectoryAdapter(getContext(), R.layout.english_directory_layout, directoryList);
        listView.setAdapter(adapter);
        linearLayoutTitle.setVisibility(View.VISIBLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                vitri = position;
                dialog.show();
                 btnUpdate.setOnClickListener(v -> openClickUpdate());
            }
        });

        return view;
    }



    private void openClickUpdate() {
        directoryList.set(vitri, new Directory(directoryList.get(vitri).getId(), edtEnglish.getText().toString(), edtVietnamese.getText().toString(), edtKind.getText().toString()));
        data.QueryDate("Update EnglishDirectory set english = " + "'" + edtEnglish.getText().toString() + "'" + ",vietnamese = " + "'" + edtVietnamese.getText().toString() + "'" + ",kind = " + "'" + edtKind.getText().toString() + "'" + "where id = " + String.valueOf(directoryList.get(vitri).getId()));
        adapter.notifyDataSetChanged();
        Toast.makeText(getContext(), "Cập nhập thành công", Toast.LENGTH_SHORT).show();
        dialog.cancel();
        edtEnglish.setText("");
        edtVietnamese.setText("");
        edtKind.setText("");
    }

    private void init(View view) {
        directoryList = new ArrayList<>();
        listView = view.findViewById(R.id.listViewUpdate);
        dialog = new Dialog(getContext());
        dialog.setTitle("Cập nhập");
        dialog.setContentView(R.layout.update_layout);
        Window window = dialog.getWindow();
        if(window == null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        window.setAttributes(layoutParams);
        linearLayoutTitle = view.findViewById(R.id.linearLayoutUpdate);
        edtEnglish = dialog.findViewById(R.id.editTextEnglishUpdate);
        edtVietnamese = dialog.findViewById(R.id.editTextVietnameseUpdate);
        edtKind = dialog.findViewById(R.id.editTextKindUpdate);
        btnUpdate = dialog.findViewById(R.id.buttonUpdate);
    }
}