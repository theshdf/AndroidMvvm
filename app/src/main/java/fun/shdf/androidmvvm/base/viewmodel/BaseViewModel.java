package fun.shdf.androidmvvm.base.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import fun.shdf.androidmvvm.base.model.BaseReposity;
import fun.shdf.androidmvvm.utils.GenericUtil;


/**
 * code-time: 2018/9/20
 * code-author: by shdf
 * coder-wechat: zcm656025633
 * exp:通用的viewmodel,由于view和viewmodel分离
 * viewmodel中无法使用context，如果viewmodel要使用context，继承androidVM即可
 **/
public class BaseViewModel<T extends BaseReposity> extends AndroidViewModel {

    protected T mReposity;

    public MutableLiveData<String> state;

    /**
     * 可以引入application，并且完成初始化工作
     *
     */
    public BaseViewModel(@NonNull Application application) {
        super(application);
        mReposity = GenericUtil.getNewInstance(this,0);
        if(null == state)
            state = new MutableLiveData<>();
    }

}
