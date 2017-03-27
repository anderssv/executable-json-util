package no.f12;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.IOUtils;
import org.docopt.Docopt;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map;

public class App {
    public static void main(String[] args) throws IOException {
        System.out.println(run(args));
    }

    static String run(String... args) throws IOException {
        String usage = readClassPathFile(App.class, "usage.txt");
        Map<String, Object> result = new Docopt(usage).parse(args);

        if (result == null) {
            return usage;
        } else if ((Boolean) result.get("animate")) {
            DocumentContext ctx = parseJson(getAnimatedGif());

            print(ctx.read("$data.id"));
            print(ctx.read("$data.image_original_url"));
        } else if ((Boolean) result.get("say")) {
            if (result.get("--encrypt") != null && result.get("--encrypt").equals("rot13")) {
                return rot13((String) result.get("<something>"));
            } else {
                return (String) result.get("<something>");
            }
        } else if ((Boolean) result.get("--help")) {
            return usage;
        }
        return "";
    }


    private static DocumentContext parseJson(String response) {
        return JsonPath.parse(response);
    }

    private static String getAnimatedGif() throws IOException {
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
        String json = IOUtils.toString(resource.openStream(), Charset.defaultCharset());
        return json;
    }

    private static String rot13(String input) {
        String result = "";
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c >= 'a' && c <= 'm') c += 13;
            else if (c >= 'A' && c <= 'M') c += 13;
            else if (c >= 'n' && c <= 'z') c -= 13;
            else if (c >= 'N' && c <= 'Z') c -= 13;
            result += c;
        }

        return result;
    }

}
