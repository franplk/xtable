package com.emar.xreport.web.controller;

import com.emar.xreport.exception.DataException;
import com.emar.xreport.exception.PageException;
import com.emar.xreport.web.WebResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class RestExceptionAdvice {

    private static final String PAGE_FAIL = "/frame/error/failed";

    @ResponseBody
    @ExceptionHandler
    public WebResult dataException(DataException e) {
        return WebResult.fail(e.getMessage());
    }

    @ExceptionHandler
    public ModelAndView pageException(PageException e) {
        return new ModelAndView(PAGE_FAIL, "result", e.getMessage());
    }
}
