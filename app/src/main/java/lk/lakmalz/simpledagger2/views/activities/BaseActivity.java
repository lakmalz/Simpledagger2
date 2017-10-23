package lk.lakmalz.simpledagger2.views.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import javax.inject.Inject;

import lk.lakmalz.simpledagger2.App;
import lk.lakmalz.simpledagger2.service.RestAPI;

/**
 * Created by A Lakmal Weerasekara (Lakmalz) on 22/10/17.
 * alrweerasekara@gmail.com
 */

public class BaseActivity extends AppCompatActivity {
    public Context mContext;
    private ProgressDialog mProgressDailog;

    @Inject
    RestAPI mRestAPI;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((App) getApplication()).getNetComponent().inject(this);
        init();
    }

    private void init() {
        mContext = this;
        mProgressDailog = new ProgressDialog(this);
        mProgressDailog.setMessage("Please wait...");
    }

    protected void showProgress() {
        if (mProgressDailog != null) {
            mProgressDailog.show();
        }
    }

    protected void dismissProgress() {
        if (mProgressDailog != null) {
            mProgressDailog.dismiss();
        }
    }

    protected void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
