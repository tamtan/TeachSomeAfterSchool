package com.example.pc.teachsomeafterschool.Class;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Spinner;

import com.example.pc.teachsomeafterschool.R;

import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TAM on 05-Oct-15.
 */
@org.androidannotations.annotations.EActivity
public class ClassInfoActivity extends Activity {
    @ViewById
    Spinner spGradeSelector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_info);
    init();
    }

    private void init() {
        addItemsOnSpinner2();
    }

    private void addItemsOnSpinner2() {


    }
}
