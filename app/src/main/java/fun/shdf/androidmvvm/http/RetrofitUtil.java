package fun.shdf.androidmvvm.http;

import android.annotation.SuppressLint;
import fun.shdf.androidmvvm.api.ApiConstant;
import fun.shdf.androidmvvm.api.ApiService;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zcm on 2018/9/22.
 * qq:656025633
 * Func：封装了一个常用的固定url和解析方式的retrofti和一个可以自己扩展的retrofit
 */
public class RetrofitUtil {

    private volatile ApiService apiService;

    private Retrofit retrofit;

    private volatile static RetrofitUtil mRetrofitUtil;

    @SuppressLint("CheckResult")
    private RetrofitUtil() {
        if (retrofit == null) {
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(ApiConstant.URL)
                    .client(OkHttpClient.getInstance().getClient())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }
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
        if (apiService == null)
            apiService = retrofit.create(ApiService.class);
        return apiService;
    }
}
