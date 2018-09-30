package fun.shdf.androidmvvm.utils;

/**
 * code-time: 2018/9/30 10:55
 * code-author: by shdf
 * coder-wechat: zcm656025633
 * exp:主动检查对象是否为空
 **/
public class CheckObj {
    public static <T>  T checkNotNull(T obj,String msg){
        if(obj == null){
            throw new NullPointerException(msg);
        }
        return obj;
    }
}
