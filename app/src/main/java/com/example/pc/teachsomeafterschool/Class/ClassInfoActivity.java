package com.example.pc.teachsomeafterschool.Class;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.AnyRes;
import android.support.annotation.ColorRes;
import android.support.annotation.StringRes;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.pc.teachsomeafterschool.ClassActivity;
import com.example.pc.teachsomeafterschool.Infra.Const;
import com.example.pc.teachsomeafterschool.Infra.DBHelper;
import com.example.pc.teachsomeafterschool.Model.ClassModel;
import com.example.pc.teachsomeafterschool.Model.WeekDay;
import com.example.pc.teachsomeafterschool.Model.WeekSchedule;
import com.example.pc.teachsomeafterschool.R;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.ViewById;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by TAM on 05-Oct-s15.
 */
@org.androidannotations.annotations.EActivity(R.layout.class_info)

public class ClassInfoActivity extends Activity {
    DBHelper db;
    @ViewById
    Spinner spGradeSelector;
    @ViewById
    EditText edtTuition;
    @ViewById
    TextView tvMonday, tvTuesday, tvWednesday, tvThursday, tvFriday, tvSaturday, tvSunday;
    @ViewById
    TimePicker tpTimePicker;
    @ViewById
    Button btOk, btEdit, btCancel;
    @ViewById
    TextView tvStartTime, tvStopTime;

//    @StringRes
//    String start;
////    @StringRes(R.string.stop)
//    int stop;
//    @StringRes(R.string.start)
//    int two;
//    @StringRes
//    String monday, tuesday, wednesday, thursday, friday, saturday, sunday;
//

