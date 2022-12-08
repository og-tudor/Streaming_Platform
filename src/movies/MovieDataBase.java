package movies;

import Input.MovieInput;

import java.util.ArrayList;

public class MovieDataBase {
    ArrayList<Movie> movies = new ArrayList<>();

    public MovieDataBase(ArrayList<MovieInput> moviesInput) {
        for (int i = 0; i < moviesInput.size(); i++) {
            MovieInput movieInput = moviesInput.get(i);
            Movie movie = new Movie(movieInput.getName(), movieInput.getYear(), movieInput.getDuration(), movieInput.getGenres(), movieInput.getActors(), movieInput.getCountriesBanned(), 0, 0 ,0 );
            movies.add(movie);
        }
    }

    public void insertMovie(Movie movie) {
        movies.add(movie);
    }
    public MovieDataBase() {
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        return "MovieDataBase{" +
                "movies=" + movies +
                '}';
    }
}
