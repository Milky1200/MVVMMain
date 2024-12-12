package com.example.mvvmmain.Repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmmain.Api.RetrofitHelper;
import com.example.mvvmmain.Model.Quote;
import com.example.mvvmmain.Model.Root;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class QuoteRepository {
    private final RetrofitHelper retrofitHelper;
    private final MutableLiveData<List<Quote>> quotesLiveData = new MutableLiveData<>();

    public QuoteRepository(RetrofitHelper retrofitHelper) {
        this.retrofitHelper = retrofitHelper;
    }

    public LiveData<List<Quote>> getQuotes() {
        Call<Root> call = retrofitHelper.getQuoteService().getQuotes();

        call.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                if (response.isSuccessful() && response.body() != null) {
                    quotesLiveData.postValue(response.body().getQuotes());
                    Log.d("QuoteRepository", "Data fetched successfully: " + response.body().getQuotes().size());
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

        return quotesLiveData;
    }
}
