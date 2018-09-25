package fun.shdf.java_aac.ui;

import fun.shdf.java_aac.base.BaseResponse;
import fun.shdf.java_aac.base.CallBack;
import fun.shdf.java_aac.base.model.BaseReposity;
import fun.shdf.java_aac.http.BaseObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * code-time: 2018/9/21 10:48
 * code-author: by shdf
 * coder-wechat: zcm656025633
 * exp:
 **/
public class HomeReposity extends BaseReposity {


    /**
     * 登录
     * @param username
     * @param password
     */
    public void getHomeData(String username, String password, final CallBack callBack){

        apiService.login(username,password)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new BaseObserver<String>() {
            @Override
            public void onSuccessData(BaseResponse<String> data) {
                callBack.onSuccessData(data);
            }

            @Override
            public void onSuccessMsg(String msg) {
                callBack.onSuccessMsg(msg);
            }

            @Override
            public void onFailure(String msg) {
                callBack.onFailer(msg);
            }
        });
    }

}
