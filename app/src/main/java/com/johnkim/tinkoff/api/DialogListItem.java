package com.johnkim.tinkoff.api;

import android.app.Dialog;

import com.johnkim.tinkoff.db.ChatDB;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Date;

@Table(database = ChatDB.class)
public class DialogListItem extends BaseModel {

    public DialogListItem() {

    }

    public DialogListItem(String title, String description,String image, Date date) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.date = date;
    }

    public DialogListItem(Integer id, String title, String description,String image, Date date) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @PrimaryKey(autoincrement = true)
    private Integer id;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private String image;

    @Column
    private Date date;
}