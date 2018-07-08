package com.fathzer.localization.ws.controller;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fathzer.localization.URLMessageBuilder;
import com.fathzer.localization.URLMessageBuilder.BundleAddress;

@RestController
public class MessagesController {
  public static final String BUNDLE_ID_PARAM = "bundle";
  
	@Resource(name = "bundleRoot")
  private String rootURL;
	
	@GetMapping(value="v1/messages", produces = {"application/JSON"})
	public Map<String,String> get(@RequestParam(value=BUNDLE_ID_PARAM) String bundle, @RequestParam(value="country") String country, @RequestParam(value="lng") String lng, @RequestParam(value="id", required=false) List<String> id) {
		if (rootURL!=null) {
			bundle = rootURL + bundle;
		}
		BundleAddress bundleAddress = URLMessageBuilder.parseURL(bundle);
		Locale locale = new Locale(lng, country);
		return id==null ? URLMessageBuilder.getMessages(bundleAddress, locale) : URLMessageBuilder.getMessages(bundleAddress, locale, id);
	}
}