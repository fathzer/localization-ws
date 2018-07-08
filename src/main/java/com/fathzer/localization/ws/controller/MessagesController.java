package com.fathzer.localization.ws.controller;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fathzer.localization.URLMessageBuilder;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class MessagesController {
	private static final String BUNDLE_ROOT_URL = "BUNDLE_ROOT_URL";
	private String rootURL;
	
	@PostConstruct
	public final void init() {
		rootURL = System.getenv(BUNDLE_ROOT_URL);
		if (rootURL!=null) {
			if (!rootURL.endsWith("/")) {
				rootURL = rootURL + "/";
			}
			log.info("{} set to {}",BUNDLE_ROOT_URL,rootURL);
		} else {
			log.info("{} is not initialized", BUNDLE_ROOT_URL);
		}
	}
	
	
	@GetMapping(value="v1/messages", produces = {"application/JSON"})
	public Map<String,String> get(@RequestParam(value="bundle") String bundle, @RequestParam(value="country") String country, @RequestParam(value="lng") String lng, @RequestParam(value="id", required=false) List<String> id) {
		if (rootURL!=null) {
			bundle = rootURL + bundle;
		}
		if (id==null) {
			return URLMessageBuilder.getMessages(URLMessageBuilder.parseURL(bundle), new Locale(lng, country));
		} else {
			return URLMessageBuilder.getMessages(URLMessageBuilder.parseURL(bundle), new Locale(lng, country), id);
		}
	}
}