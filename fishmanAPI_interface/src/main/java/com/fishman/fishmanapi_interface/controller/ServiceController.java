package com.fishman.fishmanapi_interface.controller;
import cn.hutool.json.JSONUtil;
import icu.qimuu.qiapisdk.model.params.NameParams;
import icu.qimuu.qiapisdk.model.response.NameResponse;
import org.springframework.web.bind.annotation.*;
/**
 * 名称 API
 *
 */
@RestController
@RequestMapping("/")
public class ServiceController {
    @GetMapping("/name")
    public NameResponse getName(NameParams nameParams) {
        return JSONUtil.toBean(JSONUtil.toJsonStr(nameParams), NameResponse.class);
    }
}
