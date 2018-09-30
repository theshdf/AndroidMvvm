package fun.shdf.androidmvvm.http;

import fun.shdf.androidmvvm.api.ApiConstant;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * code-time: 2018/9/29 16:48
 * code-author: by shdf
 * coder-wechat: zcm656025633
 * exp: 建造者模式打造可扩展的retrofit
 **/
public class RetrofitExtUtil{

    private static  Retrofit mBuildRetrofit;

    private String baseUrl;

    private CallAdapter.Factory callAdapter;

    private Converter.Factory factory;

    OkHttpClient.Builder builder;

    public RetrofitExtUtil(Builder builder){
        this.builder = new OkHttpClient.Builder();
        this.baseUrl = builder.baseUrl;
        this.callAdapter = builder.callAdapter;
        this.factory = builder.factory;
        mBuildRetrofit = new Retrofit
                .Builder()
                .baseUrl(baseUrl)
                .client(fun.shdf.androidmvvm.http.OkHttpClient.getInstance().getClient())
                .addCallAdapterFactory(callAdapter)
                .addConverterFactory(factory)
                .build();
    }


    /**
     * 用来扩展定义retrofit和apiservice
     */
    public static class Builder {

        private String baseUrl;

        private CallAdapter.Factory callAdapter;

        private Converter.Factory factory;

        /**
         * 初始化参数
         */
        public Builder() {
            baseUrl = ApiConstant.URL;
            callAdapter = RxJava2CallAdapterFactory.create();
            factory = GsonConverterFactory.create();
        }

        public Builder addUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder addCallAdapter(CallAdapter.Factory callAdapter) {
            this.callAdapter = callAdapter;
            return this;
        }

        public Builder addConver(Converter.Factory factory) {
            this.factory = factory;
            return this;
        }

        /**
         * 构建对象
         *
         * @return
         */
        public RetrofitExtUtil build() {
            return new RetrofitExtUtil(this);
        }

        /**
         * 创建retrofit
         *
         * @param apiservice
         * @param <T>
         * @return
         */
    }
    public <T> T createApiService(Class<T> apiservice) {
        return mBuildRetrofit.create(apiservice);
    }
}
