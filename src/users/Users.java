package users;

import Input.Credentials;
import Input.Input;
import Input.UserInput;

import java.util.ArrayList;
import java.util.HashMap;

public class Users {

    private static Users instance = null;

    public static Users getInstance() {
        if (instance == null) {
            instance = new Users(Input.getInstance().getUsers()) {
            };
        }
        return instance;
    }
    HashMap<String, Credentials> users = new HashMap<>();

    public Users(ArrayList<UserInput> userInputs) {
        for (int i = 0; i < userInputs.size(); i++) {
            UserInput userInput = userInputs.get(i);
            Credentials credentials = userInput.getCredentials();
            this.users.put(credentials.getName(), credentials);
        }
    }

    public void insertUser(Credentials credentials) {
        users.put(credentials.getName(), credentials);
    }

    /** Returneaza true daca exista in baza de data un user cu credentialele date ca parametru */
    public boolean checkUser(Credentials credentials) {
//        boolean exists = false;
        if (!users.containsKey(credentials.getName())) {
            return false;
        }
        Credentials existentUser = users.get(credentials.getName());
        if (!existentUser.getPassword().equals(credentials.getPassword())) {
            return false;
        }
        return true;
    }

    public void registerUser(Credentials credentials) {
        insertUser(credentials);
    }

    @Override
    public String toString() {
        return "users.Users{" +
                "user=" + users +
                '}';
    }
}
