package com.example.pc.teachsomeafterschool.Student;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pc.teachsomeafterschool.Model.Student;
import com.example.pc.teachsomeafterschool.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 10/5/2015.
 */
public class StudentListAdapter extends ArrayAdapter<Student> {
    Context context;
    int resource;
    ArrayList<Student> objects;

    public StudentListAdapter(Context context, int resource, ArrayList<Student> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resource, null);
            holder.tvstudent_name = (TextView) convertView.findViewById(R.id.tvstudent_name);
            holder.tvmonth_in_debt = (TextView) convertView.findViewById(R.id.tvmonth_in_debt);
            holder.tvphone = (TextView) convertView.findViewById(R.id.tvphone);
            holder.imgavatar = (ImageView) convertView.findViewById(R.id.imgavatar);
            holder.tvreal_class_name = (TextView) convertView.findViewById(R.id.tvreal_class_name);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvstudent_name.setText(objects.get(position).getFull_name());
        return convertView;
    }

    class ViewHolder {
        ImageView imgavatar;
        TextView tvstudent_name, tvreal_class_name, tvmonth_in_debt, tvphone;
    }
}
