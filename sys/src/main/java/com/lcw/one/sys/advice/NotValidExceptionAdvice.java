package com.lcw.one.sys.advice;

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

import java.util.List;

/**
 * 参考：http://javaninja.net/2013/12/getting-spring-mvc-validation-messages-from-a-json-service/
 */
@ControllerAdvice
public class NotValidExceptionAdvice {

    private static final Logger logger = LoggerFactory.getLogger(NotValidExceptionAdvice.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseMessage<List<FieldError>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();

        for (FieldError fieldError : result.getFieldErrors()) {
            logger.info("valid error: obj[{}], filed[{}], message[{}]", fieldError.getObjectName(), fieldError.getField(), fieldError.getDefaultMessage());
        }

        return new ResponseMessage<>(ResponseCodeEnum.VALID_ERROR.getCode(), "", result.getFieldErrors());

//        ValidationMessages validationMessages = new ValidationMessages();
//        BindingResult result = exception.getBindingResult();
//
//        // process the field validations
//        for (FieldError fieldError : result.getFieldErrors()) {
//            validationMessages.addFieldError(messageSource.getMessage(fieldError, LocaleContextHolder.getLocale()));
//        }
//
//        // process the global validations
//        for (ObjectError globalError : result.getGlobalErrors()) {
//            validationMessages.addFieldError(globalError.getDefaultMessage());
//        }

//        return validationMessages;
    }

//    /**
//     * Resolves localized messages.  Not currently used, but here if we switch to message resource files.
//     * @param fieldError the field error object
//     * @return the localized error message
//     */
//    private String resolveLocalizedErrorMessage(FieldError fieldError) {
//        Locale currentLocale = LocaleContextHolder.getLocale();
//        String localizedErrorMessage = messageSource.getMessage(fieldError, currentLocale);
//
//        //If the message was not found, return the most accurate field error code instead.
//        //You can remove this check if you prefer to get the default error message.
//        if (localizedErrorMessage.equals(fieldError.getDefaultMessage())) {
//            String[] fieldErrorCodes = fieldError.getCodes();
//            localizedErrorMessage = fieldErrorCodes[0];
//        }
//
//        return localizedErrorMessage;
//    }
}



