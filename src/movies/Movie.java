package movies;

import serializer.MovieSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

@JsonSerialize(using = MovieSerializer.class)
public class Movie {
    private String name;
    private String year;
    private int duration;
    private ArrayList<String> genres;
    private ArrayList<String> actors;
    private ArrayList<String> countriesBanned;
    private int numLikes;
    private Double rating;
    private int numRatings;

    private Double totalRating = 0.00;

    public Movie(final String name, final String year, final int duration,
                 final ArrayList<String> genres, final ArrayList<String> actors,
                 final ArrayList<String> countriesBanned, final int numLikes,
                 final Double rating, final int numRatings) {
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

    public Movie(final Movie movie) {
        this(movie.name, movie.year, movie.duration, movie.genres, movie.actors,
             movie.countriesBanned, movie.numLikes, movie.rating, movie.numRatings);
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
    public String getYear() {
        return year;
    }
    /** Setter */
    public void setYear(String year) {
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
    public ArrayList<String> getGenres() {
        return genres;
    }
    /** Setter */
    public void setGenres(final ArrayList<String> genres) {
        this.genres = genres;
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
    public ArrayList<String> getCountriesBanned() {
        return countriesBanned;
    }
    /** Setter */
    public void setCountriesBanned(final ArrayList<String> countriesBanned) {
        this.countriesBanned = countriesBanned;
    }
    /** Getter */
    public int getNumLikes() {
        return numLikes;
    }
    /** Setter */
    public void setNumLikes(final int numLikes) {
        this.numLikes = numLikes;
    }
    /** Getter */
//    @JsonIgnore
    public Double getRating() {
//        this.rating = rating + 0.0001;
        BigDecimal bigDecimal = new BigDecimal(rating).setScale(2, RoundingMode.FLOOR);
        return bigDecimal.doubleValue();
    }
    /** Setter */
    public void setRating(final Double rating) {
        BigDecimal bigDecimal = new BigDecimal(rating).setScale(2, RoundingMode.FLOOR);
        this.rating = bigDecimal.doubleValue();
    }

    /** Getter */
    public int getNumRatings() {
        return numRatings;
    }
    /** Setter */
    public void setNumRatings(final int numRatings) {
        this.numRatings = numRatings;
    }
    /** Getter */
    @JsonIgnore
    public Double getTotalRating() {
        return totalRating;
    }
    /** Setter */
    @JsonIgnore
    public void setTotalRating(final Double totalRating) {
        this.totalRating = totalRating;
    }
    /** Prints the movie and it's details */
    @Override
    public String toString() {
        return "Movie{"
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
                ", countriesBanned=" + countriesBanned
                +
                ", numLikes=" + numLikes
                +
                ", rating=" + rating
                +
                ", numRatings=" + numRatings
                +
                '}';
    }
}
