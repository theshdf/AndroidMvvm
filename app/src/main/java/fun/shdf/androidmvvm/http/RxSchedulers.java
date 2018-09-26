package fun.shdf.androidmvvm.http;

import android.widget.Toast;
import fun.shdf.androidmvvm.App;
import fun.shdf.androidmvvm.utils.NetUtil;
import fun.shdf.androidmvvm.utils.ViewUtil;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by shdf on 2018/9/26
 * wechat：zcm656025633
 * exp：包装上游observer 也可以实现ObservableTransformer接口
 **/
public class RxSchedulers {

    private static CompositeDisposable cd;

    public static <T> ObservableTransformer<T, T> compose(CompositeDisposable compositeDisposable) {
        cd = compositeDisposable;
        return observable -> observable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> {
                    if (!NetUtil.isNetworkAvailable()) {
                        Toast.makeText(App.getContext(), "请检查网络", Toast.LENGTH_SHORT).show();
                        disposable.dispose();
                    } else {
                        addDisposable(disposable);
                        ViewUtil.getDialogInstance(App.getActivity()).show();
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnTerminate(() -> {
                    ViewUtil.getDialogInstance(App.getActivity()).hide();
                });
    }

    /**
     * 添加订阅关系，统一管理
     * @param disposable
     */
    public static void addDisposable(Disposable disposable) {
        if (null != cd) {
            cd.add(disposable);
        }
    }
}
