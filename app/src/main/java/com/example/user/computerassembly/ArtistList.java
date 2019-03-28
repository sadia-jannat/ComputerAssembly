package com.example.user.computerassembly;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by User on 11/16/2018.
 */

public class ArtistList extends ArrayAdapter<Artist> {

    private Activity context;
    private List<Artist> artistList;

    public ArtistList(Activity context,List<Artist> artistList)
    {
        super(context,R.layout.list_layout,artistList);
        this.context=context;
        this.artistList=artistList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View listViewItem=inflater.inflate(R.layout.list_layout,null,true);


        TextView tone=listViewItem.findViewById(R.id.usernamelistid);
        TextView ttwo=listViewItem.findViewById(R.id.spnerlistid);
        TextView tthree=listViewItem.findViewById(R.id.brandlistid);
        TextView tfour=listViewItem.findViewById(R.id.quantitylistid);
        TextView tfive=listViewItem.findViewById(R.id.phonlistid);
        TextView tsix=listViewItem.findViewById(R.id.addresslistid);

        Artist artist=artistList.get(position);
        tone.setText(artist.getArtistName());
        ttwo.setText(artist.getArtistGenre());
        tthree.setText(artist.getArtistBrand());
        tfour.setText(artist.getArtistQuantity());
        tfive.setText(artist.getArtistNumber());
        tsix.setText(artist.getArtistAddress());
        return listViewItem;


    }
}
