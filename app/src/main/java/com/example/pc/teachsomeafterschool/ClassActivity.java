package com.example.pc.teachsomeafterschool;

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
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.pc.teachsomeafterschool.Class.DrawerClassListAdapter;
import com.example.pc.teachsomeafterschool.Infra.DBHelper;
import com.example.pc.teachsomeafterschool.Model.ClassModel;

import java.util.ArrayList;
import java.util.Date;

public class ClassActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, DrawerLayout.DrawerListener {
    DBHelper db;
    LinearLayout content_fragment, nav_view;
    //TextView nav_view;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    DrawerClassListAdapter classAdapter;
    ListView lvclass;
    Toolbar toolbar;
    ArrayList<ClassModel> classList;
    ClassModel firstClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       /**/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);
        db = new DBHelper(getApplicationContext());
        Date date = new Date();
        firstClass = new ClassModel("tam", date, false, 1000);
        long i = db.createClass(firstClass);
        init();

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        //nav_view.setNavigationItemSelectedListener(this);
        //
        // nav_view = (TextView) findViewById(R.id.nav_view);
    }

    public void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
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
        classList = new ArrayList<ClassModel>();
        classList.add(firstClass);
        //Take a look why it displays nothing.
        classAdapter = new DrawerClassListAdapter(this, R.layout.drawer_class_list_item, classList);
        lvclass.setAdapter(classAdapter);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camara) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
}
