package lk.lakmalz.simpledagger2.views.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;

import lk.lakmalz.simpledagger2.R;

/**
 * Created by A Lakmal Weerasekara (Lakmalz) on 30/10/17.
 * alrweerasekara@gmail.com
 */

public class MainActionBarActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_actionbar);

        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
*/
        /*CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapse_bar);
        collapsingToolbarLayout.setTitle("ABX");*/
    }
}
