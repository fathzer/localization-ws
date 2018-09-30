package com.fathzer.localization.ws.config;

import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class RootBundleURLConfig {
	public static final String BUNDLE_ROOT_URL = "BUNDLE_ROOT_URL";
	
	@Getter
	@AllArgsConstructor(access=AccessLevel.PRIVATE)
	public static class BundleConfig {
		private String bundleRoot;
	}

	@Bean
	public BundleConfig build() throws MalformedURLException {
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
		return new BundleConfig(rootURL);
	}
}