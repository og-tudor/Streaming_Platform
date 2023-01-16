import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import input.ActionInput;
import input.Credentials;
import input.Input;
import input.MovieInput;
import movies.Movie;
import movies.MovieDataBase;
import pagestructure.*;
import users.User;
import users.UserDataBase;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class Main {
    public static void main(final String[] args) throws IOException {
        // Reading inputData
        ObjectMapper objectMapper = new ObjectMapper();
        Input inputData = Input.getInstance();
        inputData = objectMapper.readValue(new File(args[0]), Input.class);
        // Initialising the output node
        ArrayNode output = objectMapper.createArrayNode();
        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        // Initialising the movies and users database and other useful variables
        ArrayList<ActionInput> actions = inputData.getActions();
        Page currentPage = HomeUnauth.getInstance();
        User currentUser = null;
        MovieDataBase.clearInstance();
        UserDataBase.clearInstance();
        MovieDataBase allMovies = MovieDataBase.getInstance(inputData.getMovies());
        MovieDataBase currentMovieList = new MovieDataBase();
        Movie currentMovie = null;
        UserDataBase userDataBase = UserDataBase.getInstance(inputData.getUsers());
        MovieDataBase seeDetailsMovie = null;
        // Filter is used to determine if a filter is active or not
        boolean filter = false;
        // Going through each command
        for (int i = 0; i < actions.size(); i++) {
            // PrintOut and Print error determine if output will be written and what type
            boolean printOut = false;
            boolean printError = false;
//            System.out.println(currentPage.getPreviousPages());
            ObjectNode node = objectMapper.createObjectNode();
            ActionInput action = actions.get(i);
            String switchPage = action.getPage();
            switch (action.getType()) {
                case "change page" :
                    // Checking if the page the user is trying to access is on the current page
                    if (currentPage.getLinks().containsKey(action.getPage())) {
                        // Adding to the previous pages stack
                        currentPage.getPreviousPages().getStack().push(currentPage);
                        // Switching the Page
                        currentPage = currentPage.getLinks().get(switchPage);
                        if (HomeUnauth.getInstance().equals(currentPage)) {
                            currentMovieList.getMovies().clear();
                            currentUser = null;
                        } else if (Movies.getInstance().equals(currentPage)) {
                            printOut = true;
                            filter = false;
                            currentMovieList.populate(currentUser);
                        } else if (Upgrade.getInstance().equals(currentPage)) {
                            filter = false;
                            currentMovieList.populate(currentUser);
                        } else if (Details.getInstance().equals(currentPage)) {
                            if (currentMovieList.getMovies().isEmpty() && !filter) {
                                currentMovieList.populate(currentUser);
                            }
                            seeDetailsMovie = currentMovieList;
                            seeDetailsMovie.search(action.getMovie());
                            printOut = true;
                            if (seeDetailsMovie.getMovies().isEmpty()) {
                                printError = true;
                            }
                        }
                    } else {
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
                            // Not on login page
                            if (!(currentPage.equals(Login.getInstance()))) {
                                printError = true;
                                break;
                            }
                            if (userDataBase.checkUser(credentials)) {
                                // Login successful
                                currentPage = HomeAuth.getInstance();
                                currentUser = userDataBase.findUser(credentials);
                            } else {
                                currentPage = HomeUnauth.getInstance();
                                printError = true;
                            }
                            break;
                        case "register" :
                            printOut = true;
                            // Not on register page
                            if (!(currentPage.equals(Register.getInstance()))) {
                                printError = true;
                                break;
                            }
                            // Register && Login successful
                            userDataBase.insertUser(credentials);
                            currentPage = HomeAuth.getInstance();
                            currentUser = userDataBase.findUser(credentials);
                            break;
                        case "search" :
                            printOut = true;
                            // Not on movies page
                            if (!Movies.getInstance().equals(currentPage)) {
                                printError = true;
                                break;
                            }
                            // Searching the movie
                            currentMovieList.populate(currentUser);
                            currentMovieList.search(action.getStartsWith());
                            break;
                        case "filter" :
                            printOut = true;
                            // Checking if the user in on the right pages
                            if (Movies.getInstance().equals(currentPage)
                                || Details.getInstance().equals(currentPage)) {
                                // Filtering the movieList
                                currentMovieList.populate(currentUser);
                                currentMovieList.filter(action);
                                filter = true;
                            } else {
                                // Not on movies or Details pages
                                printError = true;
                            }

                            break;
                        case "buy tokens":
                            // Checking if the user is on Upgrades Page
                            if (!Upgrade.getInstance().equals(currentPage)) {
                                printOut = true;
                                printError = true;
                                break;
                            }
                            assert currentUser != null;
                            String balance = currentUser.getCredentials().getBalance();
                            int currentUserBalance = Integer.parseInt(balance);
                            int count = Integer.parseInt(action.getCount());
                            if (count <= currentUserBalance) {
                                // Buying the tokens
                                currentUser.setTokensCount(currentUser.getTokensCount() + count);
                                int newBalanceInt = currentUserBalance - count;
                                String newBalance = Integer.toString(newBalanceInt);
                                currentUser.getCredentials().setBalance(newBalance);
                            } else {
                                // Balance too low to buy tokens
                                printOut = true;
                                printError = true;
                                break;
                            }
                            break;

                        case "buy premium account":
                            if (!Upgrade.getInstance().equals(currentPage)) {
                                printOut = true;
                                printError = true;
                                break;
                            }
                            int userTokens = currentUser.getTokensCount();
                            // Already a premium user
                            if (currentUser.getCredentials().getAccountType().equals(Credentials.AccountType.premium)) {
                                printOut = true;
                                printError = true;
                                break;
                            }
                            if (userTokens >= 10) {
                                // Buying Premium account
                                Credentials userCredentials = currentUser.getCredentials();
                                currentUser.setTokensCount(currentUser.getTokensCount() - 10);
                                userCredentials.setAccountType(Credentials.AccountType.premium);
                            } else {
                                // Tokens too low to purchase premium account
                                printOut = true;
                                printError = true;
                                break;
                            }
                            break;
                        case "purchase":
                            printOut = true;
                            printError = true;
                            if (!Details.getInstance().equals(currentPage)) {
                                break;
                            }
                            if (!seeDetailsMovie.getMovies().isEmpty()) {
                                currentMovie = seeDetailsMovie.getMovies().get(0);
                                printError = currentUser.purchaseMovie(currentMovie);
                                printOut = true;
                            }
                            break;
                        case "watch":
                            printOut = true;
                            printError = true;
                            if (!Details.getInstance().equals(currentPage)) {
                                break;
                            }
                            if (!seeDetailsMovie.getMovies().isEmpty()) {
                                currentMovie = seeDetailsMovie.getMovies().get(0);
                                printError = currentUser.watchMovie(currentMovie);
                                printOut = true;
                            }
                            break;
                        case "like":
                            printOut = true;
                            printError = true;
                            if (!Details.getInstance().equals(currentPage)) {
                                break;
                            }
                            if (!seeDetailsMovie.getMovies().isEmpty()) {
                                currentMovie = seeDetailsMovie.getMovies().get(0);
                                printError = currentUser.likeMovie(currentMovie);
                                printOut = true;
                            }
                            break;
                        case "rate":
                            printOut = true;
                            printError = true;
                            if (!Details.getInstance().equals(currentPage)) {
                                break;
                            }
                            if (!seeDetailsMovie.getMovies().isEmpty()) {

                                Double rate = action.getRate();
                                currentMovie = seeDetailsMovie.getMovies().get(0);
                                printError = currentUser.rateMovie(currentMovie, rate);
                                printOut = true;
                            }
                            break;
                        case "subscribe":
//                            printError = true;
                            if (!Details.getInstance().equals(currentPage)) {
                                printOut = true;
                                printError = true;
                                break;
                            }
                            if (!seeDetailsMovie.getMovies().isEmpty()) {

                                currentMovie = seeDetailsMovie.getMovies().get(0);
                                printError = currentUser.subscribe(currentMovie, action.getSubscribedGenre());
                                printOut = true;
                            }
                            break;
                        default:
                            // Not a valid command
                            printOut = true;
                            printError = true;
                            break;
                    }
                    break;
                case "database":
                    feature = action.getFeature();
                    credentials = action.getCredentials();
                    switch (feature) {
                        case "add":
                            MovieInput addedMovie = action.getAddedMovie();
                            printError = allMovies.insertMovie(addedMovie);
                            if (printError)
                                printOut = true;
                            break;
                        case "delete":
                            String deletedMovie = action.getDeletedMovie();

                            break;
                    }
                    break;

                default:
                    // Not a valid command
                    printOut = true;
                    printError = true;
                    break;
            }


            // Making deep copies
            MovieDataBase currentMovieListCopy = new MovieDataBase(currentMovieList, currentUser);
            User currentUserCopy = null;
            if (currentUser != null)
                currentUserCopy = new User(currentUser);
            if (printError && printOut) {
                currentMovieListCopy.getMovies().clear();
                node.putPOJO("error", "Error");
                node.putPOJO("currentMoviesList", currentMovieListCopy.getMovies());
                node.putPOJO("currentUser", null);
                output.add(node);
            } else if (printOut) {
                node.putPOJO("error", null);
                node.putPOJO("currentMoviesList", currentMovieListCopy.getMovies());
                node.putPOJO("currentUser", currentUserCopy);
                output.add(node);
            }
        }
        // Writing to the JSON file
        char[] testNumber = args[0].toCharArray();
        objectWriter.writeValue(new File("checker/resources/result/output"+ testNumber[testNumber.length-6] + ".json"), output);
        objectWriter.writeValue(new File("results.out"), output);
    }
}
