package movies;

import java.util.ArrayList;

public class Movie {
//            "name": "Lord, Save Us from Your Followers",
//            "year": 1998,
//            "duration": 125,
//            "genres": [
//            "Comedy",
//            "Western",
//            "Mystery"
//            ],
//            "actors": [
//            "Camey Ingold",
//            "Grace Volleth",
//            "Jermaine D'Ruel",
//            "Anselma Saxelby"
//            ],
//            "countriesBanned": [
//            "Russia",
//            "Saudi Arabia",
//            "Zimbabwe"
//            ],
//            "numLikes": 0,
//            "rating": 0,
//            "numRatings": 0
//},
    private String name;
    private int year;
    private int duration;
    private ArrayList<String> genres;
    private ArrayList<String> actors;
    private ArrayList<String> countriesBanned;
    private int numLikes;
    private int rating;
    private int numRatings;

    public Movie(String name, int year, int duration, ArrayList<String> genres, ArrayList<String> actors, ArrayList<String> countriesBanned, int numLikes, int rating, int numRatings) {
        this.name = name;
        this.year = year;
        this.duration = duration;
        this.genres = genres;
        this.actors = actors;
        this.countriesBanned = countriesBanned;
        this.numLikes = numLikes;
        this.rating = rating;
        this.numRatings = numRatings;
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

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public ArrayList<String> getActors() {
        return actors;
    }

    public void setActors(ArrayList<String> actors) {
        this.actors = actors;
    }

    public ArrayList<String> getCountriesBanned() {
        return countriesBanned;
    }

    public void setCountriesBanned(ArrayList<String> countriesBanned) {
        this.countriesBanned = countriesBanned;
    }

    public int getNumLikes() {
        return numLikes;
    }

    public void setNumLikes(int numLikes) {
        this.numLikes = numLikes;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getNumRatings() {
        return numRatings;
    }

    public void setNumRatings(int numRatings) {
        this.numRatings = numRatings;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", year=" + year +
                ", duration=" + duration +
                ", genres=" + genres +
                ", actors=" + actors +
                ", countriesBanned=" + countriesBanned +
                ", numLikes=" + numLikes +
                ", rating=" + rating +
                ", numRatings=" + numRatings +
                '}';
    }
}