package fun.shdf.androidmvvm.utils;

import java.lang.reflect.ParameterizedType;

/**
 * code-time: 2018/9/21 9:09
 * code-author: by shdf
 * coder-wechat: zcm656025633
 * exp:用来获取泛型类
 **/
public class GenericUtil {

    public static  <T> T getInstance(Object object,int i){
        if(null != object) {
           return  (T) ((ParameterizedType) object.getClass()
                    .getGenericSuperclass())
                    .getActualTypeArguments()[i];
        }
        return null;
    }

    public static <T> T getNewInstance(Object object, int i) {
        if(object!=null){
            try {
                return ((Class<T>) ((ParameterizedType) (object.getClass()
                        .getGenericSuperclass())).getActualTypeArguments()[i])
                        .newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassCastException e) {
                e.printStackTrace();
            }

        }
        return null;

    }

}
