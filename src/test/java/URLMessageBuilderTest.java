import static org.junit.Assert.*;

import org.junit.Test;

import com.fathzer.localization.URLMessageBuilder;
import com.fathzer.localization.URLMessageBuilder.BundleAddress;

public class URLMessageBuilderTest {

	@Test
	public void test() {
		BundleAddress ba = URLMessageBuilder.parseURL("http://example.com/toto");
		assertEquals("http://example.com/", ba.getUrl().toString());
		assertEquals("toto", ba.getName());
		
		ba = URLMessageBuilder.parseURL("http://example.com/toto/");
		assertEquals("http://example.com/", ba.getUrl().toString());
		assertEquals("toto", ba.getName());

		ba = URLMessageBuilder.parseURL("http://example.com/toto////");
		assertEquals("http://example.com/", ba.getUrl().toString());
		assertEquals("toto", ba.getName());

		ba = URLMessageBuilder.parseURL("http://example.com:8080/message");
		assertEquals("http://example.com:8080/", ba.getUrl().toString());
		assertEquals("message", ba.getName());
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testMissingName() {
		URLMessageBuilder.parseURL("http://example.com/");
	}

	@Test (expected=IllegalArgumentException.class)
	public void testWrongProtocol() {
		URLMessageBuilder.parseURL("xxx://example.com/titi");
	}
}
