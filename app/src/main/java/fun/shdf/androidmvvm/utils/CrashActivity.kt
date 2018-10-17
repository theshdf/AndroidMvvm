package `fun`.shdf.androidmvvm.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import java.text.SimpleDateFormat
import `fun`.shdf.androidmvvm.R
import kotlinx.android.synthetic.main.activity_crash.*

/**
 * author : shdf
 * date : 2018/4/20
 * description :
 */
class CrashActivity : Activity() {
    @SuppressLint("SimpleDateFormat")
    private val df = SimpleDateFormat("yyyy-MM-dd HH:mm")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crash)
        val model = intent.getParcelableExtra<CrashModel>(CRASH_MODEL) ?: return
        model.ex.printStackTrace()
        tv_packageName.text = model.className
        textMessage.text = model.exceptionMsg
        tv_className.text = model.fileName
        tv_methodName.text = model.methodName
        tv_lineNumber.text = model.lineNumber.toString()
        tv_exceptionType.text = model.exceptionType
        tv_fullException.text = model.fullException
        tv_time.text = df.format(model.time)
        tv_model.text = model.device.model
        tv_brand.text = model.device.brand
        tv_version.text = model.device.version
    }

    companion object {
        val CRASH_MODEL = "crash_model"
    }
}
