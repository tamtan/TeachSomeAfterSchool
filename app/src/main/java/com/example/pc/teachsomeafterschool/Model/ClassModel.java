package com.example.pc.teachsomeafterschool.Model;

import java.util.Date;

/**
 * Created by pc on 10/2/2015.
 */
public class ClassModel {
    String name;
    Date startingTime;
    boolean isFinish;
    int tuition;

    public ClassModel(String name, Date startingTime, boolean isFinish, int tuition){
        this.name = name;
        this.startingTime = startingTime;
        this.isFinish = isFinish;
        this.tuition = tuition;
    }

    public boolean isFinish() {
        return isFinish;
    }

    public long getTuition() {
        return tuition;
    }

    public void setTuition(int tuition) {
        this.tuition = tuition;
    }

    public void setIsFinish(boolean isFinish) {
        this.isFinish = isFinish;
    }

    public void setName(String name){
        this.name= name;
    }
    public String getName(){
        return name;
    }
    public void setStartingTime(Date date){
        this.startingTime = date;
    }
    public Date getStartingTime(){
        return startingTime;
    }
}
