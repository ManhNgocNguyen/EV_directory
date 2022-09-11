package com.example.demoproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class DisplayUserFragment extends Fragment {
    private ListView listView;
    private List<User> userList = new ArrayList<>();
    private AlertDialog.Builder dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display_user, container, false);
        listView = view.findViewById(R.id.listViewDisplayUser);
        dialog = new AlertDialog.Builder(getContext());
        DataBaseConnect data = new DataBaseConnect(getContext(),"User.sql",null,1);
        data.QueryDate("create table if not exists UserDirectory(id INTEGER primary key autoincrement, userName varchar(40), password varchar(40), name text(40), age int(10), address varchar(40))");
        Cursor cursor = data.GetData("select * from UserDirectory");
        if(cursor!=null){
            while (cursor.moveToNext()){
                userList.add(new User(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getInt(4),cursor.getString(5)));
            }
            UserAdapter adapter = new UserAdapter(userList,getContext(),R.layout.user_layout);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    data.QueryDate("Delete from UserDirectory where id = " + (userList.get(position).getId()));
                    dialog.setTitle("Xóa từ");
                    dialog.setMessage("Bạn muốn xóa từ này??");
                    dialog.setNegativeButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            userList.remove(position);
                            adapter.notifyDataSetChanged();
                            Toast.makeText(getContext(), "Xóa thành công", Toast.LENGTH_SHORT).show();
                        }
                    });
                    dialog.setPositiveButton("Không", null);
                    dialog.show();
                }
            });
        }else{
            Toast.makeText(getContext(),"Have no user",Toast.LENGTH_SHORT).show();
        }

        return view;
    }
}