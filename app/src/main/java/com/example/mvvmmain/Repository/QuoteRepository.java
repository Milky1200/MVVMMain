package com.example.mvvmmain.Repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmmain.Api.RetrofitHelper;
import com.example.mvvmmain.Model.Quote;
import com.example.mvvmmain.Model.Root;
import com.example.mvvmmain.QuoteDB.MyQuoteDB;
import com.example.mvvmmain.Utils.NetworkUtils;

import java.net.ContentHandler;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class QuoteRepository {
    private final RetrofitHelper retrofitHelper;
    private final MyQuoteDB myQuoteDB;
    Context context;

    private final MutableLiveData<List<Quote>> quotesLiveData = new MutableLiveData<>();

    public QuoteRepository(RetrofitHelper retrofitHelper, MyQuoteDB myQuoteDB,Context context) {
        this.retrofitHelper = retrofitHelper;
        this.myQuoteDB = myQuoteDB;
        this.context=context;
    }

    public LiveData<List<Quote>> getQuotes() {
        if(NetworkUtils.isInternetAvailable(context)){
            Call<Root> call = retrofitHelper.getQuoteService().getQuotes();
            call.enqueue(new Callback<Root>() {
                @Override
                public void onResponse(Call<Root> call, Response<Root> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        quotesLiveData.postValue(response.body().getQuotes());
                        Log.d("QuoteRepository", "Data fetched successfully: " + response.body().getQuotes().size());
                        List<Quote> quotes=response.body().getQuotes();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                new Thread(()->myQuoteDB.dao().insertQuotes(quotes)).start();
                            }
                        }).start();
                    } else {
                        quotesLiveData.postValue(null);
                        Log.e("QuoteRepository", "Response unsuccessful: " + response.message());
                    }
                }

                @Override
                public void onFailure(Call<Root> call, Throwable t) {
                    quotesLiveData.postValue(null);
                    Log.e("QuoteRepository", "Data fetch failed: " + t.getMessage());
                }
            });
            Log.e("Internet Available", "getQuotes: ");
        }
        else {
            LiveData<List<Quote>> localQuotes = myQuoteDB.dao().getQuotes();
            localQuotes.observeForever(quotes -> quotesLiveData.postValue(quotes));
            Log.e("Internet Not Available", "getQuotes: ");

        }

        return quotesLiveData;
    }
}
