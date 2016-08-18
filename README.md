# Android-Http-Example
Android网络请求的统一封装和调用.集成了android-async-http, volley, okhttp3等网络请求框架.可直接切换底层库.

## 1 简介

### 架构分层

总共分为三层:
1. 网络请求调用层. app统一调用该层接口和这层提供的回调,该层可以切换不同的网络请求库.
1. 网络请求库封装层. 封装了开源的Android网络请求库请求对外提供的方法.
1. 胶水层.负责调用层和封装层的连接.调用网络封装层的接口并将返回responseHandler转为调用层自定义的responseHandler.

### 功能
1. post get请求
1. cookie功能
1. 上传文件
1. 下载文件
1. 取消请求(页面销毁时可调用,防止页面销毁异步请求未取消而导致的crash)

### 集成的第三方网络请求库

#### android-async-http

官网:http://loopj.com/android-async-http/

文档:https://loopj.com/android-async-http/doc/

示例:https://github.com/loopj/android-async-http/tree/master/sample/src/main/java/com/loopj/android/http/sample

注意点:

1. 取消请求接口cancelAllRequest,cancelRequest,cancelRequestByTag都必须在post,get时加上参数Context,否则cancel不起作用

### volley

Git地址：https://android.googlesource.com/platform/frameworks/volley

官网地址：https://developer.android.com/training/volley/index.html?hl=zh-cn

郭霖的volley解析：http://blog.csdn.net/guolin_blog/article/details/17482095

注意点:

1. git clone后选用android-6.0.1_25 tag的版本,用master最新的引入出错
1. 将下载后的volley删除.git目录和src下test目录,然后拷贝到项目目录中
1. 修改项目setting.gradle添加":volley",并修改volley的gradle版本变为项目一样的版本,最好项目app添加dependency即引入volley成功
1. volley仅实现post和get请求,upload和download未实现.(volley不适合大数据下载文件下载等，因为volley会在parse过程中将数据缓存在内存中)
1. volley cacelRequest无onCancel事件触发
1. volley只能获取第一个cookie,如果需要多个cookie参考此[文章](http://www.w2bc.com/article/31961)

### okhttp3

官方地址: http://square.github.io/okhttp/

注意点:

## 2 项目结构

### 统一调用层
1. MyHttp.java     (**调用入口**)
1. MyHttpJsonResponseHandler.java      (**请求回调json数据**)
1. MyHttpFileResponseHandler.java      (**文件下载回调**)

### android-async-http
1. LibAsyncHttp.java   (**封装了android-async-http对外提供的接口**)
1. MyAsyncHttp.java    (**android-async-http和myhttp之间连接层**)

### volley
1. LibVolley.java  (**封装了volley的对外post get接口**)
1. LibVolleyResponseModel.java  (**volley返回数据封住**)
1. LibVolleyJSONObjectRequest.java     (**volley自定义的request**)
1. MyVolley.java     (**volley和myhttp之间连接层**)

### okhttp
1. LibOkHttp.java   (**封装了okhttp的对外接口**)
1. MyOkHttp.java    (**okhttp和myhttp之间连接层**)

## 3 调用示例

```java
        //post请求
        private void doPostHttp() {
            //文本参数
            Map<String, String> params = new HashMap<String, String>();
            params.put("uid", "111");

            //post
            MyHttp.doPost(this, "http://192.168.3.1/test_post.php", params, new MyHttpJsonResponseHandler() {
                @Override
                public void onSuccess(int statusCode, JSONObject response) {
                    Log.i("tsy", "onSuccess status code=" + statusCode + " response=" + response);
                }

                @Override
                public void onFailure(int statusCode, String error_msg) {
                    Log.i("tsy", "onFailure status code=" + statusCode + " error_msg=" + error_msg);
                }

                @Override
                public void onCancel() {
                    Log.i("myhttp", "request on cancel");
                }
            });
        }

        //get请求
        private void doGetHttp() {
            //文本参数
            Map<String, String> params = new HashMap<String, String>();
            params.put("uid", "111");

            //get
            MyHttp.doGet(this, "http://192.168.3.1/test_post.php", params, new MyHttpJsonResponseHandler() {
                @Override
                public void onSuccess(int statusCode, JSONObject response) {
                    Log.i("tsy", "onSuccess status code=" + statusCode + " response=" + response);
                }

                @Override
                public void onFailure(int statusCode, String error_msg) {
                    Log.i("tsy", "onFailure status code=" + statusCode + " error_msg=" + error_msg);
                }

                @Override
                public void onCancel() {
                    Log.i("myhttp", "request on cancel");
                }
            });
        }

        //上传文件
        private void doUpload() {
            //文件参数
            File file = new File(Environment.getExternalStorageDirectory() + "/girls/head/output_tmp2.jpg");
            Map<String, File> files = new HashMap<String, File>();
            files.put("avatar", file);

            //upload
            MyHttp.doUpload(this, "http://192.168.3.1/test_post.php", files, new MyHttpFileResponseHandler() {
                @Override
                public void onSuccess(int statusCode) {
                    Log.i("tsy", "onSuccess status code=" + statusCode);
                }

                @Override
                public void onFailure(int statusCode, String error_msg) {
                    Log.i("tsy", "onFailure status code=" + statusCode + " error_msg=" + error_msg);
                }

                @Override
                public void onProgress(long bytesWritten, long totalSize) {
                    Log.i("tsy", String.format("Progress %d from %d" +
                     " (%2.0f%%)", bytesWritten, totalSize, (totalSize > 0) ? (bytesWritten * 1.0 / totalSize) * 100 : -1));
                }

                @Override
                public void onCancel() {
                    Log.i("tsy", "request on cancel");
                }
            });
        }

        //下载文件
        private void doDownload() {
            File file = new File(Environment.getExternalStorageDirectory() + "/girls/head/output_tmp2.jpg");    //下载后存储的file位置
            MyHttp.doDownload(this, "http://192.168.3.1/head.jpg", file, new MyHttpFileResponseHandler() {
                @Override
                public void onSuccess(int statusCode) {
                    Log.i("tsy", "onSuccess status code=" + statusCode);
                }

                @Override
                public void onFailure(int statusCode, String error_msg) {
                    Log.i("tsy", "onFailure status code=" + statusCode);
                }

                @Override
                public void onProgress(long bytesWritten, long totalSize) {
                    Log.i("tsy", String.format("Progress %d from %d (%2.0f%%)", bytesWritten, totalSize, (totalSize > 0) ? (bytesWritten * 1.0 / totalSize) * 100 : -1));
                }

                @Override
                public void onCancel() {
                    Log.i("tsy", "request on cancel");
                }
            });
        }

        //页面销毁关闭请求 防止crash 建议放到BaseActivity BaseFragrament里面
        @Override
        protected void onDestroy() {
            super.onDestroy();

            MyHttp.cancelRequest(this);     //页面退出 关闭所有请求
        }
```

### 欢迎关注我的公众号

![我的公众号](https://github.com/tsy12321/PayAndroid/blob/master/wxmp_avatar.jpg)
