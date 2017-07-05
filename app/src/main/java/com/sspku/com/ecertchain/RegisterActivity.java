package com.sspku.com.ecertchain;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.exception.ProposalException;

/**
 * Created by zuosoul on 2017/6/28.
 */

public class RegisterActivity extends Activity {
    public ChaincodeService chaincodeService;
    Button btn_submit,btn_cancel,btn_quit;
    EditText username,password,email,repassword;
    String checkinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        username=(EditText) findViewById(R.id.register_nameEdittext);
        password=(EditText) findViewById(R.id.register_passwordEdittext);
        email=(EditText) findViewById(R.id.register_emailEdittext);
        repassword=(EditText) findViewById(R.id.register_repasswordEdittext);
        btn_submit = (Button) findViewById(R.id.register_submit);
        btn_cancel = (Button) findViewById(R.id.register_cancel);

        btn_submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String name=username.getText().toString().trim();
                String pass=password.getText().toString().trim();
                String repass=repassword.getText().toString().trim();
                String ema=email.getText().toString().trim();
                boolean check_flag=usercheck(name, pass,repass, ema);
                if(check_flag){
                    Log.i("TAG",name+"_"+pass+"_"+ema+"_"+ema);
                    UserService uService=new UserService(RegisterActivity.this);
                    MyUser user=new MyUser();
                    user.setUsername(name);
                    user.setPassword(pass);
                    user.setEmail(ema);
                    String txid= null;
                    try {
                        txid = chaincodeService.invoke("addUser", new String[] { user.getUsername(), user.getPassword() });
                    } catch (InvalidArgumentException e) {
                        e.printStackTrace();
                    } catch (ProposalException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (TimeoutException e) {
                        e.printStackTrace();
                    }
                    if(!txid.equals("")){
                        uService.register(user);
                        Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(RegisterActivity.this, "注册失败！", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(RegisterActivity.this, checkinfo, Toast.LENGTH_LONG).show();
                }


            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(RegisterActivity.this,LoginActivity .class);
                startActivity(intent);
            }
        });
    }
    //检查注册信息
    public boolean usercheck(String n,String p,String rp,String e){
        if(n.equals("")){
            checkinfo="姓名不能为空，请重新输入！";
            return false;
        }
        if(p.equals("")){
            checkinfo="密码不能为空，请重新输入！";
            return false;
        }
        if(e.equals("")){
            checkinfo="邮箱不能为空，请重新输入！";
            return false;
        }
        if(!rp.equals(p)){
            checkinfo="两次密码输入不匹配，请重新输入！";
            return false;
        }
        return true;
    }


}
