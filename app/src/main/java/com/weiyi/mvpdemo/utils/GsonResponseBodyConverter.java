package com.weiyi.mvpdemo.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;


final class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    GsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        try {
            JsonParser parser = new JsonParser();
            JsonElement root = parser.parse(new JsonReader(value.charStream()));
            Log.e("测试", root.toString()); //打印服务器原始数据
//            if (root instanceof JsonObject) {
//                JsonElement resultCodeEle = ((JsonObject) root).get("succeed");
//                if (resultCodeEle instanceof JsonPrimitive){
//                    byte resultCode = resultCodeEle.getAsByte();
//                    //如果responseCode 不为 “0”，抛出 RuntimeException，或者自定义的异常类型
//                    if (resultCode == 0) {//TODO  这里可以拦截到数据状态码， 根据状态码  抛出自定义的异常信息
//                        throw new RuntimeException("responseCode 出错了!");
//                    }
//                }
//            }

            return adapter.fromJsonTree(root);
        } finally {
            value.close();
        }
    }

}