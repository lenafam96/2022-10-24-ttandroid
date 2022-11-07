package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class vd extends AppCompatActivity {
    EditText Name, Pass, updateold, updatenew, delete;
    myDbAdapter helper;
    Button button,button2,button3,button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vd);
        Init();
        Action();
    }

    private void Init() {
        Name = (EditText) findViewById(R.id.editName);
        Pass = (EditText) findViewById(R.id.editPass);
        updateold = (EditText) findViewById(R.id.editText3);
        updatenew = (EditText) findViewById(R.id.editText5);
        delete = (EditText) findViewById(R.id.editText6);
        helper = new myDbAdapter(this);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
    }

    private void Action() {
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = helper.getData();
                Message.message(vd.this, data);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t1 = Name.getText().toString();
                String t2 = Pass.getText().toString();
                if (t1.isEmpty() || t2.isEmpty()) {
                    Message.message(getApplicationContext(), "Enter Both Name and Password");
                } else {
                    long id = helper.insertData(t1, t2);
                    if (id <= 0) {
                        Message.message(getApplicationContext(), "Insertion Unsuccessful");
                    } else {
                        Message.message(getApplicationContext(), "Insertion Successful");
                    }
                    Name.setText("");
                    Pass.setText("");
                }
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String u1 = updateold.getText().toString();
                String u2 = updatenew.getText().toString();
                if (u1.isEmpty() || u2.isEmpty()) {
                    Message.message(getApplicationContext(),"Enter Data");
                } else {
                    Message.message(getApplicationContext(), "Enter Data");
                    int a = helper.updateName(u1, u2);
                    if (a <= 0) {
                        Message.message(getApplicationContext(), "Unsuccessful");
                        updateold.setText("");
                        updatenew.setText("");
                    } else {
                        Message.message(getApplicationContext(), "Updated");
                        updateold.setText("");
                        updatenew.setText("");
                    }
                }
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = delete.getText().toString();
                if (uname.isEmpty()) {
                    Message.message(getApplicationContext(), "Enter  Data");
                } else {
                    int a = helper.delete(uname);
                    if (a <= 0) {
                        Message.message(getApplicationContext(), "Unsuccessful");
                        delete.setText("");
                    } else {
                        Message.message(vd.this, "DELETED");
                        delete.setText("");
                    }
                }
            }
        });
    }
}