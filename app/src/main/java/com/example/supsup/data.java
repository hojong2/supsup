package com.example.supsup;

public class data {
    private int textimage;
    private String textname;
    private String textperson;

    public void setTextname(String name) {
        textname = name;
    }
    public void setTextperson(String person) {
        textperson = person;
    }
    public void setImage(int image) {
        textimage = image;
    }
    public int getImage()
    {
        return this.textimage;
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
