package com.example.pc.teachsomeafterschool.Infra;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.pc.teachsomeafterschool.Model.ClassModel;
import com.example.pc.teachsomeafterschool.Model.ClassStudent;
import com.example.pc.teachsomeafterschool.Model.Student;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by pc on 9/30/2015.
 */
public class DBHelper extends SQLiteOpenHelper {

    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "TeachSomeAfterSchool";

    // Table Names
    private static final String TABLE_STUDENT = "students";
    private static final String TABLE_CLASS = "classes";
    private static final String TABLE_CLASS_STUDENT = "class_students";

    // Common column names
    private static final String KEY_ID = "id";
    private static final String KEY_STUDENT_ID = "student_id";

    // CLASSSES Table - column names
    private static final String KEY_NAME = "name";
    private static final String KEY_STARTING_TIME = "startingTime";
    private static final String KEY_IS_FINISH = "isFinish";
    private static final String KEY_TUITION = "Tuition";
    private static final String KEY_WEEK_TIME = "week_time";

    // STUDENTS Table - column names
    private static final String KEY_FULL_NAME = "full_name";
    private static final String KEY_OFFICIAL_CLASS = "official_class";
    private static final String KEY_SCHOOL = "school";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_SEX = "sex";
    private static final String KEY_AVATAR = "image_url";
    private static final String KEY_MONTHLY_PAYMENT = "monthly_payment";


    // CLASS_STUDENTS table - column names
    private static final String KEY_CLASS_ID = "class_id";

//    // STUDENT_TUITION table - column names
//    private static final String KEY_TUITION_ID = "tuition_id";


    // Table Create Statements
    // STUDENT table create statement
    private static final String CREATE_TABLE_STUDENT = "CREATE TABLE "
            + TABLE_STUDENT + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_FULL_NAME
            + " TEXT," + KEY_OFFICIAL_CLASS
            + " TEXT,"
            + KEY_SCHOOL
            + " TEXT," + KEY_PHONE
            + " TEXT,"
            + KEY_ADDRESS
            + " TEXT,"
            + KEY_SEX
            + " INTEGER,"
            + KEY_AVATAR + " TEXT,"
            + KEY_STARTING_TIME + " TEXT,"
            + KEY_MONTHLY_PAYMENT + " TEXT" +

            ")";

    // CLASS table create statement
    private static final String CREATE_TABLE_CLASS = "CREATE TABLE " + TABLE_CLASS
            + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
            + KEY_STARTING_TIME + " TEXT," + KEY_IS_FINISH + " INTEGER," + KEY_TUITION + " INTEGER," + KEY_WEEK_TIME + " TEXT" + ")";

    // CLASS_STUDENT table create statement
    private static final String CREATE_TABLE_CLASS_STUDENT = "CREATE TABLE "
            + TABLE_CLASS_STUDENT + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_CLASS_ID + " INTEGER," + KEY_STUDENT_ID + " INTEGER"
            + ")";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables

        db.execSQL(CREATE_TABLE_CLASS);
        db.execSQL(CREATE_TABLE_CLASS_STUDENT);
        db.execSQL(CREATE_TABLE_STUDENT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tsables

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLASS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLASS_STUDENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        onCreate(db);
    }

    // ------------------------ "todos" table methods ----------------//

