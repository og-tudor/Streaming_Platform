package input;

import java.util.ArrayList;

public class Input {
    private static Input instance = null;
    private ArrayList<UserInput> users = new ArrayList<UserInput>();
    private ArrayList<MovieInput> movies = new ArrayList<MovieInput>();
    private ArrayList<ActionInput> actions = new ArrayList<ActionInput>();
    /** Returns the input from the JSON file as an object */
    public static synchronized Input getInstance() {
        if (instance == null) {
            instance = new Input() {

            };
        }
        return instance;
    }
//    /** Returns the input from the JSON file as an object */
//    public static synchronized Input createInstance(Input inputData) {
//        if (instance == null) {
//            instance = inputData; {
//            }
//        }
//        return instance;
//    }
    /** Getter */
    public ArrayList<UserInput> getUsers() {
        return users;
    }
    /** Setter */
    public void setUsers(final ArrayList<UserInput> users) {
        this.users = users;
    }
    /** Getter */
    public ArrayList<MovieInput> getMovies() {
        return movies;
    }
    /** Setter */
    public void setMovies(final ArrayList<MovieInput> movies) {
        this.movies = movies;
    }
    /** Getter */
    public ArrayList<ActionInput> getActions() {
        return actions;
    }
    /** Setter */
    public void setActions(final ArrayList<ActionInput> actions) {
        this.actions = actions;
    }
    /** Prints the input */
    @Override
    public String toString() {
        return "Input.Input{"
                +
                "users=" + users
                +
                ", movies=" + movies
                +
                ", actions=" + actions
                +
                '}';
    }
}
