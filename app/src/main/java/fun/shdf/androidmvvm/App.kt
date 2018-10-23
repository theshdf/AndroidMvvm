package `fun`.shdf.androidmvvm

import `fun`.shdf.androidmvvm.base.model.BaseReposity
import `fun`.shdf.androidmvvm.ui.HomeReposity
import `fun`.shdf.androidmvvm.ui.HomeViewModel
import android.app.Application
import android.content.Context
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import `fun`.shdf.androidmvvm.utils.ActivityControlUtil
import `fun`.shdf.androidmvvm.utils.SpiderMan
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.module

/**
 * code-time: 2018/9/20
 * code-author: by shdf
 * coder-wechat: zcm656025633
 * exp:
 */
class App : Application() {

    private val appModule = module {
        //todo 首页注入
        single<BaseReposity> { HomeReposity()  }
        factory{HomeViewModel(get())}
    }
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        Logger.addLogAdapter(AndroidLogAdapter())
        //todo 显示异常信息页面
        SpiderMan.getInstance()
                .init(this)
                .setEnable(true)
                .showCrashMessage(true)
                .setOnCrashListener { t, ex, model ->
                    //todo  log 异常信息
                }
        startKoin(this, listOf(appModule))
    }

    companion object {

        var context: Context? = null
            private set

        val activity: Context?
            get() = ActivityControlUtil.getLastActivity()
    }
}
