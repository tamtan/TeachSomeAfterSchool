package com.example.pc.teachsomeafterschool.Student;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pc.teachsomeafterschool.Infra.Const;
import com.example.pc.teachsomeafterschool.Model.Student;
import com.example.pc.teachsomeafterschool.R;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by TAM on 06-Oct-15.
 */
@EActivity(R.layout.student_info)
public class StudentInfoActivity extends Activity {
    @ViewById
    EditText edtStudent_class, edtStudent_full_name, edtStudent_phone_number, edtStudent_real_class,edtStudent_school, edtStudent_address;
    @ViewById
    CheckBox cbMale, cbFemale;
    @ViewById
    TextView tvAvatar;
    @ViewById
    ImageView imgBack, imgOk;

    Student studentModel;
    String avatarURL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected void onResume() {
        super.onResume();
        studentModel = new Student();
        cbMale.setChecked(true);
    }

    @Click({R.id.cbFemale,R.id.cbMale,R.id.tvAvatar, R.id.imgBack, R.id.imgOk})
    void onClick(View view){
        switch(view.getId()){
            case R.id.cbMale:
                if(!cbMale.isChecked()){
                    cbMale.setChecked(true);
                }
                cbFemale.setChecked(false);
                studentModel.setSex(Const.MALE);
                break;

            case R.id.cbFemale:
                if(!cbFemale.isChecked()){
                    cbFemale.setChecked(true);
                }
                cbMale.setChecked(false);
                studentModel.setSex(Const.FEMALE);
                break;
            case R.id.imgBack:
                onBackPressed();
                break;
            case R.id.imgOk:
                if(isInputDataOk()){
                    SaveData();
                }else{
                    Toast.makeText(this, "error",Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    private boolean isInputDataOk() {
        boolean result = true;
        if(edtStudent_full_name.getText().toString().isEmpty()){
            Toast.makeText(this, "error",Toast.LENGTH_LONG).show();
            edtStudent_full_name.setFocusable(true);
            result = false;
        }else
        if(edtStudent_real_class.getText().toString().isEmpty()){
            Toast.makeText(this, "error",Toast.LENGTH_LONG).show();
            edtStudent_real_class.setFocusable(true);
            result = false;
        }else
        if(edtStudent_school.getText().toString().isEmpty()){
            Toast.makeText(this, "error",Toast.LENGTH_LONG).show();
            edtStudent_school.setFocusable(true);
            result = false;
        }else
        if(edtStudent_phone_number.getText().toString().split(" ").length==0){
            Toast.makeText(this, "error",Toast.LENGTH_LONG).show();
            edtStudent_phone_number.setFocusable(true);
            result = false;
        }else
        if(edtStudent_address.getText().toString().split(" ").length ==0){
            Toast.makeText(this, "error",Toast.LENGTH_LONG).show();
            edtStudent_address.setFocusable(true);
            result = false;
        }

        return result;
    }

    private void SaveData() {
        studentModel.setFull_name(edtStudent_full_name.getText().toString());
        studentModel.setAdd(edtStudent_address.getText().toString());
        studentModel.setOfficial_class(edtStudent_real_class.getText().toString());
        studentModel.setPhone(edtStudent_phone_number.getText().toString());
        studentModel.setSchool(edtStudent_school.getText().toString());

    }
}
