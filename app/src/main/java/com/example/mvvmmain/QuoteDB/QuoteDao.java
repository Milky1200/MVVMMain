package com.example.mvvmmain.QuoteDB;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mvvmmain.Model.Quote;

import java.util.List;

@Dao
public interface QuoteDao {
    @Insert
    public void insertQuote(Quote quote);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertQuotes(List<Quote> quotes);

    @Query("Select * from QuoteTable")
    public LiveData<List<Quote>> getQuotes();

}
