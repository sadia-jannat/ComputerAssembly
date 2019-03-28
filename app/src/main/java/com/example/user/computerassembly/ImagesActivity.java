package com.example.user.computerassembly;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class ImagesActivity extends AppCompatActivity implements ImageAdapter.OnItemClickListener {

    private RecyclerView mRecyclerView;
    private ImageAdapter mAdapter;

    private ProgressBar mprogress;

    private FirebaseStorage mStorage;
    private DatabaseReference mDatabase;

    private ValueEventListener mDBlistener;
    private List<Upload> mUploads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView=findViewById(R.id.recyclerid);
        mprogress=findViewById(R.id.progressid);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mUploads=new ArrayList <>();

        mAdapter=new ImageAdapter(ImagesActivity.this,mUploads);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(ImagesActivity.this);

        mStorage=FirebaseStorage.getInstance();


        mDatabase= FirebaseDatabase.getInstance().getReference("upload");
        mDBlistener=mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

             mUploads.clear();

                for (DataSnapshot postSnapshot:dataSnapshot.getChildren())
                {
                    Upload upload=postSnapshot.getValue(Upload.class);

                    upload.setkey(postSnapshot.getKey());
                    mUploads.add(upload);
                }

               mAdapter.notifyDataSetChanged();


                mprogress.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(ImagesActivity.this,databaseError.getMessage(),Toast.LENGTH_LONG).show();

                mprogress.setVisibility(View.INVISIBLE);

            }
        });
    }


    @Override
    public void onItemClick(int position) {
        //Toast.makeText(this,"Normal Click At Position: "+position,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onWhatEverClick(int position) {

        //Toast.makeText(this,"Whatever Click At Position: "+position,Toast.LENGTH_LONG).show();

    }

    @Override
    public void onDeleteClick(int position) {
       Upload selectedItem=mUploads.get(position);

       final String selectedkey=selectedItem.getKey();
        StorageReference imageRef=mStorage.getReferenceFromUrl(selectedItem.getmImageUrl());
        imageRef.delete().addOnSuccessListener(new OnSuccessListener <Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                mDatabase.child(selectedkey).removeValue();
                Toast.makeText(ImagesActivity.this,"Delete successful",Toast.LENGTH_LONG).show();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDatabase.removeEventListener(mDBlistener);
    }
}
