package input;

import java.util.ArrayList;

public class Contains {
    private ArrayList<String> actors = new ArrayList<>();
    private ArrayList<String> genre = new ArrayList<>();

    public Contains() {
    }
    /** Getter */
    public ArrayList<String> getActors() {
        return actors;
    }
    /** Setter */
    public void setActors(final ArrayList<String> actors) {
        this.actors = actors;
    }
    /** Getter */
    public ArrayList<String> getGenre() {
        return genre;
    }
    /** Setter */
    public void setGenre(final ArrayList<String> genre) {
        this.genre = genre;
    }
}
