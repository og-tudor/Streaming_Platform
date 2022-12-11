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
        MovieDataBase.clearInstance();
        UserDataBase.clearInstance();
        MovieDataBase allMovies = MovieDataBase.getInstance(inputData.getMovies());
        MovieDataBase currentMovieList = new MovieDataBase();
        UserDataBase userDataBase = UserDataBase.getInstance(inputData.getUsers());

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
                            currentMovieList.getMovies().clear();
                            currentUser = null;
                        } else if (Movies.getInstance().equals(currentPage)) {
                            printOut = true;
//                            currentMovieList = new MovieDataBase(allMovies, currentUser);
                            currentMovieList.populate(currentUser);
                        } else if (Upgrade.getInstance().equals(currentPage)) {
//                            printOut = true;
//                            currentMovieList = new MovieDataBase(allMovies, currentUser);
                            currentMovieList.populate(currentUser);
                        } else if (Details.getInstance().equals(currentPage)) {
//                            currentMovieList = new MovieDataBase(allMovies, currentUser);
                            currentMovieList.populate(currentUser);
                            currentMovieList.search(action.getMovie());
                            System.out.println(currentMovieList);
                            printOut = true;
                            if (currentMovieList.getMovies().isEmpty()) {
                                printError = true;
                                System.out.println("There is no such movie");
                            }
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
                            if (!Movies.getInstance().equals(currentPage)) {
                                System.out.println("not on MoviesPage");
                                printError = true;
                                break;
                            }
//                            currentMovieList = new MovieDataBase(allMovies, currentUser);
                            currentMovieList.populate(currentUser);
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
//                            currentMovieList = new MovieDataBase(allMovies, currentUser);
                            currentMovieList.populate(currentUser);
                            System.out.println(currentMovieList);
                            currentMovieList.filter(action);
                            System.out.println("after filter " + currentMovieList);
                            System.out.println("printError " + printError);

                            break;

                        case "buy tokens":
                            // Verific daca e details
                            if (!Upgrade.getInstance().equals(currentPage)) {
                                System.out.println("not on UpgradesPage");
                                printOut = true;
                                printError = true;
                                break;
                            }
                            System.out.println("countSTRING = " + action.getCount());
                            System.out.println("currentBalanceSTRING = " + currentUser.getCredentials().getBalance());
                            System.out.println(currentUser);

                            int currentUserBalance = Integer.parseInt(currentUser.getCredentials().getBalance());
                            int count = Integer.parseInt(action.getCount());
                            if (count <= currentUserBalance) {
                                currentUser.setTokensCount(currentUser.getTokensCount() + count);
                                int newBalanceInt = currentUserBalance - count;

                                System.out.println("count = " + count);
                                System.out.println("currentBalance = " + currentUserBalance);
                                System.out.println("new balance = " + newBalanceInt);
                                String newBalance = Integer.toString(newBalanceInt);
                                currentUser.getCredentials().setBalance(newBalance);
                                System.out.println("tokens bought");
                            } else {
                                System.out.println("Balance too low to purchase tokens");
                                printOut = true;
                                printError = true;
                                break;
                            }
                            break;

                        case "buy premium account":
                            if (!Upgrade.getInstance().equals(currentPage)) {
                                System.out.println("not on UpgradesPage");
                                printOut = true;
                                printError = true;
                                break;
                            }
                            int userTokens = currentUser.getTokensCount();
                            // already premium user
                            if (currentUser.getCredentials().getAccountType().equals(Credentials.AccountType.premium)) {
                                printOut = true;
                                printError = true;
                                System.out.println("already a premium user");
                                break;
                            }
                            if (userTokens >= 10) {
                                currentUser.setTokensCount(currentUser.getTokensCount() - 10);
                                currentUser.getCredentials().setAccountType(Credentials.AccountType.premium);
                                System.out.println("premium bought");
                            } else {
                                System.out.println("Balance too low to purchase premium");
                                printOut = true;
                                printError = true;
                                break;
                            }
                            break;
                        case "purchase":
                            if (!Details.getInstance().equals(currentPage)) {
                                System.out.println("not on DetailsPage");
                                printOut = true;
                                printError = true;
                                break;
                            }
                            if (!currentMovieList.getMovies().isEmpty()) {
                                printError = currentUser.purchaseMovie(currentMovieList.getMovies().get(0));
                                printOut = true;
                            }
                            break;
                        case "watch":
                            if (!Details.getInstance().equals(currentPage)) {
                                System.out.println("not on DetailsPage");
                                printOut = true;
                                printError = true;
                                break;
                            }
                            if (!currentMovieList.getMovies().isEmpty()) {
                                printError = currentUser.watchMovie(currentMovieList.getMovies().get(0));
                                printOut = true;
                            }
                            break;
                        case "like":
                            if (!Details.getInstance().equals(currentPage)) {
                                System.out.println("not on DetailsPage");
                                printOut = true;
                                printError = true;
                                break;
                            }
                            if (!currentMovieList.getMovies().isEmpty()) {
                                printError = currentUser.likeMovie(currentMovieList.getMovies().get(0));
                                printOut = true;
                            }
                            break;
                        case "rate":
                            if (!Details.getInstance().equals(currentPage)) {
                                System.out.println("not on DetailsPage");
                                printOut = true;
                                printError = true;
                                break;
                            }
                            if (!currentMovieList.getMovies().isEmpty()) {
                                double rate = action.getRate();
                                printError = currentUser.rateMovie(currentMovieList.getMovies().get(0), rate);
                                printOut = true;
                            }
                            break;
                    }
                    break;
            }


            // Deep Copies
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

        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();

        // TODO delete out file
        char[] testNumber = args[0].toCharArray();
        objectWriter.writeValue(new File("checker/resources/result/output"+ testNumber[testNumber.length-6] + ".json"), output);
        objectWriter.writeValue(new File("results.out"), output);

    }
}
