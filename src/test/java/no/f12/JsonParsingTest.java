package no.f12;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.Test;

import com.google.gson.Gson;

public class JsonParsingTest {

	@Test
	public void testShouldParseTextCorrectly() throws Exception {
		String filename = "result.json";
		String json = App.readClassPathFile(this.getClass(), filename);

		DocumentContext ctx = JsonPath.parse(json);

		assertNotNull(ctx.read("$.data.id"));
		assertEquals("78D1Ms4p5U9yM", ctx.read("$.data.id"));
		assertEquals(
				Arrays.asList(new String[] { "the big bang theory",
						"thank you", "penny", "kaley cuoco" }),
				ctx.read("$.data.tags"));
		assertEquals(200, ctx.read("$.meta.status"));
	}

}
