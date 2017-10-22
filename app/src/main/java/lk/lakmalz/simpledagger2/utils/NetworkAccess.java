package lk.lakmalz.simpledagger2.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.widget.Toast;

/**
 * Created by A Lakmal Weerasekara (Lakmalz) on 22/10/17.
 * alrweerasekara@gmail.com
 */

public class NetworkAccess {

    private static NetworkAccess mNetworkAccess;
    private Context mContext;
    private ConnectivityManager mConnectivityManager;

    public NetworkAccess(Context context) {
        mContext = context;
        init();
    }

    public static NetworkAccess create(Context context) {
        if (mNetworkAccess == null) {
            mNetworkAccess = new NetworkAccess(context);
        }
        return mNetworkAccess;
    }

    private void init() {
        mConnectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public boolean isConnected() {
        if (mConnectivityManager.getActiveNetworkInfo() != null && mConnectivityManager.getActiveNetworkInfo().isConnected()) {
            //Toast.makeText(mContext, "Connected", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(mContext, "No internet connection", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
