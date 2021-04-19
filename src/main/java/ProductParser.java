import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductParser {
    private final String aliExpressUrl;
    private final int totalProducts;
    private final int stackSize;

    public ProductParser(String aliExpressUrl, int totalProducts, int stackSize) {
        this.aliExpressUrl = aliExpressUrl;
        this.totalProducts = totalProducts;
        this.stackSize = stackSize;
    }

    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        for (int productsIndex = 0; productsIndex < totalProducts; productsIndex += stackSize) {
            products.addAll(getProductsStack(stackSize, productsIndex));
        }
        return products;
    }

    private List<Product> getProductsStack(int stackSize, int productsIndex) {
        String url = String.format(aliExpressUrl, stackSize, productsIndex);
        HttpGet request = new HttpGet(url);

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                String responseString = EntityUtils.toString(responseEntity);
                return parseResponse(extractJson(responseString));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
    private String extractJson (String responseString) {
        int beginIndex = responseString.indexOf("{");
        int endIndex = responseString.lastIndexOf("}") + 1;
        return responseString.substring(beginIndex, endIndex);
    }

    private List<Product> parseResponse(String responseJson) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNode results = objectMapper.readTree(responseJson).get("results");
        String string = results.toString();
        return objectMapper.readValue(string, new TypeReference<List<Product>>() {});
    }
}
