package com.nfgj.servicebase.exceptionhandler;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author nanfgj
 * @create 2022-09-20 9:16
 */
@Data
@AllArgsConstructor //有参构造器
@NoArgsConstructor  //无参构造器
@ToString
public class GuliException extends RuntimeException{

    @ApiModelProperty(value = "状态码")
    private Integer code;

    private String msg;
}
