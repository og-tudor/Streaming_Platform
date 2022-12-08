package movies;

import Input.MovieInput;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NavigableMap;

public class MovieDataBase {
    LinkedList<Movie> movies = new LinkedList<>();

    public MovieDataBase(ArrayList<MovieInput> moviesInput) {
        for (int i = 0; i < moviesInput.size(); i++) {
            MovieInput movieInput = moviesInput.get(i);
            Movie movie = new Movie(movieInput.getName(), movieInput.getYear(), movieInput.getDuration(), movieInput.getGenres(), movieInput.getActors(), movieInput.getCountriesBanned(), 0, 0 ,0 );
            movies.add(movie);
        }
    }

    public MovieDataBase(MovieDataBase movieDataBase) {
        for (int i = 0; i < movieDataBase.getMovies().size(); i++) {
            Movie movie = new Movie(movieDataBase.getMovies().get(i));
            movies.add(movie);
        }
    }

    public void search(String startWith) {
        this.movies.removeIf(x -> !(x.getName().contains(startWith)));
//        for (int i = 0; i < this.movies.size(); i++) {
////            String movieName = this.movies.get(i).getName();
////            if (!movieName.contains(startWith)) {
////
////            }
////        }
    }

    public void insertMovie(Movie movie) {
        movies.add(movie);
    }
    public MovieDataBase() {
    }

    public LinkedList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(LinkedList<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        return "MovieDataBase{" +
                "movies=" + movies +
                '}';
    }
}
