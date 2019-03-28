package com.example.user.computerassembly;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class AddImageActivity extends AppCompatActivity {


    private static final int pickimage=1;

    private Button bimage,b2image;

    private EditText eimage,eimage2;
    private ImageView iimage;

    private Uri mImageUri;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;

    private StorageTask mUploadtask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_image);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bimage=findViewById(R.id.bimageid);
        b2image=findViewById(R.id.b2imageid);
        eimage=findViewById(R.id.eimageid);
        eimage2=findViewById(R.id.eimage2id);
        iimage=findViewById(R.id.iimageid);


        mStorageRef= FirebaseStorage.getInstance().getReference("upload");
        mDatabaseRef= FirebaseDatabase.getInstance().getReference("upload");

        bimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openfilechooser();
            }
        });
        b2image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mUploadtask!=null)
                {
                    Toast.makeText(AddImageActivity.this,"upload is progress",Toast.LENGTH_LONG).show();

                }else {
                    uploadfile();
                }

            }
        });
    }

    private  void openfilechooser()
    {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,pickimage);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==pickimage && resultCode==RESULT_OK && data!=null && data.getData() !=null)
        {
            mImageUri=data.getData();

            Picasso.with(this).load(mImageUri).into(iimage);

        }
    }

    private  String getfileextension(Uri uri)
    {
        ContentResolver cr=getContentResolver();
        MimeTypeMap mime=MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }


    private void uploadfile()
    {
        if (mImageUri!=null)
        {
            StorageReference fileReference=mStorageRef.child(System.currentTimeMillis()+"."+getfileextension(mImageUri));

            mUploadtask= fileReference.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Toast.makeText(AddImageActivity.this,"success upload",Toast.LENGTH_LONG).show();

                    Upload upload=new Upload(eimage.getText().toString().trim(),eimage2.getText().toString().trim(),taskSnapshot.getDownloadUrl().toString());

                    String uploadId=mDatabaseRef.push().getKey();
                    mDatabaseRef.child(uploadId).setValue(upload);


                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(AddImageActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();

                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                }
            });
        }else
        {
            Toast.makeText(this,"no file selected",Toast.LENGTH_LONG).show();
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
