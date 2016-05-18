# Android-Http-Example
Android相关http框架的使用和封装。包括使用HttpUrlConnection, android-async-http, volley, okHttp, litehttp等网络请求框架.

## 1. 功能介绍

1. 对网络层进行封装,可以随时切换底层网络请求库.功能:post请求,get请求,cookie,上传文件,下载文件
1. 封装android-async-http网络请求
1. 封装基于HttpUrlConnection的简单网络请求
1. 封装volley网络请求
1. 封装litehttp网络请求
1. **todo..持续更新中...**


## 2. 网络封装层

1. MyHttp **封装了一层App统一调用的Http接口层,在里面可以方便切换网络请求库**
1. MyHttpJsonResponseHandler **普通请求回调抽象类 json格式数据**
1. MyHttpFileResponseHandler **文件下载回调抽象类**
1. todo... 如果需要其他返回数据可以定义其他的responseHander接口


## 3. 第三方网络请求库

### android-async-http

官网:http://loopj.com/android-async-http/

文档:https://loopj.com/android-async-http/doc/

代码介绍:
- LibAsyncHttp **对android-async-http的请求封装**
- MyAsyncHttp **调用LibAsyncHttp请求的胶水层.处理MyHttp和LibAsyncHttp之间的通信**

### volley

Git地址：https://android.googlesource.com/platform/frameworks/volley

官网地址：https://developer.android.com/training/volley/index.html?hl=zh-cn

引入volley注意点:
- git clone后选用android-6.0.1_25 tag的版本,用master最新的出错
- 将下载后的volley拷贝到项目中删除.git目录以import module的方式导入项目
- 修改gradle版本,添加dependency即引入volley成功


