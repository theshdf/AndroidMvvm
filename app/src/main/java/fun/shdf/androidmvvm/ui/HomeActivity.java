package fun.shdf.androidmvvm.ui;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import fun.shdf.androidmvvm.R;
import fun.shdf.androidmvvm.base.view.ProBaseActivity;

/**
 * code-time: 2018/9/21 10:47
 * code-author: by shdf
 * coder-wechat: zcm656025633
 * exp: 使用时 先绑定view和viewmodel 然后获取数据
 **/
public class HomeActivity extends ProBaseActivity<HomeViewModel> {

    @Override
    protected void initIntent() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData(Bundle binder) {
        //todo 获取数据
        mViewModel.bind().observe(this, str ->{
                    if(!TextUtils.isEmpty(str))
                        Toast.makeText(HomeActivity.this,str,Toast.LENGTH_SHORT).show();
        }
        );
        mViewModel.getHomeData("shdf","shdf");
        getVer();
    }

    private void getVer() {
        try {
            PackageInfo packageInfo = getPackageManager()
                    .getPackageInfo(this.getPackageName(),0);
            Log.d("Tag",packageInfo.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}
