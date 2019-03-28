package com.example.user.computerassembly;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegUserActivity extends AppCompatActivity {
    DatabaseReference database;
    FirebaseAuth mauth;
    EditText nameuser,phoneuser,edtPassword,edtMail;
    Button btnsingup,btnsingin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_user);
        mauth=FirebaseAuth.getInstance();

        database= FirebaseDatabase.getInstance().getReference("user");

        //edtUsername=findViewById(R.id.b1id);
        edtMail=findViewById(R.id.e1userregid);
        edtPassword=findViewById(R.id.e2userregid);
        nameuser=findViewById(R.id.nameuserid);
        phoneuser=findViewById(R.id.phoneuserid);


        btnsingup=findViewById(R.id.buttonuserregid);
        //btnsingin=findViewById(R.id.b5id);

        //   btnsingup.setOnClickListener(new View.OnClickListener() {
        //  @Override
        // public void onClick(View view) {
        //  Intent i=new Intent(getApplicationContext(),LoginActivity.class);
        //  startActivity(i);
        //  }
        // });

        btnsingup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUserInfo();
            }
        });
    }

    private void addUserInfo(){
        //final   String name= edtUsername.getText().toString().trim();
        final   String email= edtMail.getText().toString().trim();
        final  String password= edtPassword.getText().toString().trim();

        final   String usernameinfo= nameuser.getText().toString().trim();
        final  String userphoneinfo= phoneuser.getText().toString().trim();

        mauth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    String userid = mauth.getCurrentUser().getUid();
                    DatabaseReference userdata = database.child("main").child(userid);

                    userdata.child("email").setValue(email);
                    userdata.child("usernameinfo").setValue(usernameinfo);
                    userdata.child("userphoneinfo").setValue(userphoneinfo);
                    //userdata.child("name").setValue(name);

                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(i);


                }

                else {
                    Toast.makeText(RegUserActivity.this, "already registered..Go login..", Toast.LENGTH_LONG).show();
                }


            }
        });



    }
}
