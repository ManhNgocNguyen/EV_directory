package com.example.demoproject;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TuVungThongDungFragment extends Fragment {

    private Dialog dialog;
    private List<TuVungThongDung> vungThongDungList;
    private ListView listView;
    private TuVungThongDungAdapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tu_vung_thong_dung, container, false);
        dialog = new Dialog(getContext());
        dialog.setTitle("Từ điển thông dụng");
        dialog.setContentView(R.layout.tu_vung_thong_dung_dialog);
        listView = dialog.findViewById(R.id.listViewTuVungThongDung);
        addList();
        adapter = new TuVungThongDungAdapter(vungThongDungList,R.layout.tu_vung_thong_dung_layout,getContext());
        listView.setAdapter(adapter);
        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        return view;
    }


    private void addList() {
        vungThongDungList = new ArrayList<>();
        vungThongDungList.add(new TuVungThongDung("map","Bản đồ"));
        vungThongDungList.add(new TuVungThongDung("government","Chính phủ"));
        vungThongDungList.add(new TuVungThongDung("way","Đường"));
        vungThongDungList.add(new TuVungThongDung("art","Nghệ thuật"));
        vungThongDungList.add(new TuVungThongDung("world","Thế giới"));
        vungThongDungList.add(new TuVungThongDung("computer","Máy tính"));
        vungThongDungList.add(new TuVungThongDung("people","Người"));
        vungThongDungList.add(new TuVungThongDung("two","Hai"));
        vungThongDungList.add(new TuVungThongDung("family","Gia đình"));
        vungThongDungList.add(new TuVungThongDung("history","Lịch sử"));
        vungThongDungList.add(new TuVungThongDung("health","Sức khỏe"));
        vungThongDungList.add(new TuVungThongDung("system","Hệ thống"));
        vungThongDungList.add(new TuVungThongDung("information","Thông tin"));
        vungThongDungList.add(new TuVungThongDung("meat","Thịt"));
        vungThongDungList.add(new TuVungThongDung("year","Năm"));
        vungThongDungList.add(new TuVungThongDung("thanks","Lời cảm ơn"));
        vungThongDungList.add(new TuVungThongDung("music","Âm nhạc"));
        vungThongDungList.add(new TuVungThongDung("person","Người"));
        vungThongDungList.add(new TuVungThongDung("reading","Cách đọc"));
        vungThongDungList.add(new TuVungThongDung("method","Phương pháp"));
        vungThongDungList.add(new TuVungThongDung("data","Dữ liệu"));
        vungThongDungList.add(new TuVungThongDung("map","Bản đồ"));
        vungThongDungList.add(new TuVungThongDung("government","Chính phủ"));
        vungThongDungList.add(new TuVungThongDung("way","Đường"));
        vungThongDungList.add(new TuVungThongDung("art","Nghệ thuật"));
        vungThongDungList.add(new TuVungThongDung("world","Thế giới"));
        vungThongDungList.add(new TuVungThongDung("computer","Máy tính"));
        vungThongDungList.add(new TuVungThongDung("people","Người"));
        vungThongDungList.add(new TuVungThongDung("two","Hai"));
        vungThongDungList.add(new TuVungThongDung("family","Gia đình"));
        vungThongDungList.add(new TuVungThongDung("history","Lịch sử"));
        vungThongDungList.add(new TuVungThongDung("health","Sức khỏe"));
        vungThongDungList.add(new TuVungThongDung("system","Hệ thống"));
        vungThongDungList.add(new TuVungThongDung("information","Thông tin"));
        vungThongDungList.add(new TuVungThongDung("meat","Thịt"));
        vungThongDungList.add(new TuVungThongDung("year","Năm"));
        vungThongDungList.add(new TuVungThongDung("thanks","Lời cảm ơn"));
        vungThongDungList.add(new TuVungThongDung("music","Âm nhạc"));
        vungThongDungList.add(new TuVungThongDung("person","Người"));
        vungThongDungList.add(new TuVungThongDung("reading","Cách đọc"));
        vungThongDungList.add(new TuVungThongDung("method","Phương pháp"));
        vungThongDungList.add(new TuVungThongDung("data","Dữ liệu"));

    }
}