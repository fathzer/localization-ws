package com.fathzer.localization.ws.error;

import java.util.MissingResourceException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.fathzer.localization.ws.controller.MessagesController;

@ControllerAdvice
@RestController
public class ExceptionMappers {
	@ExceptionHandler(MissingResourceException.class)
	public final ResponseEntity<Object> handleMissingResourceException (MissingResourceException e, WebRequest request) {
		return new ResponseEntity<Object>(String.format("The bundle %s is not found.",request.getParameter(MessagesController.BUNDLE_ID_PARAM)), HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(IllegalArgumentException.class)
	public final ResponseEntity<Object> handleMissingResourceException (IllegalArgumentException e, WebRequest request) {
		return new ResponseEntity<Object>(String.format("The format of url %s is wrong, it should be http(s)://host[:port][/path]/bundleName",request.getParameter("url")), HttpStatus.NOT_FOUND);
	}
}