    /*
     * Creating a class
     */
    public long createClass(com.example.pc.teachsomeafterschool.Model.ClassModel instant) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, instant.getName());
        values.put(KEY_STARTING_TIME, Util.getDateTime());
        values.put(KEY_IS_FINISH, false);
        values.put(KEY_TUITION, instant.getTuition());
        values.put(KEY_WEEK_TIME, instant.getWeekSchedule());
        long todo_id = db.insert(TABLE_CLASS, null, values);
        return todo_id;
    }

    /*get all classes*/
    public ArrayList<ClassModel> getAllClasses() {
        ArrayList<ClassModel> classes = new ArrayList<ClassModel>();
        String selectQuery = "SELECT  * FROM " + TABLE_CLASS + " ORDER BY " + KEY_ID + " DESC";

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                ClassModel cl = new ClassModel();
                cl.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                cl.setName((c.getString(c.getColumnIndex(KEY_NAME))));
                cl.setStartingTime(c.getString(c.getColumnIndex(KEY_STARTING_TIME)));
                cl.setTuition(c.getInt(c.getColumnIndex(KEY_TUITION)));
                cl.setIsFinish(c.getInt(c.getColumnIndex(KEY_IS_FINISH)));
                cl.setWeekSchedule(c.getString(c.getColumnIndex(KEY_WEEK_TIME)));
                classes.add(cl);
            } while (c.moveToNext());
        }
        return classes;
    }

    public ArrayList<ClassModel> getClasses(String grade) {
        ArrayList<ClassModel> classes = new ArrayList<ClassModel>();
        String selectQuery = "SELECT  * FROM " + TABLE_CLASS + " where " + KEY_NAME + " like ?" + " ORDER BY " + KEY_ID + " DESC";

        Log.e(LOG, selectQuery);
        String[] para = new String[1];
        para[0] = grade + "%";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, para);
        if (c.moveToFirst()) {
            do {
                ClassModel cl = new ClassModel();
                cl.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                cl.setName((c.getString(c.getColumnIndex(KEY_NAME))));
                cl.setStartingTime(c.getString(c.getColumnIndex(KEY_STARTING_TIME)));
                cl.setTuition(c.getInt(c.getColumnIndex(KEY_TUITION)));
                cl.setIsFinish(c.getInt(c.getColumnIndex(KEY_IS_FINISH)));
                cl.setWeekSchedule(c.getString(c.getColumnIndex(KEY_WEEK_TIME)));
                classes.add(cl);
            } while (c.moveToNext());
        }
        return classes;
    }

    /*
     * get single class
	 */
    public ClassModel getClass(long class_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_CLASS + " WHERE "
                + KEY_ID + " = " + class_id;
        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        ClassModel cl = new ClassModel();
        cl.setId(c.getInt((c.getColumnIndex(KEY_ID))));
        cl.setName((c.getString(c.getColumnIndex(KEY_NAME))));
        cl.setStartingTime(c.getString(c.getColumnIndex(KEY_STARTING_TIME)));
        cl.setTuition(c.getInt(c.getColumnIndex(KEY_TUITION)));
        cl.setIsFinish(c.getInt(c.getColumnIndex(KEY_IS_FINISH)));
        return cl;
    }


    /*
     * Creating a student
     */
    public long createStudent(com.example.pc.teachsomeafterschool.Model.Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_FULL_NAME, student.getFullName());
        values.put(KEY_OFFICIAL_CLASS, student.getOfficialClass());
        values.put(KEY_SCHOOL, student.getSchool());
        values.put(KEY_PHONE, student.getPhone());
        values.put(KEY_ADDRESS, student.getAdd());
        values.put(KEY_SEX, student.getSex());
        values.put(KEY_AVATAR, student.getImageUrl());
        values.put(KEY_MONTHLY_PAYMENT, student.getMonthlyPayment());


        // insert row
        long student_id = db.insert(TABLE_STUDENT, null, values);
        return student_id;
    }

    public ArrayList<Student> getStudents(long classId) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Student> result = new ArrayList<Student>();
        ArrayList<ClassStudent> classStudentList = new ArrayList<ClassStudent>();
        classStudentList = getClassStudentByClassId(classId);
        for (ClassStudent clSt : classStudentList) {
            result.add(getStudent(clSt.getStudentId()));
        }
        return result;
    }

    public ArrayList<Student> getAllStudents(int classId) {
        ArrayList<Student> result = new ArrayList<Student>();
        return result;
    }

    public Student getStudent(int studentId) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_STUDENT + " WHERE "
                + KEY_ID + " = " + studentId;
        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();
        int i = c.getCount();
        Student cl = new Student();
        cl.setId(c.getInt((c.getColumnIndex(KEY_ID))));
        cl.setFullNname((c.getString(c.getColumnIndex(KEY_FULL_NAME))));
        cl.setOfficialClass(c.getString(c.getColumnIndex(KEY_OFFICIAL_CLASS)));
        cl.setSchool(c.getString(c.getColumnIndex(KEY_SCHOOL)));
        cl.setImageUrl(c.getString(c.getColumnIndex(KEY_AVATAR)));

        cl.setPhone((c.getString(c.getColumnIndex(KEY_PHONE))));
        cl.setAdd(c.getString(c.getColumnIndex(KEY_ADDRESS)));
        cl.setSex(c.getInt(c.getColumnIndex(KEY_SEX)));
        cl.setMonthlyPayment(c.getString(c.getColumnIndex(KEY_MONTHLY_PAYMENT)));
        return cl;
    }

    public long createClass_Student(int classID, int studentID) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CLASS_ID, classID);
        values.put(KEY_STUDENT_ID, studentID);

        // insert row
        long class_student_id = db.insert(TABLE_CLASS_STUDENT, null, values);
        return class_student_id;
    }

    public ArrayList<ClassStudent> getClassStudentByClassId(long classId) {
        ArrayList<ClassStudent> result = new ArrayList<ClassStudent>();
        String selectQuery = "SELECT  * FROM " + TABLE_CLASS_STUDENT + " where " + KEY_CLASS_ID + " =" + classId + " ORDER BY " + KEY_ID + " DESC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                ClassStudent cl = new ClassStudent();
                cl.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                cl.setClassId((c.getInt(c.getColumnIndex(KEY_CLASS_ID))));

                cl.setStudentId(c.getInt(c.getColumnIndex(KEY_STUDENT_ID)));

                result.add(cl);
            } while (c.moveToNext());
        }
        return result;
    }

    public void deleteClass(int classId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<ClassStudent> students = getClassStudentByClassId(classId);
        for (ClassStudent student : students) {
            deleteStudent(student.getId());
        }
        db.delete(TABLE_CLASS, KEY_ID + " = ?",
                new String[]{String.valueOf(classId)});
    }


    public void deleteStudent(int studentId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STUDENT, KEY_ID + " = ?",
                new String[]{String.valueOf(studentId)});
        deleteClass_Student(studentId);
    }

    public void deleteClass_Student(int studentId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CLASS_STUDENT, KEY_STUDENT_ID + " = ?",
                new String[]{String.valueOf(studentId)});
    }
}
