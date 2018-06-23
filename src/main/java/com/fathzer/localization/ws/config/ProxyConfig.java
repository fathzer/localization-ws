package com.fathzer.localization.ws.config;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class ProxyConfig {
	@Bean
	public Object config() {
		String proxyHost = System.getenv("PROXY_HOST");
		if (proxyHost!=null) {
			String proxyPort = System.getenv("PROXY_PORT");
			log.info("Setting proxy to {}:{} ...", proxyHost, proxyPort);
			System.setProperty("https.proxyHost", proxyHost);
			System.setProperty("https.proxyPort", proxyPort);
			
			String proxyUser = System.getenv("PROXY_USER");
			if (proxyUser!=null) {
				log.info("Setting proxy authentication for user {} ...", proxyUser);
				System.setProperty("jdk.http.auth.tunneling.disabledSchemes", "");
				Authenticator.setDefault(
						new Authenticator() {
							@Override
							public PasswordAuthentication getPasswordAuthentication() {
								return new PasswordAuthentication(proxyUser, System.getenv("PROXY_PWD").toCharArray());
							}
						}
				);
			}
		}

		return null;
	}
}