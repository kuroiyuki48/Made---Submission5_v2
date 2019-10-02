package com.example.submission5_v2.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.submission5_v2.BuildConfig;
import com.example.submission5_v2.R;
import com.example.submission5_v2.activity.DetailTvActivity;
import com.example.submission5_v2.activity.ItemClickSupport;
import com.example.submission5_v2.adapter.RecyclerPopularTvAdapter;
import com.example.submission5_v2.entity.MovieResponse;
import com.example.submission5_v2.entity.MovieResult;
import com.example.submission5_v2.retrofit.ApiClientTv;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopularFragmentTv extends Fragment {

    private static final String API_KEY = BuildConfig.API_KEY;
    private static final String LANGUAGE = "en-US";
    private int currentPage = 1;
    private String region = null;

    private RecyclerPopularTvAdapter adapter;

    private Call<MovieResponse> call;
    private ApiClientTv apiClient = null;
    private List<MovieResult> moviesItem = new ArrayList<>();

    @BindView(R.id.recycler_movie)
    RecyclerView recyclerView;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;


    public PopularFragmentTv() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_popular, container, false);

        ButterKnife.bind(this, view);

        region = Locale.getDefault().getCountry();

        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (savedInstanceState != null) {
            moviesItem = savedInstanceState.getParcelableArrayList("movies");
            prepareView();
        } else {
            getMovie();
        }

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                showDetailMovie(moviesItem.get(position).getMovieId());
            }
        });

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("movies", new ArrayList<>(adapter.getMovies()));
    }

    private void prepareView() {
        progressBar.setVisibility(View.GONE);
        adapter = new RecyclerPopularTvAdapter(getActivity(), moviesItem);
        recyclerView.setAdapter(adapter);
    }

    private void getMovie() {
        apiClient = ApiClientTv.getInstance();
        call = apiClient.getApi().getPopularMovies(API_KEY, LANGUAGE, currentPage, region);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    moviesItem = response.body().getMovieResults();
                    if (moviesItem != null) {
                        progressBar.setVisibility(View.GONE);
                        adapter = new RecyclerPopularTvAdapter(getActivity(), moviesItem);
                        recyclerView.setAdapter(adapter);
                    } else {
                        Toast.makeText(getActivity(), R.string.toast_empty, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), R.string.toast_failed, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Toast.makeText(getActivity(), R.string.toast_failed, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void showDetailMovie(int movie_id) {
        String local = "0";
        Intent movieIdIntent = new Intent(getActivity(), DetailTvActivity.class);
        movieIdIntent.putExtra(DetailTvActivity.MOVIE_ID, movie_id);
        movieIdIntent.putExtra(DetailTvActivity.LOCAL_STATUS, local );
        getActivity().startActivity(movieIdIntent);
    }
}
