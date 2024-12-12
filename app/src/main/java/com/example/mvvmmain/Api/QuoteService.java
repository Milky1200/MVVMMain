package com.example.mvvmmain.Api;

import com.example.mvvmmain.Model.Quote;
import com.example.mvvmmain.Model.Root;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface QuoteService {
    @GET("quotes")
    Call<Root> getQuotes();
}
