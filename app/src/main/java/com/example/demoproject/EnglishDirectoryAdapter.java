package com.example.demoproject;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class EnglishDirectoryAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Directory> directoryList;

    public EnglishDirectoryAdapter(Context context, int layout, List<Directory> directoryList) {
        this.context = context;
        this.layout = layout;
        this.directoryList = directoryList;
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
            LayoutInflater  inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            holder.txtID = view.findViewById(R.id.textViewId);
            holder.txtTA = view.findViewById(R.id.textViewTiengAnhDirectory);
            holder.txtTV = view.findViewById(R.id.textViewTiengViet);
            holder.txtKind = view.findViewById(R.id.textViewLoai);
            view.setTag(holder);

        }else{
            holder = (ViewHolder) view.getTag();
        }

        Directory directory = directoryList.get(i);
        holder.txtID.setText(String.valueOf(directory.getId()));
        holder.txtTA.setText(directory.getEnglish());
        holder.txtTV.setText(directory.getVietnamese());
        holder.txtKind.setText(directory.getKind() );
        return view;
    }

    private class ViewHolder{
        TextView txtID,txtTA,txtTV,txtKind;
    }
}
