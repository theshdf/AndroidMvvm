package fun.shdf.androidmvvm.base.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import fun.shdf.androidmvvm.AppConstants;
import fun.shdf.androidmvvm.base.viewmodel.BaseViewModel;
import fun.shdf.androidmvvm.utils.GenericUtil;

/**
 * code-time: 2018/9/20
 * code-author: by shdf
 * coder-wechat: zcm656025633
 * exp:升级版的activity,适用于需要网络请求的activity,由于activity有的具有此功能，有的不具备，所以单独把需要网络请求的
 * 的功能放在接口实现接口，如果继承的话，父类中已经封装的逻辑不易改变
 **/
public abstract class ProBaseActivity<T extends BaseViewModel> extends BaseActivity implements NetInterface{

    protected T mViewModel;

    @Override
    protected void initOther() {
        initInstance();
    }

    public void initInstance(){
        try {
            mViewModel = ViewModelProviders.of(this).get(GenericUtil.getInstance(this,0));
            mViewModel.state.observe(this,mObserver);
        }
        catch (Exception e){
            Log.d("TAG",e.getMessage());
        }
    }

    /**
     * 显示返回信息
     */
    private Observer<String> mObserver = state -> {
           if(AppConstants.UNKNOW_HOST.equals(state)){

           }
           else if(AppConstants.CONNECT_EXCEPTION.equals(state)){

           }
           else if(AppConstants.HTTP_ECEPTION.equals(state)){

           }
           else if(AppConstants.JSON_PARSE.equals(state)){

           }
           else if(AppConstants.SOCKET_TIMEOUT.equals(state)){
           }
           else if(!TextUtils.isEmpty(state)){
               Toast.makeText(this,state,Toast.LENGTH_SHORT).show();
           }
           else if(TextUtils.isEmpty(state)){
               Toast.makeText(this,"不能为空啊",Toast.LENGTH_SHORT).show();
           }
    };

}
