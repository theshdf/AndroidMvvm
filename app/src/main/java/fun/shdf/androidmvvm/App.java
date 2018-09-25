package fun.shdf.java_aac;

import android.app.Application;
import android.content.Context;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import fun.shdf.java_aac.utils.SpiderMan;

/**
 * code-time: 2018/9/20
 * code-author: by shdf
 * coder-wechat: zcm656025633
 * exp:
 **/
public class App extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        Logger.addLogAdapter(new AndroidLogAdapter());
        //todo 显示异常信息页面
        SpiderMan.getInstance()
                .init(this)
                .setEnable(true)
                .showCrashMessage(true)
                .setOnCrashListener((t, ex, model) -> {
                    //todo  log 异常信息
                });
    }

    public static Context getContext(){
        return context;
    }
}
