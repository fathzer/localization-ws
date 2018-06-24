package com.fathzer.localization;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.UtilityClass;

@UtilityClass
public class URLMessageBuilder {

	@AllArgsConstructor
	@Getter
	@ToString
	public static class BundleAddress {
		private URL url;
		private String name;
	}

	/** Parses a URL String into a BundleAddress instance.
	 * <br>The url should have the format: <b>host[:port]/[path/]/resourceName</b>
	 * @param urlStr The url
	 * @return A BundleAddress
	 * @throws IllegalArgumentException is wrong (for example if url has no bundle name)
	 */
	public static BundleAddress parseURL(String urlStr) {
		while (urlStr.endsWith("/")) {
			urlStr = urlStr.substring(0, urlStr.length()-1);
		}
		int index = urlStr.lastIndexOf('/');
		String bundleName = urlStr.substring(index+1);
		urlStr = urlStr.substring(0, index+1);
		try {
			URL url = new URL(urlStr);
			if (url.getHost().trim().isEmpty()) {
				throw new MalformedURLException("Host is not specificied in "+urlStr);
			}
			return new BundleAddress(url, bundleName);
		} catch (MalformedURLException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/** Gets some values of a resource bundle.
	 * @param bundleAddress A resource bundle address
	 * @param locale A locale
	 * @param ids The list of ids we want to get the value 
	 * @return A map with ids as key and their values in the bundle as values. If an id is unknown, its value will be null.
	 * @throws MissingResourceException if the resourceBundle was not found.
	 */
	public static Map<String, String> getMessages(BundleAddress bundleAddress, Locale locale, Iterable<String> ids) {
		Map<String, String> result = new HashMap<>();
		ResourceBundle rb = getBundle(bundleAddress, locale);
		for (String id : ids) {
			try {
				result.put(id, rb.getString(id));
			} catch (MissingResourceException e) {
				result.put(id, null);
			}
		}
		return result;
	}

	/** Gets all values of a resource bundle.
	 * @param bundleAddress A resource bundle address
	 * @param locale A locale
	 * @return A map containing all the id/value pairs if the resource bundle.
	 * @throws MissingResourceException if the resourceBundle was not found.
	 */
	public static Map<String, String> getMessages(BundleAddress bundleAddress, Locale locale) {
		Map<String, String> result = new HashMap<>();
		ResourceBundle rb = getBundle(bundleAddress, locale);
		Enumeration<String> ids = rb.getKeys();
		while (ids.hasMoreElements()) {
			String id = ids.nextElement();
			result.put(id, rb.getString(id));
		}
		return result;
	}

	private static ResourceBundle getBundle(BundleAddress bundleAddress, Locale locale) {
		ClassLoader loader = new URLClassLoader(new URL[] {bundleAddress.getUrl()});
		return ResourceBundle.getBundle(bundleAddress.getName(), locale, loader);
	}
}