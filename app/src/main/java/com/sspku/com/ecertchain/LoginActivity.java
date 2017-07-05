package com.sspku.com.ecertchain;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by zuosoul on 2017/6/28.
 */

public class LoginActivity extends Activity {
    Button btn_login,btn_register;
    EditText username,password,email;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        findViews();
    }

    private void findViews() {
        btn_login = (Button) findViewById(R.id.login_submit);
        btn_register  = (Button) findViewById(R.id.register_submit);
        username=(EditText) findViewById(R.id.login_nameEdittext);
        password=(EditText) findViewById(R.id.password_idEdittext);


        btn_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String name=username.getText().toString();
                String pass=password.getText().toString();
                UserService uService=new UserService(LoginActivity.this);
                boolean flag=uService.login(name, pass);
                if(flag){
                    username.setText("");
                    password.setText("");
                    name=" ";
                    pass=" ";
                    Log.i("TAG","登录成功");
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                }else{
                    Log.i("TAG","登录失败");
                    Toast.makeText(LoginActivity.this, "登录失败，请检查您的用户名和密码", Toast.LENGTH_LONG).show();
                }
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(LoginActivity.this,RegisterActivity .class);
                startActivity(intent);
            }
        });
    }

}
