package no.f12;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

import com.google.gson.Gson;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) throws HttpException, IOException {
		HttpClient client = new HttpClient();
		HttpMethod httpMethod = new GetMethod(
				"http://api.giphy.com/v1/gifs/screensaver?api_key=dc6zaTOxFJmzC");
		client.executeMethod(null, httpMethod);

		Map<String, Object> map = new Gson().fromJson(
				httpMethod.getResponseBodyAsString(), HashMap.class);
		MapNavigationWrapper wrapper = new MapNavigationWrapper(map);
		print(wrapper.get("data.id"));
	}

	public static void print(Object print) {
		System.out.println(print);
	}

}
