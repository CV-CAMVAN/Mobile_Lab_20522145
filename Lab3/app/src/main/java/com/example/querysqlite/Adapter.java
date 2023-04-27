package com.example.querysqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Adapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<User> arraylist;

    public Adapter(Context context, int layout, List<User> arraylist) {
        this.context = context;
        this.layout = layout;
        this.arraylist = arraylist;
    }

    @Override
    public int getCount() {
        return arraylist.size();
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
        View viewUser;
        if(view == null)
            viewUser = View.inflate(viewGroup.getContext(), this.layout,null);
        else {
            viewUser = view;
        }

        User user = this.arraylist.get(i);

        //ánh xạ id vào biến
        TextView rsFullname = viewUser.findViewById(R.id.rsName);
        TextView rsNumber = viewUser.findViewById(R.id.rsNumber);

        rsFullname.setText(user.getFullName());
        rsNumber.setText(user.getPhoneNumber());


        return viewUser;
    }
}