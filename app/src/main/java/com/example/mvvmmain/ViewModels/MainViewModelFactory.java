package com.example.mvvmmain.ViewModels;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.mvvmmain.Repository.QuoteRepository;

public class MainViewModelFactory implements ViewModelProvider.Factory {
    private final QuoteRepository quoteRepository;

    public MainViewModelFactory(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            //noinspection unchecked
            return (T) new MainViewModel(quoteRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
