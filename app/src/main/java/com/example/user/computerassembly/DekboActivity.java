package com.example.user.computerassembly;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DekboActivity extends AppCompatActivity {
    ListView l;
    DatabaseReference databaseArtist;
    List<Artist> artistList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dekbo);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        databaseArtist= FirebaseDatabase.getInstance().getReference("artists");
        l=findViewById(R.id.dekboid);

        artistList=new ArrayList <>();

    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseArtist.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                artistList.clear();

                for (DataSnapshot artistSnapshot:dataSnapshot.getChildren())
                {
                    Artist artist=artistSnapshot.getValue(Artist.class);
                    artistList.add(artist);
                }

                ArtistList adapter=new ArtistList(DekboActivity.this,artistList);
                l.setAdapter(adapter);

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
