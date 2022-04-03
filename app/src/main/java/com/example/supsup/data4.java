package com.example.supsup;

public class data4 {
    private int textimage;
    private String textname;
    private String textperson;
    private String textlocation;

    public void setTextname(String name) {
        textname = name;
    }
    public void setTextlocation(String location) { textlocation = location;}
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

    public String getTextlocation() { return this.textlocation; }

    public String getTextperson()
    {
        return this.textperson;
    }
}