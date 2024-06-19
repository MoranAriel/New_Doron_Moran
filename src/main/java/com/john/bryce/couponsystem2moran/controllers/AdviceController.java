package com.john.bryce.couponsystem2moran.controllers;

import com.john.bryce.couponsystem2moran.exceptions.CouponSystemException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler(value = { CouponSystemException.class })
    public ProblemDetail couponSystemHandlerError(CouponSystemException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(value = { Exception.class })
    public ProblemDetail generalHandlerError(Exception e) {
//        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "something went wrong please try again later :(");
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());

    }

}
