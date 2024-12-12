package com.example.mvvmmain.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmmain.Model.Quote;
import com.example.mvvmmain.Repository.QuoteRepository;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {
    QuoteRepository quoteRepository;

    public MainViewModel(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public LiveData<List<Quote>> getQuotes(){
        return quoteRepository.getQuotes();
    }
}
