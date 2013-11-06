package no.f12;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.google.gson.Gson;

public class MapNavigationWrapperTest {

	@Test
	public void testShouldParseTextCorrectly() throws Exception {
		String filename = "result.json";
		String json = App.readClassPathFile(this.getClass(), filename);

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
