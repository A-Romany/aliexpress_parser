import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CsvWriter {
    private final String pathCSV;

    public CsvWriter(String pathCSV) {
        this.pathCSV = pathCSV;
    }

    public void write(List<Product> products) throws IOException {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(Product.class);
        mapper.writer(schema.withHeader().withColumnSeparator(',')).writeValue(new File(pathCSV), products);
    }
}

