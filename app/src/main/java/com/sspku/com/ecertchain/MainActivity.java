package com.sspku.com.ecertchain;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by zuosoul on 2017/6/28.
 */

public class MainActivity extends Activity {
    Button btn_apply,btn_query,btn_quit,btn_myecert;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //btn_myecert = (Button) findViewById(R.id.my_ecert);
        btn_apply = (Button) findViewById(R.id.go_apply);
        btn_query = (Button) findViewById(R.id.go_query);
        btn_quit = (Button) findViewById(R.id.main_quit);

        /*
        btn_myecert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(MainActivity.this,MyecertActivity.class);
                startActivity(intent);
            }
        });
        */

        btn_apply.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(MainActivity.this,ApplyActivity.class);
                startActivity(intent);
            }
        });

        btn_query.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(MainActivity.this,QueryActivity.class);
                startActivity(intent);
            }
        });

        btn_quit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                System.exit(0);
            }
        });
    }
}

