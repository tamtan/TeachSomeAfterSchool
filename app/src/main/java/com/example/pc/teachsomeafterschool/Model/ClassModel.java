package com.example.pc.teachsomeafterschool.Model;

import java.util.Date;

/**
 * Created by pc on 10/2/2015.
 */
public class ClassModel {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    int id;
    String name;
    String startingTime;
    int isFinish;
    int tuition;

    public ClassModel(String name, String startingTime, int isFinish, int tuition){
        this.name = name;
        this.startingTime = startingTime;
        this.isFinish = isFinish;
        this.tuition = tuition;
    }

    public ClassModel() {
    }

    public int isFinish() {
        return isFinish;
    }

    public long getTuition() {
        return tuition;
    }

    public void setTuition(int tuition) {
        this.tuition = tuition;
    }

    public void setIsFinish(int isFinish) {
        this.isFinish = isFinish;
    }

    public void setName(String name){
        this.name= name;
    }
    public String getName(){
        return name;
    }
    public void setStartingTime(String date){
        this.startingTime = date;
    }
    public String getStartingTime(){
        return startingTime;
    }
}
