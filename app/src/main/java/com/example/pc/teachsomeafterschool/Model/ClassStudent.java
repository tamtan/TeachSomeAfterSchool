package com.example.pc.teachsomeafterschool.Model;

/**
 * Created by TAM on 17-Oct-15.
 */
public class ClassStudent {
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    int classId;
    int studentId;
}
