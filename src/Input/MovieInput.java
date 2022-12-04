package Input;

import java.util.ArrayList;

public class MovieInput {
//          "name": "John Wick: Chapter 3 - Parabellum",
//          "year": 2019,
//          "duration": 131,
//          "genres": [
//          "Input.Action",
//          "Thriller",
//          "Crime"
//          ],
//          "actors": [
//          "Laurence Fishburne",
//          "Halle Berry",
//          "Keanu Reeves"
//          ],
//          "countriesBanned": [
//          "Russia"
//          ]

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


    public MovieInput(String name, int year, int duration, ArrayList<String> genres, ArrayList<String> actors, ArrayList<String> countriesBanned) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }


    public ArrayList<String> getActors() {
        return actors;
    }

    public void setActors(ArrayList<String> actors) {
        this.actors = actors;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public ArrayList<String> getCountriesBanned() {
        return countriesBanned;
    }

    public void setCountriesBanned(ArrayList<String> countriesBanned) {
        this.countriesBanned = countriesBanned;
    }

    @Override
    public String toString() {
        return "Input.Movie{" +
                "name='" + name + '\'' +
                ", year=" + year +
                ", duration=" + duration +
                ", genres=" + genres +
                ", actors=" + actors +
                ", countries=" + countriesBanned +
                '}';
    }
}
