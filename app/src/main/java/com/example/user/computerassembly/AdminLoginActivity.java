package com.example.user.computerassembly;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminLoginActivity extends AppCompatActivity {
    private Button b1;

    private TextView t;
    private FirebaseAuth mAuth;
    private EditText studentID,Pin;

    private DatabaseReference database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        studentID=findViewById(R.id.e1adminid);
        Pin=findViewById(R.id.e2adminid);

        b1=findViewById(R.id.buttonadminid);
        t=findViewById(R.id.textadminid);




        mAuth=FirebaseAuth.getInstance();

        database= FirebaseDatabase.getInstance().getReference().child("user").child("admin");
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(AdminLoginActivity.this,AdminRegActivity.class);
                startActivity(i);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cheaklogin();


            }
        });
    }

    public  void cheaklogin()
    {

        String email=studentID.getText().toString().trim();
        String pass=Pin.getText().toString().trim();
        if (!TextUtils.isEmpty(email)  && !TextUtils.isEmpty(pass))
        {
            mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful())
                    {
                        checkuserlist();

                    }
                    else if (task.getException()instanceof FirebaseAuthInvalidCredentialsException)
                    {
                        Toast.makeText(AdminLoginActivity.this,"Invaild email and password",Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }

    }

    public  void  checkuserlist()
    {
        final String userid=mAuth.getCurrentUser().getUid();
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.hasChild(userid))
                {
                    Toast.makeText(AdminLoginActivity.this,"success log in",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(getApplicationContext(),AdminHomeActivity.class);
                    startActivity(i);
                }

                else
                {
                    Toast.makeText(AdminLoginActivity.this,"need reg",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();

        if (id==android.R.id.home)
        {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
