package com.example.pc.teachsomeafterschool.Model;

/**
 * Created by TAM on 03-Oct-15.
 */
public class Student {
    int id;
    String full_name;
    String official_class;
    String school;
    String phone;
    String add;
    int sex;
    String image_url;

    public void setId(int id) {
        this.id = id;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public void setOfficial_class(String official_class) {
        this.official_class = official_class;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }


    public int getId() {
        return id;
    }

    public String getFull_name() {
        return full_name;
    }

    public String getOfficial_class() {
        return official_class;
    }

    public String getSchool() {
        return school;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getPhone() {
        return phone;
    }

    public String getAdd() {
        return add;
    }

    public int getSex() {
        return sex;
    }
}
