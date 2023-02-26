package com.example.countryname;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.countryname.Model.CountryModel;
import com.example.countryname.Model.Result;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView  recycler;
    CountryAdapter countryAdapter;
    ArrayList<CountryModel>  countries;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GetCountries();
    }

    public Object GetCountries() {

       // Class<Result> call = RetrofitInstance.getInstance().getApi().getResult();

      Call<Result> call = RetrofitInstance.getInstance().getApi().getResult();


      call.enqueue(new Callback<Result>() {
          @Override
          public void onResponse(Call<Result> call, Response<Result> response) {
                Result result = response.body();
                if(result!= null && result.getResult() != null){
                    countries =(ArrayList<CountryModel>) result.getResult();
                    ViewData();
                }
                
          }

          @Override
          public void onFailure(Call<Result> call, Throwable t) {
              Toast.makeText(MainActivity.this, "Error occur Bastard "+ t.getMessage(), Toast.LENGTH_SHORT).show();

          }
      });
      return  countries;
    }

    private void ViewData() {
        recycler = findViewById(R.id.recyclerView);
        countryAdapter = new CountryAdapter(countries,this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(countryAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        MenuItem item = menu.findItem(R.id.search_menu);
        SearchView searchView= (SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                countryAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}