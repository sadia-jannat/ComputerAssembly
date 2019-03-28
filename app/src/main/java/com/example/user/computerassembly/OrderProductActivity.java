package com.example.user.computerassembly;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OrderProductActivity extends AppCompatActivity {
    EditText eone,etwo,ethree,efour,efive;
    Spinner spinner;
    Button orderbutton;

    DatabaseReference databaseArtist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_product);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        eone=findViewById(R.id.usernameid);
        etwo=findViewById(R.id.brandid);
        ethree=findViewById(R.id.productquantityid);
        efour=findViewById(R.id.phonenumid);
        efive=findViewById(R.id.addressnameid);
        spinner=findViewById(R.id.spnerid);
        orderbutton=findViewById(R.id.orderbuttonid);

        databaseArtist= FirebaseDatabase.getInstance().getReference("artists");

        orderbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addArtist();

            }
        });
    }
    private void addArtist()
    {
        String name=eone.getText().toString().trim();
        String genre=spinner.getSelectedItem().toString();
        String brand=etwo.getText().toString().trim();
        String quentity=ethree.getText().toString().trim();
        String phone=efour.getText().toString().trim();
        String address=efive.getText().toString().trim();

        if (!TextUtils.isEmpty(name) &&!TextUtils.isEmpty(brand)&&!TextUtils.isEmpty(quentity)&&!TextUtils.isEmpty(phone)&&!TextUtils.isEmpty(address) )
        {
            String id=databaseArtist.push().getKey();
            Artist artist=new Artist(id,name,genre,brand,quentity,phone,address);
            databaseArtist.child(id).setValue(artist);

            Toast.makeText(this,"order successful",Toast.LENGTH_LONG).show();

        }else
        {
            Toast.makeText(this,"You should fillup all data",Toast.LENGTH_LONG).show();
        }


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
