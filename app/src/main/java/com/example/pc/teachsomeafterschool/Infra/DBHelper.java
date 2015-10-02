package com.example.pc.teachsomeafterschool.Infra;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.pc.teachsomeafterschool.Model.*;

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
    private static final String TABLE_CLASS_STUDENT = "students";
    private static final String TABLE_TUITION = "tuition";
    private static final String TABLE_STUDENT_TUITION = "student_tuitions";


    // Common column names
    private static final String KEY_ID = "id";
    private static final String KEY_CREATED_AT = "created_at";
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
    private static final String KEY_AVATAR = "avatar";

    // TUITIONS Table - column names
    private static final String KEY_JAN = "Jan";
    private static final String KEY_FEB = "Feb";
    private static final String KEY_MAR = "Mar";
    private static final String KEY_APR = "Apr";
    private static final String KEY_MAY = "May";
    private static final String KEY_JUN = "Jun";
    private static final String KEY_JUL = "Jul";
    private static final String KEY_AUG = "Aug";
    private static final String KEY_SEP = "Sep";
    private static final String KEY_OCT = "Oct";
    private static final String KEY_NOV = "Nov";
    private static final String KEY_DEC = "Dec";

    // CLASS_STUDENTS table - column names
    private static final String KEY_CLASS_ID = "class_id";

    // STUDENT_TUITION table - column names
    private static final String KEY_TUITION_ID = "created_at";


    // Table Create Statements
    // STUDENT table create statement
    private static final String CREATE_TABLE_STUDENT = "CREATE TABLE "
            + TABLE_STUDENT + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_FULL_NAME
            + " TEXT," + KEY_OFFICIAL_CLASS
            + " TEXT,"
            + KEY_SCHOOL
            + " TEXT," + KEY_PHONE
            + KEY_ADDRESS
            + " TEXT,"
            + KEY_SEX
            + " INTEGER,"
            + KEY_AVATAR + " IMAGE" + ")";

    // CLASS table create statement
    private static final String CREATE_TABLE_CLASS = "CREATE TABLE " + TABLE_CLASS
            + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
            + KEY_STARTING_TIME + " DATETIME" + KEY_IS_FINISH + " BOOLEAN," + KEY_TUITION + " INTEGER" + ")";

    // TUITION table create statement
    private static final String CREATE_TABLE_TUITION = "CREATE TABLE " + TABLE_TUITION
            + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_JAN + " BOOLEAN,"
            + KEY_FEB + " BOOLEAN,"
            + KEY_MAR + " BOOLEAN,"
            + KEY_APR + " BOOLEAN,"
            + KEY_MAY + " BOOLEAN,"
            + KEY_JUN + " BOOLEAN,"
            + KEY_JUL + " BOOLEAN,"
            + KEY_AUG + " BOOLEAN,"
            + KEY_SEP + " BOOLEAN,"
            + KEY_OCT + " BOOLEAN,"
            + KEY_NOV + " BOOLEAN,"
            + KEY_DEC + " BOOLEAN,"
            + ")";

    // CLASS_STUDENT table create statement
    private static final String CREATE_TABLE_CLASS_STUDENT = "CREATE TABLE "
            + TABLE_CLASS_STUDENT + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_CLASS_ID + " INTEGER," + KEY_STUDENT_ID + " INTEGER,"
            + ")";

    // STUDENT_TUITION table create statement
    private static final String CREATE_TABLE_STUDENT_TUITION = "CREATE TABLE "
            + TABLE_STUDENT_TUITION + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_STUDENT_ID + " INTEGER," + KEY_TUITION_ID + " INTEGER,"
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
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLASS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLASS_STUDENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TUITION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT_TUITION);

        // create new tables
        onCreate(db);
    }

    // ------------------------ "todos" table methods ----------------//

    /*
     * Creating a todo
     */
    public long createClass(com.example.pc.teachsomeafterschool.Model.Class instant) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, instant.getName());
        values.put(KEY_STARTING_TIME, instant.getStartingTime().toString());
        values.put(KEY_IS_FINISH, instant.isFinish());
        values.put(KEY_TUITION, instant.getTuition());

        // insert row
        long todo_id = db.insert(TABLE_CLASS, null, values);
        return todo_id;
    }


}
