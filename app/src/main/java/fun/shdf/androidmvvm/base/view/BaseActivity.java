package fun.shdf.androidmvvm.base.view;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.Toast;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //todo 完成对用户界面的初始化操作
        setFullScreen();
        initWindow();
        setOrientation();
        initOriention();
        setStatus();
        initStatus();
        setHomeAvtivity();//当是首页时，点击两次关闭
        setContentView(getLayoutId());
        initIntent();
        initView();
        initListener();
        initOther();
        initData(savedInstanceState);
    }

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
    protected void setOrientation(){
        orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
    }

    /**
     * 设置全屏
     */
    protected void setFullScreen(){
        IF_FULL_SCREEN = false;
    }

    /**
     * 设置沉浸式状态栏
     */
    protected void setStatus(){
        IF_STATUS = false;
    }

    protected void setHomeAvtivity(){
        IS_HOME_ACTIVITY = false;
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
    }
}
