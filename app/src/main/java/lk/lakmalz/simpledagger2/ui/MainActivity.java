package lk.lakmalz.simpledagger2.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

import javax.inject.Inject;

import lk.lakmalz.simpledagger2.App;
import lk.lakmalz.simpledagger2.R;
import lk.lakmalz.simpledagger2.service.RestAPI;
import lk.lakmalz.simpledagger2.model.Post;
import lk.lakmalz.simpledagger2.service.sync.PostSync;
import lk.lakmalz.simpledagger2.utils.NetworkAccess;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Inject
    RestAPI mRestAPI;

    private PostSync mPostSync;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((App)getApplication()).getNetComponent().inject(this);

        Button btnCheckInternet = (Button)findViewById(R.id.btn_get_posts);

        btnCheckInternet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mPostSync = new PostSync(this, mRestAPI);

        mPostSync.getPosts();
    }
}
