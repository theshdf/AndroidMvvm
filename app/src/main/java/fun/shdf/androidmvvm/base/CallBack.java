package fun.shdf.androidmvvm.base;

/**
 * code-time: 2018/9/21 14:32
 * code-author: by shdf
 * coder-wechat: zcm656025633
 * exp:用于viewmodel和model之间的回调
 **/
public interface CallBack<T> {

    /**
     *
     * @param data
     */
    void onSuccessData(T data);

    /**
     *
     * @param msg
     */
    void onSuccessMsg(String msg);

    /**
     *
     * @param msg
     */
    void onFailer(String msg);

}
