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

public class QueryActivity extends Activity {
    Button btn_query_cancel,btn_query_submit;
    public ChaincodeService chaincodeService;
    EditText name,category;
    String checkinfo;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.query);

        btn_query_cancel = (Button) findViewById(R.id.query_cancel);
        btn_query_submit = (Button) findViewById(R.id.query_submit);
        name=(EditText)findViewById(R.id.query_nameEdittext);
        category=(EditText)findViewById(R.id.query_caEdittext);

        btn_query_submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String na=name.getText().toString().trim();
                String cat=category.getText().toString().trim();
                boolean check_flag=infocheck(na, cat);
                if(check_flag){
                    String txid= null;
                    try {
                        txid = chaincodeService.invoke("queryEcert", new String[] { na,cat });
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
                        Toast.makeText(QueryActivity.this, "查询成功", Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(QueryActivity.this,QueryResultActivity.class);
                        intent.putExtra("nam", na);
                        //intent.putExtra("nums", );
                        intent.putExtra("nam", na);
                        startActivity(intent);
                    }else{
                        Toast.makeText(QueryActivity.this, "查询失败！", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(QueryActivity.this, checkinfo, Toast.LENGTH_LONG).show();
                }
            }
        });

        btn_query_cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(QueryActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
    //检查输入信息
    public boolean infocheck(String n,String e){
        if(n.equals("")){
            checkinfo="用户姓名不能为空，请重新输入！";
            return false;
        }
        if(e.equals("")){
            checkinfo="证照类别码不能为空，请重新输入！";
            return false;
        }
        return true;
    }
}

