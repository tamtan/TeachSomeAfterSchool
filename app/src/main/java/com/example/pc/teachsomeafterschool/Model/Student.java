package com.example.pc.teachsomeafterschool.Model;

/**
 * Created by TAM on 03-Oct-15.
 */
public class Student {
    int id;
    String fullName;
    String officialClass;
    String school;
    String phone;
    String add;
    int sex;
    String imageUrl;
    String monthlyPayment;

    public String getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(String monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public void setFullNname(String fullName) {
        this.fullName = fullName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getOfficialClass() {
        return officialClass;
    }

    public void setOfficialClass(String officialClass) {
        this.officialClass = officialClass;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}
