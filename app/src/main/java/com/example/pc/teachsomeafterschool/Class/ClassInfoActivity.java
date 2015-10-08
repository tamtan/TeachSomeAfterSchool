package com.example.pc.teachsomeafterschool.Class;

import android.app.Activity;
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

import com.example.pc.teachsomeafterschool.Infra.Const;
import com.example.pc.teachsomeafterschool.Model.ClassModel;
import com.example.pc.teachsomeafterschool.Model.WeekDay;
import com.example.pc.teachsomeafterschool.Model.WeekSchedule;
import com.example.pc.teachsomeafterschool.R;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * Created by TAM on 05-Oct-s15.
 */
@org.androidannotations.annotations.EActivity(R.layout.class_info)

public class ClassInfoActivity extends Activity {
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
    WeekSchedule weedSchedule;
    ClassModel classModel;
    WeekDay chosenDay;
    boolean isStartFinish, isStopFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
            String time = Integer.toString(hour) + "-" + Integer.toString(minute);
            tvStartTime.setText(time);
            isStartFinish = true;
        } else if (!isStopFinish) {
            int hour = tpTimePicker.getCurrentHour();
            int minute = tpTimePicker.getCurrentMinute();
            String time = Integer.toString(hour) + ":" + Integer.toString(minute);
            tvStopTime.setText(time);
            isStopFinish = true;
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

}
