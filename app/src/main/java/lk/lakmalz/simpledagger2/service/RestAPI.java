package lk.lakmalz.simpledagger2.service;

import java.util.List;

import lk.lakmalz.simpledagger2.model.Post;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by A Lakmal Weerasekara (Lakmalz) on 21/10/17.
 * alrweerasekara@gmail.com
 */

public interface RestAPI {

    @GET("/posts")
    Call<List<Post>> getPosts();
}
