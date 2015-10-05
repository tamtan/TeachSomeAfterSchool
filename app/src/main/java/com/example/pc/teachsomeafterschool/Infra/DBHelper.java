package com.example.pc.teachsomeafterschool.Infra;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.pc.teachsomeafterschool.Model.ClassModel;

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
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "TeachSomeAfterSchool";

    // Table Names
    private static final String TABLE_STUDENT = "students";
    private static final String TABLE_CLASS = "classes";
    private static final String TABLE_CLASS_STUDENT = "class_students";
    private static final String TABLE_TUITION = "tuitions";
    private static final String TABLE_STUDENT_TUITION = "student_tuitions";
    private static final String TABLE_WEEK_SCHEDULE = "week_schedules";
    private static final String TABLE_CLASS_WEEK_SCHEDULE = "class_week_schedule";

    // WEEK SCHEDULE table - column names
    private static final String KEY_WEEK_TIME = "week_time";
    // Common column names
    private static final String KEY_ID = "id";
    private static final String KEY_STUDENT_ID = "student_id";

    // CLASSSES Table - column names
    private static final String KEY_NAME = "name";
    private static final String KEY_STARTING_TIME = "startingTime";
    private static final String KEY_IS_FINISH = "isFinish";
    private static final String KEY_TUITION = "tuition";

    // STUDENTS Table - column names
    private static final String KEY_FULL_NAME = "full_name";
    private static final String KEY_OFFICIAL_CLASS = "official_class";
    private static final String KEY_SCHOOL = "school";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_ADDRESS = "add";
    private static final String KEY_SEX = "sex";
    private static final String KEY_AVATAR = "image_url";

    // TUITIONS Table - column names
    private static final String KEY_JAN = "jan";
    private static final String KEY_FEB = "feb";
    private static final String KEY_MAR = "mar";
    private static final String KEY_APR = "apr";
    private static final String KEY_MAY = "may";
    private static final String KEY_JUN = "jun";
    private static final String KEY_JUL = "jul";
    private static final String KEY_AUG = "aug";
    private static final String KEY_SEP = "sep";
    private static final String KEY_OCT = "oct";
    private static final String KEY_NOV = "nov";
    private static final String KEY_DEC = "dec";

    // CLASS_STUDENTS table - column names
    private static final String KEY_CLASS_ID = "class_id";

    // STUDENT_TUITION table - column names
    private static final String KEY_TUITION_ID = "tuition_id";


    // Table Create Statements
    // STUDENT table create statement
    private static final String CREATE_TABLE_STUDENT = "CREATE TABLE "
            + TABLE_STUDENT + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_FULL_NAME
            + " TEXT," + KEY_OFFICIAL_CLASS
            + " TEXT,"
            + KEY_SCHOOL
            + " TEXT," + KEY_PHONE
            +" TEXT,"
            + KEY_ADDRESS
            + " TEXT,"
            + KEY_SEX
            + " INTEGER,"
            + KEY_AVATAR + " TEXT,"
            + KEY_STARTING_TIME + " DATETIME" + ")";

    // CLASS table create statement
    private static final String CREATE_TABLE_CLASS = "CREATE TABLE " + TABLE_CLASS
            + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
            + KEY_STARTING_TIME + " DATETIME," + KEY_IS_FINISH + " INTEGER," + KEY_TUITION + " INTEGER" + ")";

    // TUITION table create statement
    private static final String CREATE_TABLE_TUITION = "CREATE TABLE " + TABLE_TUITION
            + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_JAN + " INTEGER,"
            + KEY_FEB + " INTEGER,"
            + KEY_MAR + " INTEGER,"
            + KEY_APR + " INTEGER,"
            + KEY_MAY + " INTEGER,"
            + KEY_JUN + " INTEGER,"
            + KEY_JUL + " INTEGER,"
            + KEY_AUG + " INTEGER,"
            + KEY_SEP + " INTEGER,"
            + KEY_OCT + " INTEGER,"
            + KEY_NOV + " INTEGER,"
            + KEY_DEC + " INTEGER"
            + ")";

    // CLASS_STUDENT table create statement
    private static final String CREATE_TABLE_CLASS_STUDENT = "CREATE TABLE "
            + TABLE_CLASS_STUDENT + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_CLASS_ID + " INTEGER," + KEY_STUDENT_ID + " INTEGER"
            + ")";



    // WEEK_SCHEDULE table create statement
    private static final String CREATE_TABLE_WEEK_SCHEDULE = "CREATE TABLE "
            + TABLE_WEEK_SCHEDULE + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_WEEK_TIME + " TEXT"
            + ")";

    private static final String KEY_WEEK_SCHEDULE_ID = "week_schedule_id" ;
    // CLASS_WEEK_SCHEDULE table create statement
    private static final String CREATE_TABLE_CLASS_WEEK_SCHEDULE = "CREATE TABLE "
            + TABLE_CLASS_WEEK_SCHEDULE + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_CLASS_ID + " INTEGER," + KEY_WEEK_SCHEDULE_ID + " INTEGER"
            + ")";

    // STUDENT_TUITION table create statement
    private static final String CREATE_TABLE_STUDENT_TUITION = "CREATE TABLE "
            + TABLE_STUDENT_TUITION + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_STUDENT_ID + " INTEGER," + KEY_TUITION_ID + " INTEGER"
            + ")";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_STUDENT);
        db.execSQL(CREATE_TABLE_CLASS);
        db.execSQL(CREATE_TABLE_TUITION);
        db.execSQL(CREATE_TABLE_CLASS_STUDENT);
        db.execSQL(CREATE_TABLE_STUDENT_TUITION);
        db.execSQL(CREATE_TABLE_WEEK_SCHEDULE);
        db.execSQL(CREATE_TABLE_CLASS_WEEK_SCHEDULE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLASS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLASS_STUDENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TUITION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT_TUITION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WEEK_SCHEDULE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLASS_WEEK_SCHEDULE);
        // create new tables
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
        values.put(KEY_STARTING_TIME, getDateTime());
        values.put(KEY_IS_FINISH, instant.isFinish());
        values.put(KEY_TUITION, instant.getTuition());

        // insert row
        long todo_id = db.insert(TABLE_CLASS, null, values);
        return todo_id;
    }

