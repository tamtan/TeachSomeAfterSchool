package com.example.pc.teachsomeafterschool;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pc.teachsomeafterschool.Class.ClassInfoActivity_;
import com.example.pc.teachsomeafterschool.Class.DrawerClassListAdapter;
import com.example.pc.teachsomeafterschool.Infra.DBHelper;
import com.example.pc.teachsomeafterschool.Model.ClassModel;
import com.example.pc.teachsomeafterschool.Model.Student;
import com.example.pc.teachsomeafterschool.Student.StudentInfoActivity_;
import com.example.pc.teachsomeafterschool.Student.StudentListAdapter;

import java.util.ArrayList;

public class ClassActivity extends AppCompatActivity
        implements DrawerLayout.DrawerListener, AdapterView.OnItemClickListener, View.OnClickListener{
    ImageView imgAdd;
    DBHelper db;
    LinearLayout content_fragment, nav_view;
    //TextView nav_view;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    DrawerClassListAdapter classAdapter;
    ListView lvclass, lvstudent;
    Toolbar toolbar;
    ArrayList<ClassModel> classList;
    ArrayList<Student> studentList;
    StudentListAdapter studentAdapter;
    ClassModel presentClass;
    private boolean isAddButtonClicked;
    private Animation anim;
    private Animation.AnimationListener animListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       /**/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);
        db = new DBHelper(getApplicationContext());
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (drawer.getVisibility() == View.INVISIBLE) {
            drawer.setVisibility(View.VISIBLE);
        }

        classList = db.getAllClasses();
        classAdapter = new DrawerClassListAdapter(this, R.layout.drawer_class_list_item, classList);
        lvclass.setAdapter(classAdapter);
        if (classList.size() > 0) {
            presentClass = classList.get(0);
            //  lvclass.getChildAt(2).setBackgroundColor(Color.YELLOW);
            //Student test = new Student();
            //test = db.getStudent(1);
            studentList = db.getStudents(presentClass.getId());

            if (studentList.size() != 0) {
                studentAdapter = new StudentListAdapter(this, R.layout.student_list_item, studentList, presentClass);
                lvstudent.setAdapter(studentAdapter);
            }
        }
    }

    public void init() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        imgAdd = (ImageView) findViewById(R.id.imgAdd);
        imgAdd.setOnClickListener(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        lvstudent = (ListView) findViewById(R.id.lvstudent_list);
        lvstudent.setOnItemClickListener(this);
        setSupportActionBar(toolbar);
        content_fragment = (LinearLayout) findViewById(R.id.content_fragment);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.setDrawerListener(this);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //drawer.setDrawerListener(toggle);
        toggle.syncState();
        nav_view = (LinearLayout) findViewById(R.id.nav_view);
        lvclass = (ListView) findViewById(R.id.lvdrawer_class_list);
        lvclass.setOnItemClickListener(this);

//Add class to class listview
        classList = new ArrayList<>();
        studentList = new ArrayList<>();

        animListener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                drawer.setVisibility(View.INVISIBLE);
                Intent intent = new Intent(ClassActivity.this, StudentInfoActivity_.class);
//                intent.putExtra("classModel", (Parcelable) presentClass);
                intent.putExtra("className", presentClass.getName());
                intent.putExtra("classId", presentClass.getId());
                intent.putExtra("startingTime", presentClass.getStartingTime());
                startActivity(intent);
                overridePendingTransition(R.anim.slide_left_enter, R.anim.no_anim);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
    }

    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.class_layout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            if (presentClass != null) {
                anim = AnimationUtils.loadAnimation(ClassActivity.this, R.anim.slide_down_leave);
                anim.setAnimationListener(animListener);
                drawer.startAnimation(anim);
            } else {
                Toast.makeText(ClassActivity.this, getApplicationContext().getResources().getString(R.string.error_no_class_existed), Toast.LENGTH_LONG).show();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
        if (nav_view != null && content_fragment != null) {
            int width = nav_view.getWidth();
            content_fragment.setTranslationX(width * slideOffset);
        }
    }

    @Override
    public void onDrawerOpened(View drawerView) {

    }

    @Override
    public void onDrawerClosed(View drawerView) {

    }

    @Override
    public void onDrawerStateChanged(int newState) {
        if (isAddButtonClicked) {
            isAddButtonClicked = false;
            Intent intent = new Intent(ClassActivity.this, ClassInfoActivity_.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_down_enter, R.anim.slide_down_leave);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.lvdrawer_class_list) {
            presentClass = classList.get(position);
            for (int a = 0; a < parent.getChildCount(); a++) {
                parent.getChildAt(a).setBackgroundColor(getResources().getColor(R.color.lightgoldenrodyellow));
            }
            view.setBackgroundColor(getResources().getColor(R.color.gold));
            studentList.clear();
            studentList = db.getStudents(presentClass.getId());
            studentAdapter = new StudentListAdapter(this, R.layout.student_list_item, studentList, presentClass);
            lvstudent.setAdapter(studentAdapter);
        }
        if (parent.getId() == R.id.lvstudent_list) {
            MenuDialog dialog = new MenuDialog(ClassActivity.this);
            dialog.getWindow().setBackgroundDrawable(
                    new ColorDrawable(android.graphics.Color.TRANSPARENT));
            dialog.show();
//            Intent intent = new Intent(ClassActivity.this, MonthlyPaymentActivity_.class);
//            intent.putExtra("studentFullName", studentList.get(position).getFullName());
//            intent.putExtra("studentPhone", studentList.get(position).getPhone());
//            intent.putExtra("studentAdd", studentList.get(position).getAdd());
//            intent.putExtra("studentImageUrl", studentList.get(position).getImageUrl());
//            intent.putExtra("studentMonthlyPayment", studentList.get(position).getMonthlyPayment());
//            intent.putExtra("classTuition", presentClass.getTuition());
//            intent.putExtra("startingTime", presentClass.getStartingTime());
//            startActivity(intent);
//            overridePendingTransition(R.anim.slide_left_enter, R.anim.no_anim);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imgAdd) {
            drawer.closeDrawer(GravityCompat.START);
            isAddButtonClicked = true;
        }
    }

    protected class MenuDialog extends Dialog {
        Context context;

        public MenuDialog(Context context) {
            super(context);
            this.context = context;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//            WindowManager.LayoutParams params = getWindow().getAttributes();
//            params.height = LinearLayout.LayoutParams.WRAP_CONTENT;
//            getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
//            Window window = this.getWindow();
//            window.setLayout(60, 60);//


            setContentView(R.layout.menu_dialog);

        }
    }
}
