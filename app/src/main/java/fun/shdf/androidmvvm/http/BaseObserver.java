package fun.shdf.androidmvvm.http;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import fun.shdf.androidmvvm.AppConstants;
import fun.shdf.androidmvvm.base.BaseResponse;
import fun.shdf.androidmvvm.utils.NetUtil;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * code-time: 2018/9/21 13:51
 * code-author: by shdf
 * coder-wechat: zcm656025633
 * exp: 网络处理有三种情况
 * 1. 请求服务器成功，拿到了返回数据，返回数据 返回code true.进行页面跳转
 * 2. 请求服务器成功，拿到返回数据， code false  进行提示
 * 3. 种种原因 未能正确拿到数据  网络问题 数据解析问题等
 * Observer使用BaseREsonse<T>是为了使用Baseresponse中的方法。BaseObserver不适用BaseResponse<T>
 *     是因为直接用T就可以了
 * </>
 **/
public abstract class BaseObserver<T> implements Observer<BaseResponse<T>> {

    @Override
    public void onSubscribe(Disposable d) {
        //todo 判断网络
        if(NetUtil.isNetworkAvailable()){
        }
        else{
            d.dispose();
            //todo 提示没有网络
        }
    }

    @Override
    public void onNext(BaseResponse<T> response) {
        if(response.isSuccess()){
            onSuccessData(response);
        }
        else{
             onSuccessMsg(response.getErrorMsg());
        }
    }

    /**
     * 进行网络异常处理
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        String message;
        if (e instanceof UnknownHostException) {
            message = AppConstants.UNKNOW_HOST;
        } else if (e instanceof HttpException) {
            message = AppConstants.HTTP_ECEPTION;
        } else if (e instanceof SocketTimeoutException) {
            message = AppConstants.SOCKET_TIMEOUT;
        } else if (e instanceof JsonParseException
                || e instanceof JSONException) {
            message = AppConstants.JSON_PARSE;
        } else if (e instanceof ConnectException) {
            message = AppConstants.CONNECT_EXCEPTION;
        }
        else
            message = e.getMessage();
        onFailure(message);
    }

    @Override
    public void onComplete() {
    }


    /**
     * 回调请求成功的接口 code == 1
     * @param data
     */
    public abstract void onSuccessData(BaseResponse<T> data);

    /**
     * 请求成功但是 code != 1
     * @param msg
     */
    public abstract void onSuccessMsg(String msg);

    /**
     * 没有正确获取数据时回调
     * @param msg
     */
    public abstract void onFailure(String msg);

}
