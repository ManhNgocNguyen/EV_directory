package com.example.demoproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class DongNghiaAdapter extends BaseAdapter {
    private List<Directory> directoryList;
    private Context context;
    private int layout;

    public DongNghiaAdapter(List<Directory> directoryList, Context context, int layout) {
        this.directoryList = directoryList;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return directoryList.size();
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
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            holder.txtTA = view.findViewById(R.id.textViewTiengAnhDongNghia);
            holder.txtTV = view.findViewById(R.id.textViewTiengVietDongNghia);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        Directory directory = directoryList.get(i);
        holder.txtTV.setText(directory.getVietnamese());
        holder.txtTA.setText(directory.getEnglish());
        return view;
    }
    private class ViewHolder{
        TextView txtTA,txtTV;
    }
}
