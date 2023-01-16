package users;

import input.Credentials;
import input.UserInput;
import movies.Movie;
import movies.MovieDataBase;

import java.util.ArrayList;
import java.util.HashMap;

public class UserDataBase {
    private final int numberOfPremiumMovies = 15;
    private static UserDataBase instance = null;
    private HashMap<String, User> users = new HashMap<>();

    public static UserDataBase getInstance() {
        return instance;
    }
    /** Clears the usersDatabase instance */
    public static void clearInstance() {
        instance = null;
    }
    /** Returns the usersDatabase instance */
    public static UserDataBase getInstance(final ArrayList<UserInput> userInputs) {
        if (instance == null) {
            instance = new UserDataBase(userInputs);
        }
        return instance;
    }

    public UserDataBase(final ArrayList<UserInput> userInputs) {
        for (int i = 0; i < userInputs.size(); i++) {
            UserInput userInput = userInputs.get(i);
            Credentials credentials = userInput.getCredentials();
            User user = new User(credentials, 0, numberOfPremiumMovies);

            this.users.put(credentials.getName(), user);
            //TODO add stats
        }
    }

    /** Sends a notification to all users */
    public void notify(final String movieName, final String message) {
        for (int i = 0; i < this.users.size(); i++) {
            User user = this.users.get(i);
            MovieDataBase allMovies = MovieDataBase.getInstance();
            if (user != null) {
                String userCountry = user.getCredentials().getCountry();
                Movie movie = allMovies.find(movieName);
                if (movie.getCountriesBanned().contains(userCountry)) {
                    if (user.isSubscribed(movie)) {
                        Notification notification = new Notification(movieName, message);
                        user.getNotification(notification);
                    }
                }
            }
        }
    }

    /** Find a user based on his credentials and returns it */
    public User findUser(final Credentials credentials) {
        User user = instance.users.get(credentials.getName());
        return user;
    }
    /** Registers a user into the dataBase */
    public void insertUser(final Credentials credentials) {
        User user = new User(credentials, 0, numberOfPremiumMovies);
        users.put(credentials.getName(), user);
    }

    /** Returneaza true daca exista in baza de data un user cu credentialele date ca parametru */
    public boolean checkUser(final Credentials credentials) {
        if (!users.containsKey(credentials.getName())) {
            return false;
        }
        User existentUser = users.get(credentials.getName());
        if (!existentUser.getCredentials().getPassword().equals(credentials.getPassword())) {
            return false;
        }
        return true;
    }
    /** Prints all the users in the database */
    @Override
    public String toString() {
        return "users.UserDataBase{"
                +
                "user=" + users
                +
                '}';
    }
}
