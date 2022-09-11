package com.example.demoproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends BaseAdapter implements Filterable {
    private Context context;
    private int layout;
    private List<Directory> directoryList;
    private List<Directory> directoryListOld;

    public Adapter(Context context, int layout, List<Directory> directoryList) {
        this.context = context;
        this.layout = layout;
        this.directoryList = directoryList;
        this.directoryListOld = directoryList;
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
        ViewHolder viewHolder;
        if(view ==  null){
            viewHolder  = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            viewHolder.txtTASameMeaning = view.findViewById(R.id.textViewTiengAnhSearch);
            viewHolder.txtTVSameMeaning = view.findViewById(R.id.textViewTiengVietSearch);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        Directory directory = directoryList.get(i);
        viewHolder.txtTASameMeaning.setText(directory.getEnglish());
        viewHolder.txtTVSameMeaning.setText(directory.getVietnamese());
        return view;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String wordFind =  charSequence.toString();
                List<Directory> list = new ArrayList<>();
                if(wordFind.isEmpty()){
                    directoryList = directoryListOld;
                }else{
                    for(Directory directory: directoryListOld){

                        if(directory.getEnglish().toLowerCase().contains(wordFind)){
                           list.add(directory);
                        }
                        directoryList = list;
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = list;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                directoryList = (List<Directory>) filterResults.values;
            }
        };
    }

    private class ViewHolder{
        TextView txtTASameMeaning,txtTVSameMeaning;
    }
}
