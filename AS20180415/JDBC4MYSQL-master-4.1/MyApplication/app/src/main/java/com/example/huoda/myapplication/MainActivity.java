package com.example.huoda.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        new Thread(new Runnable() {
            @Override
            public void run() {
                try { System.out.println("1！！");
                    Class.forName("com.mysql.jdbc.Driver");
                    System.out.println("2！！");
                    Connection cn= DriverManager.getConnection("jdbc:mysql://192.168.117.1:3306/librarydb","test","");
                    System.out.println("3！！");
                  //   Toast.makeText(MainActivity.this,"I Love You",Toast.LENGTH_SHORT).show();
                    //String sql="select B_Name from book";
                      String sql="select S_Name from student";
                    // String sql="select B_Buytime from book";
                    Statement st=(Statement)cn.createStatement();
                      ResultSet rs=st.executeQuery(sql);
                    System.out.println("成功地获取数据库连接！！");
                    //  i=0;
                    while(rs.next()){
                        String mybook=rs.getString("S_Name");
                        Log.i("Mainactivity",mybook);
                        System.out.println(mybook);
                        //i++;
                        //tv_1.setText(mybook);
                    }
                    // String mybook=rs.getString("S_Name");
                    // tv_1.setText(mybook);
                    cn.close();
                    st.close();
                      rs.close();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
