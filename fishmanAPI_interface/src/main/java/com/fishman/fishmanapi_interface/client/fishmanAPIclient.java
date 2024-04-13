package com.fishman.fishmanapi_interface.client;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.fishman.fishmanapi_interface.model.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

public class fishmanAPIclient {

    public String getNameByGet(String name){
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String result= HttpUtil.get("https://localhost:8123/api/name", paramMap);
        System.out.println(result);
        return result;
        }

    public String getNameByPost(@RequestParam String name){
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String result= HttpUtil.get("https://localhost:8123/api/name", paramMap);
        System.out.println(result);
        return result;
    }


    public String getUserNameByPost(@RequestBody User user){
        String json = JSONUtil.toJsonStr(user);
         HttpResponse httpResponse= HttpRequest.post("https://localhost:8123/api/name")
                 .body(json)
                 .execute();
         System.out.println(httpResponse.getStatus());
        return httpResponse.body();
    }
}
