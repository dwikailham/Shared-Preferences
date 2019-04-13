package com.dwikailham.notesapp.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Note {
    public String title, content;
    public Date date;

    public Note(String title, Date date, String content) {
        this.title = title;
        this.content = content;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFormattedDate(){
        //Tampilan tanggal yang dicetak : 01 Mar 2019
        DateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
        return formatter.format(date);
    }
}
