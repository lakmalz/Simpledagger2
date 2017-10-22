package lk.lakmalz.simpledagger2.service.sync.user;

import android.content.Context;

import java.util.List;

import lk.lakmalz.simpledagger2.model.User;
import lk.lakmalz.simpledagger2.service.RestAPI;
import lk.lakmalz.simpledagger2.utils.NetworkAccess;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by A Lakmal Weerasekara (Lakmalz) on 21/10/17.
 * alrweerasekara@gmail.com
 */

public class UserSync {

    private static final String TAG = "PostSync";

    Context mContext;
    RestAPI mRestAPI;

    public UserSync(Context context, RestAPI restAPI) {
        mContext = context;
        mRestAPI = restAPI;
    }

    public void getUserList(final UserSyncCallback.GetUserListCallBack callBack, int page, int perPage) {

        if (!NetworkAccess.create(mContext).isConnected()) {
            callBack.onFailureGetUserList(null);
            return;
        }

        mRestAPI.getUsers(page, perPage).enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    callBack.onSuccessGetUserList(response.body());
                } else {
                    callBack.onFailureGetUserList("Request Failed. Please try again.");
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                callBack.onFailureGetUserList("Request Failed. Please try again.");
            }
        });
    }

    public void getUserDetails(final UserSyncCallback.GetUserDetailsCallBack callBack, String userName) {

        if (!NetworkAccess.create(mContext).isConnected()) {
            callBack.onFailureGetUserDetails(null);
            return;
        }

        mRestAPI.getUserDetails(userName).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    callBack.onSuccessGetUserDetails(response.body());
                } else {
                    callBack.onFailureGetUserDetails("Request Failed. Please try again.");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                callBack.onFailureGetUserDetails("Request Failed. Please try again.");
            }
        });
    }
}
