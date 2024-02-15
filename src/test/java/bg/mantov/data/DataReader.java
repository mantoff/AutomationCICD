package bg.mantov.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;

public class DataReader {

    public List<HashMap<String, String>> convertJsonDataToMap(String filePath) throws IOException {

        //Read json file to String
        String jsonContent = FileUtils.readFileToString(new File(filePath),
                Charset.defaultCharset());

        //Convert String to HashMap using Jackson Databind dependency
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
        });

        return data;
    }
}
