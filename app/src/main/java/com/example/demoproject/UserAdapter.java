package com.example.demoproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class UserAdapter extends BaseAdapter {
    private List<User> userList;
    private Context context;
    private int layout;

    public UserAdapter(List<User> userList, Context context, int layout) {
        this.userList = userList;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return userList.size();
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
            holder.txtId = view.findViewById(R.id.textViewIdUser);
            holder.txtUserName = view.findViewById(R.id.textViewUserNameUser);
            holder.txtPassword = view.findViewById(R.id.textViewPasswordUser);
            holder.txtName = view.findViewById(R.id.textViewNameUser);
            holder.txtAge = view.findViewById(R.id.textViewAgeUser);
            holder.txtAddress = view.findViewById(R.id.textViewAddressUser);
            view.setTag(holder);

        }else{
            holder = (ViewHolder) view.getTag();

        }

        User user = userList.get(i);
        holder.txtId.setText(String.valueOf(user.getId()));
        holder.txtUserName.setText(user.getUserName());
        holder.txtPassword.setText(user.getPassword());
        holder.txtName.setText(user.getName());
        holder.txtAge.setText(String.valueOf(user.getAge()));
        holder.txtAddress.setText(user.getAddress());
    return view;
    }

    private class ViewHolder{
        TextView txtId, txtUserName, txtPassword, txtName, txtAge, txtAddress;
    }
}
