package com.luv2code.springdemo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentRestExceptionHandler {

//    add exception handling code from StudentRestController.java

    //	exception handler returns a ResponseEntity object with the error message in the body and the status code in the header
//	ResponseEntity is a wrapper for the HTTP response object. It provides for specifying HTTP status code + Response Body and HTTP headers
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc) {

//		custom pojo created earlier to hold error message and timestamp
        StudentErrorResponse error = new StudentErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value()); //not found is 404
        error.setMessage(exc.getMessage()); //error message set based on exception message
        error.setTimeStamp(System.currentTimeMillis()); //timestamp

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
//		jackson returns the created object error(body of response) and status code as JSON in the response body
    }

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception exc) {
//		this one is for any other exception

//		again create a StudentErrorResponse object
        StudentErrorResponse error = new StudentErrorResponse();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
//		error.setMessage(exc.getMessage());    //can update this line to give whatever plain text message you want
        error.setMessage("I don't know what that is...");
        error.setTimeStamp(System.currentTimeMillis());

//		return ResponseEntity object with error message and status code in the body and headers
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
