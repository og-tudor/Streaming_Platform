import java.util.ArrayList;

public class Input {
    private ArrayList<UserInput> users;
    private ArrayList<Movie> movies;
    private ArrayList<Action> actions;

    public Input(ArrayList<UserInput> users, ArrayList<Movie> movies, ArrayList<Action> actions) {
        this.users = users;
        this.movies = movies;
        this.actions = actions;
    }

    public Input() {
        this.users = new ArrayList<UserInput>();
        this.movies = new ArrayList<Movie>();
        this.actions = new ArrayList<Action>();
    }

    public ArrayList<UserInput> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<UserInput> users) {
        this.users = users;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public ArrayList<Action> getActions() {
        return actions;
    }

    public void setActions(ArrayList<Action> actions) {
        this.actions = actions;
    }

    @Override
    public String toString() {
        return "Input{" +
                "users=" + users +
                ", movies=" + movies +
                ", actions=" + actions +
                '}';
    }
}
