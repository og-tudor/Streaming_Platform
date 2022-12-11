package users;

import Input.Credentials;
import Input.Input;
import Input.UserInput;

import java.util.ArrayList;
import java.util.HashMap;

public class UserDataBase {

    private static UserDataBase instance = null;
    HashMap<String, User> users = new HashMap<>();

    public static UserDataBase getInstance() {
        return instance;
    }

    public static UserDataBase getInstance(ArrayList<UserInput> userInputs) {
        if (instance == null) {
            instance = new UserDataBase(userInputs);
            System.out.println(userInputs);
        }
        return instance;
    }

    public UserDataBase(ArrayList<UserInput> userInputs) {
        for (int i = 0; i < userInputs.size(); i++) {
            UserInput userInput = userInputs.get(i);
            Credentials credentials = userInput.getCredentials();
            User user = new User(credentials, 0, 15);

            this.users.put(credentials.getName(), user);
            //TODO add stats
        }
    }

    public User findUser(Credentials credentials) {
        User user = users.get(credentials.getName());
        return user;
    }
    public void insertUser(Credentials credentials) {
        User user = new User(credentials, 0, 15);
        users.put(credentials.getName(), user);
    }

    /** Returneaza true daca exista in baza de data un user cu credentialele date ca parametru */
    public boolean checkUser(Credentials credentials) {
//        boolean exists = false;
        if (!users.containsKey(credentials.getName())) {
            return false;
        }
        User existentUser = users.get(credentials.getName());
        if (!existentUser.getCredentials().getPassword().equals(credentials.getPassword())) {
            return false;
        }
        return true;
    }

    public void registerUser(Credentials credentials) {
        insertUser(credentials);
    }

    @Override
    public String toString() {
        return "users.UserDataBase{" +
                "user=" + users +
                '}';
    }
}
