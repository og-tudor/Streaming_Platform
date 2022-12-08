package users;

import Input.Credentials;
import movies.Movie;
import movies.MovieDataBase;

import java.util.ArrayList;

public class User {
    Credentials credentials;
    int tokensCount;
    int numFreePremiumMovies;
    //    MovieDataBase purchasedMovies;
    ArrayList<Movie> purchasedMovies = new ArrayList<>();
    ArrayList<Movie> watchedMovies = new ArrayList<>();
    ArrayList<Movie> likedMovies = new ArrayList<>();
    ArrayList<Movie> ratedMovies = new ArrayList<>();

//    MovieDataBase watchedMovies;
//    MovieDataBase likedMovies;
//    MovieDataBase ratedMovies;

    public User(Credentials credentials, int tokensCount, int numFreePremiumMovies) {
        this.credentials = credentials;
        this.tokensCount = tokensCount;
        this.numFreePremiumMovies = numFreePremiumMovies;
//        this.purchasedMovies = new MovieDataBase();
//        this.watchedMovies = new MovieDataBase();
//        this.likedMovies = new MovieDataBase();
//        this.ratedMovies = new MovieDataBase();
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public int getTokensCount() {
        return tokensCount;
    }

    public void setTokensCount(int tokensCount) {
        this.tokensCount = tokensCount;
    }

    public int getNumFreePremiumMovies() {
        return numFreePremiumMovies;
    }

    public void setNumFreePremiumMovies(int numFreePremiumMovies) {
        this.numFreePremiumMovies = numFreePremiumMovies;
    }

    public ArrayList<Movie> getPurchasedMovies() {
        return purchasedMovies;
    }

    public void setPurchasedMovies(ArrayList<Movie> purchasedMovies) {
        this.purchasedMovies = purchasedMovies;
    }

    public ArrayList<Movie> getWatchedMovies() {
        return watchedMovies;
    }

    public void setWatchedMovies(ArrayList<Movie> watchedMovies) {
        this.watchedMovies = watchedMovies;
    }

    public ArrayList<Movie> getLikedMovies() {
        return likedMovies;
    }

    public void setLikedMovies(ArrayList<Movie> likedMovies) {
        this.likedMovies = likedMovies;
    }

    public ArrayList<Movie> getRatedMovies() {
        return ratedMovies;
    }

    public void setRatedMovies(ArrayList<Movie> ratedMovies) {
        this.ratedMovies = ratedMovies;
    }

    @Override
    public String toString() {
        return "User{" +
                "credentials=" + credentials +
                ", tokensCount=" + tokensCount +
                ", numFreePremiumMovies=" + numFreePremiumMovies +
                ", purchasedMovies=" + purchasedMovies +
                ", watchedMovies=" + watchedMovies +
                ", likedMovies=" + likedMovies +
                ", ratedMovies=" + ratedMovies +
                '}';
    }
}
