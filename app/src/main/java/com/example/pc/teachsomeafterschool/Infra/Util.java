package com.example.pc.teachsomeafterschool.Infra;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.example.pc.teachsomeafterschool.Model.Tuition;
import com.example.pc.teachsomeafterschool.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by pc on 10/8/2015.
 */
public class Util {

    public static String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd-MM-yyyy", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
    public Util(Context context){


    }

    public static String getCurrentYear(){
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
    public static String getCurrentMonth(){
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "MM", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
    public static String getMonth(String time){
       String[] timeArr = time.split("-");
        return timeArr[1];
    }

    public int calculateTotalDebt(ArrayList<Tuition> tuis, String startingMonth, String currentMonth) {
        int result = 0;
        int intStartingMonth = Integer.valueOf(startingMonth);
        int intCurrentMonth = Integer.valueOf(currentMonth);
        if(intStartingMonth>intCurrentMonth){
            for(int i = intStartingMonth; i<=12; i++){
                if(tuis.get(i-1).getIsPay()== Const.NO){
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


}