/*get all classes*/

    public ArrayList<ClassModel> getAllClasses(){
        ArrayList<ClassModel> classes = new ArrayList<ClassModel>();
        String selectQuery = "SELECT  * FROM " + TABLE_CLASS+" ORDER BY "+KEY_ID+" DESC";

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                ClassModel cl = new ClassModel();
                cl.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                cl.setName((c.getString(c.getColumnIndex(KEY_NAME))));
                cl.setStartingTime(c.getString(c.getColumnIndex(KEY_STARTING_TIME)));
                cl.setTuition(c.getInt(c.getColumnIndex(KEY_TUITION)));
                cl.setIsFinish(c.getInt(c.getColumnIndex(KEY_IS_FINISH)));

                // adding to Class list
                classes.add(cl);
            } while (c.moveToNext());
        }
        return classes;
    }

    /*
	 * get single class
	 */
    public ClassModel getTodo(long class_id) {
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
     * Creating a sdudent
     */
    public long createStudent(com.example.pc.teachsomeafterschool.Model.Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_FULL_NAME, student.getFull_name());
        values.put(KEY_OFFICIAL_CLASS, student.getOfficial_class());
        values.put(KEY_SCHOOL, student.getSchool());
        values.put(KEY_PHONE, student.getPhone());
        values.put(KEY_ADDRESS, student.getAdd());
        values.put(KEY_SEX, student.getSex());
        values.put(KEY_AVATAR, student.getImage_url());
        // insert row
        long student_id = db.insert(TABLE_STUDENT, null, values);
        return student_id;
    }



    /*
     * Creating a tuition
     */

    public long createTuition(com.example.pc.teachsomeafterschool.Model.Tuition tuition) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_JAN, tuition.isJan());
        values.put(KEY_FEB, tuition.isFeb());
        values.put(KEY_MAR, tuition.isMar());
        values.put(KEY_APR, tuition.isApr());
        values.put(KEY_MAY, tuition.isMay());
        values.put(KEY_JUN, tuition.isJun());
        values.put(KEY_JUL, tuition.isJul());
        values.put(KEY_AUG, tuition.isAug());
        values.put(KEY_SEP, tuition.isSep());
        values.put(KEY_OCT, tuition.isOct());
        values.put(KEY_NOV, tuition.isNov());
        values.put(KEY_DEC, tuition.isDec());

        // insert row
        long tuition_id = db.insert(TABLE_STUDENT, null, values);
        return tuition_id;
    }
    /*
     * Creating a class_student
     */

    public long createClass_Student(int classID, int studentID) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CLASS_ID, classID);
        values.put(KEY_STUDENT_ID, studentID);

        // insert row
        long class_student_id = db.insert(TABLE_CLASS_STUDENT, null, values);
        return class_student_id;
    }

    /*
     * Creating a student_tuition
     */
    public long createStudent_Tuition(int studentID, int tuitionID) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_STUDENT_ID, studentID);
        values.put(KEY_TUITION_ID, tuitionID);

        // insert row
        long student_tuition_id = db.insert(TABLE_STUDENT_TUITION, null, values);
        return student_tuition_id;
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd-MM-yyyy", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }


}
