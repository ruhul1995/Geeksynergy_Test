package com.example.geeksynergy_test.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.geeksynergy_test.R;
import com.example.geeksynergy_test.model.Result;

import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {

    private String POSTER_IMAGE_PATH_PREFIX = "https://image.tmdb.org/t/p/w300" ;
    private Context context;
    private List<Result> movieList;

    public MovieListAdapter(Context context, List<Result> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    //for modifying the data
    public void setMovieList(List<Result> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListAdapter.ViewHolder holder, int position) {

        Result obj = movieList.get(position);

        //holder.tvTitle.setText(movieList.get(position).getResults().get(position).getTitle());
        holder.mMovieTitle.setText("Title : "+obj.getOriginalTitle());
        holder.mLanguage.setText("Language: "+obj.getOriginalLanguage());
        holder.mRatings.setText("Ratings: "+obj.getVoteAverage().toString());
        holder.mReleaseDate.setText("ReleaseDate: "+obj.getReleaseDate());

        String poster_image_path = POSTER_IMAGE_PATH_PREFIX + obj.getPosterPath();

        Glide.with(context).load(poster_image_path).into(holder.imageView);
         /*Glide.with(context)
                .load(poster_image_path)
                .apply(RequestOptions.fitCenterTransform())
                .into(holder.imageView);*/
    }

    @Override
    public int getItemCount() {
        if (movieList != null)
            return movieList.size();

        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mMovieTitle, mLanguage, mRatings, mReleaseDate;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageViewIv);
            mMovieTitle = itemView.findViewById(R.id.titleNameView);
            mLanguage = itemView.findViewById(R.id.languageTView);
            mRatings = itemView.findViewById(R.id.VoteAvgView);
            mReleaseDate = itemView.findViewById(R.id.ReleaseDateView);
        }
    }
}