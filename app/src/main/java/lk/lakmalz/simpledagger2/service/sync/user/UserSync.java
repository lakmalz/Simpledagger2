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
    private UserSyncCallback mCallback;

    public UserSync(Context context, RestAPI restAPI) {
        mContext = context;
        mRestAPI = restAPI;
        mCallback = (UserSyncCallback) context;
    }

    public void getUserList(int page, int perPage) {

        if (!NetworkAccess.create(mContext).isConnected()) {
            mCallback.onFailureGetUserList(null);
            return;
        }

        mRestAPI.getUsers(page, perPage).enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    mCallback.onSuccessGetUserList(response.body());
                } else {
                    mCallback.onFailureGetUserList("Request Failed. Please try again.");
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                mCallback.onFailureGetUserList("Request Failed. Please try again.");
            }
        });
    }

}
