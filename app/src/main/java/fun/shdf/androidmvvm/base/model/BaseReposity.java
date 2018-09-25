package fun.shdf.androidmvvm.base.model;

import fun.shdf.androidmvvm.api.ApiService;
import fun.shdf.androidmvvm.http.RetrofitUtil;

/**
 * code-time: 2018/9/20 16:09
 * code-author: by shdf
 * coder-wechat: zcm656025633
 * exp: 从服务器i获取数据
 **/
public abstract class BaseReposity {

    protected ApiService apiService;

     public BaseReposity(){
        apiService = RetrofitUtil.newInstance().getApiService();
    }

}
