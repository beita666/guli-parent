package com.atguigu.serviceedu.controller;


import com.atguigu.commonutils.Rr;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/serviceedu/user")
@CrossOrigin
public class EduLoginController {


    @PostMapping("login")
    public Rr login(){
        return Rr.ok().data("token","admin");
    }

    @GetMapping("info")
    public Rr info(){
        return Rr.ok().data("roles","[admin]").data("name","admin").data("avatar","");
    }
}
