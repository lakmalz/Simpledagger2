package lk.lakmalz.simpledagger2.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lk.lakmalz.simpledagger2.R;
import lk.lakmalz.simpledagger2.model.User;

/**
 * Created by A Lakmal Weerasekara (Lakmalz) on 22/10/17.
 * alrweerasekara@gmail.com
 */

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserHolder> {

    private Context mContext;
    private List<User> mDataSet;
    private UsersAdapterListener mListener;

    public UsersAdapter(Context context) {
        mContext = context;
        mListener = (UsersAdapterListener) context;
        mDataSet = new ArrayList<>();
    }

    public void addAll(final List<User> dataSet) {
        if (dataSet == null) return;
        if (dataSet.size() == 0) return;

        mDataSet.clear();
        notifyDataSetChanged();

        mDataSet.addAll(dataSet);
        notifyDataSetChanged();
    }

    public void addItem(User item) {
        mDataSet.add(item);
        notifyDataSetChanged();
    }

    public void clear() {
        if (mDataSet != null) {
            mDataSet.clear();
            notifyDataSetChanged();
        }
    }

    @Override
    public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_list, parent, false);

        return new UserHolder(rowItem);
    }

    @Override
    public void onBindViewHolder(UserHolder holder, int position) {
        User user = mDataSet.get(position);
        holder.tvUserName.setText(user.getLogin());
    }

    @Override
    public int getItemCount() {
        return mDataSet == null ? 0 : mDataSet.size();
    }

    public class UserHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.tv_user_name)
        TextView tvUserName;

        public UserHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mListener.onClickUserItem(mDataSet.get(getAdapterPosition()).getLogin());
        }
    }

}
