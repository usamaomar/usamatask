package usama.task.movies.screens;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import cat.us.movies.R;

/**
 * Created by usamaomar on 4/7/19.
 */

public class StartScreen extends BaseActivity implements View.OnClickListener {

    private TextView startTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_start);
        intiViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        context = this;
    }


    private void intiViews() {
        startTextView = (TextView) findViewById(R.id.startTextView);
        startTextView.setOnClickListener(this);
    }

    private void implementation() {
        MainActivity.start(context);
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.startTextView:
                implementation();
                break;
            default:
                break;
        }
    }
}
