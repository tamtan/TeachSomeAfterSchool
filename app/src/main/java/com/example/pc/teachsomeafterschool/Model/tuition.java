package com.example.pc.teachsomeafterschool.Model;

/**
 * Created by TAM on 03-Oct-15.
 */
public class tuition {
    public tuition(String month, int isPay){
        this.month = month;
        this.isPay = isPay;
    }

    public tuition() {

    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getIsPay() {
        return isPay;
    }

    public void setIsPay(int isPay) {
        this.isPay = isPay;
    }

    String month;
    int isPay;
}
