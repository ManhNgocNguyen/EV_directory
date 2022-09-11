package com.example.demoproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

public class TuVungThongDungAdapter extends BaseAdapter {
    private List<TuVungThongDung> vungThongDungList;
    private int layout;
    private Context context;

    public TuVungThongDungAdapter(List<TuVungThongDung> vungThongDungList, int layout, Context context) {
        this.vungThongDungList = vungThongDungList;
        this.layout = layout;
        this.context = context;
    }

    @Override
    public int getCount() {
        return vungThongDungList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context.getApplicationContext());
            view = inflater.inflate(layout,null);
            holder.txtTA = view.findViewById(R.id.tuVungThongDungTiengAnh);
            holder.txtTV = view.findViewById(R.id.tuVungThongDungTiengViet);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        TuVungThongDung tuVungThongDung = vungThongDungList.get(i);
        holder.txtTV.setText(tuVungThongDung.getVietnamese());
        holder.txtTA.setText(tuVungThongDung.getEnglish());
        return view;
    }

    private class ViewHolder{
        private TextView txtTA,txtTV;
    }
}
