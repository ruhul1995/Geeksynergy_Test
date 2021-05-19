package com.example.geeksynergy_test.viewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.geeksynergy_test.model.MovieModel;
import com.example.geeksynergy_test.model.Result;
import com.example.geeksynergy_test.network_call.ApiInterface;
import com.example.geeksynergy_test.network_call.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListViewModel extends ViewModel {

    private MutableLiveData<List<Result>> moviesList;

    public MovieListViewModel()
    {
        moviesList = new MutableLiveData<>();
    }

    public MutableLiveData<List<Result>> getMoviesListObservables()
    {
        return moviesList;
    }
    public void makeApiCall() {
        ApiInterface apiInterface = RetrofitInstance.getRetrofitInstance().create(ApiInterface.class);
        Call<MovieModel> call = apiInterface.getMoviesList("6c97fd958e68bd6fbf932b9f3a995306");
        call.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {

                //Log.d("response : ",response.body().toString());
                MovieModel resource = response.body();
                //Log.d("resource : ",resource.toString());
                List<Result> mylist = resource.getResults();
                //Log.d("value of mylist : ",mylist.toString());
                moviesList.postValue(mylist);
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {
                Log.d("error : ","Api call was not successfull");
                moviesList.postValue(null);
            }
        });
    }
}
