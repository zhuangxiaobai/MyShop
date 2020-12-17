package com.zc.shop.common.exception;


import com.zc.shop.common.api.CommonResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * 全局异常处理
 */
@RestControllerAdvice
public class ExceptionControllerAdvice {

    //处理(jdk提供的)参数验证的异常(@valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResult MethodArgumentNotValidException(MethodArgumentNotValidException e) {
       // CommonResult commonResult;
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);

        return    CommonResult.validateFailed(objectError.getDefaultMessage());
    }
    /**
     * 处理(spring提供的)参数验证的异常(@valided)
     * ConstraintViolationException 参数校验统一异常处理
     */
    @ResponseBody
    @ExceptionHandler(value = ConstraintViolationException.class)
    public CommonResult errorHandler(ConstraintViolationException ex) {
        //logger.error(ex.getMessage(),ex);
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
//        for (ConstraintViolation<?> item : violations) {
//            return CommonResult.validateFailed(item.getMessage());
//        }

        return CommonResult.validateFailed(violations.iterator().next().getMessage());
    }




    //处理自定义业务异常
    @ExceptionHandler(BusinessException.class)
    public CommonResult ExceptionHandler(BusinessException e) {
        if (e.getErrorCode() != null) {
            return CommonResult.failed(e.getErrorCode());
        }else {

            if (e.getMessage() !=null){
                return CommonResult.failed(e.getMessage());
            }
            return CommonResult.failed();
        }


    }
}