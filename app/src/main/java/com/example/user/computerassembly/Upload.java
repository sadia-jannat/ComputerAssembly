package com.example.user.computerassembly;

import com.google.firebase.database.Exclude;

import java.security.Key;

/**
 * Created by User on 11/15/2018.
 */

public class Upload {
    private String mName;
    private  String mPrize;
    private String mImageUrl;

  private String mKey;

    public Upload()
    {

    }
    public Upload(String name,String prize,String imageurl)
    {
        if (name.trim().equals("")){
            name="No Name";
        }
        if (prize.trim().equals("")){
            name="No Prize";
        }
        mName=name;
        mPrize=prize;
        mImageUrl=imageurl;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmPrize() {
        return mPrize;
    }

    public void setmPrize(String mPrize) {
        this.mPrize = mPrize;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }
    @Exclude
    public String getKey()
    {
        return  mKey;
    }
    @Exclude
    public void setkey(String key)
    {
       mKey= key;
    }


}
