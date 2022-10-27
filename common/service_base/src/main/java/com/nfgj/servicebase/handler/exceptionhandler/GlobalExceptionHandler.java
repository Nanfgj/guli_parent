package com.nfgj.servicebase.handler.exceptionhandler;


import com.nfgj.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理器
 * @author nanfgj
 * @create 2022-09-20 8:48
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    //统一异常
    //指定出现什么异常执行这个方法
    @ExceptionHandler(Exception.class)
    @ResponseBody //为了返回数据
    public R error(Exception e){
        e.printStackTrace();
        return R.error().message("执行了统一异常处理...");
    }

    //指定异常执行这个方法----除数为零异常
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody //为了返回数据
    public R ArithmeticException(ArithmeticException e){
        e.printStackTrace();
        return R.error().message("执行了除数为零异常处理...");
    }

    //指定异常执行这个方法----自定义异常处理方法
    @ExceptionHandler(GuliException.class)
    @ResponseBody //为了返回数据
    public R ArithmeticException(GuliException e){

        log.error(ExceptionUtil.getMessage(e));

        e.printStackTrace();
        return R.error().message(e.getMsg()).code(e.getCode());
    }
}
