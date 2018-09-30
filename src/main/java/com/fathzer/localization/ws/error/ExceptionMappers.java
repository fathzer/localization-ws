package com.fathzer.localization.ws.error;

import java.util.MissingResourceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.fathzer.localization.ws.config.RootBundleURLConfig.BundleConfig;
import com.fathzer.localization.ws.controller.MessagesController;

@ControllerAdvice
@RestController
public class ExceptionMappers {
	private static final String COMMON_PREFIX = "The format of the bundle id (%s) is wrong, ";
	private static final String WITH_ROOT_FORMAT = COMMON_PREFIX+"it should be a bundle name relative to %s.";
	private static final String NO_ROOT_FORMAT = COMMON_PREFIX+"it should be http(s)://host[:port][/path]/bundleName";
	@Autowired
	BundleConfig bundleConfig;
	
	@ExceptionHandler(MissingResourceException.class)
	public final ResponseEntity<Object> handleMissingResourceException (MissingResourceException e, WebRequest request) {
		return new ResponseEntity<Object>(String.format("The bundle %s is not found.",request.getParameter(MessagesController.BUNDLE_ID_PARAM)), HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(IllegalArgumentException.class)
	public final ResponseEntity<Object> handleMissingResourceException (IllegalArgumentException e, WebRequest request) {
		String bundle = request.getParameter(MessagesController.BUNDLE_ID_PARAM);
		String message = bundleConfig.getBundleRoot()==null ?
				String.format(NO_ROOT_FORMAT, bundle) :
				String.format(WITH_ROOT_FORMAT, bundle, bundleConfig.getBundleRoot());
		return new ResponseEntity<Object>(message, HttpStatus.NOT_FOUND);
	}
}
