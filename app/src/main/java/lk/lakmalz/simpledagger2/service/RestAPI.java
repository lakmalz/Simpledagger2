package lk.lakmalz.simpledagger2.service;

import java.util.List;

import lk.lakmalz.simpledagger2.model.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by A Lakmal Weerasekara (Lakmalz) on 21/10/17.
 * alrweerasekara@gmail.com
 */

public interface RestAPI {
    //?page=1&per_page=3
    @GET("/users")
    Call<List<User>> getUsers(
            @Query("page") int page,
            @Query("per_page") int per_page);
}
