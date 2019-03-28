package com.example.user.computerassembly;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class AdminHomeActivity extends AppCompatActivity {

    private Button addbutton;
    private Button viewbutton;
    private Button confermbutton;
    private Button logoutbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        addbutton=findViewById(R.id.addviewid);
        viewbutton=findViewById(R.id.vieworderid);
        confermbutton=findViewById(R.id.confermid);
        logoutbutton=findViewById(R.id.logoutadminid);
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add=new Intent(AdminHomeActivity.this,AddImageActivity.class);
                startActivity(add);
            }
        });


        viewbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent d=new Intent(AdminHomeActivity.this,DekboActivity.class);
                startActivity(d);
            }
        });

        confermbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent con=new Intent(AdminHomeActivity.this,ConfermUserActivity.class);
                startActivity(con);
            }
        });

        logoutbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                finish();
                Intent out=new Intent(AdminHomeActivity.this,MainActivity.class);
                startActivity(out);
            }
        });


    }
}
