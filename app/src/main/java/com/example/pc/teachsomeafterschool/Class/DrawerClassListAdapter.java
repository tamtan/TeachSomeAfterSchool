package com.example.pc.teachsomeafterschool.Class;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.pc.teachsomeafterschool.Model.ClassModel;
import com.example.pc.teachsomeafterschool.Model.WeekDay;
import com.example.pc.teachsomeafterschool.R;
import java.util.ArrayList;

/**
 * Created by TAM on 03-Oct-15.
 */
public class DrawerClassListAdapter extends ArrayAdapter<ClassModel> {

    int layout;
    Context context;
    ArrayList<ClassModel> classList;
    ArrayList<WeekDay> weekDays;
    public DrawerClassListAdapter(Context context, int layout,ArrayList<ClassModel> classList){
        super(context,layout,classList);
        this.layout = layout;
        this.context = context;
        this.classList = classList;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder.tvclass_name = (TextView) convertView.findViewById(R.id.tvclass_name);
            holder.tvmonday = (TextView) convertView.findViewById(R.id.tvmonday);
            holder.tvtuesday = (TextView) convertView.findViewById(R.id.tvtuesday);
            holder.tvwednesday = (TextView) convertView.findViewById(R.id.tvwednesday);
            holder.tvthusday = (TextView) convertView.findViewById(R.id.tvthusday);
            holder.tvfriday = (TextView) convertView.findViewById(R.id.tvfriday);
            holder.tvsaturday = (TextView) convertView.findViewById(R.id.tvsaturday);
            holder.tvsunday = (TextView) convertView.findViewById(R.id.tvsunday);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvclass_name.setText(classList.get(position).getName());
        weekDays = new ArrayList<WeekDay>();
        weekDays = getWeekSchedule(classList.get(position));
        for(WeekDay day: weekDays){
            switch (day.getDay()){
            }
        }
        return convertView;
    }

    private ArrayList<WeekDay> getWeekSchedule(ClassModel classModel) {
        ArrayList<WeekDay> weekDays = new ArrayList<WeekDay>();
        WeekDay weekDay;
        String[] weekSchedule = classModel.getWeekSchedule().split("_");
        if(weekSchedule.length  !=0){
            for(String day : weekSchedule){
                weekDay = new WeekDay();
                weekDay.setDay(day.split("-")[0]);
                weekDay.setStartTime(day.split("-")[1]);
                weekDay.setStopTime(day.split("-")[2]);
                weekDays.add(weekDay);
            }
        }
        return weekDays;
    }

    class ViewHolder{
        TextView tvclass_name, tvmonday, tvtuesday, tvwednesday, tvthusday, tvfriday, tvsaturday, tvsunday;
    }
}
