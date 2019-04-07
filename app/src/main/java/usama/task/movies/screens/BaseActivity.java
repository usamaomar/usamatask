package usama.task.movies.screens;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cat.us.movies.R;

/**
 * Created by usamaomar on 4/7/19.
 */

public class BaseActivity extends AppCompatActivity {

    protected static Context context;
    private ProgressDialog progressDialog;


    protected static void transitionTo(Activity context, Intent intent) {
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;

    }

    protected void showProgressDialog() {
        try {
            if (progressDialog == null) {
                progressDialog = ProgressDialog.show(context, "Loading..", "Please Wait", true, true);
            } else progressDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void hideProgressDialog() {
        try {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.hide();
                progressDialog.dismiss();
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
