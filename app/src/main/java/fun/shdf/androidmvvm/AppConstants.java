package fun.shdf.java_aac;

/**
 * Created by shdf on 2018/9/21.
 * wechat：zcm656025633
 * exp：
 **/
public  class AppConstants {

    public static final long TIME_OUT = 30;

    //todo 定义网络状态码
    public static final String UNKNOW_HOST = "UNKNOW_HOST";

    public static final String HTTP_ECEPTION = "HTTP_ECEPTION";

    public static final String SOCKET_TIMEOUT = "SOCKET_TIMEOUT";

    public static final String JSON_PARSE = "JSON_PARSE";

    public static final String CONNECT_EXCEPTION = "CONNECT_EXCEPTION";

    /**
     *枚举类型列出网络状态
     */
    public enum NETTYPE{
        BEFORE_LOAD,
        LOADING,
        SUCCESS,
        FAIL,
        ERROR
    }
}
