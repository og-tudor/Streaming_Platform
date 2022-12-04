import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        Input inputData = objectMapper.readValue(new File(args[0]), Input.class);
        System.out.println(inputData);
        ArrayNode output = objectMapper.createArrayNode();
        ObjectNode node = objectMapper.createObjectNode();
        node.putPOJO("output", inputData);
//        output.add(node);
        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
//        objectWriter.writeValue(new File("checker/resources/result/output.json"), output);
        objectWriter.writeValue(new File("checker/resources/result/output.json"), inputData);


    }
}
