package usama.task.movies.screens;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;

import usama.task.movies.core.models.ErrorModel;
import usama.task.movies.core.models.MainMovieModel;
import usama.task.movies.core.models.MovieModel;
import usama.task.movies.core.operators.MovieOperator;
import usama.task.movies.core.operators.Operator;
import usama.task.movies.screens.adapter.MovieAdapter;
import cat.us.movies.R;

public class MainActivity extends BaseActivity {

    private RecyclerView moviesRecyclerView;
    private int pageValue = 1;
    private MovieAdapter movieAdapter;
    private ArrayList<MovieModel> moviesModelArrayList;
    private ProgressBar progressBar;

    public static void start(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        transitionTo((Activity) context, intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intViews();
        initApis();
    }


    private void intViews() {
        moviesRecyclerView = (RecyclerView) findViewById(R.id.moviesRecyclerView);
        moviesRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    private void initApis() {
        progressBar.setVisibility(View.VISIBLE);
        MovieOperator.getTopRated(pageValue, new Operator.OnServerResponseListener<MainMovieModel>() {
            @Override
            public void onSuccess(MainMovieModel movieModel) {
                try {
                    progressBar.setVisibility(View.GONE);
                    if (movieModel != null && movieModel.getResults() != null && movieModel.getResults().size() > 0) {
                        if (moviesModelArrayList == null) {
                            moviesModelArrayList = movieModel.getResults();
                        } else {
                            moviesModelArrayList.addAll(movieModel.getResults());
                        }
                        intiAdapter();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();

                }
            }

            @Override
            public void onError(ErrorModel e) {
            }
        });
    }

    private void intiAdapter() {
        if (movieAdapter == null) {
            movieAdapter = new MovieAdapter(context, moviesModelArrayList, new MovieAdapter.OnItemClick() {
                @Override
                public void itemSelectedToShare(MovieModel categoriesModel) {
                    showDetails(categoriesModel);
                }

                @Override
                public void pageCount() {
                    try {
                        pageValue = pageValue + 1;
                        initApis();
                    } catch (Exception ex) {
                        ex.getMessage();
                    }
                }
            });
            moviesRecyclerView.setAdapter(movieAdapter);
        }
        movieAdapter.notifyDataSetChanged();
    }

    private void showDetails(MovieModel movieModel) {
        DescriptionActivity.start(context, movieModel);
    }

}
