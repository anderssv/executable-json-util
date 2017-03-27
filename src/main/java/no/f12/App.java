package no.f12;

import com.google.gson.Gson;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.IOUtils;
import org.docopt.Docopt;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) throws HttpException, IOException {
        String usage = readClassPathFile(App.class, "usage.txt");
        Map<String, Object> result = new Docopt(usage).parse(args);

        if (result == null) {
            print(usage);
            System.exit(1);
        } else if ((Boolean) result.get("--help")) {
            print(usage);
            System.exit(0);
        } else if ((Boolean) result.get("animate")) {
            MapNavigationWrapper wrapper = parseJson(getAnimatedGif());

            print(wrapper.get("data.id"));
            print(wrapper.get("data.image_original_url"));
        }
    }


    private static MapNavigationWrapper parseJson(String response) {
        Map<String, Object> map = new Gson().fromJson(response, HashMap.class);
        MapNavigationWrapper wrapper = new MapNavigationWrapper(map);
        return wrapper;
    }

    private static String getAnimatedGif() throws IOException, HttpException {
        HttpClient client = new HttpClient();
        HttpMethod httpMethod = new GetMethod(
                "http://api.giphy.com/v1/gifs/screensaver?api_key=dc6zaTOxFJmzC");
        client.executeMethod(httpMethod);
        return httpMethod.getResponseBodyAsString();
    }

    public static void print(Object print) {
        System.out.println(print);
    }

    public static String readClassPathFile(Class clazz, String filename)
            throws IOException {
        URL resource = clazz.getClassLoader().getResource(filename);
        String json = IOUtils.toString(resource.openStream());
        return json;
    }

}
