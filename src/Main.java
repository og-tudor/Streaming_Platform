import Input.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import movies.Movie;
import movies.MovieDataBase;
import pageStructure.*;
import users.User;
import users.UserDataBase;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
//        Input inputdata = new Input()
        Input inputData = Input.getInstance();
        inputData = objectMapper.readValue(new File(args[0]), Input.class);
//        Input data2 = Input.getInstance();
        ArrayNode output = objectMapper.createArrayNode();


        ArrayList<ActionInput> actions = inputData.getActions();
        Page currentPage = HomeUnauth.getInstance();
        User currentUser = null;
        MovieDataBase allMovies = new MovieDataBase(inputData.getMovies());
        MovieDataBase currentMovieList = new MovieDataBase();
        UserDataBase userDataBase = new UserDataBase(inputData.getUsers());

        for (int i = 0; i < actions.size(); i++) {
            boolean printOut = false;
            boolean printError = false;
            ObjectNode node = objectMapper.createObjectNode();
            ActionInput action = actions.get(i);
            String switchPage = action.getPage();
            System.out.println(currentPage);
            switch (action.getType()) {
                case "change page" :
                    if (currentPage.getLinks().containsKey(action.getPage())) {
                        currentPage = currentPage.getLinks().get(switchPage);
                        if (action.getPage().equals("logout")) {
                            System.out.println("logout successul");
                            currentUser = null;
                        } else if (Movies.getInstance().equals(currentPage)) {
                            printOut = true;
                            System.out.println("ajunge pe movies");
                            currentMovieList = new MovieDataBase(allMovies, currentUser);
                        }
                    } else {
                        System.out.println("error, can't change PAGE");
                        printOut = true;
                        printError = true;
                    }
                    break;
                case "on page" :
                    String feature = action.getFeature();
                    Credentials credentials = action.getCredentials();
                    switch (feature) {
                        case "login" :
                            printOut = true;
                            if (!(currentPage instanceof Login)) {
                                System.out.println("not on loginPage");
                                printError = true;
                                break;
                            }
                            if (userDataBase.checkUser(credentials)) {
                                currentPage = HomeAuth.getInstance();
                                currentUser = userDataBase.findUser(credentials);
                                System.out.println("login succesul");
                            } else {
                                currentPage = HomeUnauth.getInstance();
                                printError = true;
                            }
                            break;
                        case "register" :
//                            if (userDataBase.checkUser(credentials))
                            printOut = true;
                            if (!(currentPage instanceof Register)) {
                                System.out.println("not on registerPage");
                                printError = true;
                                break;
                            }
                            userDataBase.insertUser(credentials);
                            currentPage = HomeAuth.getInstance();
                            currentUser = userDataBase.findUser(credentials);
                            System.out.println("register & login succesul");

                            break;
                        case "search" :
                            System.out.println("search succesful");
                            printOut = true;
                            currentMovieList.search(action.getStartsWith());
                            break;
                        case "filter" :
                            printOut = true;
                            if (!Movies.getInstance().equals(currentPage)) {
                                System.out.println("not on MoviesPage");
                                printError = true;
                                break;
                            }
                            System.out.println("filter succesful");
                            currentMovieList = new MovieDataBase(allMovies, currentUser);
                            currentMovieList.filter(action);
                            break;
                    }
                    break;
            }


            // Deep Copies
            MovieDataBase currentMovieListCopy = new MovieDataBase(currentMovieList, currentUser);
            if (printError && printOut) {
                node.putPOJO("error", "Error");
                node.putPOJO("currentMoviesList", currentMovieListCopy.getMovies());
                node.putPOJO("currentUser", null);
                output.add(node);
            } else if (printOut) {
                node.putPOJO("error", null);
                node.putPOJO("currentMoviesList", currentMovieListCopy.getMovies());
                node.putPOJO("currentUser", currentUser);
                output.add(node);
            }
        }

        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();

        // TODO delete out file
        char[] testNumber = args[0].toCharArray();
        objectWriter.writeValue(new File("checker/resources/result/output"+ testNumber[testNumber.length-6] + ".json"), output);
        objectWriter.writeValue(new File("results.out"), output);

    }
}
