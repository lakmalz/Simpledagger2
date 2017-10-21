package lk.lakmalz.simpledagger2.service.sync;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

import lk.lakmalz.simpledagger2.App;
import lk.lakmalz.simpledagger2.model.Post;
import lk.lakmalz.simpledagger2.service.RestAPI;
import lk.lakmalz.simpledagger2.utils.NetworkAccess;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by A Lakmal Weerasekara (Lakmalz) on 21/10/17.
 * alrweerasekara@gmail.com
 */

public class PostSync {

    private static final String TAG = "PostSync";

    Context mContext;
    RestAPI mRestAPI;

    public PostSync(Context context, RestAPI restAPI) {
        mContext = context;
        mRestAPI = restAPI;
    }

    public void getPosts() {

        if (!NetworkAccess.create(mContext).isConnected()) {

            return;
        }

        mRestAPI.getPosts().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                for (Post post : response.body()) {
                    Log.i(TAG, "onResponse: " + post.getTitle());
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.i(TAG, "onFailure: "+t.toString());
            }
        });
    }
}
