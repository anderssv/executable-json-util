package no.f12;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.google.gson.Gson;

public class MapNavigationWrapperTest {

	@Test
	public void testShouldParseTextCorrectly() throws Exception {
		URL resource = this.getClass().getClassLoader()
				.getResource("result.json");
		String json = IOUtils.toString(resource.openStream());

		Map<String, Object> jsonMap = new Gson().fromJson(json, HashMap.class);
		MapNavigationWrapper wrapper = new MapNavigationWrapper(jsonMap);

		assertNotNull(wrapper.get("data.id"));
		assertEquals("78D1Ms4p5U9yM", wrapper.get("data.id"));
		assertEquals(
				Arrays.asList(new String[] { "the big bang theory",
						"thank you", "penny", "kaley cuoco" }),
				wrapper.get("data.tags"));
		assertEquals(200.0, wrapper.get("meta.status"));
	}

}
