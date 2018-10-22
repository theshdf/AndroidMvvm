package fun.shdf.androidmvvm.base.view;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.hjq.bar.TitleBar;

import fun.shdf.androidmvvm.R;
import fun.shdf.androidmvvm.utils.ActivityControlUtil;
import fun.shdf.androidmvvm.utils.statusUtil.StatusBarCompat;

/**
 * code-time: 2018/9/20
 * code-author: by shdf
 * coder-wechat: zcm656025633
 * exp:根据生命周期封装activity通用的功能和逻辑代码
 **/
public abstract class BaseActivity extends AppCompatActivity {

    private int orientation;//默认竖屏

    private boolean IF_FULL_SCREEN;//默认非全屏

    private boolean IF_STATUS;//默认沉浸式

    private boolean IS_HOME_ACTIVITY;

    private long lastPointTime = 0;

    protected TitleBar titleBar;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //todo 完成对用户界面的初始化操作
        initActivity();//初始化activity界面的值
        setActivity();//修改activity界面的值
        initWindow();
        initOriention();
        initStatus();
        ActivityControlUtil.addActivity(this);
        setContentView(R.layout.baseactvity_layout);
        initIntent();
        initView();
        initListener();
        initOther();
        initData(savedInstanceState);

    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        View view = LayoutInflater.from(this).inflate(getLayoutId(),null);
        FrameLayout frameLayout = findViewById(R.id.content);
        frameLayout.addView(view);
        titleBar = findViewById(R.id.commonTitle);
    }

    public void initActivity(){
        orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        IF_FULL_SCREEN = false;
        IS_HOME_ACTIVITY = false;
    }

    /**
     * 对当前activity进行初始化
     * 1. setOrientation()初始化屏幕方向
     * 2. setFullScreen() 是否全屏显示
     * 3. setStatus()设置是否沉浸式，当前是默认情况
     * 4. setHomeAvtivity()：当前activity是否是首页
     */
    public abstract void setActivity();

    /**
     * 初始化窗口，是否全屏
     */
    protected void initWindow(){
        if(IF_FULL_SCREEN){
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
                    );
        }
    }

    /**
     * 初始化屏幕方向
     */
    protected void initOriention(){
        setRequestedOrientation(orientation);
    }

    /**
     * 初始化状态栏
     */
    protected  void initStatus(){
        if(IF_STATUS){
            StatusBarCompat.setStatusBarColor(this,getResources().getColor(R.color.colorAccent));
        }
    }

    protected abstract void initIntent();

    /**
     * 获取view视图id
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化视图界面
     */
    protected abstract void initView();

    /**
     * 初始化view事件
     */
    protected  abstract void initListener();

    /**
     * 在初始化数居前用来初始化
     */
    protected abstract void initOther();

    /**
     * 初始化view展示的数据
     */
    protected abstract void initData(Bundle bundle);

    /**
     * =============== set==============
     */

    /**
     * 设置屏幕方向
     */
    protected void setOrientation(int orientation){
        this.orientation = orientation;
    }

    /**
     * 设置全屏
     */
    protected void setFullScreen(boolean IF_FULL_SCREEN){
        this.IF_FULL_SCREEN = IF_FULL_SCREEN;
    }

    /**
     * 设置沉浸式状态栏
     */
    protected void setStatus(boolean status){
        IF_STATUS = status;
    }

    protected void setHomeAvtivity(boolean IS_HOME_ACTIVITYf){
        this.IS_HOME_ACTIVITY = IS_HOME_ACTIVITY;
    }

    /**
     * 首页点击两次退出
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(IS_HOME_ACTIVITY){
            if(keyCode == KeyEvent.KEYCODE_BACK&&event.getAction() == KeyEvent.ACTION_DOWN){
                if(System.currentTimeMillis()-lastPointTime>2000){
                    lastPointTime = System.currentTimeMillis();
                    Toast.makeText(this,"再按一次退出",Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityControlUtil.removeActivity(this);
    }
}
