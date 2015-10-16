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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.pc.teachsomeafterschool.Infra.Const;
import com.example.pc.teachsomeafterschool.Infra.DBHelper;
import com.example.pc.teachsomeafterschool.Infra.Util;
import com.example.pc.teachsomeafterschool.Model.ClassModel;
import com.example.pc.teachsomeafterschool.Model.WeekDay;
import com.example.pc.teachsomeafterschool.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.ViewById;

import java.lang.reflect.Array;
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
    @ViewById
    ImageView imgBack, imgOk;
    ArrayList<WeekDay> weekDays;
    ClassModel classModel;
    WeekDay chosenDay, preChosenDay;
    boolean isStartFinish, isStopFinish;

    public boolean isStartFinish() {
        return isStartFinish;
    }

    public void setIsStartFinish(boolean isStartFinish) {
        this.isStartFinish = isStartFinish;
    }

    public boolean isStopFinish() {
        return isStopFinish;
    }

    public void setIsStopFinish(boolean isStopFinish) {
        this.isStopFinish = isStopFinish;
    }

    boolean dataOk;
    TextView tvChosenDay, tvPreChosenDay;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DBHelper(getApplicationContext());
    }

    @AfterViews
    void init() {
        classModel = new ClassModel();
        weekDays = new ArrayList<WeekDay>();
        preChosenDay = new WeekDay();
    }

    @Click({R.id.tvMonday, R.id.tvTuesday, R.id.tvWednesday, R.id.tvThursday, R.id.tvFriday, R.id.tvSaturday, R.id.tvSunday})
    void AddNewWeekDay(View view) {
//        Log.d("Start", Integer.toString(start));

//        MarkScheduleDays(weekDays);
//        isStartFinish = false;
//        isStopFinish = false;

        chosenDay = new WeekDay();
        if (tvStartTime.getVisibility() == View.INVISIBLE) {
            tvStartTime.setVisibility(View.VISIBLE);
        }
        if (tvStopTime.getVisibility() == View.INVISIBLE) {
            tvStopTime.setVisibility(View.VISIBLE);
        }
        if (tpTimePicker.getVisibility() == View.INVISIBLE) {
            tpTimePicker.setVisibility(View.VISIBLE);
        }
        switch (view.getId()) {
            case R.id.tvMonday:
                chosenDay.setDay(getApplicationContext().getResources().getString(R.string.monday));
                tvChosenDay = tvMonday;
                break;
            case R.id.tvTuesday:
                chosenDay.setDay(getApplicationContext().getResources().getString(R.string.tuesday));
                tvChosenDay = tvTuesday;
                break;
            case R.id.tvWednesday:
                chosenDay.setDay(getApplicationContext().getResources().getString(R.string.wednesday));
                tvChosenDay = tvWednesday;
                break;
            case R.id.tvThursday:
                chosenDay.setDay(getApplicationContext().getResources().getString(R.string.thusday));
                tvChosenDay = tvThursday;
                break;
            case R.id.tvFriday:
                chosenDay.setDay(getApplicationContext().getResources().getString(R.string.friday));
                tvChosenDay = tvFriday;
                break;
            case R.id.tvSaturday:
                chosenDay.setDay(getApplicationContext().getResources().getString(R.string.saturday));
                tvChosenDay = tvSaturday;
                break;
            case R.id.tvSunday:
                chosenDay.setDay(getApplicationContext().getResources().getString(R.string.sunday));
                tvChosenDay = tvSunday;
                break;
        }
        if (!isDayExisted1(chosenDay, weekDays)) {
            setTvStartTimeColor();
            tvStartTime.setText(R.string.start);
            tvStopTime.setText(R.string.stop);
            if (tvPreChosenDay != tvChosenDay) {
                tvChosenDay.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.weekly_rounded_button_chosen));
                if (!isDayExisted1(preChosenDay, weekDays) && tvPreChosenDay != null) {
                    tvPreChosenDay.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.weekly_rounded_button_nomal));
                } else if (tvPreChosenDay != null) {
                    tvPreChosenDay.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.weekly_rounded_button_scheduled));
                }
                tvPreChosenDay = tvChosenDay;
                preChosenDay = chosenDay;
            }
            setIsStartFinish(false);
            setIsStopFinish(false);

        } else {
            chosenDay.setStartTime(getStartChosenDay(chosenDay, weekDays));
            chosenDay.setStopTime(getStopChosenDay(chosenDay, weekDays));
            tvStartTime.setText(getStartChosenDay(chosenDay, weekDays));
            tvStopTime.setText(getStopChosenDay(chosenDay, weekDays));
            if (tvPreChosenDay != tvChosenDay) {
                tvChosenDay.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.weekly_rounded_button_chosen));
                if (!isDayExisted1(preChosenDay, weekDays) && tvPreChosenDay != null) {
                    tvPreChosenDay.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.weekly_rounded_button_nomal));
                } else if (tvPreChosenDay != null) {
                    tvPreChosenDay.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.weekly_rounded_button_scheduled));
                }
                tvPreChosenDay = tvChosenDay;
                preChosenDay = chosenDay;
            }
        }

    }

    private void setStartStopTimeTrigger(boolean isStartFinish, boolean isStopFinish) {
        this.isStartFinish = isStartFinish;
        this.isStopFinish = isStopFinish;
    }

    private String getStartChosenDay(WeekDay chosenDay, ArrayList<WeekDay> weekDays) {
        if (weekDays.size() != 0) {
            for (WeekDay day : weekDays) {
                if (day.getDay().equals(chosenDay.getDay())) {
                    return day.getStartTime();
                }
            }
        }
        return null;
    }

    private String getStopChosenDay(WeekDay chosenDay, ArrayList<WeekDay> weekDays) {
        if (weekDays.size() != 0) {
            for (WeekDay day : weekDays) {
                if (day.getDay().equals(chosenDay.getDay())) {
                    return day.getStopTime();
                }
            }
        }
        return null;
    }

    @Click(R.id.tvStartTime)
    void AddNewStartTime() {
        setTvStartTimeColor();
        setIsStartFinish(false);
        tvStartTime.setText(getApplicationContext().getResources().getString(R.string.start));
        if (tpTimePicker.getVisibility() == View.INVISIBLE) {
            tpTimePicker.setVisibility(View.VISIBLE);
        }
    }

    @Click(R.id.tvStopTime)
    void AddNewStopTime() {
        if (isStartFinish()) {
            setTvStopTimeColor();
            setIsStopFinish(false);
            tvStopTime.setText(getApplicationContext().getResources().getString(R.string.stop));

            if (tpTimePicker.getVisibility() == View.INVISIBLE) {
                tpTimePicker.setVisibility(View.VISIBLE);
            }
        }
    }

    @Click(R.id.btOk)
    void AddTime() {
        if (chosenDay != null) {
            if (!isStartFinish) {
                int hour = tpTimePicker.getCurrentHour();
                int minute = tpTimePicker.getCurrentMinute();
                String hourStr = hour < 10 ? "0" + Integer.toString(hour) : Integer.toString(hour);
                String minuteStr = minute < 10 ? "0" + Integer.toString(minute) : Integer.toString(minute);
                String time = hourStr + ":" + minuteStr;
                tvStartTime.setText(time);
                setIsStartFinish(true);
                chosenDay.setStartTime(time);
                if (!isStopFinish()) {
                    setTvStopTimeColor();
                }
            } else if (!isStopFinish) {
                int hour = tpTimePicker.getCurrentHour();
                int minute = tpTimePicker.getCurrentMinute();
                String time = Integer.toString(hour) + ":" + Integer.toString(minute);
                tvStopTime.setText(time);
                setIsStopFinish(true);
                chosenDay.setStopTime(time);
            }
            if (isStartFinish() && isStopFinish()) {
                setTvStartStopTimeColor();
                if (!isDayExisted1(chosenDay, weekDays)) {
                    Add(chosenDay, weekDays);
                } else {
                    Edit(chosenDay, weekDays);
                }
                if (tpTimePicker.getVisibility() == View.VISIBLE) {
                    tpTimePicker.setVisibility(View.INVISIBLE);
                }
            }
        } else {
            Toast.makeText(ClassInfoActivity.this, getResources().getString(R.string.null_chosen_day), Toast.LENGTH_LONG).show();
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

    private boolean isDayExisted1(WeekDay chosenDay, ArrayList<WeekDay> weekDays) {
        if (weekDays.size() != 0) {
            for (WeekDay day : weekDays) {
                if (day.getDay().equals(chosenDay.getDay())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Click({R.id.imgBack, R.id.imgOk})
    public void clickOnActionBar(View v) {
        switch (v.getId()) {
            case R.id.imgBack:
                onBackPressed();

                break;
            case R.id.imgOk:
                if (isDataOk()) {
                    long id = SaveData();
                    String t = "";
                } else {

                }

                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_right_enter, R.anim.slide_right_leave);
    }

    private long SaveData() {
        getData();
        long id = db.createClass(classModel);
        return id;

    }

    public void getData() {
        String className = spGradeSelector.getSelectedItem().toString();
        className = convertToClassName(className);
        classModel.setName(className);
        classModel.setTuition(Integer.valueOf(edtTuition.getText().toString()));
        classModel.setWeekSchedule(getWeekTime(weekDays));
    }

    private String getWeekTime(ArrayList<WeekDay> weekDays) {
        String result = "";
        for (WeekDay day : weekDays) {
            result += day.getDay() + "-" + day.getStartTime() + "-" + day.getStopTime() + "_";
        }
        return result;
    }

    public boolean isDataOk() {
        boolean dataOk = true;
        if (edtTuition.getText().toString().equals("")) {
            Toast.makeText(ClassInfoActivity.this, getResources().getString(R.string.miss_tuition), Toast.LENGTH_LONG).show();
            return false;
        }
        if (weekDays.size() == 0) {
            Toast.makeText(ClassInfoActivity.this, getResources().getString(R.string.miss_schedule), Toast.LENGTH_LONG).show();
            return false;
        }
        return dataOk;
    }

    private void setTvStartTimeColor() {
        tvStartTime.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.weekly_rounded_button_chosen));
        tvStopTime.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.dayly_time_rounded_button_nomal));
    }

    private void setTvStopTimeColor() {
        tvStopTime.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.weekly_rounded_button_chosen));
        tvStartTime.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.dayly_time_rounded_button_nomal));
    }

    private void setTvStartStopTimeColor() {
        tvStopTime.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.dayly_time_rounded_button_nomal));
        tvStartTime.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.dayly_time_rounded_button_nomal));
    }

    public String convertToClassName(String grade) {
        String result = "";
        ArrayList<ClassModel> classes = new ArrayList<ClassModel>();
        classes = db.getClasses(grade.split(" ")[1]);
//        classes = db.getClasses("11");
        int orderNumber = 1 + classes.size();
        String year = Util.getCurrentYear();
        result += grade.split(" ")[1] + "_" + Integer.toString(orderNumber) + "_" + Util.getCurrentYear();
        return result;
    }
}
