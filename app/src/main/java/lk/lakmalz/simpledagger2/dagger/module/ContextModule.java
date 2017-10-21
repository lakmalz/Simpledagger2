package lk.lakmalz.simpledagger2.dagger.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import lk.lakmalz.simpledagger2.dagger.qualifier.ApplicationContext;

/**
 * Created by A Lakmal Weerasekara (Lakmalz) on 22/10/17.
 * alrweerasekara@gmail.com
 */
@Module
public class ContextModule {

    Context mContext;

    public ContextModule(Context context) {
        mContext = context.getApplicationContext();
    }

    @Provides
    @Singleton
    @ApplicationContext
    Context providesContext() {
        return mContext;
    }
}
