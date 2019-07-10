package com.zxp.jdbc4mysql;

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
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection cn= DriverManager.getConnection("jdbc:mysql://192.168.117.1.1:3306/librarydb","test","");
                    System.out.println("成功地获取数据库连接！！");
                    String sql="select S_Name from student";
                    Statement st=(Statement)cn.createStatement();
                    ResultSet rs=st.executeQuery(sql);

                    while(rs.next()){
                        String mybook=rs.getString("S_Name");
                        Log.i("Mainactivity",mybook);
                    }
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
