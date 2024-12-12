package com.example.mvvmmain.Model;

import androidx.annotation.NonNull;

public class Quote{
    public int id;
    public String quote;
    public String author;

    @NonNull
    @Override
    public String toString() {
        return (" "+id+" "+quote+" "+author);
    }
}
