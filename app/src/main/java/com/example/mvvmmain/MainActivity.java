package com.example.mvvmmain;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.mvvmmain.Api.RetrofitHelper;
import com.example.mvvmmain.Model.Quote;
import com.example.mvvmmain.QuoteDB.MyQuoteDB;
import com.example.mvvmmain.Repository.QuoteRepository;
import com.example.mvvmmain.ViewModels.MainViewModel;
import com.example.mvvmmain.ViewModels.MainViewModelFactory;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    MainViewModel mainViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        QuoteRepository quoteRepository=new QuoteRepository(RetrofitHelper.getInstance(), MyQuoteDB.getInstance(this),this);
        mainViewModel= new ViewModelProvider(this, new MainViewModelFactory(quoteRepository)).get(MainViewModel.class);
        mainViewModel.getQuotes().observe(this, quotes -> {
            if(quotes!=null){
                for(int i=0;i<quotes.size();i++ ){
                    Log.e("Printing Quotes", "onCreate: "+quotes.get(i).toString());
                }
            }else Log.e("Printing Quotes", "onCreate: null");
        });
    }
}