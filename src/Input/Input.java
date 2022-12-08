package Input;

import pageStructure.HomeAuth;
import pageStructure.HomeUnauth;
import pageStructure.Login;

import java.util.ArrayList;

public class Input {
    private static Input instance = null;
    private ArrayList<UserInput> users = new ArrayList<UserInput>();
    private ArrayList<MovieInput> movies = new ArrayList<MovieInput>();
    private ArrayList<ActionInput> actions = new ArrayList<ActionInput>();
    public static synchronized Input getInstance() {
        if (instance == null) {
            instance = new Input() {

            };
        }
        return instance;
    }

    public static synchronized Input createInstance(Input inputData) {
        if (instance == null) {
            instance = inputData; {
            }
        }
        return instance;
    }


//    public Input(ArrayList<UserInput> users, ArrayList<MovieInput> movies, ArrayList<ActionInput> actions) {
//        this.users = users;
//        this.movies = movies;
//        this.actions = actions;
//    }

//    public Input() {
//        this.users = new ArrayList<UserInput>();
//        this.movies = new ArrayList<MovieInput>();
//        this.actions = new ArrayList<ActionInput>();
//    }

    public ArrayList<UserInput> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<UserInput> users) {
        this.users = users;
    }

    public ArrayList<MovieInput> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<MovieInput> movies) {
        this.movies = movies;
    }

    public ArrayList<ActionInput> getActions() {
        return actions;
    }

    public void setActions(ArrayList<ActionInput> actions) {
        this.actions = actions;
    }

    @Override
    public String toString() {
        return "Input.Input{" +
                "users=" + users +
                ", movies=" + movies +
                ", actions=" + actions +
                '}';
    }
}
