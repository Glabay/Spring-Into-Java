package xyz.glabaystudios.dislib.services;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class HttpService {
    String getOpenLibraryApi(Long isbn) {
        return "https://openlibrary.org/isbn/%d.json".formatted(isbn);
    }
    public CloseableHttpClient getHttpClient() {
        return HttpClientBuilder.create().build();
    }

    private HttpResponse fetchHttpGetResponse(Long isbn, CloseableHttpClient httpClient) {
        try (httpClient) {
            HttpGet httpGet = new HttpGet(getOpenLibraryApi(isbn));
            httpGet.setHeader("Accept", "application/json");
            httpGet.setHeader("Content-type", "application/json");
            return httpClient.execute(httpGet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String submitHttpGet(Long isbn, CloseableHttpClient httpClient) {
        try (httpClient) {
            HttpResponse response = fetchHttpGetResponse(isbn, httpClient);
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
