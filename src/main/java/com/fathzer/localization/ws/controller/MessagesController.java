package com.fathzer.localization.ws.controller;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fathzer.localization.URLMessageBuilder;

@RestController
public class MessagesController {
	@GetMapping(value="v1/messages", produces = {"application/JSON"})
	public Map<String,String> get(@RequestParam(value="url") String url, @RequestParam(value="country") String country, @RequestParam(value="lng") String lng, @RequestParam(value="id", required=false) List<String> id) {
		if (id==null) {
			return URLMessageBuilder.getMessages(URLMessageBuilder.parseURL(url), new Locale(lng, country));
		} else {
			return URLMessageBuilder.getMessages(URLMessageBuilder.parseURL(url), new Locale(lng, country), id);
		}
	}
}