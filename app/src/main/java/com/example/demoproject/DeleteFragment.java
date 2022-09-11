package com.example.demoproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class DeleteFragment extends Fragment {
    private List<Directory> directoryList;
    private EnglishDirectoryAdapter adapter;
    private ListView listView;
    private LinearLayout linearLayoutTitle;
    private AlertDialog.Builder dialog;
    private DataBaseConnect data;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delete, container, false);
        intit(view);
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
                data.QueryDate("Delete from EnglishDirectory where id = " + String.valueOf(directoryList.get(position).getId()));
                dialog.setTitle("Xóa từ");
                dialog.setMessage("Bạn muốn xóa từ này??");
                dialog.setNegativeButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        directoryList.remove(position);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(getContext(), "Xóa thành công", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.setPositiveButton("Không", null);
                dialog.show();
            }
        });

        return view;
    }


    private void intit(View view) {
        directoryList = new ArrayList<>();
        listView = view.findViewById(R.id.listViewDelete);
        dialog = new AlertDialog.Builder(getContext());
        linearLayoutTitle = view.findViewById(R.id.linearLayoutDelete);
    }
}