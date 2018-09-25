package fun.shdf.androidmvvm.api;


import fun.shdf.androidmvvm.base.BaseResponse;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * code-time: 2018/9/21 11:07
 * code-author: by shdf
 * coder-wechat: zcm656025633
 * exp:
 **/
public interface ApiService {

    @FormUrlEncoded
    @POST(ApiConstant.LOGIN)
    Observable<BaseResponse<String>> login(@Field("username") String username,
                                           @Field("password") String password);

}
