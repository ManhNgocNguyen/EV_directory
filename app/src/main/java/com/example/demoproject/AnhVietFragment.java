package com.example.demoproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;


public class AnhVietFragment extends Fragment {
    private EditText edtAnhViet;
    private TextView txtTA, txtVN, txtKind, txtNoun, txtAdj, txtAdv;
    private LinearLayout linearLayout1, linearLayout2;
    private List<Directory> directoryList;
    private Button btTra;
    private boolean flag = false;
    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_anh_viet, container, false);
        init(view);
        DataBaseConnect data = new DataBaseConnect(getContext(), "EnglishDirectory.sql", null, 1);
        Cursor cursor = data.GetData("select * from EnglishDirectory");
        while (cursor.moveToNext()) {
            directoryList.add(new Directory(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
        }
        btTra.setOnClickListener(v -> openClickForward());
        return view;
    }

    private void openClickForward() {
        String tuCanTim;
        tuCanTim = edtAnhViet.getText().toString().trim();
        Intent intent = new Intent("mainKey");
        intent.putExtra("stringKey", tuCanTim);
        if (getContext() != null) {
            getContext().sendBroadcast(intent);
        }
        for (Directory directory : directoryList) {
            if (directory.getEnglish().equals(tuCanTim) && directory.getKind().equals("noun")) {
                txtTA.setText(directory.getEnglish());
                txtVN.setText(directory.getVietnamese());
                txtKind.setText(directory.getKind());
                txtNoun.setVisibility(View.VISIBLE);
                linearLayout1.setVisibility(View.VISIBLE);
                linearLayout2.setVisibility(View.VISIBLE);
                flag = true;
            }
            if (directory.getEnglish().equals(tuCanTim) && directory.getKind().equals("adjective")) {
                txtTA.setText(directory.getEnglish());
                txtVN.setText(directory.getVietnamese());
                txtKind.setText(directory.getKind());
                txtAdj.setVisibility(View.VISIBLE);
                linearLayout1.setVisibility(View.VISIBLE);
                linearLayout2.setVisibility(View.VISIBLE);
                flag = true;
            }
            if (directory.getEnglish().equals(tuCanTim) && directory.getKind().equals("adverb")) {
                txtTA.setText(directory.getEnglish());
                txtVN.setText(directory.getVietnamese());
                txtKind.setText(directory.getKind());
                txtAdv.setVisibility(View.VISIBLE);
                linearLayout1.setVisibility(View.VISIBLE);
                linearLayout2.setVisibility(View.VISIBLE);
                flag = true;
            }
            if (!flag) {
                Toast.makeText(getContext(), "Don't have this word in directory", Toast.LENGTH_SHORT).show();
            }
        }


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (getContext() != null) {
            getContext().unregisterReceiver(broadcastReceiver);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter("mainKey");
        if (getContext() != null) {
            getContext().registerReceiver(broadcastReceiver, intentFilter);
        }
    }

    private void init(View view) {
        edtAnhViet = view.findViewById(R.id.editTextAnhViet);
        txtTA = view.findViewById(R.id.textViewEnglish);
        txtVN = view.findViewById(R.id.textViewVietNam);
        txtKind = view.findViewById(R.id.textViewKind);
        btTra = view.findViewById(R.id.buttonTra);
        directoryList = new ArrayList<>();
        linearLayout1 = view.findViewById(R.id.linearLayoutAnhViet1);
        linearLayout2 = view.findViewById(R.id.linearLayoutAnhViet2);
        txtNoun = view.findViewById(R.id.textViewDanhTu);
        txtAdj = view.findViewById(R.id.textViewTinhTu);
        txtAdv = view.findViewById(R.id.textViewTrangTu);
    }
}