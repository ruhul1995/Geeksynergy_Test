package com.example.geeksynergy_test;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.geeksynergy_test.adapter.MovieListAdapter;
import com.example.geeksynergy_test.model.Result;
import com.example.geeksynergy_test.viewModel.MovieListViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    LinearLayout linearLayout ;

    private List<Result> movieModelList;
    private MovieListAdapter adapter;
    private MovieListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.mCompanyInfo);
        linearLayout = findViewById(R.id.mlinearLayout);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        //to show if api call fails
        final TextView noResultFound  = findViewById(R.id.noResult_Tv);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new MovieListAdapter(this, movieModelList);
        recyclerView.setAdapter(adapter);

        //call our viewModel
        viewModel = ViewModelProviders.of(this).get(MovieListViewModel.class);
        viewModel.getMoviesListObservables().observe(this, new Observer<List<Result>>() {
            @Override
            public void onChanged(List<Result> movieModels) {
                if(movieModels != null){
                    Log.d("value : ",movieModels.toString());
                    movieModelList = movieModels;
                    adapter.setMovieList(movieModelList);
                    noResultFound.setVisibility(View.GONE);
                }
                else
                {
                    Log.d("error : ",viewModel.toString());
                    noResultFound.setVisibility(View.VISIBLE);
                }
            }
        });

        viewModel.makeApiCall();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.Company_info:
                textView.setText(" Company : Geeksynergy Technologies Pvt Ltd \n Address: Sanjayanagar, Bengaluru-56 \n Phone: XXXXXXXXX09 \n Email: XXXXXX@gmail.com");
                textView.setVisibility(View.VISIBLE);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        textView.setVisibility(View.GONE);
        //super.onBackPressed();
    }
}