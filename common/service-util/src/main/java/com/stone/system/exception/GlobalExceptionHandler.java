package com.stone.system.exception;

import com.stone.common.result.R;
import com.stone.common.result.ResultCodeEnum;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * 全局异常处理类
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    //全局异常处理
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e){
        e.printStackTrace();
        return R.fail().message("执行了全局异常处理");
    }
    //特定异常处理
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R error(ArithmeticException e){
        e.printStackTrace();
        return R.fail().message("执行了特定异常处理");
    }
    @ExceptionHandler(StoneException.class)
    @ResponseBody
    public R error(StoneException e){
        e.printStackTrace();
        return R.fail().message(e.getMessage()).code(e.getCode());
    }
    /**
     * spring security异常
     * @param e
     * @return
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public R error(AccessDeniedException e) throws AccessDeniedException {
        return R.fail().code(ResultCodeEnum.PERMISSION.getCode()).message("没有操作的权限");
    }
}
