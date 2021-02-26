package com.atguigu.commonutils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Rr {
    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<String, Object>();

    private Rr(){}

    public static Rr ok(){
        Rr rr = new Rr();
        rr.setSuccess(true);
        rr.setCode(ResultCode.SUCCESS);
        rr.setMessage("成功");
        return rr;
    }

    public static Rr error(){
        Rr rr = new Rr();
        rr.setSuccess(false);
        rr.setCode(ResultCode.ERROR);
        rr.setMessage("失败");
        return rr;
    }

    public Rr success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public Rr message(String message){
        this.setMessage(message);
        return this;
    }

    public Rr code(Integer code){
        this.setCode(code);
        return this;
    }

    public Rr data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public Rr data(Map<String, Object> map){
        this.setData(map);
        return this;
    }
}
