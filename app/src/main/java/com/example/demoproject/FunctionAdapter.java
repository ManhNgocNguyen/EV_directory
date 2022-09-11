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

public class FunctionAdapter extends BaseAdapter{
    private Context context;
    private int layout;
    private List<FunctionName> functionNameList;

    public FunctionAdapter(Context context, int layout, List<FunctionName> functionNameList) {
        this.context = context;
        this.layout = layout;
        this.functionNameList = functionNameList;
    }

    @Override
    public int getCount() {
        return functionNameList.size();
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
            viewHolder.imgFunctionName = view.findViewById(R.id.imageViewFunctionName);
            viewHolder.txtFunctionName = view.findViewById(R.id.textViewFunctionName);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        FunctionName functionName = functionNameList.get(i);
        viewHolder.imgFunctionName.setImageResource(functionName.getImageResource());
        viewHolder.txtFunctionName.setText(functionName.getFunctionName());
        return view;
    }

    private class ViewHolder{
        ImageView imgFunctionName;
        TextView txtFunctionName;
    }
}
