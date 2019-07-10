package com.example.huoda.helloworld;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.huoda.helloworld.R.id.love_id1;

public class MainActivity extends BaseActivity {

    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    private EditText mIphone_id;
    private EditText mMessage;
    private EditText et_phoneNum;
    private EditText et_messageCon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.layout02);
        setContentView(R.layout.activity_main);


        //找到按钮

        mMessage = (EditText) findViewById(R.id.message_id);
        Button send = (Button) findViewById(love_id1);
        send.setOnClickListener(new MyMessagesender());

        EditText password = (EditText) findViewById(R.id.pass_id);
        Button signin = (Button) findViewById(R.id.sign_in_id);

        et_phoneNum =(EditText) findViewById(R.id.et_phoneNum);
        et_messageCon =(EditText) findViewById(R.id.et_messsageCon);

        Button love_id1 = (Button) findViewById(R.id.love_id1);
       Button love_id2 = (Button) findViewById(R.id.love_id2);
        Button love_id3 = (Button) findViewById(R.id.love_id3);

       // Button call1 = (Button) findViewById(R.id.love_id1);
        Button call2 = (Button) findViewById(R.id.love_id2);
        mIphone_id = (EditText) findViewById(R.id.iphone_id);

        love_id3.setOnClickListener(new MySendMessage());

       // call1.setOnClickListener(new MyClickListener());
        //点击按钮触发时间
        love_id1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //事件
                System.out.println("I Love You");
                Toast.makeText(MainActivity.this,"I Love You",Toast.LENGTH_SHORT).show();
            }
        });

        love_id2.setOnClickListener(new MyClickListener());
        //love_id2.setOnClickListener(new MyLove());

    }

    public void callPhone() {
        // 2.当用户点击按钮的时候获取里面的电话号码
        String phone =  mIphone_id.getText().toString().trim();

        if ("".equals(phone)) {
            Toast.makeText(MainActivity.this,"电话号码不能为空", Toast.LENGTH_SHORT).show();
        } else {
            // 1.创建一个意图对象
            Intent intent = new Intent();
            // 2.设置动作
            intent.setAction(Intent.ACTION_CALL);
            // 3.指定动作的数据
            intent.setData(Uri.parse("tel://" + phone));
            // 4.开启一个界面，根据程序员指定的行为来做事情
            startActivity(intent);
        }
    }

    private class MyClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            System.out.println("call");
         /*   //获取电话号码
            String phone = mIphone_id.getText().toString().trim();
            //创建意图对象
            Intent intent =new Intent();
            //设置动作
            intent.setAction(Intent.ACTION_CALL);
            //指定动作的数据
            intent.setData(Uri.parse("tel//" + phone));
            //开启界面
            startActivity(intent);*/
            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST_CALL_PHONE);
            } else {
                callPhone();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == MY_PERMISSIONS_REQUEST_CALL_PHONE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callPhone();
            } else {
                // Permission Denied
                Toast.makeText(MainActivity.this,"Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void click (View view)
    {
        System.out.println("I Love You");
        Toast.makeText(MainActivity.this,"I Love You",Toast.LENGTH_SHORT).show();
        System.out.println("I Love You");
        Toast.makeText(MainActivity.this,"I Love You",Toast.LENGTH_SHORT).show();

    }

    public void sign_in_click (View view)
    {
        System.out.println("sign_in");
       // Toast.makeText(MainActivity.this,"I Love You",Toast.LENGTH_SHORT).show();
       // setContentView(R.layout.activity_main);

    }



    class MyLove implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            System.out.println("I Love You Too");
            Toast.makeText(MainActivity.this, "I Love You Too", Toast.LENGTH_SHORT).show();
        }
    }

   class MyMessagesender implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            String messagelove = mMessage.getText().toString().trim();
            String phone = mIphone_id.getText().toString().trim();

            System.out.println(phone+":"+messagelove);
            SmsManager smsmanager = SmsManager.getDefault();
            smsmanager.sendTextMessage(phone,null,messagelove,null,null);
        }
    }


    private class MySendMessage implements View.OnClickListener {
        @Override
        public void onClick(View v) {
           final  String phoneNumber =  et_phoneNum.getText().toString().trim();
           final String message =  et_messageCon.getText().toString().trim();
            if ("".equals(phoneNumber) || "".equals(message)) {
                Toast.makeText(MainActivity.this,"号码和内容不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
            System.out.println(phoneNumber +":" + message);
            performCodeWithPermission("发送短信权限", new PermissionCallback() {
                @Override
                public void hasPermission() {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNumber, null, message, null, null);
                }

                @Override
                public void noPermission() {

                }
            }, Manifest.permission.SEND_SMS);



        }
    }
}

