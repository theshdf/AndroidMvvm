package fun.shdf.androidmvvm.ui;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import fun.shdf.androidmvvm.base.CallBack;
import fun.shdf.androidmvvm.base.viewmodel.BaseViewModel;

/**
 * code-time: 2018/9/21 10:47
 * code-author: by shdf
 * coder-wechat: zcm656025633
 * exp:
 **/
public class HomeViewModel extends BaseViewModel<HomeReposity> {

    private MutableLiveData<String> homeData;

    /**
     * 可以引入application，并且完成初始化工作
     *
     * @param application
     */
    public HomeViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     *
     * @return
     */
    public LiveData<String> bind(){
        if(null == homeData)
            homeData = new MutableLiveData<>();

        return  homeData;
    }

    public void getHomeData(String username,String password){

        mReposity.getHomeData(username, password, new CallBack<String>() {

            @Override
            public void onSuccessData(String data) {
                homeData.postValue(data);
            }

            @Override
            public void onSuccessMsg(String msg) {
                state.postValue(msg);
            }

            @Override
            public void onFailer(String msg) {
                state.postValue(msg);
            }
        });
    }
}
