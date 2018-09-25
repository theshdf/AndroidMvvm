package fun.shdf.androidmvvm.base;


import fun.shdf.androidmvvm.api.ApiConstant;

/**
 * code-time: 2018/9/21 11:18
 * code-author: by shdf
 * coder-wechat: zcm656025633
 * exp:
 **/
public class BaseResponse<T> {

    private T data;

    private String errorMsg;

    private int errorCode;//todo 加入code == 时，数据返回的正常

    public  boolean isSuccess(){
        return errorCode == ApiConstant.SERVER_CODE;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
