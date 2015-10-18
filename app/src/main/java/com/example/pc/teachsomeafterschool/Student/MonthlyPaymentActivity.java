package com.example.pc.teachsomeafterschool.Student;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pc.teachsomeafterschool.Infra.Const;
import com.example.pc.teachsomeafterschool.Infra.Util;
import com.example.pc.teachsomeafterschool.Model.Tuition;
import com.example.pc.teachsomeafterschool.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * Created by TAM on 17-Oct-15.
 */
@EActivity(R.layout.tuition_status)
public class MonthlyPaymentActivity extends Activity {
    @ViewById
    TextView tvTotal, tvInDebt, tvPhone, tvFull_name, tvAdd, tvTitle;
    @ViewById
    ImageView imgAvatar_tuition, imgOk, imgBack, imgJan, imgFeb, imgMar, imgApr, imgMay, imgJun, imgJul, imgAug, imgSep, imgOct, imgNov, imgDec;
    String studentMonthlyPayment, startingMonth, currentMonth;
    int classTuition;
    ArrayList<Tuition> tuis;

    @Click({R.id.imgBack, R.id.imgOk})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgBack:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_right_enter, R.anim.slide_right_leave);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    public void init() {
        tvTitle.setText(getApplicationContext().getResources().getString(R.string.tuition_detail));
        tvFull_name.setText(getIntent().getExtras().getString("studentFullName"));
        tvPhone.setText(getIntent().getExtras().getString("studentPhone"));
        tvAdd.setText(getIntent().getExtras().getString("studentAdd"));
        studentMonthlyPayment = getIntent().getExtras().getString("studentMonthlyPayment");
        classTuition = getIntent().getExtras().getInt("classTuition");
        startingMonth = Util.getMonth(getIntent().getExtras().getString("startingTime"));
        currentMonth = Util.getCurrentMonth();
        tuis = calculateMonthlyPaymentToTuitionArray(studentMonthlyPayment, startingMonth, currentMonth);
        for (Tuition tui : tuis) {
            int isPay = tui.getIsPay();
            switch (tui.getMonth()) {

                case "01":
                    if (isPay == -1) {
                        imgJan.setImageResource(R.drawable.img_disable_box);
                    } else if (isPay == 1) {
                        imgJan.setImageResource(R.drawable.img_checked_box);
                    } else {
                        imgJan.setImageResource(R.drawable.img_unchecked_box);
                    }
                    break;
                case "02":
                    if (isPay == -1) {
                        imgFeb.setImageResource(R.drawable.img_disable_box);
                    } else if (isPay == 1) {
                        imgFeb.setImageResource(R.drawable.img_checked_box);
                    } else {
                        imgFeb.setImageResource(R.drawable.img_unchecked_box);
                    }
                    break;
                case "03":
                    if (isPay == -1) {
                        imgMar.setImageResource(R.drawable.img_disable_box);
                    } else if (isPay == 1) {
                        imgMar.setImageResource(R.drawable.img_checked_box);
                    } else {
                        imgMar.setImageResource(R.drawable.img_unchecked_box);
                    }
                    break;
                case "04":
                    if (isPay == -1) {
                        imgApr.setImageResource(R.drawable.img_disable_box);
                    } else if (isPay == 1) {
                        imgApr.setImageResource(R.drawable.img_checked_box);
                    } else {
                        imgApr.setImageResource(R.drawable.img_unchecked_box);
                    }
                    break;
                case "05":
                    if (isPay == -1) {
                        imgMay.setImageResource(R.drawable.img_disable_box);
                    } else if (isPay == 1) {
                        imgMay.setImageResource(R.drawable.img_checked_box);
                    } else {
                        imgMay.setImageResource(R.drawable.img_unchecked_box);
                    }
                    break;
                case "06":
                    if (isPay == -1) {
                        imgJun.setImageResource(R.drawable.img_disable_box);
                    } else if (isPay == 1) {
                        imgJun.setImageResource(R.drawable.img_checked_box);
                    } else {
                        imgJun.setImageResource(R.drawable.img_unchecked_box);
                    }
                    break;
                case "07":
                    if (isPay == -1) {
                        imgJul.setImageResource(R.drawable.img_disable_box);
                    } else if (isPay == 1) {
                        imgJul.setImageResource(R.drawable.img_checked_box);
                    } else {
                        imgJul.setImageResource(R.drawable.img_unchecked_box);
                    }
                    break;
                case "08":
                    if (isPay == -1) {
                        imgAug.setImageResource(R.drawable.img_disable_box);
                    } else if (isPay == 1) {
                        imgAug.setImageResource(R.drawable.img_checked_box);
                    } else {
                        imgAug.setImageResource(R.drawable.img_unchecked_box);
                    }
                    break;
                case "09":
                    if (isPay == -1) {
                        imgSep.setImageResource(R.drawable.img_disable_box);
                    } else if (isPay == 1) {
                        imgSep.setImageResource(R.drawable.img_checked_box);
                    } else {
                        imgSep.setImageResource(R.drawable.img_unchecked_box);
                    }
                    break;
                case "10":
                    if (isPay == -1) {
                        imgOct.setImageResource(R.drawable.img_disable_box);
                    } else if (isPay == 1) {
                        imgOct.setImageResource(R.drawable.img_checked_box);
                    } else {
                        imgOct.setImageResource(R.drawable.img_unchecked_box);
                    }
                    break;
                case "11":
                    if (isPay == -1) {
                        imgNov.setImageResource(R.drawable.img_disable_box);
                    } else if (isPay == 1) {
                        imgNov.setImageResource(R.drawable.img_checked_box);
                    } else {
                        imgNov.setImageResource(R.drawable.img_unchecked_box);
                    }
                    break;
                case "12":
                    if (isPay == -1) {
                        imgDec.setImageResource(R.drawable.img_disable_box);
                    } else if (isPay == 1) {
                        imgDec.setImageResource(R.drawable.img_checked_box);
                    } else {
                        imgDec.setImageResource(R.drawable.img_unchecked_box);
                    }
                    break;
            }
        }
        tvTotal.setText(calculateTotalTuition(startingMonth, currentMonth));
        tvInDebt.setText(Integer.toString(calculateTotalDebt(tuis, startingMonth, currentMonth)*classTuition));
    }

    public int calculateTotalDebt(ArrayList<Tuition> tuis, String startingMonth, String currentMonth) {
        int result = 0;
        int intStartingMonth = Integer.valueOf(startingMonth);
        int intCurrentMonth = Integer.valueOf(currentMonth);
        if(intStartingMonth>intCurrentMonth){
            for(int i = intStartingMonth; i<=12; i++){
                if(tuis.get(i-1).getIsPay()==Const.NO){
                    result+=1;
                }
            }
            for(int i = 1; i<=intCurrentMonth; i++){
                if(tuis.get(i-1).getIsPay()==Const.NO){
                    result+=1;
                }
            }

        }else{
            for(int i = intStartingMonth; i<=intCurrentMonth; i++){
                if(tuis.get(i-1).getIsPay()==Const.NO){
                    result+=1;
                }
            }
        }
        return result;
    }

    public void onClicked(View v) {

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public ArrayList<Tuition> calculateMonthlyPaymentToTuitionArray(String monthlyPayment, String startingMonth, String currentMonth) {
        ArrayList<Tuition> result = new ArrayList<Tuition>();
        Tuition tui;
        String s;
        String[] monthlyPaymentStr = monthlyPayment.split("_");
        int intStartingMonth = Integer.valueOf(startingMonth);
        int intCurrentMonth = Integer.valueOf(currentMonth);
        for (int i =  0; i < 12; i++) {
            tui = new Tuition();
            s = monthlyPaymentStr[i];
            tui.setMonth(s.split(":")[0]);

            if ((Integer.valueOf(s.split(":")[1]) == -1)&& (intStartingMonth-2<i)) {
                tui.setIsPay(Const.NO);
            } else {
                tui.setIsPay(Integer.valueOf(s.split(":")[1]));
            }
            result.add(tui);
        }
        return result;
    }

    public String calculateTuitionArrayToMonthlyPayment(ArrayList<Tuition> tuis) {
        String result = "";
//        String tui = "";
        for (Tuition tui : tuis) {
            result += tui.getMonth() + ":" + Integer.toString(tui.getIsPay()) + "_";
        }
        return result;
    }
    public String calculateTotalTuition(String startingMonth, String currentMonth){
        String result = "";
        int totalMonths = 0;
        int intStartingMonth = Integer.valueOf(startingMonth);
        int intCurrentMonth = Integer.valueOf(currentMonth);
        if(intCurrentMonth<intStartingMonth){
            totalMonths += intCurrentMonth + 12 - intStartingMonth;
        }else{
            totalMonths += intCurrentMonth - intStartingMonth;
        }
        result += Integer.toString(totalMonths*classTuition);
        return result;
    }
}
