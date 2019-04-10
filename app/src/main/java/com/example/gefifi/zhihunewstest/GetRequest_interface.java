package com.example.gefifi.zhihunewstest;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by gefifi on 2018/12/3.
 */

public interface GetRequest_interface {

    // 第1部分：在网络请求接口的注解设置
    @GET("ajax.php?a=fy&f=auto&t=auto&w=hello%20world")
    Call<Translation> getCall();
    // @GET注解的作用:采用Get方法发送网络请求

    // getCall() = 接收网络请求数据的方法
    // 其中返回类型为Call<*>，*是接收数据的类（即上面定义的Translation类）
    // 如果想直接获得Responsebody中的内容，可以定义网络请求返回值为Call<ResponseBody>

}
