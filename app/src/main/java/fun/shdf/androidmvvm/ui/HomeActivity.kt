package `fun`.shdf.androidmvvm.ui

import android.os.Bundle
import android.text.TextUtils
import `fun`.shdf.androidmvvm.R
import `fun`.shdf.androidmvvm.base.view.ProBaseActivity
import android.arch.lifecycle.Observer
import android.widget.Toast
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import kotlinx.android.synthetic.main.activity_main.*

/**
 * code-time: 2018/9/21 10:47
 * code-author: by shdf
 * coder-wechat: zcm656025633
 * exp: 使用时 先绑定view和viewmodel 然后获取数据
 */
class HomeActivity : ProBaseActivity<HomeViewModel>() {

    override fun initIntent() {}

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        bottomNavationBar
                .addItem(BottomNavigationItem(R.mipmap.ic_launcher,"首页"))
                .setActiveColor(R.color.colorAccent)
                .setInActiveColor(R.color.colorPrimary)
                .addItem(BottomNavigationItem(R.mipmap.ic_launcher,"聊天"))
                .addItem(BottomNavigationItem(R.mipmap.ic_launcher,"我的"))
                .initialise()
    }

    override fun initListener() {

    }

    override fun initData(binder: Bundle) {
        //todo 获取数据
        mViewModel.bind().observe(this, Observer{
                Toast.makeText(this@HomeActivity, it!!, Toast.LENGTH_SHORT).show()
        }
        )
        mViewModel.getHomeData("shdf", "shdf")
    }
}
