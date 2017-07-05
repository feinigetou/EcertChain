package com.sspku.com.ecertchain;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by zuosoul on 2017/6/28.
 */

public class ApplyActivity extends Activity {
    Button btn_student,btn_identity,btn_driving,btn_teacher,btn_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apply);

        btn_student = (Button) findViewById(R.id.apply_for_student);
        btn_identity = (Button) findViewById(R.id.apply_for_identity);
        btn_driving = (Button) findViewById(R.id.apply_for_driving);
        btn_teacher = (Button) findViewById(R.id.apply_for_teacher);
        btn_cancel = (Button) findViewById(R.id.apply_cancel);

        btn_student.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(ApplyActivity.this,StudentActivity.class);
                startActivity(intent);
            }
        });

        btn_identity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(ApplyActivity.this,IdentityActivity.class);
                startActivity(intent);
            }
        });

        btn_driving.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(ApplyActivity.this,DrivingActivity.class);
                startActivity(intent);
            }
        });

        btn_teacher.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(ApplyActivity.this,TeacherActivity.class);
                startActivity(intent);
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(ApplyActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}

