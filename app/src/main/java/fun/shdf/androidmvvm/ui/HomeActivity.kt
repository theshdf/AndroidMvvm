package `fun`.shdf.androidmvvm.ui

import android.os.Bundle
import `fun`.shdf.androidmvvm.R
import `fun`.shdf.androidmvvm.base.view.ProBaseActivity
import android.arch.lifecycle.Observer
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import q.rorbin.badgeview.Badge
import q.rorbin.badgeview.QBadgeView

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
        //todo 底部按钮切换
        bottomNaviga.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    Toast.makeText(this@HomeActivity, "home", Toast.LENGTH_SHORT).show()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.tongji -> {
                    Toast.makeText(this@HomeActivity, "tongji", Toast.LENGTH_SHORT).show()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.mine -> {
                    Toast.makeText(this@HomeActivity, "mine", Toast.LENGTH_SHORT).show()
                    return@setOnNavigationItemSelectedListener true
                }
            }
            return@setOnNavigationItemSelectedListener false
        }
        //todo 底部按钮添加标记
        QBadgeView(this).bindTarget(bottomNaviga.findViewById(R.id.home))
                .setBadgePadding(10.0f,true)
                .setGravityOffset(30f,0f,true)
                .badgeNumber = 10
    }

    override fun initListener() {

    }

    override fun initData(binder: Bundle?) {
        //todo 获取数据
        mViewModel.bind().observe(this, Observer{
                Toast.makeText(this@HomeActivity, "a", Toast.LENGTH_SHORT).show()
        }
        )
        mViewModel.getHomeData("shdf", "shdf")
    }
}
