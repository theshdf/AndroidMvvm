package fun.shdf.androidmvvm.http;

import com.orhanobut.logger.Logger;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by shdf on 2018/9/22.
 * wechat：zcm656025633
 * exp：request,response 请求和响应的拦截器
 **/
public class HttpLogger implements HttpLoggingInterceptor.Logger{

    private static String TAG = HttpLogger.class.getSimpleName();

    private StringBuilder mMessage = new StringBuilder();

    @Override
    public void log(String message) {
        if (message.startsWith("--> POST")) {
            mMessage.setLength(0);
        }
        if ((message.startsWith("{") && message.endsWith("}"))
                || (message.startsWith("[") && message.endsWith("]"))) {
            Logger.json(message);
        }
        mMessage.append(message.concat("\n"));
        if (message.startsWith("<-- END HTTP")) {
            Logger.e(TAG, mMessage.toString());
        }
    }
}
