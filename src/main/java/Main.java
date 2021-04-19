import java.io.IOException;
import java.util.List;

public class Main {
    public static final String ALIEXPRESS_URL = "https://gpsfront.aliexpress.com/getRecommendingResults.do?callback=jQuery18302554242081162217_1618820833082" +
            "&widget_id=5547572&platform=pc&limit=%d&offset=%d&phase=1&productIds2Top=&postback=3dda66f9-7831-4a4d-aae1-984c02fabe36&_=1618820834619";
    public static final int TOTAL_PRODUCTS = 100;
    public static final  int STACK_SIZE = 25;
    public static final String CSV_FILE = "products.csv";

    public static void main(String[] args) throws IOException {
        ProductParser aliExpressParser = new ProductParser(ALIEXPRESS_URL, TOTAL_PRODUCTS, STACK_SIZE);
        List<Product> products = aliExpressParser.getProducts();

        CsvWriter writer = new CsvWriter(CSV_FILE);
        writer.write(products);
    }
}
