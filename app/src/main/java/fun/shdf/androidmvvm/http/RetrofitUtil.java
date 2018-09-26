package fun.shdf.androidmvvm.http;

import android.annotation.SuppressLint;

import java.util.concurrent.TimeUnit;

import fun.shdf.androidmvvm.AppConstants;
import fun.shdf.androidmvvm.BuildConfig;
import fun.shdf.androidmvvm.api.ApiConstant;
import fun.shdf.androidmvvm.api.ApiService;
import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zcm on 2018/9/22.
 * qq:656025633
 * Func：provider a Retrofit Object
 */
public class RetrofitUtil {

    private static OkHttpClient httpClient;

    private volatile ApiService apiService;

    private volatile static  RetrofitUtil  mRetrofitUtil;//防止指令重排序

    private BasicParamsInterceptor basicParamsInterceptor;

    @SuppressLint("CheckResult")
    private RetrofitUtil() {
        Observable.create(emitter -> {
            initOkhttp();
            emitter.onNext(httpClient);
        }).subscribe(client -> {
            if (apiService == null) {
                apiService = new Retrofit
                        .Builder()
                        .baseUrl(ApiConstant.URL)
                        .client((OkHttpClient) client)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                        .create(ApiService.class);
            }
        });
    }

    /**
     * 单例模式
     *
     * @return
     */
    public static RetrofitUtil newInstance() {
        if (mRetrofitUtil == null) {
            synchronized (RetrofitUtil.class) {
                if (mRetrofitUtil == null) {
                    mRetrofitUtil = new RetrofitUtil();
                }
            }
        }
        return mRetrofitUtil;
    }

    /**
     * 获取apiservice
     *
     * @return
     */
    public ApiService getApiService() {
        return apiService;
    }

    public void initOkhttp() {
        basicParamsInterceptor = new BasicParamsInterceptor.Builder()
                .addParam("from", "android") //添加公共参数到 post 请求体
                .addQueryParam("version", "1")  // 添加公共版本号，加在 URL 后面
                .addHeaderLine("X-Ping: Pong")  // 示例： 添加公共.addParamsMap(map) // 可以添加 Map 格式的参数
                .build();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(basicParamsInterceptor).
                connectTimeout(AppConstants.TIME_OUT, TimeUnit.SECONDS).
                readTimeout(AppConstants.TIME_OUT, TimeUnit.SECONDS).
                writeTimeout(AppConstants.TIME_OUT, TimeUnit.SECONDS);
        if(BuildConfig.DEBUG){
            builder.addInterceptor(
                    new HttpLoggingInterceptor(
                    new HttpLogger()).setLevel(HttpLoggingInterceptor.Level.BODY));
        }
        httpClient = builder.build();
    }
}
