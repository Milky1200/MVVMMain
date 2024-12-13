package com.example.mvvmmain.QuoteDB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mvvmmain.Model.Quote;

@Database(entities = Quote.class,version = 1)
public abstract class MyQuoteDB extends RoomDatabase {
    public abstract QuoteDao dao();

    private static volatile MyQuoteDB myQuoteDB;

    public static MyQuoteDB getInstance(Context context){
        if(myQuoteDB==null) {
            synchronized (MyQuoteDB.class){
                if (myQuoteDB==null){
                    myQuoteDB=Room.databaseBuilder(context.getApplicationContext()
                            ,MyQuoteDB.class,
                            "QuoteDataBase").allowMainThreadQueries()
                            .build();
                }
            }
        }
        return myQuoteDB;
    }
}
