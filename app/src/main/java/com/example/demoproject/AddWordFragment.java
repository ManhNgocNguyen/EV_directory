package com.example.demoproject;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AddWordFragment extends Fragment {
    private EditText edtEnglish, edtVietnamese, edtKind;
    private Button btnAddWord;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_word, container, false);

        edtEnglish = view.findViewById(R.id.editTextEnglishAddWord);
        edtVietnamese = view.findViewById(R.id.editTextVietnameseAddWord);
        edtKind = view.findViewById(R.id.editTextKindAddWord);
        btnAddWord = view.findViewById(R.id.buttonAddWord);
        btnAddWord.setOnClickListener(v -> openClickAddWord());

        return view;
    }

    private void openClickAddWord() {
        DataBaseConnect dataBaseConnect = new DataBaseConnect(getContext(), "EnglishDirectory.sql", null, 1);
        dataBaseConnect.QueryDate("create table if not exists EnglishDirectory (id INTEGER primary key autoincrement,english Text(40),vietnamese Text(40), kind Text(40))");
        dataBaseConnect.QueryDate("INSERT INTO EnglishDirectory VALUES(null," + "'" + edtEnglish.getText().toString() + "'," + "'" + edtVietnamese.getText().toString() + "'," + "'" + edtKind.getText().toString() + "')");
        Toast.makeText(getContext(), "Successfully", Toast.LENGTH_SHORT).show();
        clearText();
    }

    private void clearText() {
        edtKind.setText("");
        edtVietnamese.setText("");
        edtEnglish.setText("");
    }
}