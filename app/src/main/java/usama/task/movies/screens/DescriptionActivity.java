package usama.task.movies.screens;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import cat.us.movies.R;
import usama.task.movies.core.configuration.RequestsUrls;
import usama.task.movies.core.models.MovieModel;

/**
 * Created by usamaomar on 4/7/19.
 */

public class DescriptionActivity extends BaseActivity {

    public static String EXTRA_DESCRIPTION_CONTENT = "extra_description_content";
    private MovieModel movieModel;
    private ImageView mainImageView;
    private TextView titleTextView;
    private TextView descriptionView;

    public static void start(Context context, MovieModel moveModel) {
        Intent intent = new Intent(context, DescriptionActivity.class);
        intent.putExtra(EXTRA_DESCRIPTION_CONTENT, moveModel);
        transitionTo((Activity) context, intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        getIntents();
        intViews();
        implementation();
    }

    @Override
    protected void onResume() {
        super.onResume();
        context = this;
    }


    private void getIntents() {
        Intent intent = getIntent();
        movieModel = intent.getParcelableExtra(EXTRA_DESCRIPTION_CONTENT);
    }

    private void implementation() {
        try {
            titleTextView.setText(movieModel.getTitle() != null ? movieModel.getTitle() : "No Title");
            descriptionView.setText(movieModel.getOverview() != null ? movieModel.getOverview() : "No Overview");
            if (movieModel.getPosterPath() != null) {
                mainImageView.setVisibility(View.VISIBLE);
                Glide.with(context).load(RequestsUrls.IMAGE_URL + movieModel.getPosterPath()).centerCrop().override(200, 200).into(mainImageView);
            } else {
                mainImageView.setVisibility(View.INVISIBLE);
            }
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    private void intViews() {
        mainImageView = (ImageView) findViewById(R.id.mainImageView);
        titleTextView = (TextView) findViewById(R.id.titleTextView);
        descriptionView = (TextView) findViewById(R.id.descriptionView);
    }


}
