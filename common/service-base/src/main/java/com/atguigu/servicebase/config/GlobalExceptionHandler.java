package com.atguigu.servicebase.config;


import com.atguigu.commonutils.Rr;
import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理类
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Rr error(Exception e){
        e.printStackTrace();
        return Rr.error();
    }

    @ExceptionHandler(GuliException.class)
    @ResponseBody
    public Rr error(GuliException e){
        e.printStackTrace();
        return Rr.error().message(e.getMsg()).code(e.getCode());
    }
}
