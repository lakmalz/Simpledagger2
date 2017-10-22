package lk.lakmalz.simpledagger2.service.sync.user;

import java.util.List;

import lk.lakmalz.simpledagger2.model.User;

/**
 * Created by A Lakmal Weerasekara (Lakmalz) on 22/10/17.
 * alrweerasekara@gmail.com
 */

public interface UserSyncCallback {

    interface GetUserListCallBack {
        void onSuccessGetUserList(List<User> userList);

        void onFailureGetUserList(String message);
    }

    interface GetUserDetailsCallBack {
        void onSuccessGetUserDetails(User user);

        void onFailureGetUserDetails(String message);

    }
}
