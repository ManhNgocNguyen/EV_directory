package com.example.demoproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class TenseAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<EnglishTense> englishTenseList;

    public TenseAdapter(Context context, int layout, List<EnglishTense> englishTenseList) {
        this.context = context;
        this.layout = layout;
        this.englishTenseList = englishTenseList;
    }

    @Override
    public int getCount() {
        return englishTenseList.size();
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
        ViewHolder viewHolder;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            viewHolder.txtTenseName = view.findViewById(R.id.textViewTenseName);
            viewHolder.txtTenseStructure = view.findViewById(R.id.textViewTenseStructure);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        EnglishTense englishTense = englishTenseList.get(i);
        viewHolder.txtTenseName.setText(englishTense.getTenseName());
        viewHolder.txtTenseStructure.setText(englishTense.getTenseStructure());
        return view;
    }

    private class ViewHolder {
        TextView txtTenseName,txtTenseStructure;
    }
}
