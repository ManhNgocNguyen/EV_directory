package com.example.demoproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class TuDaTraFragment extends Fragment {
    private List<String> tuDaTraList;
    private ArrayAdapter adapter;
    private ListView listView;
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if("Key".equals(intent.getAction())){
                tuDaTraList = new ArrayList<>();
                tuDaTraList.add(intent.getStringExtra("stringKey"));
                adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1,tuDaTraList);
                listView.setAdapter(adapter);
            }
        }
    };



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tu_da_tra, container, false);
        listView = view.findViewById(R.id.listViewDaTra);
        return view;
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
}