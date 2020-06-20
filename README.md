# AndroidMvvm2020
#### 说明
> 基于MVVM，使用lifecycle+livedata+rxjava+retrofit+okhttp封装了通用的快速开发框架，并对Android中常见的内存泄漏做了处理。可以直接拿来快速开发

> 1. Gradle中的常量统一管理
> 2. 封住了通用的Activity+ViewModel+Resposity
> 3. 添加了通用的标题栏和沉浸式状态栏处理
> 4. 添加了屏幕适配
> 5. 添加了常用的首页
> 6. 通过继承AndroidViewModel,在View退出时会条用viewmodel中的onCleared()方法管理声明周期来处理内存泄漏

#### 使用

直接下载在此基础上开发即可

#### 使用框架
* Rxjava2
* Retrofit2
* Okhttp3
* Gson
* Glide
* Spideman
* logger
* [titlebar]("https://github.com/getActivity/TitleBar")
* [badgeview]("https://github.com/qstumn/BadgeView")

