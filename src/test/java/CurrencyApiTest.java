import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class CurrencyApiTest {

    @Test
    public void testCurrencyJsonHasMoreThan20Items() throws Exception {
        String url = "https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies.json";

        // Create an HttpClient instance
        HttpClient httpClient = HttpClients.createDefault();

        // Create an HTTP GET request
        HttpGet httpGet = new HttpGet(url);

        // Execute the request and get the response
        HttpResponse response = httpClient.execute(httpGet);

        // Check the response status code
        int statusCode = response.getStatusLine().getStatusCode();
        assertTrue("HTTP GET request failed with status code: " + statusCode, statusCode == 200);

        // Parse the response JSON
        String jsonResponse = EntityUtils.toString(response.getEntity());

        assertTrue("JSON response does not contain GBP", jsonResponse.contains("British Pound"));
        assertTrue("JSON response does not contain USD", jsonResponse.contains("USD"));

        int numItems = jsonResponse.split(",").length;
        // Assert that the number of items is greater than 20
        assertTrue("Expected more than 20 items, but found: " + numItems, numItems > 20);
        System.out.println("elements does API returns :: " + numItems);
        List<String> abbreviationList = new ArrayList<>();
        for (String abbreviation : jsonResponse.split(",")) {
            abbreviationList.add(abbreviation);
        }
        System.out.println(abbreviationList);
    }
}