    ArrayList<WeekDay> weekDays;
    String weekTime;
    WeekSchedule weekSchedule;
    ClassModel classModel;
    WeekDay chosenDay;
    boolean isStartFinish, isStopFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DBHelper(ClassInfoActivity.this);
        init();

    }

    private void init() {
        weekDays = new ArrayList<WeekDay>();
    }

    @Click({R.id.tvMonday, R.id.tvTuesday, R.id.tvWednesday, R.id.tvThursday, R.id.tvFriday, R.id.tvSaturday, R.id.tvSunday})
    void AddNewWeekDay(View view) {
//        Log.d("Start", Integer.toString(start));

        MarkScheduleDays(weekDays);
        chosenDay = new WeekDay();
        isStartFinish = false;
        isStopFinish = false;

        tvStartTime.setText(R.string.start);
        tvStopTime.setText(R.string.stop);
        if (tvStartTime.getVisibility() == View.INVISIBLE) {
            tvStartTime.setVisibility(View.VISIBLE);
            tvStopTime.setVisibility(View.VISIBLE);
            tpTimePicker.setVisibility(View.VISIBLE);
        }
        switch (view.getId()) {
            case R.id.tvMonday:
                chosenDay.setDay(getApplicationContext().getResources().getString(R.string.monday));
                tvMonday.setBackgroundColor(Color.GRAY);
                break;
            case R.id.tvTuesday:
                chosenDay.setDay(getApplicationContext().getResources().getString(R.string.tuesday));
                tvTuesday.setBackgroundColor(Color.GRAY);
                break;
            case R.id.tvWednesday:
                chosenDay.setDay(getApplicationContext().getResources().getString(R.string.wednesday));
                tvWednesday.setBackgroundColor(Color.GRAY);
                break;
            case R.id.tvThursday:
                chosenDay.setDay(getApplicationContext().getResources().getString(R.string.thusday));
                tvThursday.setBackgroundColor(Color.GRAY);
                break;
            case R.id.tvFriday:
                chosenDay.setDay(getApplicationContext().getResources().getString(R.string.friday));
                tvFriday.setBackgroundColor(Color.GRAY);
                break;
            case R.id.tvSaturday:
                chosenDay.setDay(getApplicationContext().getResources().getString(R.string.saturday));
                tvSaturday.setBackgroundColor(Color.GRAY);
                break;
            case R.id.tvSunday:
                chosenDay.setDay(getApplicationContext().getResources().getString(R.string.sunday));
                tvSunday.setBackgroundColor(Color.GRAY);
                break;
        }

    }

    @Click(R.id.tvStartTime)
    void AddNewStartTime() {
        isStartFinish = false;
        tvStartTime.setText(getApplicationContext().getResources().getString(R.string.start));
//        int hour = tpTimePicker.getCurrentHour();
//        int minute = tpTimePicker.getCurrentMinute();
//        String time = Integer.toString(hour) +":"+ Integer.toString(minute);
//        tvStartTime.setText(time);
        if (tpTimePicker.getVisibility() == View.INVISIBLE) {
            tpTimePicker.setVisibility(View.VISIBLE);
        }
    }

    @Click(R.id.tvStopTime)
    void AddNewStopTime() {
        tvStopTime.setText(getApplicationContext().getResources().getString(R.string.stop));
        isStopFinish = false;
        if (tpTimePicker.getVisibility() == View.INVISIBLE) {
            tpTimePicker.setVisibility(View.VISIBLE);

        }
    }

    @Click(R.id.btOk)
    void AddTime() {
        if (!isStartFinish) {
            int hour = tpTimePicker.getCurrentHour();
            int minute = tpTimePicker.getCurrentMinute();
            String time = Integer.toString(hour) + ":" + Integer.toString(minute);
            tvStartTime.setText(time);
            isStartFinish = true;
        } else if (!isStopFinish) {
            int hour = tpTimePicker.getCurrentHour();
            int minute = tpTimePicker.getCurrentMinute();
            String time = Integer.toString(hour) + ":" + Integer.toString(minute);
            tvStopTime.setText(time);
            isStopFinish = true;

            }
        if(isStartFinish && isStartFinish) {
            chosenDay.setStartTime(tvStartTime.getText().toString());
            chosenDay.setStopTime(tvStopTime.getText().toString());
            if (!isDayExisted(chosenDay, weekDays)) {
                Add(chosenDay, weekDays);
            } else {
                Edit(chosenDay, weekDays);
            }
            if (tpTimePicker.getVisibility() == View.VISIBLE) {
                tpTimePicker.setVisibility(View.INVISIBLE);
            }
        }
    }

    private void Edit(WeekDay chosenDay, ArrayList<WeekDay> weekDays) {
        for (WeekDay day : weekDays) {
            if (day.getDay().equals(chosenDay.getDay())) {
                day.setStartTime(chosenDay.getStartTime());
                day.setStopTime(chosenDay.getStopTime());
            }
        }
    }

    private void Add(WeekDay chosenDay, ArrayList<WeekDay> weekDays) {
        weekDays.add(chosenDay);
    }

    private boolean isDayExisted(WeekDay chosenDay, ArrayList<WeekDay> weekDays) {
        if (weekDays.contains(chosenDay)) {
            return true;
        } else {
            return false;
        }
    }



    private void MarkScheduleDays(ArrayList<WeekDay> weekDays) {
        for (WeekDay day : weekDays) {
            switch (day.getDay()) {
                case Const.monday:
                    tvMonday.setBackgroundColor(Color.GRAY);
                    break;
                case Const.tuesday:
                    tvTuesday.setBackgroundColor(Color.GRAY);
                    break;
                case Const.wednesday:
                    tvWednesday.setBackgroundColor(Color.GRAY);
                    break;
                case Const.thursday:
                    tvThursday.setBackgroundColor(Color.GRAY);
                    break;
                case Const.friday:
                    tvFriday.setBackgroundColor(Color.GRAY);
                    break;
                case Const.saturday:
                    tvSaturday.setBackgroundColor(Color.GRAY);
                    break;
                case Const.sunday:
                    tvSunday.setBackgroundColor(Color.GRAY);
                    break;

            }
        }
    }
    private String ConvertWeekSchedultToString(ArrayList<WeekDay> weekdays){
        String result = "";
        for(WeekDay day: weekdays){
            result+= day.getDay()+"_"+day.getStartTime()+"_"+day.getStopTime()+";";
        }
        return result;
    }
     private String GenerateClassName(String grade){
         int year = Calendar.getInstance().get(Calendar.YEAR);
         String result = null;
            if(grade!= null){
                result = grade+"_"+ CountClassNumber(grade)+"_"+Integer.toString(year);
            }
                 return result;
     }

    private int CountClassNumber(String grade){
        ArrayList<ClassModel> allClass = db.getAllClasses();
        int result = 1;
        for(ClassModel instant: allClass){
            if(grade.equals(getGrade(instant.getName()))){
                result++;
            }
        }
        return result;
    }

    private String getGrade(String name) {
        if(name!= null) {
            String[] arr = name.split("_");
            return arr[0];
        }
        return null;
    }

    @Click (R.id.imgOk)
    public void SaveData(){
//        String[] temp = null;
        classModel = new ClassModel();
        weekSchedule = new WeekSchedule();
        String grade = spGradeSelector.getSelectedItem().toString().split(" ")[1];
        String name = GenerateClassName(grade);
        int tuition = Integer.valueOf(edtTuition.getText().toString());
        classModel.setName(name);
        classModel.setStartingTime(getDateTime());
        classModel.setIsFinish(Const.notFinisth);
        classModel.setTuition(tuition);
        long classId = db.createClass(classModel);
        weekSchedule.setWeekTime(ConvertWeekSchedultToString(weekDays));
        long weekScheduleId = db.createWeekSchedule(weekSchedule);
        long class_weekSchedule_Id = db.createClass_WeekSchedule(classId, weekScheduleId);
        Intent intent = new Intent(ClassInfoActivity.this, ClassActivity.class);
        startActivity(intent);
    }
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd-MM-yyyy", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    @Click (R.id.imgBack)
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
