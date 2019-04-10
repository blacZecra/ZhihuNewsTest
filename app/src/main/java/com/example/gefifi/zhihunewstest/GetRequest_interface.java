package com.example.gefifi.zhihunewstest;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by gefifi on 2018/12/3.
 */

public interface GetRequest_interface {

    /**
     // 第1部分：在网络请求接口的注解设置
     @GET("ajax.php?a=fy&f=auto&t=auto&w=hello%20world")
     Call<Translation> getCall();
     // @GET注解的作用:采用Get方法发送网络请求

     // getCall() = 接收网络请求数据的方法
     // 其中返回类型为Call<*>，*是接收数据的类（即上面定义的Translation类）
     // 如果想直接获得Responsebody中的内容，可以定义网络请求返回值为Call<ResponseBody>

     @POST("translate?doctype=json&jsonversion=&type=&keyfrom=&model=&mid=&imei=&vendor=&screen=&ssid=&network=&abtest=")
     @FormUrlEncoded
     Call<TransNetease> getCall(@Field("i") String targetSentence);
     //采用@Post表示Post方法进行请求（传入部分url地址）
     // 采用@FormUrlEncoded注解的原因:API规定采用请求格式x-www-form-urlencoded,即表单形式
     // 需要配合@Field 向服务器提交需要的字段
     */

    @GET("api/4/news/latest")
    Call<News> getCall();

}
