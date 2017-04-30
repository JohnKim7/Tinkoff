package com.johnkim.tinkoff.api;

import com.johnkim.tinkoff.db.ChatDB;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Date;

@Table(database = ChatDB.class)
public class DialogItem extends BaseModel {

    public DialogItem() {

    }

    public DialogItem(String text, Integer senderId, Date date) {
        this.text = text;
        this.senderId = senderId;
        this.date = date;
    }

    public DialogItem(Integer id, String text, Integer senderId, Date date) {
        this.id = id;
        this.text = text;
        this.senderId = senderId;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @PrimaryKey(autoincrement = true)
    private Integer id;

    @Column
    private String text;

    @Column
    private Integer senderId;

    @Column
    private Date date;
}