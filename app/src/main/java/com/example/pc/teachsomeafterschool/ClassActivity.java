package com.example.pc.teachsomeafterschool;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.pc.teachsomeafterschool.Class.ClassInfoActivity_;
import com.example.pc.teachsomeafterschool.Class.DrawerClassListAdapter;
import com.example.pc.teachsomeafterschool.Infra.DBHelper;
import com.example.pc.teachsomeafterschool.Model.ClassModel;
import com.example.pc.teachsomeafterschool.Model.Student;
import com.example.pc.teachsomeafterschool.Student.StudentInfoActivity;
import com.example.pc.teachsomeafterschool.Student.StudentInfoActivity_;
import com.example.pc.teachsomeafterschool.Student.StudentListAdapter;
import java.util.ArrayList;
import java.util.Date;

public class ClassActivity extends AppCompatActivity
        implements DrawerLayout.DrawerListener, AdapterView.OnItemClickListener, View.OnClickListener {
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       /**/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);

        ////////////// Testing
        db = new DBHelper(getApplicationContext());
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        classList = db.getAllClasses();
        classAdapter.notifyDataSetChanged();
    }

    public void init() {
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
        classList = new ArrayList<ClassModel>();
        classList = db.getAllClasses();
        classAdapter = new DrawerClassListAdapter(this, R.layout.drawer_class_list_item, classList);
        lvclass.setAdapter(classAdapter);
//        lvclass.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
//        lvclass.setSelection(0);
        if(classList.size()>0){
            presentClass = classList.get(0);
        }

//Add to student list
        studentList = new ArrayList<Student>();
        Student firstStudent = new Student();
        firstStudent.setFull_name("Tan Vinh Tam");
        studentList.add(firstStudent);
        Student secondStudent = new Student();
        secondStudent.setFull_name("Tan Thi Oanh");
        studentList.add(secondStudent);

        studentAdapter = new StudentListAdapter(this, R.layout.student_list_item, studentList);
        lvstudent.setAdapter(studentAdapter);
    }

    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
        if (nav_view != null && content_fragment != null) {
            // Get the width of the NavigationDrawerFragment
            int width = nav_view.getWidth();
            // Set the translationX of the Fragment's View to be the offset (a percentage)
            // times the width of the NavigationDrawerFragment
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

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getId()== R.id.lvdrawer_class_list) {
            presentClass = classList.get(position);
            for(int a = 0; a < parent.getChildCount(); a++)
            {
                parent.getChildAt(a).setBackgroundColor(getResources().getColor(R.color.burlywood));
            }

            view.setBackgroundColor(Color.RED);
//            lvstudent.setAdapter(null);
//            Student thirdStudent = new Student();
//            thirdStudent.setFull_name("Nguyen Duc Hien");
            studentList.clear();
//            studentList.add(thirdStudent);
            studentList = db.getAllStudents(presentClass.getId());
            studentAdapter = new StudentListAdapter(this, R.layout.student_list_item, studentList);
            lvstudent.setAdapter(studentAdapter);
        }
        if(parent.getId()==R.id.lvstudent_list){
            Intent intent = new Intent(ClassActivity.this, StudentInfoActivity_.class);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(ClassActivity.this, ClassInfoActivity_.class);
        startActivity(intent);
    }
}
