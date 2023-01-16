package users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import input.Credentials;
import movies.Movie;
import movies.MovieDataBase;

import java.util.ArrayList;

public class User {
    private Credentials credentials;
    private int tokensCount;
    private int numFreePremiumMovies;
    private ArrayList<Movie> purchasedMovies = new ArrayList<>();
    private ArrayList<Movie> watchedMovies = new ArrayList<>();
    private ArrayList<Movie> likedMovies = new ArrayList<>();
    private ArrayList<Movie> ratedMovies = new ArrayList<>();
    private ArrayList<Movie> notifications = new ArrayList<>();
    private ArrayList<String> genres = new ArrayList<>();;
    private final int maxRating = 5;

    /** Input Constructor */
    public User(final Credentials credentials, final int tokensCount,
                final int numFreePremiumMovies) {
        this.credentials = credentials;
        this.tokensCount = tokensCount;
        this.numFreePremiumMovies = numFreePremiumMovies;
    }
    /** Copy Constructor */
    public User(final User user) {
        this.credentials = new Credentials(user.getCredentials());
        this.tokensCount = user.tokensCount;
        this.numFreePremiumMovies = user.getNumFreePremiumMovies();
        for (int i = 0; i < user.watchedMovies.size(); i++) {
            Movie movie = new Movie(user.watchedMovies.get(i));
            this.watchedMovies.add(movie);
        }
        for (int i = 0; i < user.purchasedMovies.size(); i++) {
            Movie movie = new Movie(user.purchasedMovies.get(i));
            this.purchasedMovies.add(movie);
        }
        for (int i = 0; i < user.likedMovies.size(); i++) {
            Movie movie = new Movie(user.likedMovies.get(i));
            this.likedMovies.add(movie);
        }
        for (int i = 0; i < user.ratedMovies.size(); i++) {
            Movie movie = new Movie(user.ratedMovies.get(i));
            this.ratedMovies.add(movie);
        }
        // Copiere genres Subscribed;
        this.genres.addAll(user.genres);
    }

    /** Returns true if there was an error when buying the movie */
    public boolean purchaseMovie(final Movie movie) {
        Credentials.AccountType accountType = this.credentials.getAccountType();
        if (this.numFreePremiumMovies > 0 && accountType.equals(Credentials.AccountType.premium)) {
            Movie movieInDataBase = MovieDataBase.getInstance().find(movie.getName());
            this.purchasedMovies.add(movieInDataBase);
            this.numFreePremiumMovies -= 1;
        } else if (this.tokensCount >= 2) {
            Movie movieInDataBase = MovieDataBase.getInstance().find(movie.getName());
            this.purchasedMovies.add(movieInDataBase);
            this.tokensCount -= 2;
        } else {
            return true;
        }
        return false;
    }

    /** Returns true if there was an error when watching the movie */
    public boolean watchMovie(final Movie movie) {
        if (this.purchasedMovies.contains(movie)) {
            Movie movieInDataBase = MovieDataBase.getInstance().find(movie.getName());
            this.watchedMovies.add(movieInDataBase);
        } else {
            return true;
        }
        return false;
    }
    /** Returns true if there was an error when liking the movie */
    public boolean likeMovie(final Movie movie) {
        if (this.watchedMovies.contains(movie)) {
            Movie movieInDataBase = MovieDataBase.getInstance().find(movie.getName());
            movieInDataBase.setNumLikes(movieInDataBase.getNumLikes() + 1);
            this.likedMovies.add(movieInDataBase);
        } else {
            return true;
        }
        return false;
    }
    /** Returns true if there was an error when rating the movie */
    public boolean rateMovie(final Movie movie, final Double rate) {
        if (rate > maxRating || rate < 0) {
            return true;
        }
        if (this.watchedMovies.contains(movie)) {
            Movie movieInDataBase = MovieDataBase.getInstance().find(movie.getName());
            movieInDataBase.setNumRatings(movieInDataBase.getNumRatings() + 1);
            movieInDataBase.setTotalRating(movieInDataBase.getTotalRating() + rate);
            Double newRate = (movieInDataBase.getTotalRating()) / movieInDataBase.getNumRatings();
            movieInDataBase.setRating(Math.floor(newRate));
            this.ratedMovies.add(movieInDataBase);
        } else {
            return true;
        }
        return false;
    }

    public boolean subscribe(final Movie movie, final String genre) {
        if (movie.getGenres().contains(genre)) {
            if (this.genres.contains(genre)) {
                return true;
            }
            this.genres.add(genre);
            return false;
        }
        return true;
    }
    /** Getter */
    public Credentials getCredentials() {
        return credentials;
    }
    /** Setter */
    public void setCredentials(final Credentials credentials) {
        this.credentials = credentials;
    }
    /** Getter */
    public int getTokensCount() {
        return tokensCount;
    }
    /** Setter */
    public void setTokensCount(final int tokensCount) {
        this.tokensCount = tokensCount;
    }
    /** Getter */
    public int getNumFreePremiumMovies() {
        return numFreePremiumMovies;
    }
    /** Setter */
    public void setNumFreePremiumMovies(final int numFreePremiumMovies) {
        this.numFreePremiumMovies = numFreePremiumMovies;
    }
    /** Getter */
    public ArrayList<Movie> getPurchasedMovies() {
        return purchasedMovies;
    }
    /** Setter */
    public void setPurchasedMovies(final ArrayList<Movie> purchasedMovies) {
        this.purchasedMovies = purchasedMovies;
    }
    /** Getter */
    public ArrayList<Movie> getWatchedMovies() {
        return watchedMovies;
    }
    /** Setter */
    public void setWatchedMovies(final ArrayList<Movie> watchedMovies) {
        this.watchedMovies = watchedMovies;
    }
    /** Getter */
    public ArrayList<Movie> getLikedMovies() {
        return likedMovies;
    }
    /** Setter */
    public void setLikedMovies(final ArrayList<Movie> likedMovies) {
        this.likedMovies = likedMovies;
    }
    /** Getter */
    public ArrayList<Movie> getRatedMovies() {
        return ratedMovies;
    }
    /** Setter */
    public void setRatedMovies(final ArrayList<Movie> ratedMovies) {
        this.ratedMovies = ratedMovies;
    }
    /** Getter */
    public ArrayList<Movie> getNotifications() {
        return notifications;
    }
    /** Setter */
    public void setNotifications(ArrayList<Movie> notifications) {
        this.notifications = notifications;
    }
    /** Getter */
    @JsonIgnore
    public ArrayList<String> getGenres() {
        return genres;
    }
    /** Setter */
    @JsonIgnore
    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    /** Prints the users credentials and data */
    @Override
    public String toString() {
        return "User{" +
                "credentials=" + credentials
                +
                ", tokensCount=" + tokensCount
                +
                ", numFreePremiumMovies=" + numFreePremiumMovies
                +
                ", purchasedMovies=" + purchasedMovies
                +
                ", watchedMovies=" + watchedMovies
                +
                ", likedMovies=" + likedMovies
                +
                ", ratedMovies=" + ratedMovies
                +
                ", notifications=" + notifications
                +
                ", maxRating=" + maxRating
                +
                '}';
    }
}
