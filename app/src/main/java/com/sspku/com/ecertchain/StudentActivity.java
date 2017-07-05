package com.sspku.com.ecertchain;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

public class StudentActivity extends Activity {
    Button btn_student_cancel,btn_student_submit;
    public ChaincodeService chaincodeService;
    EditText name,number;
    String checkinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_card);

        btn_student_cancel = (Button) findViewById(R.id.student_cancel);
        btn_student_submit = (Button) findViewById(R.id.student_submit);
        name=(EditText)findViewById(R.id.student_nameEdittext);
        number=(EditText)findViewById(R.id.student_idEdittext);

        btn_student_submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String na=name.getText().toString().trim();
                String num=number.getText().toString().trim();
                boolean check_flag=infocheck(na, num);
                if(check_flag){
                    String txid= null;
                    try {
                        txid = chaincodeService.invoke("addEcert", new String[] { "1", na,num });
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
                        Toast.makeText(StudentActivity.this, "申请成功", Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(StudentActivity.this,ApplyActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(StudentActivity.this, "申请失败！", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(StudentActivity.this, checkinfo, Toast.LENGTH_LONG).show();
                }

            }
        });

        btn_student_cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(StudentActivity.this,ApplyActivity.class);
                startActivity(intent);
            }
        });

    }
    //检查输入信息
    public boolean infocheck(String n,String e){
        if(n.equals("")){
            checkinfo="姓名不能为空，请重新输入！";
            return false;
        }
        if(e.equals("")){
            checkinfo="证件号码不能为空，请重新输入！";
            return false;
        }
        return true;
    }

}
