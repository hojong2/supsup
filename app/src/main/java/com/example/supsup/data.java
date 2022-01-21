package com.example.supsup;

public class data {
    private int image;
    private String textname;
    private String textperson;

    public data(int image, String textname, String textperson){
        this.image=image;
        this.textname=textname;
        this.textperson=textperson;
    }

    public int getImage()
    {
        return this.image;
    }

    public String getTextname()
    {
        return this.textname;
    }

    public String getTextperson()
    {
        return this.textperson;
    }
}
