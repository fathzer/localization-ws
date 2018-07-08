package com.fathzer.localization.ws.error;

import java.net.MalformedURLException;

import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;

import com.fathzer.localization.ws.config.RootBundleURLConfig;

public class CustomFailureAnalyzer extends AbstractFailureAnalyzer<MalformedURLException> {
	@Override
	protected FailureAnalysis analyze(Throwable rootFailure, MalformedURLException cause) {
    String message = String.format("The %s environment variable contains an invalid URL (%s)", RootBundleURLConfig.BUNDLE_ROOT_URL, System.getenv(RootBundleURLConfig.BUNDLE_ROOT_URL));
   	return new FailureAnalysis(message , "Fix the URL syntax", cause);
	}
}
