package lk.lakmalz.simpledagger2.views.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import lk.lakmalz.simpledagger2.App;
import lk.lakmalz.simpledagger2.R;
import lk.lakmalz.simpledagger2.model.User;
import lk.lakmalz.simpledagger2.service.RestAPI;
import lk.lakmalz.simpledagger2.service.sync.user.UserSync;
import lk.lakmalz.simpledagger2.service.sync.user.UserSyncCallback;
import lk.lakmalz.simpledagger2.views.adapters.UsersAdapter;

public class MainActivity extends BaseActivity implements UserSyncCallback {

    @BindView(R.id.rv_user_list)
    RecyclerView rvUserList;

    @Inject
    RestAPI mRestAPI;

    private UserSync mUserSync;
    private LinearLayoutManager mLinearLayoutManager;
    private UsersAdapter mUserAdapter;

    private int currentPage = 1;
    private int perPage = 15;
    private boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ((App) getApplication()).getNetComponent().inject(this);
        init();

    }

    private void init() {
        isLoading = false;
        initUserListView();
        mUserSync = new UserSync(this, mRestAPI);
        getUserList(currentPage);
    }

    private void initUserListView() {
        mLinearLayoutManager = new LinearLayoutManager(this);
        mUserAdapter = new UsersAdapter(this);

        rvUserList.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        rvUserList.hasFixedSize();
        rvUserList.setLayoutManager(mLinearLayoutManager);
        rvUserList.setAdapter(mUserAdapter);
        rvUserList.addOnScrollListener(mOnScrollListener);
    }

    private void getUserList(int _currentPage) {
        showProgress();
        isLoading = true;
        mUserSync.getUserList(_currentPage, perPage);
    }

    @Override
    public void onSuccessGetUserList(List<User> userList) {
        for (User user : userList) {
            mUserAdapter.addItem(user);
        }
        dismissProgress();
        isLoading = false;
        currentPage++;
    }

    @Override
    public void onFailureGetUserList(String message) {
        dismissProgress();
        if (message != null) showMessage(message);
        isLoading = false;
    }

    protected RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            int visibleItemCount = mLinearLayoutManager.getChildCount();
            int totalItemCount = mLinearLayoutManager.getItemCount();
            int firstVisibleItemPosition = mLinearLayoutManager.findFirstVisibleItemPosition();

            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0) {

                if (!isLoading) {
                    getUserList(currentPage + 1);

                }
            }

        }
    };

}
