package com.lcw.one.main.advice;

import com.lcw.one.util.http.ResponseMessage;
import com.lcw.one.util.http.ResponseMessageCodeEnum;
import com.lcw.one.util.http.Result;
import com.lcw.one.util.http.ValidError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 参考：http://javaninja.net/2013/12/getting-spring-mvc-validation-messages-from-a-json-service/
 */
@ControllerAdvice
public class NotValidExceptionAdvice {

    private static final Logger logger = LoggerFactory.getLogger(NotValidExceptionAdvice.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseMessage<List<ValidError>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
//        logger.error(exception.getMessage(), exception);

        List<ValidError> validErrorList = new ArrayList<>();
        BindingResult result = exception.getBindingResult();
        for (FieldError fieldError : result.getFieldErrors())  {
            validErrorList.add(new ValidError(fieldError.getField(), fieldError.getDefaultMessage()));
            logger.warn("valid error: obj[{}], filed[{}], message[{}]", fieldError.getObjectName(), fieldError.getField(), fieldError.getDefaultMessage());
        }

        return Result.error(ResponseMessageCodeEnum.VALID_ERROR.getCode(), "", validErrorList);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseMessage<List<ValidError>> handleConstraintViolationException(ConstraintViolationException exception) {
//        logger.error(exception.getMessage(), exception);

        List<ValidError> validErrorList = new ArrayList<>();
        Set<ConstraintViolation<?>> violationSet = exception.getConstraintViolations();
        for (ConstraintViolation violation: violationSet) {
            validErrorList.add(new ValidError(violation.getPropertyPath().toString(), violation.getMessage()));
            logger.warn("param valid error: obj[{}], filed[{}], message[{}]", violation.getRootBeanClass(), violation.getPropertyPath(), violation.getMessage());
        }

        return Result.error(ResponseMessageCodeEnum.VALID_ERROR.getCode(), "", validErrorList);
    }

}



