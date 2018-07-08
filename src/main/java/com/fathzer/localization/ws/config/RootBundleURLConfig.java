package com.fathzer.localization.ws.config;

import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class RootBundleURLConfig {
	public static final String BUNDLE_ROOT_URL = "BUNDLE_ROOT_URL";

	@Bean(name = "bundleRoot")
	public String build() throws MalformedURLException {
		String rootURL = System.getenv(BUNDLE_ROOT_URL);
		if (rootURL!=null) {
				URL url = new URL(rootURL);
				if (!rootURL.endsWith("/")) {
					rootURL = rootURL + "/";
				}
				log.info("{} set to {}",BUNDLE_ROOT_URL,url);
		} else {
			log.info("{} is not initialized", BUNDLE_ROOT_URL);
		}
		return rootURL;
	}
}