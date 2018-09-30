package fun.shdf.androidmvvm.http;

import java.util.concurrent.TimeUnit;

import fun.shdf.androidmvvm.AppConstants;
import fun.shdf.androidmvvm.BuildConfig;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * code-time: 2018/9/30 9:23
 * code-author: by shdf
 * coder-wechat: zcm656025633
 * exp:打造一个通用配置的okhttp
 **/
public class OkHttpClient {

    private okhttp3.OkHttpClient.Builder builder;

    private BasicParamsInterceptor basicParamsInterceptor;

    private  okhttp3.OkHttpClient httpClient;

    private OkHttpClient() {
        builder = new okhttp3.OkHttpClient.Builder();
        basicParamsInterceptor = new BasicParamsInterceptor.Builder()
                .addParam("from", "android") //添加公共参数到 post 请求体
                .addQueryParam("version", "1")  // 添加公共版本号，加在 URL 后面
                .addHeaderLine("X-Ping: Pong")  // 示例： 添加公共.addParamsMap(map) // 可以添加 Map 格式的参数
                .build();

        builder.addInterceptor(basicParamsInterceptor).
                connectTimeout(AppConstants.TIME_OUT, TimeUnit.SECONDS).
                readTimeout(AppConstants.TIME_OUT, TimeUnit.SECONDS).
                writeTimeout(AppConstants.TIME_OUT, TimeUnit.SECONDS);
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(
                    new HttpLoggingInterceptor(
                            new HttpLogger()).setLevel(HttpLoggingInterceptor.Level.BODY));
        }
        httpClient = builder.build();
    }

    public static OkHttpClient getInstance() {

        return BuildClient.client;
    }

    public okhttp3.OkHttpClient getClient(){
        return httpClient;
    }

    static class BuildClient {
        public static OkHttpClient client = new OkHttpClient();
    }

}
