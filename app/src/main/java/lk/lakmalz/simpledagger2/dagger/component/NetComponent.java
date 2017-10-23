package lk.lakmalz.simpledagger2.dagger.component;

import javax.inject.Singleton;

import dagger.Component;
import lk.lakmalz.simpledagger2.dagger.module.ContextModule;
import lk.lakmalz.simpledagger2.views.activities.BaseActivity;
import lk.lakmalz.simpledagger2.views.activities.DetailActivity;
import lk.lakmalz.simpledagger2.views.activities.MainActivity;
import lk.lakmalz.simpledagger2.dagger.module.AppModule;
import lk.lakmalz.simpledagger2.dagger.module.NetModule;

/**
 * Created by A Lakmal Weerasekara (Lakmalz) on 21/10/17.
 * alrweerasekara@gmail.com
 */
@Singleton
@Component(modules = {AppModule.class, ContextModule.class, NetModule.class})
public interface NetComponent {
    void inject(BaseActivity activity);
}
