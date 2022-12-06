import Input.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import pageStructure.HomeAuth;
import pageStructure.HomeUnauth;
import pageStructure.Page;
import users.Users;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        Input inputData = objectMapper.readValue(new File(args[0]), Input.class);
        ArrayNode output = objectMapper.createArrayNode();
        ObjectNode node = objectMapper.createObjectNode();
        node.putPOJO("output", inputData);
//        output.add(node);
        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
//        objectWriter.writeValue(new File("checker/resources/result/output.json"), output);
//        objectWriter.writeValue(new File("checker/resources/result/output.json"), inputData);
        objectWriter.writeValue(new File("results.out"), inputData);

        ArrayList<ActionInput> actions = inputData.getActions();
        Page currentPage = HomeUnauth.getInstance();

        Users users = new Users(inputData.getUsers());
        System.out.println(users);
//        System.out.println(currentPage);

        for (int i = 0; i < actions.size(); i++) {
            ActionInput action = actions.get(i);
            String switchPage = action.getPage();
            System.out.println(currentPage);
            switch (action.getType()) {
                case "change page" :
                    currentPage = currentPage.getLinks().get(switchPage);
                    break;
                case "on page" :
                    String feature = action.getFeature();
                    Credentials credentials = action.getCredentials();
                    switch (feature) {
                        case "login" :
                            if (users.checkUser(credentials)) {
                                currentPage = HomeAuth.getInstance();
                                System.out.println("Ai intrat in cont");
                            } else {
                                System.out.println("Parola gresita sau nu ai cont sorry");
                                currentPage = HomeUnauth.getInstance();
                            }
                            break;
                        case "register" :
                            users.insertUser(credentials);
                            currentPage = HomeUnauth.getInstance();
                            break;
                        case "logout" :
                            System.out.println("te ai delogat");
                            currentPage = HomeUnauth.getInstance();
                            break;
                    }
                    break;
            }
        }

    }
}
