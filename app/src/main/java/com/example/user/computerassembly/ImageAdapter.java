package com.example.user.computerassembly;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by User on 11/15/2018.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private Context mccontext;
    private List<Upload> mUploads;

    private OnItemClickListener mListener;



    public ImageAdapter(Context context,List<Upload>uploads)
    {
        mccontext=context;
        mUploads=uploads;

    }


    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mccontext).inflate(R.layout.image_item,parent,false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        Upload uploadcurrent=mUploads.get(position);
        holder.textViewname.setText(uploadcurrent.getmName());
        holder.textViewprize.setText(uploadcurrent.getmPrize());

        Picasso.with(mccontext).load(uploadcurrent.getmImageUrl()).placeholder(R.mipmap.ic_launcher).fit().centerInside().into(holder.imageViewdekbo);

    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnCreateContextMenuListener,MenuItem.OnMenuItemClickListener{

        public TextView textViewname;
        public TextView textViewprize;
        public ImageView imageViewdekbo;

        public ImageViewHolder(View itemView)  {
            super(itemView);

            textViewname=itemView.findViewById(R.id.name_image_id);
            textViewprize=itemView.findViewById(R.id.prize_image_id);
            imageViewdekbo=itemView.findViewById(R.id.image_dekbo_id);

            itemView.setOnClickListener(this);

            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListener!=null)
            {
                int position=getAdapterPosition();
                if (position!=RecyclerView.NO_POSITION)
                {
                    mListener.onItemClick(position);
                }
            }
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Select Action");
            MenuItem doWhatever=menu.add(Menu.NONE,1,1,"Do Whatever");
            MenuItem delete=menu.add(Menu.NONE,2,2,"Delete");

            doWhatever.setOnMenuItemClickListener(this);
            delete.setOnMenuItemClickListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {

            if (mListener!=null)
            {
                int position=getAdapterPosition();
                if (position!=RecyclerView.NO_POSITION)
                {
                   switch (item.getItemId())
                   {
                       case  1:
                           mListener.onWhatEverClick(position);
                           return true;
                       case  2:
                           mListener.onDeleteClick(position);
                           return true;
                   }
                }
            }
            return false;
        }
    }


    public  interface  OnItemClickListener{
        void onItemClick(int position);
        void onWhatEverClick(int position);
        void onDeleteClick(int position);
    }
    public  void setOnItemClickListener(OnItemClickListener listener)
    {

        mListener=listener;

    }

}


