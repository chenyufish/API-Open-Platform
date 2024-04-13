package com.fishman.fishmanapi_interface.controller;

import com.fishman.fishmanapi_interface.model.User;
import org.springframework.web.bind.annotation.*;

/**
 * api 测试接口
 */
@RestController
@RequestMapping("/name")
public class NameController {
    @GetMapping("/")
    public String getNameByGet(String name){
        return "GET：你的名字"+name;
    }
    @PostMapping("/")
    public String getNameByPost(@RequestParam String name){
        return "POST:你的名字"+name;
    }

    @PostMapping("/user")
    public String getUserNameByPost(@RequestBody User user){
        return "Post：你的名字"+user.getName();
    }
}
