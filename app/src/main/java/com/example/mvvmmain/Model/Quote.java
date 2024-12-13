package com.example.mvvmmain.Model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "QuoteTable")
public class Quote{
    @PrimaryKey
    public int id;
    public String quote;
    public String author;

    @NonNull
    @Override
    public String toString() {
        return (" "+id+" "+quote+" "+author);
    }
}
