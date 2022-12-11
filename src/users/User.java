package users;

import Input.Credentials;
import movies.Movie;
import movies.MovieDataBase;

import java.util.ArrayList;
import java.util.Collections;

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

    public User(User user) {
        this.credentials = new Credentials(user.getCredentials());
        this.tokensCount = user.tokensCount;
        this.numFreePremiumMovies = user.getNumFreePremiumMovies();
//        this.watchedMovies.addAll(user.watchedMovies);
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
//        this.purchasedMovies.addAll(user.purchasedMovies);
//        this.likedMovies.addAll(user.likedMovies);
//        this.ratedMovies.addAll(user.ratedMovies);
    }

    /** Returns true if there was an error when buying the movie */
    public boolean purchaseMovie(Movie movie) {
        if (this.numFreePremiumMovies > 0 && this.credentials.getAccountType().equals(Credentials.AccountType.premium)) {
            Movie movieInDataBase = MovieDataBase.getInstance().find(movie.getName());
            this.purchasedMovies.add(movieInDataBase);
            this.numFreePremiumMovies -= 1;
        } else if (this.tokensCount >= 2){
            Movie movieInDataBase = MovieDataBase.getInstance().find(movie.getName());
            this.purchasedMovies.add(movieInDataBase);
            this.tokensCount -= 2;
        } else {
            return true;
        }
        System.out.println("movie purchased");
        return false;
    }

    /** Returns true if there was an error when watching the movie */
    public boolean watchMovie(Movie movie) {
        if (this.purchasedMovies.contains(movie)) {
            Movie movieInDataBase = MovieDataBase.getInstance().find(movie.getName());
            this.watchedMovies.add(movieInDataBase);
            System.out.println("movie watched");
        } else {
            System.out.println("movie not puchased, can t be watched");
            return true;
        }
        return false;
    }

    public boolean likeMovie(Movie movie) {
        if (this.watchedMovies.contains(movie)) {
//            movie.setNumLikes(movie.getNumLikes() + 1);
            Movie movieInDataBase = MovieDataBase.getInstance().find(movie.getName());
            movieInDataBase.setNumLikes(movieInDataBase.getNumLikes() + 1);
            this.likedMovies.add(movieInDataBase);
            System.out.println("movie liked");
        } else {
            System.out.println("movie not watched, can t be liked");
            return true;
        }
        return false;
    }

    public boolean rateMovie(Movie movie, double rate) {
        if (this.watchedMovies.contains(movie)) {
//            movie.setNumLikes(movie.getNumLikes() + 1);
            Movie movieInDataBase = MovieDataBase.getInstance().find(movie.getName());
            movieInDataBase.setRating(movieInDataBase.getRating() + rate);
            this.ratedMovies.add(movieInDataBase);
            System.out.println("movie rated");
        } else {
            System.out.println("movie not watched, can t be rated");
            return true;
        }
        return false;
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
