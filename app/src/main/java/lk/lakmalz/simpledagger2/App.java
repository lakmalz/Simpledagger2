package lk.lakmalz.simpledagger2;

import android.app.Application;

import lk.lakmalz.simpledagger2.dagger.component.DaggerNetComponent;
import lk.lakmalz.simpledagger2.dagger.component.NetComponent;
import lk.lakmalz.simpledagger2.dagger.module.AppModule;
import lk.lakmalz.simpledagger2.dagger.module.ContextModule;
import lk.lakmalz.simpledagger2.dagger.module.NetModule;
import lk.lakmalz.simpledagger2.utils.Constant;

/**
 * Created by A Lakmal Weerasekara (Lakmalz) on 21/10/17.
 * alrweerasekara@gmail.com
 */

public class App extends Application {

    private NetComponent mNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .contextModule(new ContextModule(this))
                .netModule(new NetModule(Constant.BASE_URL))
                .build();
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }
}
