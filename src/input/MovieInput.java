package input;

import java.util.ArrayList;

public class MovieInput {
    public enum Genre {
        Action,
        Thriller,
        Crime
    }

    private String name;
    private int year;
    private int duration;
    private ArrayList<String> genres;
    private ArrayList<String> actors;
    private ArrayList<String> countriesBanned;


    public MovieInput(final String name, final int year, final int duration,
                      final ArrayList<String> genres, final ArrayList<String> actors,
                      final ArrayList<String> countriesBanned) {
        this.name = name;
        this.year = year;
        this.duration = duration;
        this.genres = genres;
        this.actors = actors;
        this.countriesBanned = countriesBanned;
    }

    public MovieInput() {
        this.actors = new ArrayList<String>();
        this.countriesBanned = new ArrayList<>();
        this.genres = new ArrayList<>();
    }
    /** Getter */
    public String getName() {
        return name;
    }
    /** Setter */
    public void setName(final String name) {
        this.name = name;
    }
    /** Getter */
    public int getYear() {
        return year;
    }
    /** Setter */
    public void setYear(final int year) {
        this.year = year;
    }
    /** Getter */
    public int getDuration() {
        return duration;
    }
    /** Setter */
    public void setDuration(final int duration) {
        this.duration = duration;
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
    public void setGenres(final ArrayList<String> genres) {
        this.genres = genres;
    }
    /** Getter */
    public ArrayList<String> getGenres() {
        return genres;
    }
    /** Getter */
    public ArrayList<String> getCountriesBanned() {
        return countriesBanned;
    }
    /** Setter */
    public void setCountriesBanned(final ArrayList<String> countriesBanned) {
        this.countriesBanned = countriesBanned;
    }
    /** Prints the movies input */
    @Override
    public String toString() {
        return "Input.Movie{"
                +
                "name='" + name + '\''
                +
                ", year=" + year
                +
                ", duration=" + duration
                +
                ", genres=" + genres
                +
                ", actors=" + actors
                +
                ", countries=" + countriesBanned
                +
                '}';
    }
}
