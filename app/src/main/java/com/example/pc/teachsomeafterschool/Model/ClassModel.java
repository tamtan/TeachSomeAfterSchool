package com.example.pc.teachsomeafterschool.Model;

/**
 * Created by pc on 10/2/2015.
 */
public class ClassModel {
    int id;
    String name;
    String startingTime;
    int isFinish;
    int tuition;
    String weekSchedule;

    public ClassModel(String name, String startingTime, int isFinish, int tuition) {
        this.name = name;
        this.startingTime = startingTime;
        this.isFinish = isFinish;
        this.tuition = tuition;
    }

    public ClassModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWeekSchedule() {
        return weekSchedule;
    }

    public void setWeekSchedule(String weekSchedule) {
        this.weekSchedule = weekSchedule;
    }

    public int isFinish() {
        return isFinish;
    }

    public int getTuition() {
        return tuition;
    }

    public void setTuition(int tuition) {
        this.tuition = tuition;
    }

    public void setIsFinish(int isFinish) {
        this.isFinish = isFinish;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(String date) {
        this.startingTime = date;
    }
}
