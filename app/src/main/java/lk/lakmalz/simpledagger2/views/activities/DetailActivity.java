package lk.lakmalz.simpledagger2.views.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import lk.lakmalz.simpledagger2.App;
import lk.lakmalz.simpledagger2.R;
import lk.lakmalz.simpledagger2.model.User;
import lk.lakmalz.simpledagger2.service.RestAPI;
import lk.lakmalz.simpledagger2.service.sync.user.UserSync;
import lk.lakmalz.simpledagger2.service.sync.user.UserSyncCallback;
import lk.lakmalz.simpledagger2.utils.ConstantExtras;

/**
 * Created by A Lakmal Weerasekara (Lakmalz) on 22/10/17.
 * alrweerasekara@gmail.com
 */

public class DetailActivity extends BaseActivity implements UserSyncCallback.GetUserDetailsCallBack {

    @BindView(R.id.img_user_avatar)
    ImageView mImgVwUserAvatar;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;

    @Inject
    RestAPI mRestAPI;

    private String userName;
    private UserSync mUserSync;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        ((App) getApplication()).getNetComponent().inject(this);

        init();
    }

    public static Intent getInstance(Context context, String userName) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(ConstantExtras.EXTRAS_USER_NAME, userName);
        return intent;
    }

    private void init() {
        userName = getIntent().getStringExtra(ConstantExtras.EXTRAS_USER_NAME);
        mUserSync = new UserSync(this, mRestAPI);
        getUserDetails();
    }

    private void getUserDetails() {
        showProgress();
        mUserSync.getUserDetails(this, userName);
    }

    @Override
    public void onSuccessGetUserDetails(User user) {
        tvUserName.setText(user.getLogin());
        Picasso.with(this)
                .load(user.getAvatar_url())
                .into(mImgVwUserAvatar);
        dismissProgress();
    }

    @Override
    public void onFailureGetUserDetails(String message) {
        if (message != null) showMessage(message);
        dismissProgress();
    }
}
