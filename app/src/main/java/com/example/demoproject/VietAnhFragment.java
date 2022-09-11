package com.example.demoproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class VietAnhFragment extends Fragment {
    private EditText edtTV;
    private TextView txtTA;
    private Button btDich;
    private List<Directory> directoryList;
    private boolean flag = false;
    private String tuCanTim = "";
    private String mtuCanTim = "";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_viet_anh, container, false);
        init(view);
        DataBaseConnect data = new DataBaseConnect(getContext(), "EnglishDirectory.sql", null, 1);
        Cursor cursor = data.GetData("select * from EnglishDirectory");
        while (cursor.moveToNext()) {
            directoryList.add(new Directory(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
        }
        btDich.setOnClickListener(v -> openClickTranslate());

        return view;
    }

    private void openClickTranslate() {
        tuCanTim = edtTV.getText().toString().trim();
        mtuCanTim = tuCanTim;
        for (Directory directory : directoryList) {
            if (directory.getVietnamese().equals(tuCanTim)) {
                txtTA.setText(directory.getEnglish());
                flag = true;
            }
        }
        if (!flag) {
            Toast.makeText(getContext(), "Don't have this word in directory", Toast.LENGTH_SHORT).show();
            flag = false;
        }
    }

    private void init(View view) {
        txtTA = view.findViewById(R.id.textViewTuTiengAnh);
        edtTV = view.findViewById(R.id.editTextTuTiengViet);
        btDich = view.findViewById(R.id.buttonDich);
        directoryList = new ArrayList<>();
    }

    public String getWord() {
        return mtuCanTim;
    }
}

